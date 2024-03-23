import threading
import serial
import re
import time
import queue
from io import BytesIO
import sys
import os
import inspect
import ctypes
import traceback
import zlib
import zipfile

AT_STATE_START, AT_STATE_CONNECT, AT_STATE_DISCONNECT, AT_STATE_CONNECT_TIMEOUT \
    = b'DEVICE START', b'^([0-9A-F]{2}[:]){5}([0-9A-F]{2}) CONNECTED ' \
    , b'^([0-9A-F]{2}[:]){5}([0-9A-F]{2}) DISCONNECTED', \
      b'^([0-9A-F]{2}[:]){5}([0-9A-F]{2}) CONNECTED TIMEOUT'
AT_ROLE_SLAVE, AT_ROLE_HOST, AT_ROLE_SLAVE_AND_HOST, AT_ROLE_BEACON = b'0', b'1', b'2', b'3'
AT_NAME_TYPE_ASCII, AT_NAME_TYPE_HEX = b'0', b'1'


class E104_BT08(threading.Thread):

    def __init__(self):
        threading.Thread.__init__(self)
        self.ser = None
        self.state = None
        self.q = queue.Queue()
        self.f = BytesIO()
        self.timeout = 1
        self.datacallback = []
        self.statecallback = []
        self.sendendcallback = []
        self.isatreturn = False
        self.isatmod = False
        self.issendreturn = False
        self.sendendtime = 0
        self.loopflag = True
        self.rebootflag = False
        self.senddata = b''
        self.sendcrc = b''
        self.sendflag = False
        self.sendlen = 0
        self.sendfilename = b''
        self.getflag = False
        self.getfilename = ''
        self.zipfile = ''
        self.socfile = ''
        self.getlen = 0
        self.getcrc = b''
        self.getcont = 0
        self.getcontflag = False
        self.runthread = None
        self.hostrunflag = False
        self.slaverunflag = False
        self.sendtime = 0
        self.init()

    def __del__(self):
        self.close()

    def close(self):
        self.loopflag = False
        self.join()
        self.ser.close()

    def write(self, data):
        if type(data) == bytes:
            self.ser.write(data)
        else:
            self.ser.write(data.encode())

    def init(self, baudrate=115200, parity=serial.PARITY_NONE, timeout=1):
        self.state = AT_STATE_DISCONNECT
        self.timeout = timeout
        self.ser = serial.Serial("/dev/ttyS1", baudrate=baudrate, parity=parity)  # /dev/ttyS1
        self.start()

        self.enter_at()
        self.set_service(0, b'FFF0', b'FFF1', b'FFF2', b'FFF3')
        self.set_cntinterval(6, 200)
        self.restart()

    def run(self):
        while self.loopflag:
            count = self.ser.inWaiting()
            if count != 0:
                data = self.ser.read(count)
                print(data)
                if self.__is_state(data):
                    continue
                # at指令返回
                elif self.isatreturn:
                    self.q.put(data)
                    self.isatreturn = False
                elif data[0:2] == b'\xff\xff':
                    # 发送文件结束接收返回
                    if self.issendreturn and len(data) == 16 and all(byte in b'0123456789abcdef' for byte in data[-8:]):
                        split = bytes_to_int(data[2:8])
                        crc = data[-8:]
                        if split == self.sendlen and self.sendcrc == crc:
                            for call in self.sendendcallback:
                                call(self, True)
                        else:
                            for call in self.sendendcallback:
                                call(self, False)
                        self.sendflag = False
                        self.issendreturn = False
                    # 发送指令接收方回应
                    elif self.sendflag and len(data) == 16 and all(byte in b'0123456789abcdef' for byte in data[-8:]):
                        split = bytes_to_int(data[2:8])
                        crc = data[-8:]
                        if split != 0:
                            if split < self.sendlen and crc32(self.senddata[:split]) == crc:
                                self.write(b'\xff\xff\x01')
                                threading.Thread(target=self.send_data, args=(split,)).start()
                            elif split == self.sendlen and self.sendcrc == crc:
                                self.write(b'\xff\xff\xff')
                                self.sendflag = False
                                self.issendreturn = False
                                for call in self.sendendcallback:
                                    call(self, True)
                            else:
                                self.write(b'\xff\xff\xf0')
                                threading.Thread(target=self.send_data, args=(0,)).start()
                        else:
                            self.write(b'\xff\xff\xf0')
                            threading.Thread(target=self.send_data, args=(0,)).start()
                    # 运行
                    elif data[2:3] == b'\x10':
                        runfile = data[3:]
                        if not self.slaverunflag:
                            self.runthread = threading.Thread(target=self.run_file, args=(runfile,))
                            self.slaverunflag = True
                            self.runthread.start()
                        else:
                            self.stop_thread(self.runthread)
                            self.runthread = threading.Thread(target=self.run_file, args=(runfile,))
                            self.runthread.start()
                    # 暂停
                    elif data[2:3] == b'\x11':
                        if self.slaverunflag:
                            self.stop_thread(self.runthread)
                            self.slaverunflag = False
                        self.write(b'\xff\xff\x1f')
                        sys.stdout = sys.__stdout__
                    # 收到运行结束
                    elif data[2:3] == b'\x1f':
                        self.hostrunflag = False
                    # 收到传输协议
                    elif not self.getflag and len(data) > 16 and all(byte in b'0123456789abcdef' for byte in data[-8:]):
                        self.getflag = True
                        self.getcrc = data[-8:]
                        self.getlen = bytes_to_int(data[-14:-8])
                        self.getcont = 0
                        self.zipfile = os.path.join(os.getcwd(), 'bletemp',
                                                    os.path.basename((data[2:-14] + b'.zip').decode('utf-8')))
                        self.socfile = os.path.dirname(os.path.abspath(data[2:-14].decode('utf-8')))
                        self.sendtime = time.time()
                        if os.path.exists(self.zipfile):
                            with open(self.zipfile, 'rb') as file:
                                filedata = file.read()
                            cmpcrc = crc32(filedata)
                            if self.getlen == len(filedata) and self.getcrc == cmpcrc:
                                self.write(b'\xff\xff' + data[-14:])
                            elif self.getlen > len(filedata) > 0:
                                redata = b'\xff\xff' + int_to_bytes(len(filedata)) + cmpcrc
                                self.write(redata)
                                self.getcont = len(filedata)
                            else:
                                self.write(b'\xff\xff00000000000000')
                                self.getcont = 0
                        else:
                            if not os.path.exists(os.path.dirname(self.zipfile)):
                                os.makedirs(os.path.dirname(self.zipfile))
                            self.write(b'\xff\xff00000000000000')
                            self.getcont = 0
                    # 根据接收响应发送
                    elif self.getflag and not self.getcontflag:
                        self.getcontflag = True
                        if data[:3] == b'\xff\xff\xf0':
                            self.getcont = 0
                            with open(self.zipfile, "wb") as file:
                                file.truncate(0)
                        elif data == b'\xff\xff\xff':
                            self.getflag = False
                            self.getcontflag = False
                            self.getcont = 0
                        if len(data) > 3:
                            self.getcont += len(data) - 3
                            with open(self.zipfile, "ab") as file:
                                file.write(data[3:])
                            if self.getcont == self.getlen and crc32_file(self.zipfile) == self.getcrc:
                                self.getflag = False
                                self.getcontflag = False
                                self.write(b'\xff\xff' + int_to_bytes(self.getlen) + self.getcrc)
                                self.getcont = 0
                                decompress_file(self.zipfile, self.socfile)
                                os.remove(self.zipfile)
                                print(time.time() - self.sendtime)
                    elif data == b'\xff\xff\xff':
                        self.getflag = False
                        self.getcontflag = False
                        self.getcont = 0
                elif self.getflag and self.getcontflag:
                    self.getcont += len(data)
                    with open(self.zipfile, "ab") as file:
                        file.write(data)
                    if self.getcont == self.getlen and crc32_file(self.zipfile) == self.getcrc:
                        self.getflag = False
                        self.getcontflag = False
                        self.write(b'\xff\xff' + int_to_bytes(self.getlen) + self.getcrc)
                        self.getcont = 0
                        decompress_file(self.zipfile, self.socfile)
                        os.remove(self.zipfile)
                        print(time.time() - self.sendtime)
                elif self.isatmod and data[:10] == b'+RECEIVED:':
                    temp = b''
                    for dataspl in data.split(b'\r\n')[2:-1]:
                        temp += dataspl + b'\r\n'
                    atdata = temp[:-4]
                    self.f.write(atdata)
                    if self.datacallback:
                        for call in self.datacallback:
                            call(self, atdata)
                else:
                    self.f.write(data)
                    if self.datacallback:
                        for call in self.datacallback:
                            call(self, data)
            time.sleep(0.05)
            if self.issendreturn:
                if time.time() - self.sendendtime >= 2 * self.timeout:
                    self.write(b'\xff\xff\xff')
                    self.sendflag = False
                    self.issendreturn = False
                    for call in self.sendendcallback:
                        call(self, False)

    def __is_state(self,data):
        if re.search(AT_STATE_CONNECT_TIMEOUT, data):
            if self.statecallback:
                for call in self.statecallback:
                    call(self, AT_STATE_CONNECT_TIMEOUT)
        elif re.search(AT_STATE_CONNECT, data):
            self.state = AT_STATE_CONNECT
            if self.statecallback:
                for call in self.statecallback:
                    call(self, AT_STATE_CONNECT)
        elif re.search(AT_STATE_DISCONNECT, data):
            self.getflag = False
            self.getcontflag = False
            self.state = AT_STATE_DISCONNECT
            if self.statecallback:
                for call in self.statecallback:
                    call(self, AT_STATE_DISCONNECT)
        elif AT_STATE_START in data and not self.rebootflag:
            self.rebootflag = True
            self.state = AT_STATE_DISCONNECT
            if self.statecallback:
                for call in self.statecallback:
                    call(self, AT_STATE_START)
        else:
            return False
        return True

    def is_connected(self):
        return self.get_state() == AT_STATE_CONNECT

    def send_finish(self):
        return not self.sendflag

    def read(self):
        self.f.seek(0)
        data = self.f.read()
        self.f.truncate(0)
        self.f.seek(0)
        return data

    def get_state(self):
        return self.state

    def __send_data(self, data):
        self.ser.flushInput()
        start_time = time.time()
        while self.isatreturn:
            time.sleep(0.03)
            if time.time() - start_time >= self.timeout:
                raise TimeoutError("等待上次AT指令回应超时")
        self.isatreturn = True
        self.ser.write(data)
        start_time = time.time()
        while True:
            if not self.q.empty():
                data = self.q.get()
                if b'ERROR\r\n' in data:
                    raise Exception("ATERR")
                return data
            time.sleep(0.03)
            if time.time() - start_time >= self.timeout:
                raise TimeoutError("等待AT指令回应超时")

    def __send_atdata(self, data):
        try:
            data = self.__send_data(data)
        except Exception as e:
            if str(e) == "等待AT指令回应超时":
                if data == b'+++':
                    self.isatreturn = False
                    return b'OK\r\n'
                try:
                    self.isatreturn = False
                    self.enter_at()
                    data = self.__send_data(data)
                    self.exit_at()
                except Exception as e:
                    if str(e) == "ATERR":
                        data = self.__send_data(data)
                    else:
                        raise
            elif str(e) == "ATERR":
                data = self.__send_data(data)
            else:
                raise
        return data if data else b''

    def add_data_callback(self, function):
        self.datacallback.append(function)

    def add_state_callback(self, function):
        self.statecallback.append(function)

    def add_sendend_callback(self, function):
        self.sendendcallback.append(function)

    def del_data_callback(self, function=None):
        self.datacallback.remove(function) if function else self.datacallback.pop()

    def del_state_callback(self, function=None):
        self.statecallback.remove(function) if function else self.statecallback.pop()

    def del_sendend_callback(self, function=None):
        self.sendendcallback.remove(function) if function else self.sendendcallback.pop()

    def set_uart(self, baudrate=115200, parity=serial.PARITY_NONE):
        self.ser.baudrate = baudrate
        self.ser.parity = parity

    def enter_at(self):
        self.isatmod = True
        return b'OK\r\n' in self.__send_atdata(b'+++')

    def exit_at(self):
        return b'OK\r\n' in self.__send_atdata(b'AT+EXIT\r\n')

    def restart(self):
        self.__send_atdata(b'AT+RESTART\r\n')
        start_time = time.time()
        while not self.rebootflag:
            time.sleep(0.1)
            if time.time() - start_time >= 3:
                raise TimeoutError("等待蓝牙重启超时")
        self.rebootflag = False
        return True

    def reset(self):
        self.__send_atdata(b'AT+RESET\r\n')
        self.set_uart()
        start_time = time.time()
        while not self.rebootflag:
            time.sleep(0.1)
            if time.time() - start_time >= 3:
                raise TimeoutError("等待蓝牙重启超时")
        self.rebootflag = False
        return True

    def set_service(self, bit, serviceUUID, rxUUID, txUUID, atUUID, baseUUID=None):
        if baseUUID:
            return b'OK\r\n' in self.__send_atdata(b'AT+SERVICE=' + str(bit).encode() + b',' + serviceUUID + b','
                                                   + rxUUID + b',' + txUUID + b',' + atUUID + b',' + baseUUID + b'\r\n')
        else:
            return b'OK\r\n' in self.__send_atdata(b'AT+SERVICE=' + str(bit).encode() + b',' + serviceUUID + b','
                                                   + rxUUID + b',' + txUUID + b',' + atUUID + b'\r\n')

    def get_baudrate(self):
        return re.search(b'UART=(\d+)', self.__send_atdata(b'AT+UART?\r\n')).group(1)

    def set_baudrate(self, baudrate):
        return b'OK\r\n' in self.__send_atdata(b'AT+UART=' + str(baudrate).encode() + b'\r\n')

    def get_role(self):
        return re.search(b'ROLE=(\d)', self.__send_atdata(b'AT+ROLE?\r\n')).group(1)

    def set_role(self, role):
        return b'OK\r\n' in self.__send_atdata(b'AT+ROLE=' + role + b'\r\n')

    def get_name(self):
        return re.search(b'NAME=(\S+)', self.__send_atdata(b'AT+NAME?\r\n')).group(1).split(b',')

    def set_name(self, name, nametype=AT_NAME_TYPE_ASCII):
        return b'OK\r\n' in self.__send_atdata(b'AT+NAME=' + nametype + name + b'\r\n')

    def connect_mac(self, mac):
        return b'OK\r\n' in self.__send_atdata(b'AT+CONNECT=,' + mac + b'\r\n')

    def connect_filter(self, hex):
        return b'OK\r\n' in self.__send_atdata(b'AT+CONNECT=,f1:f2:f3:f4:' + hex[:2] + b':' + hex[2:] + b'\r\n')

    def disconnect(self):
        return b'OK\r\n' in self.__send_atdata(b'AT+DISCONNECT\r\n')

    def get_mac(self):
        return re.search(b'MAC:(\w+)', self.__send_atdata(b'AT+MAC?\r\n')).group(1)

    def set_mac(self, mac=b'FF:FF:FF:FF:FF:FF'):

        return b'OK\r\n' in self.__send_atdata(b'AT+MAC=' + mac + b'\r\n')

    def set_filter(self, hex):
        self.set_mac(b'f1:f2:f3:f4:' + hex[:2] + b':' + hex[2:])
        self.set_service(0, hex, b'FFF1', b'FFF2', b'FFF3')

    def set_cntinterval(self, cntInterval=16, timeout=200):
        set_data = b'AT+CNT_INTERVAL=' + str(cntInterval).encode() + b',' + str(timeout).encode() + b'\r\n'
        return b'OK\r\n' in self.__send_atdata(set_data)

    def send_file(self, file_path, save_file_name=b''):
        if self.sendflag:
            return False
        if not os.path.exists(file_path.decode('utf-8')):
            return False
        if save_file_name == b'':
            if len(file_path) < 33:
                save_file_name = file_path
            else:
                save_file_name = b'bt_tmp.log'
        elif len(save_file_name) > 32:
            save_file_name = b'bt_tmp.log'
        self.sendfilename = save_file_name
        buffer = BytesIO()
        with zipfile.ZipFile(buffer, 'w', compression=zipfile.ZIP_DEFLATED, compresslevel=9) as zipf:
            zipf.write(file_path)
        self.senddata = buffer.getvalue()
        buffer.close()
        self.sendcrc = crc32(self.senddata)
        self.sendlen = len(self.senddata)
        split = int_to_bytes(self.sendlen)
        send_data = b'\xff\xff' + save_file_name + split + self.sendcrc
        self.write(send_data)
        self.sendflag = True
        return True

    def send_data(self, split):
        time.sleep(1)
        while self.sendlen != split:
            if self.get_state() == AT_STATE_DISCONNECT:
                self.sendflag = False
                self.issendreturn = False
                for call in self.sendendcallback:
                    call(self, False)
                return None
            if split + 40 >= self.sendlen:
                self.write(self.senddata[split:self.sendlen])
                break
            else:
                self.write(self.senddata[split:split + 40])
            time.sleep(0.005)
            split += 1
        self.issendreturn = True
        self.sendendtime = time.time()

    def run_code(self, code):
        sys.stdout = self
        try:
            exec(code, globals(), globals())
        except Exception as e:
            exc_type, exc_value, exc_traceback = sys.exc_info()
            trace = traceback.extract_tb(exc_traceback)
            _, lineno, _, _ = trace[-1]
            traceback_details = {
                'lineno': lineno,
                'type': exc_type.__name__,
                'message': str(exc_value),
            }
            self.write(str(traceback_details))
            time.sleep(1)
        # self.restore()
        self.write(b'\xff\xff\x1f')
        self.slaverunflag = False
        sys.stdout = sys.__stdout__

    def run_file(self, file_path):
        os.path.exists(file_path.decode('utf-8'))
        with open(file_path, 'r') as file:
            file_content = file.read()
        file.close()
        self.run_code(file_content)

    def slave_run(self, file_path=b''):
        if self.sendflag:
            return False
        if self.hostrunflag:
            self.slave_stop()
            time.sleep(0.07)
        if file_path == b'':
            if self.sendfilename == b'':
                return False
            else:
                file_path = self.sendfilename
        self.hostrunflag = True
        self.write(b'\xff\xff\x10' + file_path)
        return True

    def slave_stop(self):
        if self.hostrunflag:
            self.hostrunflag = False
            self.write(b'\xff\xff\x11')
            return True
        return False

    def _async_raise(self, tid, exctype):
        """raises the exception, performs cleanup if needed"""
        tid = ctypes.c_long(tid)
        if not inspect.isclass(exctype):
            exctype = type(exctype)
        res = ctypes.pythonapi.PyThreadState_SetAsyncExc(tid, ctypes.py_object(exctype))
        if res == 0:
            raise ValueError("invalid thread id")
        elif res != 1:
            # """if it returns a number greater than one, you're in trouble,
            # and you should call it again with exc=NULL to revert the effect"""
            ctypes.pythonapi.PyThreadState_SetAsyncExc(tid, None)
            raise SystemError("PyThreadState_SetAsyncExc failed")

    def stop_thread(self, thread):
        self._async_raise(thread.ident, SystemExit)


e104_bt08 = E104_BT08()


def int_to_bytes(num):
    array = bytearray(6)
    for i in range(5, -1, -1):
        array[i] = num % 256
        num >>= 8
    return array


def bytes_to_int(array):
    num = 0
    for i in range(len(array)):
        num += array[i] << ((len(array) - 1 - i) * 8)
    return num


def crc32(datas):
    return hex(zlib.crc32(datas))[2:].zfill(8).encode()


def crc32_file(file_path):
    with open(file_path, 'rb') as file:
        file_data = file.read()
    return crc32(file_data)


def compress_file(filename, output_filename):
    with zipfile.ZipFile(output_filename, 'w', zipfile.ZIP_DEFLATED) as zipf:
        zipf.write(filename, os.path.basename(filename))


def decompress_file(zip_filename, output_dir):
    with zipfile.ZipFile(zip_filename, 'r') as zipf:
        zipf.extractall(output_dir)


if __name__ == '__main__':
    e104_bt08.set_role(AT_ROLE_SLAVE)
    e104_bt08.restart()
    from maix import camera, display, image  # 引入python模块包

    image.load_freetype("/root/preset/fonts/simhei.ttf")
    hello_img = image.new(size=(320, 240), color=(0, 0, 0),
                          mode="RGB")  # 创建一张黑色背景图
    hello_img.draw_string(30, 115, "蓝牙程序已启动！", scale=1.0, color=(255, 255, 255),
                          thickness=1)  # 在黑色背景图上写下hello world
    display.show(hello_img)  # 把这张图显示出来

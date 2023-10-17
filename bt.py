import threading
import serial
import re
import time
import queue
import json
from io import BytesIO
import sys
import os

AT_STATE_CONNECT, AT_STATE_DISCONNECT, AT_STATE_WAKEUP, AT_STATE_SLEEP = \
    b'\r\n STA:connect\r\n', b'\r\n disconnect\r\n', b'\r\n STA:wakeup\r\n', b'\r\n STA:sleep\r\n'
AT_BAUD = {b'0': 1200, b'1': 2400, b'2': 4800, b'3': 9600, b'4': 14400, b'5': 19200, b'6': 28800,
           b'7': 38400, b'8': 57600, b'9': 76800, b'10': 115200, b'11': 230400, b'12': 500000, b'13': 1000000}
AT_PARITY_NONE, AT_PARITY_ODD, AT_PARITY_EVEN = b'0', b'1', b'2'
PARITY_MAPPING = {
    AT_PARITY_ODD: serial.PARITY_ODD,
    AT_PARITY_EVEN: serial.PARITY_EVEN,
    AT_PARITY_NONE: serial.PARITY_NONE
}
AT_ROLE_HOST, AT_ROLE_SLAVE = b'0', b'1'
AT_BROADCAST_OFF, AT_BROADCAST_NORMAL, AT_BROADCAST_IBEACON = b'0', b'1', b'2'
AT_PWR_8DBM, AT_PWR_0DBM, AT_PWR_NEG_5DBM, AT_PWR_NEG_20DBM = b'0', b'1', b'2', b'3'
AT_BOND_ABLE, AT_BOND_DISABLE = b'0', b'1'
AT_ATE_OFF, AT_ATE_OPEN = b'0', b'1'
AT_ERR = {b'1': '长度不匹配', b'2': '超过量程', b'3': '未找到参数', b'4': '不支持该指令', b'5': '保存flash失败', b'6': '参数非法'}


class E104_BT08(threading.Thread):

    def __init__(self, baudrate=115200, parity=AT_PARITY_NONE, timeout=1, datacallback=[], statecallback=[]):
        threading.Thread.__init__(self)
        self.q = queue.Queue()
        self.f = BytesIO()
        self.timeout = timeout
        self.datacallback = datacallback
        self.statecallback = statecallback
        self.isatreturn = False
        self.loopflag = True
        self.rebootflag = False
        self.senddata = b''
        self.sendcrc = b''
        self.sendflag = False
        self.sendlen = 0
        self.state = AT_STATE_DISCONNECT
        selected_parity = PARITY_MAPPING.get(parity, serial.PARITY_NONE)
        self.ser = serial.Serial("/dev/ttyS1", baudrate=baudrate, parity=selected_parity)
        self.start()
        try:
            self.__init()
        except Exception:
            self.close()
            raise

    def close(self):
        self.loopflag = False
        self.join()
        self.ser.close()

    def write(self, data):
        self.ser.flushInput()
        self.ser.write(data)

    def __init(self):
        while True:
            try:
                self.__send_data(b'+++')
                self.__send_atdata(b'AT+RESET')
                break
            except Exception as e:
                if str(e) == AT_ERR[b'4']:
                    self.__send_atdata(b'AT+RESET')
                    break
                else:
                    raise
        start_time = time.time()
        while not self.rebootflag:
            time.sleep(0.1)
            if time.time() - start_time >= self.timeout:
                raise TimeoutError("等待蓝牙重启超时")

    def run(self):
        while self.loopflag:
            count = self.ser.inWaiting()
            if count != 0:
                isstatedata = False
                data = self.ser.read(count)
                # print(self.isatreturn, data)
                for state in {
                    AT_STATE_CONNECT,
                    AT_STATE_DISCONNECT,
                    b'START\r\n',
                    AT_STATE_WAKEUP,
                    AT_STATE_SLEEP
                }:
                    if state in data:
                        isstatedata = True
                        self.state = state
                        if state == b'START\r\n':
                            self.rebootflag = True
                            self.state = AT_STATE_DISCONNECT
                        if self.statecallback:
                            for call in self.statecallback:
                                call(self, state)
                        break
                if isstatedata:
                    continue
                elif self.isatreturn:
                    self.q.put(data)
                    self.isatreturn = False
                elif self.sendflag and data[0:2] == b'\xff\xff' and len(data) == 20 \
                        and all(chr(byte).isalnum() and chr(byte) in '0123456789ABCDEF' for byte in data[12:]):
                    split = data[12:-2]
                    # 如果分卷大小大于或对应分卷crc不同重来
                    if split != b'000000':
                        crc = data[-2:]
                        if split > self.sendlen or crc8(self.senddata[split - 1]) != crc:
                            self.write(b'\xff\xff000000')
                            threading.Thread(target=self.send_data, args=(0,))
                        elif split == self.sendlen and self.sendcrc != crc:
                            self.write(b'\xff\xff000000')
                            threading.Thread(target=self.send_data, args=(0,))
                        elif split == self.sendlen and self.sendcrc == crc:
                            self.write(b'\xff\xffsendend')  # 传输结束
                        else:
                            self.write(b'\xff\xff' + split + crc)
                            threading.Thread(target=self.send_data, args=(int(split, 16),))
                else:
                    self.f.write(data)
                    if self.datacallback:
                        for call in self.datacallback:
                            call(self, data)
            time.sleep(0.05)

    def is_connected(self):
        return self.get_state() == AT_STATE_CONNECT

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
                err = re.search(b'\+ERROR (\d)', data)
                if err is not None:
                    if err.group(1) in AT_ERR:
                        raise Exception(AT_ERR[err.group(1)])
                return data
            time.sleep(0.03)
            if time.time() - start_time >= self.timeout:
                raise TimeoutError("等待AT指令回应超时")

    def __send_atdata(self, data):
        try:
            data = self.__send_data(data)
        except Exception as e:
            if str(e) == "等待AT指令回应超时":
                try:
                    self.enter_at()
                    self.__send_data(data)
                    self.exit_at()
                except Exception as e:
                    if str(e) == AT_ERR[b'4']:
                        data = self.__send_data(data)
                    else:
                        raise
            elif str(e) == AT_ERR[b'4']:
                data = self.__send_data(data)
            else:
                raise
        return data if data else b''

    def backup_config(self):
        data = {"BAUD": self.get_baudrate(), "PARI": self.get_parity(), "ROLE": self.get_role(), "ADV": self.get_adv(),
                "ADVDAT": self.get_advdata(), "ADVINTV": self.get_advintv(), "NAME": self.get_name(),
                "CONPARAMS": self.get_conparams(), "MAC": self.get_mac(), "BONDMAC": self.get_bondmac(),
                "MTU": self.get_mtu(), "SCANWND": self.get_scanwindow(), "UUIDSVR": self.get_uuidserver(),
                "UPAUTH": self.get_upauth(), "ATE": self.get_atestate(), "PWR": self.get_power(),
                "BOND": self.get_bondenable()}
        return json.dumps(data)

    def restore_config(self, backup):
        data = json.loads(backup)
        self.set_baudrate(data["BAUD"])
        self.set_parity(data["PARI"])
        self.set_role(data["ROLE"])
        self.set_adv(data["ADV"])
        self.set_advdata(data["ADVDAT"])
        self.set_advintv(data["ADVINTV"])
        self.set_name(data["NAME"])
        self.set_conparams(data["CONPARAMS"])
        self.set_mac(data["MAC"])
        self.set_bondmac(data["BONDMAC"])
        self.set_mtu(data["MTU"])
        self.set_scanwindow(data["SCANWND"])
        self.set_uuidserver(data["UUIDSVR"])
        self.set_upauth(data["UPAUTH"])
        self.set_atestate(data["ATE"])
        self.set_power(data["PWR"])
        self.set_bondenable(data["BOND"])

    def add_data_callback(self, function):
        self.datacallback.append(function)

    def add_state_callback(self, function):
        self.statecallback.append(function)

    def del_data_callback(self, function=None):
        self.datacallback.remove(function) if function else self.datacallback.pop()

    def del_state_callback(self, function=None):
        self.statecallback.remove(function) if function else self.statecallback.pop()

    def set_uart(self, baudrate=115200, parity=AT_PARITY_NONE):
        self.ser.baudrate = baudrate
        self.ser.parity = PARITY_MAPPING.get(parity, serial.PARITY_NONE)

    def at_test(self):
        return b'+OK\r\n' in self.__send_atdata(b'AT')

    def enter_at(self):
        return b'+enter_at_mode\r\n' in self.__send_atdata(b'+++')

    def exit_at(self):
        return b'+OK\r\n' in self.__send_atdata(b'AT+EXIT')

    def reset(self):
        return b'+OK\r\n' in self.__send_atdata(b'AT+RESET')

    def restore(self):
        return b'+OK\r\n' in self.__send_atdata(b'AT+RESTORE')

    def get_baudrate(self):
        baudnum = re.search(b'BAUD:(\d+)', self.__send_atdata(b'AT+BAUD=?'))
        return AT_BAUD[baudnum.group(1)]

    def set_baudrate(self, baudrate):
        for key, val in AT_BAUD.items():
            if val == baudrate:
                self.ser.baudrate = baudrate
                return b'+OK\r\n' in self.__send_atdata(b'AT+BAUD=' + key)
        return False

    def get_parity(self):
        return re.search(b'PARI:(\d)', self.__send_atdata(b'AT+PARI=?')).group(1)

    def set_parity(self, parity):
        self.ser.parity = PARITY_MAPPING.get(parity, serial.PARITY_NONE)
        return b'+OK\r\n' in self.__send_atdata(b'AT+PARI=' + parity)

    def get_role(self):
        return re.search(b'ROLE:(\d)', self.__send_atdata(b'AT+ROLE=?')).group(1)

    def set_role(self, role):
        return b'+OK\r\n' in self.__send_atdata(b'AT+ROLE=' + role)

    def get_adv(self):
        return re.search(b'ADV:(\d)', self.__send_atdata(b'AT+ADV=?')).group(1)

    def set_adv(self, adv):
        return b'+OK\r\n' in self.__send_atdata(b'AT+ADV=' + adv)

    def get_advdata(self):
        return re.search(b'ADVDAT:(.*?)\r\n', self.__send_atdata(b'AT+ADVDAT=?')).group(1)

    def set_advdata(self, data, vendor_data=b'\x01\x02'):
        length = len(b'\xff' + vendor_data + data)
        if length >= 29 or len(vendor_data) > 2:
            return False
        set_data = b'AT+ADVDAT=' + bytes.fromhex(format(length, '02x')) + b'\xff' + vendor_data + data
        return b'+OK\r\n' in self.__send_atdata(set_data)

    def set_ibeacon_advdata(self, uuid, major, minor, power):
        if len(uuid) != 16 or len(major) != 2 or len(minor) != 2 or len(power) != 1:
            return False
        set_data = b'AT+ADVDAT=\x1a\xff\x4c\x00\x02\x15' + uuid + major + minor + power
        return b'+OK\r\n' in self.__send_atdata(set_data)

    def get_advintv(self):
        return int(re.search(b'ADVINTV:(\d+)', self.__send_atdata(b'AT+ADVINTV=?')).group(1))

    def set_advintv(self, advintv=32):
        return b'+OK\r\n' in self.__send_atdata(b'AT+ADVINTV=' + str(advintv).encode())

    def get_name(self):
        return re.search(b'NAME:(\S+)', self.__send_atdata(b'AT+NAME=?')).group(1)

    def set_name(self, name):
        if len(name) > 25:
            return False
        return b'+OK\r\n' in self.__send_atdata(b'AT+NAME=' + name)

    def get_conparams(self):
        return [int(byte_str) for byte_str in re.findall(b'\d+', self.__send_atdata(b'AT+CONPARAMS=?'))]

    def set_conparams(self, connection_delay=40, slave_delay=0, parameter_exception=20):
        if connection_delay < 6 or connection_delay > 3200 or slave_delay > 499 \
                or parameter_exception < 10 or parameter_exception > 3200 \
                or parameter_exception * 4 <= (1 + slave_delay) * connection_delay:
            return False
        set_data = b'AT+CONPARAMS=' + str(connection_delay).encode() + b',' \
                   + str(slave_delay).encode() + b',' + str(parameter_exception).encode()
        return b'+OK\r\n' in self.__send_atdata(set_data)

    def disconnect(self, con=0):
        return b'+OK\r\n' in self.__send_atdata(b'AT+DISCON=' + str(con).encode())

    def get_mac(self):
        return re.search(b'MAC:(\w+)', self.__send_atdata(b'AT+MAC=?')).group(1)

    def set_mac(self, mac):
        if len(mac) != 12:
            return False
        return b'+OK\r\n' in self.__send_atdata(b'AT+MAC=' + mac)

    def get_bondmac(self):
        return re.findall(b'MAC:(\w+)', self.__send_atdata(b'AT+BONDMAC=?'))

    def set_bondmac(self, mac):
        if len(mac) == 12:
            return b'+OK\r\n' in self.__send_atdata(b'AT+BONDMAC=' + mac)
        elif len(mac) == 2:
            if len(mac[0] == 12):
                self.__send_atdata(b'AT+BONDMAC=' + mac[0])
                self.__send_atdata(b'AT+BONDMAC=' + mac[1])
                return True
            else:
                return False
        else:
            return False

    def get_mtu(self):
        return int(re.search(b'MTU:(\d+)', self.__send_atdata(b'AT+MTU=?')).group(1))

    def set_mtu(self, mtu):
        if mtu < 23 or mtu > 247:
            return False
        return b'+OK\r\n' in self.__send_atdata(b'AT+MTU=' + str(mtu).encode())

    def get_scanwindow(self):
        return int(re.search(b'SCANWND:(\d+)', self.__send_atdata(b'AT+SCANWND=?')).group(1))

    def set_scanwindow(self, scanwnd=1000):
        if scanwnd < 40 or scanwnd > 9999:
            return False
        return b'+OK\r\n' in self.__send_atdata(b'AT+SCANWND=' + str(scanwnd).encode())

    def get_uuidserver(self):
        return int(re.search(b'UUIDSVR:(\d+)', self.__send_atdata(b'AT+UUIDSVR=?')).group(1))

    def set_uuidserver(self, uuidserver):
        if uuidserver > 65535:
            return False
        return b'+OK\r\n' in self.__send_atdata(b'AT+UUIDSVR=' + str(uuidserver).encode())

    def set_auth(self, password):
        if len(password) != 6:
            return False
        self.__send_atdata(b'AT+AUTH=' + password)
        return True

    def get_upauth(self):
        return re.search(b'AUTH:(\w+)', self.__send_atdata(b'AT+UPAUTH=?')).group(1)

    def set_upauth(self, password):
        if len(password) != 6:
            return False
        return b'+OK\r\n' in self.__send_atdata(b'AT+UPAUTH=' + password)

    def sleep(self, para):
        return b'+OK\r\n' in self.__send_atdata(b'AT+SLEEP=' + str(para).encode())

    def get_atestate(self):
        return AT_ATE_OPEN if b'AT' in self.__send_atdata(b'AT') else AT_ATE_OFF

    def set_atestate(self, para):
        return b'+OK\r\n' in self.__send_atdata(b'ATE' + para)

    def get_power(self):
        return re.search(b'PWR:(\d)', self.__send_atdata(b'AT+PWR=?')).group(1)

    def set_power(self, pwr):
        return b'+OK\r\n' in self.__send_atdata(b'AT+PWR=' + pwr)

    def get_version(self):
        return str(self.__send_atdata(b'AT+VER').decode('utf-8')).strip().split('\r\n')[-1]

    def get_bondenable(self):
        return re.search(b'BOND:(\d)', self.__send_atdata(b'AT+BOND=?')).group(1)

    def set_bondenable(self, para):
        return b'+OK\r\n' in self.__send_atdata(b'AT+BOND=' + para)

    def sent_file(self, file_path, save_file_name):
        if not os.path.exists(file_path):
            return False
        if len(save_file_name) > 10:
            return False
        self.senddata = []
        self.sendcrc = crc8_file(file_path)
        with open(file_path, 'rb') as file:
            while True:
                data = file.read(18)
                if not data:
                    break
                self.senddata.append(data)
        self.sendlen = len(self.senddata)
        split = hex(self.sendlen)[2:].zfill(6).encode()
        send_data = b'\xff\xff' + save_file_name + split + self.sendcrc
        self.write(send_data)
        self.sendflag = True
        return True

    def send_data(self, split):
        while self.sendlen != split:
            split += 1
            self.write(b'\xff\xff' + self.senddata[split])
            time.sleep(0.07)
        self.sendflag = False


e104_bt08 = E104_BT08()


def cal_crc(data):
    crc = data
    poly = 0x07
    for i in range(8, 0, -1):
        if ((crc & 0x80) >> 7) == 1:
            crc = (crc << 1) ^ poly
        else:
            crc = (crc << 1)
    return crc & 0xFF


def crc8(datas):
    list = [int(byte) for byte in datas]
    length = len(list)
    crc = 0x00
    for i in range(length):
        if i == 0:
            crc = cal_crc(list[0])
        else:
            crc = (crc ^ list[i]) & 0xFF
            crc = cal_crc(crc)
    return crc & 0xFF


def crc8_file(file_path):
    with open(file_path, 'rb') as file:
        while True:
            data = file.read(8192)  # 一次读取一部分数据
            if not data:
                break
            print(data)
            prev_crc = crc8(data)
    return hex(prev_crc & 0xFF)[2:].zfill(2).encode()


def w_file(file_path, data):
    with open(file_path, "a") as file:
        file.write(data)


def run_code(code):
    sys.stdout = e104_bt08.ser

    try:
        exec(code)

    except Exception as e:
        exc_type, exc_value, exc_traceback = sys.exc_info()
        traceback_details = {
            'filename': exc_traceback.tb_frame.f_code.co_filename,
            'lineno': exc_traceback.tb_lineno,
            'name': exc_traceback.tb_frame.f_code.co_name,
            'type': exc_type.__name__,
            'message': str(e)
        }
        print("Exception in file '{filename}' at line {lineno}: {type} - {message}".format(**traceback_details))

    # 恢复标准输出并获取捕获的输出
    sys.stdout = sys.__stdout__


def run_file(file_path):
    file_path = file_path
    with open(file_path, 'r') as file:
        file_content = file.read()
    file.close()
    run_code(file_content)

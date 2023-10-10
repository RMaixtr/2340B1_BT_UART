import threading
import serial
import re
import time
import queue
from io import BytesIO

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
        if len(mac) != 12:
            return False
        return b'+OK\r\n' in self.__send_atdata(b'AT+BONDMAC=' + mac)

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


e104_bt08 = E104_BT08()

import threading
import serial
import re
import time
import queue

AT_STATE_CONNECT, AT_STATE_DISCONNECT, AT_STATE_WAKEUP, AT_STATE_SLEEP = \
    b'\r\n STA:connect\r\n', b'\r\n disconnect\r\n', b'\r\n STA:wakeup\r\n', b'\r\n STA:sleep\r\n'
AT_BAUD = {b'0': 1200, b'1': 2400, b'2': 4800, b'3': 9600, b'4': 14400, b'5': 19200, b'6': 28800,
           b'7': 38400, b'8': 57600, b'9': 76800, b'10': 115200, b'11': 230400, b'12': 500000, b'13': 1000000}
AT_PARITY_NONE, AT_PARITY_ODD, AT_PARITY_EVEN = b'0', b'1', b'2'
AT_ROLE_HOST, AT_ROLE_SLAVE = b'0', b'1'
AT_BROADCAST_OFF, AT_BROADCAST_NORMAL, AT_BROADCAST_IBEACON = b'0', b'1', b'2'
AT_PWR_8DBM, AT_PWR_0DBM, AT_PWR_NEG_5DBM, AT_PWR_NEG_20DBM = b'0', b'1', b'2', b'3'
AT_BOND_ABLE, AT_BOND_DISABLE = b'0', b'1'


class e104_bt08(threading.Thread):

    def __init__(self, baudrate=115200, parity=AT_PARITY_NONE, timeout=1, datacallback=None, statecallback=None):
        threading.Thread.__init__(self)
        self.q = queue.Queue()
        self.timeout = timeout
        self.startflag = True
        self.datacallback = datacallback
        self.statecallback = statecallback
        self.isatreturn = False
        self.atdata = None
        self.rebootstart = False
        self.isatmode = True
        self.state = AT_STATE_DISCONNECT
        if parity == AT_PARITY_ODD:
            self.ser = serial.Serial("/dev/ttyS1", baudrate=baudrate, parity=serial.PARITY_ODD)
        elif parity == AT_PARITY_EVEN:
            self.ser = serial.Serial("/dev/ttyS1", baudrate=baudrate, parity=serial.PARITY_EVEN)
        else:
            self.ser = serial.Serial("/dev/ttyS1", baudrate=baudrate, parity=serial.PARITY_NONE)
        self.start()
        self.__init()

    def close(self):
        self.startflag = False
        self.join()
        self.ser.close()

    def is_at_mode(self):
        return self.isatmode

    def write(self, data):
        self.ser.flushInput()
        self.ser.write(data)

    def __init(self):
        while True:
            try:
                self.__send_atdata(b'+++')
                self.reset()
                break
            except Exception as e:
                if str(e) == "不支持该指令":
                    if self.atdata == b'+++\r\n+ERROR 4\r\n':
                        self.reset()
                        break
                    else:
                        time.sleep(0.1)
                        continue
                else:
                    raise
        while not self.rebootstart:
            time.sleep(0.1)

    def run(self):
        while self.startflag:
            count = self.ser.inWaiting()
            if count != 0:
                data = self.ser.read(count)
                print(self.isatreturn, data)
                if AT_STATE_CONNECT in data:
                    self.isatmode = False
                    self.state = AT_STATE_CONNECT
                    if self.statecallback:
                        self.statecallback(self, AT_STATE_CONNECT)
                    continue
                elif AT_STATE_DISCONNECT in data or b'START\r\n' in data:
                    self.isatmode = True
                    self.rebootstart = True
                    self.state = AT_STATE_DISCONNECT
                    if self.statecallback:
                        self.statecallback(self, AT_STATE_DISCONNECT)
                    continue
                elif AT_STATE_WAKEUP in data:
                    self.isatmode = True
                    self.state = AT_STATE_WAKEUP
                    if self.statecallback:
                        self.statecallback(self, AT_STATE_WAKEUP)
                    continue
                elif AT_STATE_SLEEP in data:
                    self.isatmode = False
                    self.state = AT_STATE_SLEEP
                    if self.statecallback:
                        self.statecallback(self, AT_STATE_SLEEP)
                    continue
                if self.isatreturn:
                    self.q.put(data)
                    self.isatreturn = False
                else:
                    if self.state != AT_STATE_DISCONNECT and self.state != AT_STATE_SLEEP:
                        if self.datacallback:
                            self.datacallback(self, data)
            time.sleep(0.05)

    def get_state(self):
        return self.state

    def __send_atdata(self, data):
        if not self.isatmode and data != b'+++':
            return b''
        self.ser.flushInput()
        timecont = 0
        while self.isatreturn:
            time.sleep(0.01)
            timecont += 1
            if timecont >= self.timeout * 100:
                raise Exception("等待上次AT指令回应超时")
        self.isatreturn = True
        self.ser.write(data)
        timecont = 0
        while True:
            if not self.q.empty():
                self.atdata = self.q.get()
                err = re.search(b'\+ERROR (\d)', self.atdata)
                if err is not None:
                    if err.group(1) == b'1':
                        raise Exception('长度不匹配')
                    elif err.group(1) == b'2':
                        raise Exception('超过量程')
                    elif err.group(1) == b'3':
                        raise Exception('未找到参数')
                    elif err.group(1) == b'4':
                        raise Exception('不支持该指令')
                    elif err.group(1) == b'5':
                        raise Exception('保存flash失败')
                    elif err.group(1) == b'6':
                        raise Exception("参数非法")
                    else:
                        raise Exception("未知错误")
                return self.atdata
            time.sleep(0.01)
            timecont += 1
            if timecont >= self.timeout * 100:
                raise Exception("超时,请检测硬件连接")

    def set_data_callback(self, function):
        self.datacallback = function

    def set_state_callback(self, function):
        self.statecallback = function

    def at_test(self):
        return b'+OK\r\n' in self.__send_atdata(b'AT')

    def enter_at(self):
        if b'+enter_at_mode\r\n' in self.__send_atdata(b'+++'):
            self.isatmode = True
            return True
        else:
            self.isatmode = False
            return  False

    def exit_at(self):
        if b'+OK\r\n' in self.__send_atdata(b'AT+EXIT'):
            self.isatmode = False
            return True
        else:
            self.isatmode = True
            return False

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
        if parity == AT_PARITY_NONE:
            self.ser.parity = serial.PARITY_NONE
        elif parity == AT_PARITY_ODD:
            self.ser.parity = serial.PARITY_ODD
        elif parity == AT_PARITY_EVEN:
            self.ser.parity = serial.PARITY_EVEN
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
        set_data = b'AT+ADVDAT=' + bytes.fromhex(format(length, '02x')) + b'\xff' + vendor_data + data
        return b'+OK\r\n' in self.__send_atdata(set_data)

    def set_ibeacon_advdata(self, uuid, major, minor, power):
        set_data = b'AT+ADVDAT=\x1a\xff\x4c\x00\x02\x15' + uuid + major + minor + power
        return b'+OK\r\n' in self.__send_atdata(set_data)

    def get_advintv(self):
        return int(re.search(b'ADVINTV:(\d+)', self.__send_atdata(b'AT+ADVINTV=?')).group(1))

    def set_advintv(self, advintv=32):
        return b'+OK\r\n' in self.__send_atdata(b'AT+ADVINTV=' + str(advintv).encode())

    def get_name(self):
        return re.search(b'NAME:(.*?)', self.__send_atdata(b'AT+NAME=?')).group(1)

    def set_name(self, name):
        return b'+OK\r\n' in self.__send_atdata(b'AT+NAME=' + name)

    def get_conparams(self):
        return re.findall(b'\d+', self.__send_atdata(b'AT+CONPARAMS=?'))

    def set_conparams(self, connection_delay=40, slave_delay=0, parameter_exception=20):
        set_data = b'AT+CONPARAMS=' + str(connection_delay).encode() + b',' \
                   + str(slave_delay).encode() + b',' + str(parameter_exception).encode()
        return b'+OK\r\n' in self.__send_atdata(set_data)

    def disconnect(self, con=0):
        return b'+OK\r\n' in self.__send_atdata(b'AT+DISCON=' + str(con).encode())

    def get_mac(self):
        return re.search(b'MAC:(\w+)', self.__send_atdata(b'AT+MAC=?')).group(1)

    def set_mac(self, mac):
        return b'+OK\r\n' in self.__send_atdata(b'AT+MAC=' + mac)

    def get_bondmac(self):
        return re.findall(b'MAC:(\w+)', self.__send_atdata(b'AT+BONDMAC=?'))

    def set_bondmac(self, mac):
        return b'+OK\r\n' in self.__send_atdata(b'AT+BONDMAC=' + mac)

    def get_mtu(self):
        return int(re.search(b'MTU:(\d+)', self.__send_atdata(b'AT+MTU=?')).group(1))

    def set_mtu(self, mtu):
        return b'+OK\r\n' in self.__send_atdata(b'AT+MTU=' + str(mtu).encode())

    def get_scanwindow(self):
        return int(re.search(b'SCANWND:(\d+)', self.__send_atdata(b'AT+SCANWND=?')).group(1))

    def set_scanwindow(self, scanwnd):
        return b'+OK\r\n' in self.__send_atdata(b'AT+SCANWND=' + str(scanwnd).encode())

    def get_uuidserver(self):
        return int(re.search(b'UUIDSVR:(\d+)', self.__send_atdata(b'AT+UUIDSVR=?')).group(1))

    def set_uuidserver(self, uuidserver):
        return b'+OK\r\n' in self.__send_atdata(b'AT+UUIDSVR=' + uuidserver)

    def set_auth(self, password):
        return b'+OK\r\n' in self.__send_atdata(b'AT+AUTH=' + str(password).encode())

    def get_upauth(self):
        return re.search(b'AUTH:(\w+)', self.__send_atdata(b'AT+UPAUTH=?')).group(1)

    def set_upauth(self, password):
        return b'+OK\r\n' in self.__send_atdata(b'AT+UPAUTH=' + str(password).encode())

    def sleep(self, para):
        return b'+OK\r\n' in self.__send_atdata(b'AT+SLEEP=' + str(para).encode())

    def set_atestate(self, para):
        return b'+OK\r\n' in self.__send_atdata(b'AT+ATE=' + str(para).encode())

    def get_power(self):
        return re.search(b'PWR:(\d)', self.__send_atdata(b'AT+PWR=?')).group(1)

    def set_power(self, pwr):
        return b'+OK\r\n' in self.__send_atdata(b'AT+PWR=' + pwr)

    def get_version(self):
        lines = str(self.__send_atdata(b'AT+VER')).split('\r\n')
        return lines[len(lines) - 1]

    def get_bondenable(self):
        return re.search(b'BOND:(\d)', self.__send_atdata(b'AT+BOND=?')).group(1)

    def set_bondenable(self, para):
        return b'+OK\r\n' in self.__send_atdata(b'AT+BOND=' + para)

import threading
import serial
import re
import time
from time import *
import queue


class Bluetooth(threading.Thread):
    STATE = {b'\r\n STA:connect\r\n': "连接成功",
             b'\r\n disconnec\r\n': "连接断开",
             b'\r\n STA:wakeup\r\n': "系统唤醒",
             b'\r\n STA:sleep\r\n': "睡眠模式"}
    BAUD = {"0": 1200,
            "1": 2400,
            "2": 4800,
            '3': 9600,
            "4": 14400,
            "5": 19200,
            "6": 28800,
            "7": 38400,
            "8": 57600,
            "9": 76800,
            "10": 115200,
            "11": 230400,
            "12": 500000,
            "13": 1000000}
    PARI = {"0": "无检验",
            "1": "奇校验",
            "2": "偶校验"}
    ROLE = {"0": "从机",
            "1": "主机"}
    ADV = {"0": "关闭广播",
           "1": "普通广播",
           "2": "iBeacon广播"}

    def __init__(self, baudrate=115200, bytesize=8, stopbits=1, timeout=1):
        threading.Thread.__init__(self)
        self.q = queue.Queue()
        self.baudrate = baudrate
        self.bytesize = bytesize
        self.stopbits = stopbits
        self.timeout = timeout
        self.datacallback = None
        self.statecallback = None
        self.isatreturn = False
        self.nowstate = None
        self.ser = serial.Serial("/dev/ttyS1", baudrate=baudrate, bytesize=bytesize, stopbits=stopbits)
        self.ser.flushInput()

    def close(self):
        self.ser.close()

    def write(self, data):
        self.ser.flushInput()
        self.ser.write(data)

    def run(self):
        while True:
            count = self.ser.inWaiting()
            if count != 0:
                data = self.ser.read(count)
                self.q.put(data)
                print(data)
                if self.isatreturn:
                    self.isatreturn = False
                    err = re.search(r'\+ERROR (\d)', str(data))
                    if err is not None:
                        if err.group(1) == "1":
                            raise Exception("长度不匹配")
                        elif err.group(1) == "2":
                            raise Exception("超过量程")
                        elif err.group(1) == "3":
                            raise Exception("未找到参数")
                        elif err.group(1) == "4":
                            raise Exception("不支持该指令")
                        elif err.group(1) == "5":
                            raise Exception("保存flash失败")
                        elif err.group(1) == "6":
                            raise Exception("参数非法")
                        else:
                            raise Exception("未知错误")
                else:
                    othdata = self.q.get()
                    if othdata in self.STATE:
                        state = self.statecallback(self.STATE[othdata])
                        if self.statecallback:
                            self.statecallback(state)
                    else:
                        if self.datacallback:
                            self.datacallback(othdata)
            time.sleep(0.01)

    def __send_atdata(self, data):
        self.ser.flushInput()
        self.isatreturn = True
        self.ser.write(data)
        start_time = time()
        while True:
            if not self.q.empty():
                return self.q.get()
            if time() - start_time > self.timeout:
                raise Exception("超时,请检测是否处于AT模式及硬件连接")
            time.sleep(0.1)

    def set_data_callback(self, function):
        self.datacallback = function

    def set_state_callback(self, function):
        self.statecallback = function

    def test(self):
        if self.__send_atdata(b'AT') == b'AT\r\n+OK\r\n':
            return True
        else:
            return False

    def enter_at(self):
        if self.__send_atdata(b'+++') == b'+++\r\n+OK\r\n':
            return True
        else:
            return False

    def exit_at(self):
        if self.__send_atdata(b'AT+EXIT') == b'AT+EXIT\r\n+OK\r\n':
            return True
        else:
            return False

    def reset(self):
        if self.__send_atdata(b'AT+RESET') == b'AT+RESET\r\n+OK\r\n':
            return True
        else:
            return False

    def restore(self):
        if self.__send_atdata(b'AT+RESTORE') == b'AT+RESTORE\r\n+OK\r\n':
            return True
        else:
            return False

    def get_baudrate(self):
        baudnum = re.search(r'BAUD:(\d+)', str(self.__send_atdata(b'AT+BAUD=?')))
        return self.BAUD[baudnum]

    def set_baudrate(self, baudrate):
        strbaud = str(baudrate)
        if strbaud in self.BAUD:
            if self.__send_atdata(b'AT+BAUD=' + strbaud.encode()) \
                    == b'AT+BAUD=' + strbaud.encode() + b'\r\n+OK\r\n':
                self.ser.baudrate = self.BAUD[strbaud]
                return True
            else:
                return False
        else:
            for key, val in self.STATE.items():
                if val == baudrate:
                    if self.__send_atdata(b'AT+BAUD=' + key.encode()) \
                            == b'AT+BAUD=' + key.encode() + b'\r\n+OK\r\n':
                        self.ser.baudrate = baudrate
                        return True
                    else:
                        return False
        return False

    def get_parity(self):
        parinum = re.search(r'PARI:(\d)', str(self.__send_atdata(b'AT+PARI=?')))
        return self.PARI[parinum]

    def set_parity(self, parity):
        strbaud = str(parity)
        if self.__send_atdata(b'AT+PARI=' + strbaud.encode()) \
                == b'AT+PARI=' + strbaud.encode() + b'\r\n+OK\r\n':
            if parity == 0:
                self.ser.parity = serial.PARITY_NONE
            elif parity == 1:
                self.ser.parity = serial.PARITY_ODD
            elif parity == 2:
                self.ser.parity = serial.PARITY_EVEN
            return True
        else:
            return False

    def get_role(self):
        rolenum = re.search(r'ROLE:(\d)', str(self.__send_atdata(b'AT+ROLE=?')))
        return self.ROLE[rolenum]

    def set_role(self, role):
        strrole = str(role)
        if self.__send_atdata(b'AT+ROLE=' + strrole.encode()) \
                == b'AT+ROLE=' + strrole.encode() + b'\r\n+OK\r\n':
            return True
        else:
            return False

    def get_adv(self):
        advnum = re.search(r'ADV:(\d)', str(self.__send_atdata(b'AT+ADV=?')))
        return self.ADV[advnum]

    def set_adv(self, adv):
        stradv = str(adv)
        if self.__send_atdata(b'AT+ADV=' + stradv.encode()) \
                == b'AT+ADV=' + stradv.encode() + b'\r\n+OK\r\n':
            return True
        else:
            return False

    def get_advdata(self):
        # 查询广播数据的代码
        pass

    def set_advdat(self, para):
        # 设置广播数据的代码
        pass

    def get_advintv(self):
        # 查询广播间隙的代码
        pass

    def set_advintv(self, para):
        # 设置广播间隙的代码
        pass

    def get_name(self):
        # 查询广播设备名的代码
        pass

    def set_name(self, para):
        # 设置广播设备名的代码
        pass

    def get_conparams(self):
        # 查询连接配置的代码
        pass

    def set_conparams(self, para):
        # 设置连接配置的代码
        pass

    def disconnect(self):
        # 断开
        pass

    def get_mac(self):
        # 查询mac
        pass

    def set_mac(self, para):
        # 设置mac
        pass

    def get_bondmac(self):
        # 查询绑定mac
        pass

    def set_bondmac(self, para):
        # 绑定mac
        pass

    def get_mtu(self):
        # 查询mtu
        pass

    def set_mtu(self, para):
        # 设置mtu
        pass

    def get_scanwindow(self):
        # 查询扫描窗口
        pass

    def set_scanwindow(self, para):
        # 设置扫描窗口
        pass

    def get_uuidserver(self):
        # 查询服务 UUID
        pass

    def set_uuidserver(self, para):
        # 设置服务 UUID
        pass

    def set_auth(self, para):
        # 设置空中配置认证密码
        pass

    def set_upauth(self, para):
        # 修改空中认证密码
        pass

    def sleep(self, para):
        # 进入睡眠
        pass

    def set_atestate(self, para):
        # 设置ATE 运行状态
        pass

    def get_power(self):
        # 查询发射功率
        pass

    def set_power(self, para):
        # 设置发射功率
        pass

    def get_version(self):
        # 查询软件版本
        pass

    def bondenable(self, para):
        # 绑定mac使能
        pass


if __name__ == "__main__":
    blu = Bluetooth()
    print(blu.get_baudrate())

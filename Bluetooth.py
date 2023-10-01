import threading
import serial
import signal
import re
import time
import queue


class Bluetooth(threading.Thread):
    state = {b'\r\n STA:connect\r\n': "连接成功",
             b'\r\n disconnec\r\n': "连接断开",
             b'\r\n STA:wakeup\r\n': "系统唤醒",
             b'\r\n STA:sleep\r\n': "睡眠模式"}

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

    def run(self):
        while True:
            count = self.ser.inWaiting()
            if count != 0:
                data = self.ser.read(count)
                self.q.put(data)
                print(data)
                if self.isatreturn:
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
                    if data in self.state:
                        for key, val in self.state.items():
                            if key == data:
                                if self.statecallback:
                                    self.statecallback(val)
                    else:
                        if self.datacallback:
                            self.datacallback(data)
            time.sleep(0.01)

    def set_data_callback(self, function):
        pass

    def set_state_callback(self, function):
        pass

    def test(self):
        if self.__send_data(b'AT') == b'AT\r\n+OK\r\n':
            return True
        else:
            return False

    def enter_at(self):
        if self.__send_data(b'+++') == b'+++\r\n+OK\r\n':
            return True
        else:
            return False

    def exit_at(self):
        if self.__send_data(b'AT+EXIT') == b'AT+EXIT\r\n+OK\r\n':
            return True
        else:
            return False

    def reset(self):
        if self.__send_data(b'AT+RESET') == b'AT+RESET\r\n+OK\r\n':
            return True
        else:
            return False

    def restore(self):
        if self.__send_data(b'AT+RESTORE') == b'AT+RESTORE\r\n+OK\r\n':
            return True
        else:
            return False

    def get_baudrate(self):
        # 查询当前串口波特率的代码
        # self.__send_data(b'AT+BAUD=?')
        # err = re.search(r'\+ERROR (\d)', str( self.__send_data(b'AT+BAUD=?')))
        pass

    def set_baudrate(self, baudrate):
        # 设置当前串口波特率的代码
        pass

    def get_parity(self):
        # 查询当前串口检验位的代码
        pass

    def set_parity(self, new_parity):
        # 设置新的串口检验位的代码
        pass

    def get_role(self):
        # 查询当前蓝牙角色的代码
        pass

    def set_role(self, role):
        # 设置新的蓝牙角色的代码
        pass

    def get_adv(self):
        # 查询广播使能状态的代码
        pass

    def set_adv(self, para):
        # 设置广播使能状态的代码
        pass

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

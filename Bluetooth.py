import serial
import signal
import re


class Bluetooth(object):
    def __init__(self, baudrate=115200, bytesize=8, stopbits=1, timeout=1):
        self.timeout = timeout
        self.ser = serial.Serial("/dev/ttyS1", baudrate=baudrate, bytesize=bytesize, stopbits=stopbits)
        self.ser.flushInput()

    def __send_data(self, data):
        self.ser.flushInput()
        self.ser.write(data)

        def timeout_handler(signum, frame):
            raise TimeoutError("函数执行超时，请检查硬件是否连接正确")

        signal.signal(signal.SIGALRM, timeout_handler)
        try:
            signal.alarm(self.timeout)  # 设置闹钟定时器
            while True:
                count = self.ser.inWaiting()
                if count != 0:
                    recv = self.ser.read(count)
                    signal.alarm(0)
                    err = re.search(r'\+ERROR (\d)', str(recv))
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
                    print(recv)
                    return recv
        except TimeoutError:
            signal.alarm(0)
            print("函数执行超时，请检查硬件是否连接正确")
            return None

    def test(self):
        if self.__send_data(b'AT') == b'AT\r\n+OK\r\n':
            return True
        else:
            return False


if __name__ == "__main__":
    blu = Bluetooth()
    print(blu.test())

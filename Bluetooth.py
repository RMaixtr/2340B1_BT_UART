import serial
import signal
import re


class Bluetooth(object):
    def __init__(self, baudrate=115200, bytesize=8, stopbits=1):
        self.ser = serial.Serial("/dev/ttyS1", baudrate=baudrate, bytesize=bytesize, stopbits=stopbits)
        self.ser.flushInput()

    def __send_data(self, data, timeout=1):
        self.ser.flushInput()
        self.ser.write(data)

        def timeout_handler(signum, frame):
            raise TimeoutError("函数执行超时，请检查硬件是否连接正确")

        signal.signal(signal.SIGALRM, timeout_handler)
        try:
            signal.alarm(timeout)  # 设置闹钟定时器
            while True:
                count = self.ser.inWaiting()
                if count != 0:
                    recv = self.ser.read(count)
                    signal.alarm(0)
                    err = re.search(r'\+ERROR (\d)', str(recv))
                    # if err is None:
                    print(err.group(1))
                    print(recv)
                    return recv
        except TimeoutError:
            signal.alarm(0)
            print("函数执行超时，请检查硬件是否连接正确")
            return None

    def test(self, timeout=1):
        if self.__send_data(b'A') == b'AT\r\n+OK\r\n':
            return True
        else:
            return False


if __name__ == "__main__":
    blu = Bluetooth()
    print(blu.test())

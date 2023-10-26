import time
from bt import e104_bt08


def bt08_data_callback(self, data):
    # self.write(data)
    print("datacallback", data)


def bt08_state_callback(self, data):
    print("statechange", data)


if __name__ == '__main__':
    e104_bt08.init()
    e104_bt08.add_state_callback(bt08_state_callback)
    e104_bt08.add_data_callback(bt08_data_callback)
    input("连接蓝牙后按回车")
    e104_bt08.send_file(b'example.py', b'example')
    while e104_bt08.sendflag:
        time.sleep(1)
    time.sleep(1)
    e104_bt08.slave_run(b'example')
    time.sleep(5)
    e104_bt08.slave_stop()
    e104_bt08.close()

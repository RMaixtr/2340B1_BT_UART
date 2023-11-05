import time
from bt import e104_bt08
import bt


def bt08_data_callback(self, data):
    # self.write(data)
    print("datacallback", data)


def bt08_state_callback(self, data):
    print("statechange", data)


def unit_test_loop_change_id():
    for index in range(10):
        try:
            e104_bt08.set_name(str(index).encode())
            e104_bt08.reset()
        except Exception as e:
            if str(e) == bt.AT_ERR[b'4']:
                e104_bt08.set_name(str(index).encode())
                e104_bt08.reset()
            elif str(e) == "等待AT指令回应超时" or str(e) == "等待上次AT指令回应超时":
                try:
                    e104_bt08.enter_at()
                except Exception as e:
                    if str(e) == "等待AT指令回应超时" or str(e) == "等待上次AT指令回应超时":
                        print("请检查蓝牙模块是否断开")
                        exit()
                e104_bt08.set_name(str(index).encode())
                e104_bt08.reset()

        time.sleep(1)


def unit_test_loop_write_read():
    for index in range(10):
        if e104_bt08.is_connected():
            e104_bt08.write(str(index).encode())
            time.sleep(1)
            print('read', e104_bt08.read())


if __name__ == '__main__':
    e104_bt08.add_state_callback(bt08_state_callback)
    e104_bt08.add_data_callback(bt08_data_callback)
    unit_test_loop_change_id()
    while not e104_bt08.is_connected():
        time.sleep(1)
    unit_test_loop_write_read()
    e104_bt08.close()

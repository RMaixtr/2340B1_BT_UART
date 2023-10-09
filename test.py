import time
from bt import e104_bt08
import bt


def bt08_data_callback(self, data):
    # self.write(data)
    print("datacallback", data)


def bt08_state_callback(self, data):
    print("statechange", data)


def unit_test_loop_change_id():
    while not e104_bt08.isatmode:
        time.sleep(1)
    for index in range(10):
        e104_bt08.set_name(str(index).encode())
        e104_bt08.reset()
        time.sleep(1)


def unit_test_loop_write_read():
    while e104_bt08.get_state() != bt.AT_STATE_CONNECT:
        time.sleep(1)
    for index in range(10):
        e104_bt08.write(str(index).encode())
        time.sleep(1)


if __name__ == '__main__':
    e104_bt08.add_state_callback(bt08_state_callback)
    e104_bt08.add_data_callback(bt08_data_callback)
    unit_test_loop_change_id()
    unit_test_loop_write_read()
    e104_bt08.close()

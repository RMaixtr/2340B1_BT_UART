from bt import e104_bt08


def bt08_data_callback(self, data):
    self.write(data)
    print("datacallback", data)


def bt08_state_callback(self, data):
    print("statechange")


def unit_test_loop_change_id():
    pass


def unit_test_loop_write_read():
    ble = e104_bt08(datacallback=bt08_data_callback, statecallback=bt08_state_callback)
    for index in range(10):
        ble.set_name(str(index).encode())
        ble.reset()
        ble.close()


if __name__ == '__main__':
    unit_test_loop_write_read()
    unit_test_loop_change_id()

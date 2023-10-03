import e104_bt08


def bt08_data_callback(self, data):
    self.write(data)
    print("datacallback", data)


def bt08_state_callback(self, data):
    print("statechange")


if __name__ == '__main__':
    blu = e104_bt08.e104_bt08(baudrate=115200, bytesize=8, stopbits=1, timeout=1,
                              datacallback=bt08_data_callback, statecallback=bt08_state_callback)
    while True:
        user_input = input("按下 Enter 键来结束程序：")
        if user_input == "":
            break
    blu.close()

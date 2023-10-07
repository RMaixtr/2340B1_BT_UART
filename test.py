import e104_bt08


def bt08_data_callback(self, data):
    self.write(data)
    print("datacallback", data)


def bt08_state_callback(self, data):
    print("statechange")


if __name__ == '__main__':
    blu = e104_bt08.e104_bt08(datacallback=bt08_data_callback, statecallback=bt08_state_callback)
    while True:
        user_input = input("回车退出")
        break
    blu.close()

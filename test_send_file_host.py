import time
from bt import e104_bt08
import bt


def bt08_data_callback(self, data):
    print("datacallback", data)


def bt08_state_callback(self, data):
    print("statechange", data)


if __name__ == '__main__':
    # 设置蓝牙角色为主机,当两个蓝牙模块处于同一环境bondenable配置为disable且一个为主机一个为从机,会自动连接(此时通过UUID过滤)
    # 当未对蓝牙模块配置过时,要使处于同一环境的两个蓝牙模块自动连接只需配置一个为主机一个为从机
    e104_bt08.set_role(bt.AT_ROLE_HOST)
    e104_bt08.reset()
    e104_bt08.add_state_callback(bt08_state_callback)
    e104_bt08.add_data_callback(bt08_data_callback)
    # 主机要发送给从机的程序 example.py
    with open('example .py', 'rb') as f:
        example = f.read()
    print(example)

    state = 0
    start_time = time.time()
    while True:
        if not e104_bt08.is_connected():
            state = 0
        elif state == 0:
            if e104_bt08.is_connected():
                e104_bt08.send_file(b'example.py')
                state = 1
        elif state == 1:
            if e104_bt08.sendflag:
                e104_bt08.slave_run()
                state = 2
                start_time = time.time()
        elif state == 2:
            if time.time() - start_time >= 5:  # 运行5秒后停止运行
                e104_bt08.slave_stop()
                break
        time.sleep(1)

import time
from bt import e104_bt08
import bt


def bt08_data_callback(self, data):
    print("datacallback", data)


def bt08_state_callback(self, data):
    print("statechange", data)


def bt08_sendend_callback(self, state):
    print("sendend", state)


if __name__ == '__main__':
    # 设置蓝牙角色为主机,当两个蓝牙模块处于同一环境bondenable配置为disable且一个为主机一个为从机,会自动连接(此时通过UUID过滤)
    # 当未对蓝牙模块配置过时,要使处于同一环境的两个蓝牙模块自动连接只需配置一个为主机一个为从机
    # 当存在多个模块时
    #   若知道想连接的mac(通过get_mac获取),需将bondenable配置为able,通过set_bondmac设置绑定mac(此时通过mac过滤)
    #   若不知道想连接的mac,需将bondenable配置为disable,通过set_uuidserver单独给两个欲连接设备设置uuidserver(此时通过UUID过滤)
    #    注:set_uuidserver仅修改连接时所用到的 UUID 参数，其过滤作用，并不会修改真正的服务 UUID
    e104_bt08.set_uuidserver(65521)  # 设置uuid为65521,uuid默认为65520,方便在多个模块间区分
    e104_bt08.set_role(bt.AT_ROLE_HOST)
    e104_bt08.reset()  # 重启后生效
    e104_bt08.add_state_callback(bt08_state_callback)
    e104_bt08.add_data_callback(bt08_data_callback)
    e104_bt08.add_sendend_callback(bt08_sendend_callback)
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
                time.sleep(5)  # 延时一下,两个模块刚连接发送信息有几率发不出去
                e104_bt08.send_file(b'example.py')
                state = 1
        elif state == 1:
            if e104_bt08.send_finish():
                e104_bt08.slave_run()
                state = 2
                start_time = time.time()
        elif state == 2:
            if time.time() - start_time >= 5:  # 运行5秒后停止运行
                e104_bt08.slave_stop()
                break
        time.sleep(1)

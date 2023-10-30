import time
from bt import e104_bt08

if __name__ == '__main__':
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

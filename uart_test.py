import time
from bt import e104_bt08
import bt

if __name__ == '__main__':
    e104_bt08.set_role(bt.AT_ROLE_HOST)
    e104_bt08.reset()  # 重启后生效
    input("wait")

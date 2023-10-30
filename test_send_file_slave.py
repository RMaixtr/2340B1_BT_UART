from maix import camera, display, image
from bt import e104_bt08

if __name__ == '__main__':
    e104_bt08.set_globals(globals())  # 于主代码中导入运行文本文件所需的模块需调用set_globals传入globals,或于bt.py导入模块处导入
    input("wait host run code...")

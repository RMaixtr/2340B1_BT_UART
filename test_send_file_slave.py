from maix import camera, display, image
from bt import e104_bt08
import bt

if __name__ == '__main__':
    # 设置蓝牙角色为主机,当两个蓝牙模块处于同一环境bondenable配置为disable且一个为主机一个为从机,会自动连接(此时通过UUID过滤)
    # 当未对蓝牙模块配置过时,要使处于同一环境的两个蓝牙模块自动连接只需配置一个为主机一个为从机
    e104_bt08.set_role(bt.AT_ROLE_SLAVE)
    e104_bt08.reset()
    e104_bt08.set_globals(globals())  # 于主代码中导入运行文本文件所需的模块需调用set_globals传入globals,或于bt.py导入模块处导入
    hello_img = image.new(size = (240, 240),
                                  color = (255, 0, 0), mode = "RGB")
    hello_img.draw_string(30, 115, "wait", scale = 3,
                                  color = (255, 255, 255), thickness = 1)
    display.show(hello_img)
    input("wait host run code...")

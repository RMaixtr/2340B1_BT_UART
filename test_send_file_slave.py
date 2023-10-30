from maix import camera, display, image
from bt import e104_bt08

if __name__ == '__main__':
    e104_bt08.set_globals(globals())  # 于主代码中导入运行文本文件所需的模块需调用set_globals传入globals,或于bt.py导入模块处导入
    hello_img = image.new(size = (240, 240),
                                  color = (255, 0, 0), mode = "RGB")
    hello_img.draw_string(30, 115, "wait", scale = 3,
                                  color = (255, 255, 255), thickness = 1)
    display.show(hello_img)
    input("wait host run code...")

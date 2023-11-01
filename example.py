# from maix import camera, display, image 此处内容用于测试中文传输
# 在模块中导入运行文本文件所需的模块,或于主代码处导入模块,使用set_globals导入主代码globals
a = 1
while True:
    hello_img = image.new(size = (240, 240),
                                  color = (255, 0, 0), mode = "RGB")
    hello_img.draw_string(30, 115, str(a), scale = 10.0,
                                  color = (255, 255, 255), thickness = 1)
    display.show(hello_img)
    print(str(a))
    a = a+1
    time.sleep(1)

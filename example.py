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


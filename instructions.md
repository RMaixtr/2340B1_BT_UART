### 1.BLE透传参数

![image-20231030213434402](C:\Users\RMaixtr\AppData\Roaming\Typora\typora-user-images\image-20231030213434402.png)

### 2.发送程序

CRC-8(x8+x2+x+1)的Java和Python实现

##### 注:使用的十六进制参数的字母均为小写且不带 `0x`

```java
public class CRC8Calculator {
    public static int calCRC(int data) {
        int crc = data;
        int poly = 0x07;
        for (int i = 8; i > 0; i--) {
            if ((crc & 0x80) >> 7 == 1) {
                crc = (crc << 1) ^ poly;
            } else {
                crc = (crc << 1);
            }
        }
        return crc & 0xFF;
    }
    public static int calculateCRC8(byte[] data) {
        int crc = 0x00;
        int length = data.length;

        for (int i = 0; i < length; i++) {
            if (i == 0) {
                crc = calCRC(data[0] & 0xFF);
            } else {
                crc = (crc ^ (data[i] & 0xFF)) & 0xFF;
                crc = calCRC(crc);
            }
        }
        return crc & 0xFF;
    }
}

```

```python
def cal_crc(data):
    crc = data
    poly = 0x07
    for i in range(8, 0, -1):
        if ((crc & 0x80) >> 7) == 1:
            crc = (crc << 1) ^ poly
        else:
            crc = (crc << 1)
    return crc & 0xFF
def crc8(datas):
    list = [int(byte) for byte in datas]
    length = len(list)
    crc = 0x00
    for i in range(length):
        if i == 0:
            crc = cal_crc(list[0])
        else:
            crc = (crc ^ list[i]) & 0xFF
            crc = cal_crc(crc)
    return crc & 0xFF
```

以下内容以Python的形式叙述

##### 文件发送

发送段需要先发送 `b'\xff\xff' + 接收段保存文件名 + 分卷大小 + 所发送文件的CRC-8`

注:

​	1.`分卷大小` 及 `所发送文件的CRC-8` 均为十六进制且字母均为小写

​	2.`接收段保存文件名` 需要小于等于10个字节,不足10字节需于前面用 `b'\xff'` 补齐

​	3.`分卷大小` 需要小于等于6个字节,不足6字节需于前面用 `b'0'` 补齐,每18个字节为一个分卷

​	4.`所发送文件的CRC-8` 需要小于等于2个字节,不足2字节需于前面用 `b'0'` 补齐

接收段回应有三种情况

​	1.接收端存在所发送文件,且存在的文件分卷大小,CRC-8与接收到的相同

​		将接收到的内容去除 `接收段保存文件名` 返回

​	2.接收端存在所发送文件,且存在的文件分卷大小小于接收到的文件分卷大小

​		返回 `b'\xff\xff' + 存在的文件分卷大小 + 存在的文件最后的分卷CRC-8`

​	3.其他,例如不存在文件,存在的文件分卷大小大于接收到的文件分卷大小等

​		返回 `b'\xff\xff00000000' `

发送端需根据回应发送数据

​	1.发送 `b'\xff\xff\xff'` 结束文件发送

​	2.根据 `存在的文件分卷大小` 计算出所发送文件对应分卷的CRC-8与  `存在的文件最后的分卷CRC-8` 进行比较

​		若相同则发送 `b'\xff\xff\x01'` ,从相应分卷开始发送分卷内容

​		不相同发送 `b'\xff\xff\x00'`  ,从头开始发送分卷内容

​	3.发送 `b'\xff\xff\x00'`  ,从头开始发送分卷内容

注:

​	1.发送分卷内容需在分卷内容前加上 `b'\xff\xff'`

​	1.发送分卷内容前建议延时70ms防止分卷内容与回应内容"黏"在一起,在发送分卷内容时无需延时

​	2.发送结束后发送 `b'\xff\xff\xff'` 结束文件发送

### 3.运行程序

发送端发送 `b'\xff\xff\x10' + 所需运行的程序名`

注: 

​	1.`所需运行的程序名` 为接收端运行目录下所想运行的程序名,未限制字节长度,但建议使用20字节以内,过多字节可能会导致分包的情况,即发送 `b'\xff\xff\x10example'` 接收到 `b'\xff\xff\x10exa'` 和 `b'mple'` 的情况

​	2.接收端运行过程的输出会通过蓝牙模块直接返回,接收端只需接收蓝牙返回的信息即可

### 4.结束程序

发送端发送 `b'\xff\xff\x11'` 即可结束 `3.运行程序` 中接收端所运行的程序

### 5.可能会遇到的问题

##### 反复触发 `state_callback` ,且接收到的状态为 `AT_STATE_DISCONNECT` ,即反复接收到蓝牙模块发送数据 `START`

原因:26号引脚(P2 唤醒引脚，下降沿立即唤醒)浮空,电平不定

解决方法:将26号引脚弱上下拉

![image-20231031212903645](C:\Users\RMaixtr\AppData\Roaming\Typora\typora-user-images\image-20231031212903645.png)

### 样例数据

若需把以下程序上传至接收端并于接收端保存为b'example.py'

```
while True:
    print("1")
    time.sleep(1)
```

##### 1

发送端:b'\xff\xffexample.py0000032c'

接收端:b'\xff\xff0000032c'

发送端:b'\xff\xff\xff'

##### 2

发送端:b'\xff\xffexample.py0000032c'

​	2.1 当接收端存在b'example.py'且内容为

	while True:
		print("1")
		tim

​		接收端:b'\xff\xff0000029e'

​		分卷2(b'rint("1")\r\n    tim')CRC值为9e

​		发送端:b'\xff\xff\x01'

​		发送端建议延时

​		发送端:b'\xff\xffe.sleep(1)'

​		发送端:b'\xff\xff\xff'

​		发送结束

​	2.2 当接收端存在b'example.py'且内容为不对

​		接收端:b'\xff\xff0000018b' 	根据b'example.py'内容不同分卷和CRC不同

​		分卷1(b'while True:\r\n    p')CRC值应为bb

​		发送端:b'\xff\xff\x00'

​		发送端建议延时

​		发送端:b'\xff\xffwhile True:\r\n    p'

​		发送端:b'\xff\xffrint("1")\r\n    tim'

​		发送端:b'\xff\xffe.sleep(1)'

​		发送端建议延时

​		发送端:b'\xff\xff\xff'

​		发送结束

##### 3

发送端:b'\xff\xffexample.py0000032c'

接收端:b'\xff\xff00000000'

发送端:b'\xff\xff\x00'

发送端建议延时

发送端:b'\xff\xffwhile True:\r\n    p'

发送端:b'\xff\xffrint("1")\r\n    tim'

发送端:b'\xff\xffe.sleep(1)'

发送端建议延时

发送端:b'\xff\xff\xff'

发送结束

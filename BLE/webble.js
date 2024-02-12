
let ArrNotifyListen = [
    "0000fff1-0000-1000-8000-00805f9b34fb",
    "FFF1"
];

let ArrWriteListen = [
    "0000fff2-0000-1000-8000-00805f9b34fb",
    "FFF2"
];

let ArrReadListen = [
    "0000fff1-0000-1000-8000-00805f9b34fb",
    "FFF1"
];

let bledevice;
var timeoutId;

async function requestDevice() {
    return await new Promise((resolve, reject) => {
        navigator.bluetooth.requestDevice({
            //acceptAllDevices: true, //允许连接所有的设备
            filters: [
                { name: 'E104-BT08' },
                { services: ["0000fff0-0000-1000-8000-00805f9b34fb"] },
                { services: ["0000fff1-0000-1000-8000-00805f9b34fb"] },
                { services: ["0000fff2-0000-1000-8000-00805f9b34fb"] }
            ],
            //根据设备和服务的UUID读取设备
            optionalServices: [
                "0000fff0-0000-1000-8000-00805f9b34fb", //设备uuid
                "0000fff1-0000-1000-8000-00805f9b34fb", //读服务的uuid
                "0000fff2-0000-1000-8000-00805f9b34fb" //写服务的uuid
            ]
        }).then(device => {
            try {
                bledevice = device;
                resolve(device);
                //连接设备
                return device.gatt.connect();
            }
            catch (e) {

            }
        }).then(server => {
            try {
                //获取设备所有的服务
                return server.getPrimaryServices();
            }
            catch (e) {

            }
        }).then(services => {
            try {
                //绑定特征
                return overrideServices(services);
            }
            catch (e) {

            }
        }).catch(error => {
            console.log(error);
            reject(error)
            return error;
        });
    })
}

//把所有的服务的特性读取
async function bleconnect() {
    try {
        //绑定特征
        const devices = await navigator.bluetooth.getDevices();
        if (devices.length) {
            try {
                bledevice = devices[0];
                const server = await bledevice.gatt.connect();
                const services = await server.getPrimaryServices();
                overrideServices(services);
                return bledevice;
            }
            catch (e) {
                console.log(error);
            }

        }
        return null;
    }
    catch (e) {

    }
}

//把所有的服务的特性读取
async function overrideServices(services) {
    try {
        let queue = Promise.resolve();
        services.forEach(service => {
            queue = queue.then(_ => service.getCharacteristics().then(characteristics => {
                startNotifications(characteristics);
            }));
        });
        return queue;
    }
    catch (e) {

    }
}

//服务和读写的功能绑定
function startNotifications(newservices) {
    try {
        newservices.forEach(newservice => {
            //启动通知
            if (ArrNotifyListen.indexOf(newservice.uuid) != -1) {
                addNotifyListen(newservice);
            }
            //发送api的绑定
            if (ArrWriteListen.indexOf(newservice.uuid) != -1) {
                addWriteListen(newservice);
            }
            //读取api的绑定
            if (ArrReadListen.indexOf(newservice.uuid) != -1) {
                addReadListen(newservice);
            }
        });
    }
    catch (e) {
    }
}

//绑定通知服务
function addNotifyListen(service) {
    if (service.properties.notify) {
        service.startNotifications();//启动通知
        //获取通知信息
        service.addEventListener('characteristicvaluechanged', function (event) {
            // const rechex = new TextDecoder().decode(event.target.value.buffer);
            // console.log(rechex);
            clearTimeout(timeoutId);
            window.blechangedcallback && window.blechangedcallback(event);
        });
    }
}

//发送api
function addWriteListen(service) {
    if (service.properties.write) {
        //由于目前蓝牙设备只有一个写入功能，所以直接把写入公布到window里
        window.blewrite = async function (value, callback) {
            if (value) {
                //如果发送的数据是字符串，转化成unit8Array
                if (typeof value === 'string') {
                    value = new TextEncoder().encode(value);
                }
                //发送数据
                const data = await service.writeValue(value);
                return data;
            }
        }
    }
}

//读取返回的数据功能
function addReadListen(service) {
    if (service.properties.read) {
        window.bleread = async function (callback) {
            const data = await service.readValue().then(
                function (resolve) {
                    try {
                        const rechex = new TextDecoder().decode(resolve.buffer);
                        callback(rechex); //监听后获取返回的数据，判断需要发送什么信号过去
                        return rechex;
                    } catch (e) {
                        console.log(e);
                    };
                    return null;
                },
                function (e) {
                    console.error(e);
                    return null;
                }
            );
            return data;
        }
    }
}

var sendcrc;

//文件分块发送
async function blefilesend(event, datas, len) {
    const returnarr = new Uint8Array(event.target.value.buffer);
    //判断信号是否是发送文件的信号
    if (returnarr[0] === returnarr[1] && returnarr[0] === 0xFF) {
        try {
            let split = uint8ArrayToInt(returnarr.slice(2, 8));
            let crc = returnarr.slice(8, 16);
            //已经传输完毕无需再上传
            if (split == len && Uint8ArraysEqual(crc, sendcrc)) {
                window.blewrite(new Uint8Array([0xFF, 0xFF, 0xFF]));
            }
            //断点续传,满足是同一个文件的情况
            else if (split != 0 && split < len) {
                const splitcrc = new TextEncoder().encode((CRC32.buf(new Uint8Array([...datas.subarray(0, split)])) >>> 0).toString(16).padStart(8, '0'));
                if (Uint8ArraysEqual(crc, splitcrc)) {
                    // await window.blewrite(new Uint8Array([0xFF, 0xFF, 0x01]));//发送信号说明要发送文件
                    for (let i = split; i < datas.length; i += 240) {
                        if (i + 240 >= datas.length) {
                            timeoutId = setTimeout(() => { window.blewrite(new Uint8Array([0xFF, 0xFF, 0xFF])); }, 2000);
                        }
                        if (i == split) {
                            await window.blewrite(new Uint8Array([
                                ...new Uint8Array([0xFF, 0xFF, 0x01]),
                                ...datas.subarray(i, i + 240)]));
                        }else{
                            await window.blewrite(new Uint8Array([...datas.subarray(i, i + 240)]));
                        }
                    }
                } else {
                    // await window.blewrite(new Uint8Array([0xFF, 0xFF, 0xF0]));
                    for (let i = 0; i < datas.length; i += 240) {
                        if (i + 240 >= datas.length) {
                            timeoutId = setTimeout(() => { window.blewrite(new Uint8Array([0xFF, 0xFF, 0xFF])); }, 2000);
                        }
                        if (i == 0) {
                            await window.blewrite(new Uint8Array([
                                ...new Uint8Array([0xFF, 0xFF, 0xF0]),
                                ...datas.subarray(i, i + 240)]));
                        }else{
                            await window.blewrite(new Uint8Array([...datas.subarray(i, i + 240)]));
                        }
                    }
                }
            }
            //文件直接上传
            else {
                // await window.blewrite(new Uint8Array([0xFF, 0xFF, 0xF0]));
                for (let i = 0; i < datas.length; i += 240) {
                    if (i + 240 >= datas.length) {
                        timeoutId = setTimeout(() => { window.blewrite(new Uint8Array([0xFF, 0xFF, 0xFF])); }, 2000);
                    }
                    if (i == 0) {
                        await window.blewrite(new Uint8Array([
                            ...new Uint8Array([0xFF, 0xFF, 0xF0]),
                            ...datas.subarray(i, i + 240)]));
                    }else{
                        await window.blewrite(new Uint8Array([...datas.subarray(i, i + 240)]));
                    }
                }
            }
            return true;
        }
        catch (error) {
            return false;
            console.error('发送消息错误:', error);
        }
    }
}


//停止运行
function blestop(callback) {
    window.blewrite(new Uint8Array([0xFF, 0xFF, 0x11]), callback);
}

//运行py文件
function blerun(filename, callback) {
    const start = new Uint8Array([0xFF, 0xFF, 0x10]);
    const sendmessage = new TextEncoder().encode(filename)
    const newarr = new Uint8Array([...start, ...sendmessage]);
    window.blechangedcallback = async function (event) {
        //判断是否结束运行了
        const isrun = new Uint8Array(event.target.value.buffer).toString().indexOf(new Uint8Array([0xFF, 0xFF, 0x1F]).toString()) > -1;
        let rechex = "";
        if (isrun) {
            rechex = new TextDecoder().decode(new Uint8Array(event.target.value.buffer).slice(0, -3));
        }
        else {
            rechex = new TextDecoder().decode(event.target.value.buffer);
        }
        callback(rechex, isrun);
    };
    window.blewrite(newarr);
}

//上传py文件
function bleuploadfile(filename, sendstring, callback) {
    const textec = new TextEncoder();
    const textarr = textec.encode(sendstring); //
    sendcrc = textec.encode((CRC32.str(sendstring) >>> 0).toString(16).padStart(8, '0'));
    const sendarr = new Uint8Array([
        ...new Uint8Array([0xFF, 0xFF]),
        ...textec.encode(filename),
        ...intToUint8Array(textarr.length),
        ...sendcrc,
    ]);
    console.log(sendarr);
    window.blechangedcallback = async function (event) {
        //window.blechangedcallback = callback;
        window.blechangedcallback = null;
        //上传结束回调
        blefilesend(event, textarr, textarr.length).then(value => {
            callback(value);
        });

    };
    window.blewrite(sendarr);
}

function Uint8ArraysEqual(arr1, arr2) {
    if (arr1.length !== arr2.length) {
        return false;
    }
    for (let i = 0; i < arr1.length; i++) {
        if (arr1[i] !== arr2[i]) {
            return false;
        }
    }
    return true;
}

function intToUint8Array(val) {
    let base255Array = [];
    let number = BigInt(val)
    while (number > 0n) {
        base255Array.unshift(Number(number % 255n));
        number = number / 255n;
    }
    while (base255Array.length < 6) {
        base255Array.unshift(0);
    }
    const incrementedArray = base255Array.map(value => value + 1);
    return new Uint8Array(incrementedArray);
}
function uint8ArrayToInt(array) {
    const decrementedArray = Array.from(array).map(value => value - 1);
    let index = 0;
    while (index < decrementedArray.length && decrementedArray[index] === 0) {
        index++;
    }
    const trimmedArray = decrementedArray.slice(index);
    let result = BigInt(0);
    for (let i = 0; i < trimmedArray.length; i++) {
        result = result * 255n + BigInt(trimmedArray[i]);
    }
    return Number(result);
}

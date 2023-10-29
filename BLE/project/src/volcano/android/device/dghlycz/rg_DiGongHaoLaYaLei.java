
package volcano.android.device.dghlycz;

import cn.com.heaton.blelibrary.ble.model.BleDevice;

public class rg_DiGongHaoLaYaLei {

    public rg_DiGongHaoLaYaLei ()  { }

    private boolean isInit;
    private cn.com.heaton.blelibrary.ble.Ble.Options options = cn.com.heaton.blelibrary.ble.Ble.options();
    private cn.com.heaton.blelibrary.ble.callback.BleConnectCallback<BleDevice> getConnectCallBack(){
        return new cn.com.heaton.blelibrary.ble.callback.BleConnectCallback<BleDevice>(){
            @Override
            public void onConnectionChanged(BleDevice device) {
                rg_SheBeiLianJieZhuangTaiGaiBian(device);
            }
            @Override
            public void onConnectCancel(BleDevice device) {
                rg_SheBeiLianJieYiQuXiao(device);
            }
            @Override
            public void onReady(BleDevice device) {
                rg_SheBeiYiLianJieChengGong(device);
            }
            @Override
            public void onServicesDiscovered(BleDevice device, android.bluetooth.BluetoothGatt gatt) {
                rg_FaXianZhiDingFuWu(device, gatt);
            }
            @Override
            public void onConnectException(BleDevice device, int errorCode) {
                rg_SheBeiLianJieYiChang(device, errorCode);
            }
            @Override
            public void onConnectTimeOut(BleDevice device) {
                rg_SheBeiLianJieChaoShi(device);
            }
        };
    }
    private cn.com.heaton.blelibrary.ble.callback.BleNotifyCallback<BleDevice> getNotifyCallBack(){
        return new cn.com.heaton.blelibrary.ble.callback.BleNotifyCallback<BleDevice>(){
            @Override
            public void onChanged(BleDevice device, android.bluetooth.BluetoothGattCharacteristic characteristic) {
                rg_SheBeiTongZhiYiGaiBian(device, characteristic);
            }
            @Override
            public void onNotifyCanceled(BleDevice device) {
                rg_SheBeiTongZhiYiQuXiao(device);
            }
            @Override
            public void onNotifySuccess(BleDevice device) {
                rg_SheBeiTongZhiYiChengGong(device);
            }
        };
    }
    private void checkInit(){
        if (!isInit){
            options.create(volcano.android.base.rg_YingYongChengXu.sGetApp (), new cn.com.heaton.blelibrary.ble.Ble.InitCallback(){
                @Override
                public void success(){
                    rg_ChuShiHuaChengGong();
                }
                @Override
                public void failed(int failedCode){
                    rg_ChuShiHuaShiBai(failedCode);
                }
            });
            cn.com.heaton.blelibrary.ble.Ble.getInstance().setBleStatusCallback(new cn.com.heaton.blelibrary.ble.callback.BleStatusCallback(){
                @Override
                public void onBluetoothStatusChanged(boolean isOn){
                    rg_LaYaKaiGuanZhuangTaiGaiBian(isOn);
                }
            });
            isInit = true;
        }
    }

    public java.util.List<cn.com.heaton.blelibrary.ble.model.BleDevice> rg_YiLianJieSheBei1 () {
        checkInit();
        return (java.util.List<BleDevice>)cn.com.heaton.blelibrary.ble.Ble.getInstance().getConnectedDevices();
    }

    public boolean rg_LaYaKaiQiZhuangTai () {
        checkInit();
        return cn.com.heaton.blelibrary.ble.Ble.getInstance().isBleEnable();
    }

    public void rg_ChuShiHua1 () {
        checkInit();
    }

    public void rg_KaiShiSaoMiaoSheBei (long rg_SaoMiaoShiChang2) {
        checkInit();
        cn.com.heaton.blelibrary.ble.Ble.getInstance().startScan(new cn.com.heaton.blelibrary.ble.callback.BleScanCallback<BleDevice>(){
            @Override
            public void onStart() {
                rg_SaoMiaoYiKaiShi();
            }
            @Override
            public void onStop() {
                rg_SaoMiaoYiTingZhi();
            }
            @Override
            public void onScanFailed(int errorCode) {
                rg_SaoMiaoShiBai(errorCode);
            }
            @Override
            public void onLeScan(BleDevice device, int rssi, byte[] scanRecord) {
                rg_FaXianXinSheBei(device, rssi, scanRecord);
            }
            @Override
            public void onParsedData(BleDevice device, cn.com.heaton.blelibrary.ble.model.ScanRecord scanRecord) {
                rg_YiJieXiAnBoBao(device, scanRecord);
            }
        }, rg_SaoMiaoShiChang2 > 0 ? rg_SaoMiaoShiChang2 : cn.com.heaton.blelibrary.ble.Ble.getInstance().options().getScanPeriod());
    }

    public void rg_TingZhiSaoMiao () {
        checkInit();
        cn.com.heaton.blelibrary.ble.Ble.getInstance().stopScan();
    }

    public void rg_LianJieSheBeiDeZhi (String rg_SheBeiDeZhi) {
        checkInit();
        cn.com.heaton.blelibrary.ble.Ble.getInstance().connect(rg_SheBeiDeZhi, getConnectCallBack());
    }

    public void rg_ZhiSheBeiZhiDingBiaoShiTongZhi (cn.com.heaton.blelibrary.ble.model.BleDevice rg_device24, boolean rg_QiYong12, java.util.UUID rg_FuWuBiaoShi, java.util.UUID rg_TongZhiTeZhengBiaoShi) {
        checkInit();
        cn.com.heaton.blelibrary.ble.Ble.getInstance().enableNotifyByUuid(rg_device24, rg_QiYong12, rg_FuWuBiaoShi, rg_TongZhiTeZhengBiaoShi, getNotifyCallBack());
    }

    public boolean rg_XieSheBeiZhiDingBiaoShiShuJu (cn.com.heaton.blelibrary.ble.model.BleDevice rg_device28, byte [] rg_ShuJu69, java.util.UUID rg_FuWuBiaoShi2, java.util.UUID rg_TongZhiTeZhengBiaoShi2) {
        checkInit();
        return cn.com.heaton.blelibrary.ble.Ble.getInstance().writeByUuid(rg_device28, rg_ShuJu69, rg_FuWuBiaoShi2, rg_TongZhiTeZhengBiaoShi2, new cn.com.heaton.blelibrary.ble.callback.BleWriteCallback<BleDevice>(){
            @Override
            public void onWriteSuccess(BleDevice device, android.bluetooth.BluetoothGattCharacteristic characteristic) {
                rg_XieSheBeiShuJuChengGong(device, characteristic);
            }
            @Override
            public void onWriteFailed(BleDevice device, int failedCode) {
                rg_XieSheBeiShuJuShiBai(device, failedCode);
            }
        });
    }

    public void rg_ShiFangSuoYouZiYuan () {
        checkInit();
        cn.com.heaton.blelibrary.ble.Ble.getInstance().released();
    }

    public boolean rg_ShiFouZhiChiDiGongHaoLaYa (android.content.Context rg_HuanJingDuiXiang12) {
        checkInit();
        return cn.com.heaton.blelibrary.ble.Ble.getInstance().isSupportBle(rg_HuanJingDuiXiang12);
    }

    public void rg_DaKaiLaYa_JingMo () {
        checkInit();
        cn.com.heaton.blelibrary.ble.Ble.getInstance().turnOnBlueToothNo();
    }

    public static interface re_ChuShiHuaChengGong { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber); }
    private re_ChuShiHuaChengGong rd_ChuShiHuaChengGong;
    private int rd_ChuShiHuaChengGong_tag;
    public void rl_DiGongHaoLaYaLei_ChuShiHuaChengGong (re_ChuShiHuaChengGong objListener, int nTagNumber) {
        synchronized (this) { rd_ChuShiHuaChengGong = objListener;  rd_ChuShiHuaChengGong_tag = nTagNumber; }
    }
    public int rg_ChuShiHuaChengGong () {
        re_ChuShiHuaChengGong objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_ChuShiHuaChengGong;  nTagNumber = rd_ChuShiHuaChengGong_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber) : 0);
    }

    public static interface re_ChuShiHuaShiBai { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, int rg_CuoWuMa3); }
    private re_ChuShiHuaShiBai rd_ChuShiHuaShiBai;
    private int rd_ChuShiHuaShiBai_tag;
    public void rl_DiGongHaoLaYaLei_ChuShiHuaShiBai (re_ChuShiHuaShiBai objListener, int nTagNumber) {
        synchronized (this) { rd_ChuShiHuaShiBai = objListener;  rd_ChuShiHuaShiBai_tag = nTagNumber; }
    }
    public int rg_ChuShiHuaShiBai (int rg_CuoWuMa3) {
        re_ChuShiHuaShiBai objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_ChuShiHuaShiBai;  nTagNumber = rd_ChuShiHuaShiBai_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_CuoWuMa3) : 0);
    }

    public static interface re_LaYaKaiGuanZhuangTaiGaiBian { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, boolean rg_KaiQiZhuangTai1); }
    private re_LaYaKaiGuanZhuangTaiGaiBian rd_LaYaKaiGuanZhuangTaiGaiBian;
    private int rd_LaYaKaiGuanZhuangTaiGaiBian_tag;
    public void rl_DiGongHaoLaYaLei_LaYaKaiGuanZhuangTaiGaiBian (re_LaYaKaiGuanZhuangTaiGaiBian objListener, int nTagNumber) {
        synchronized (this) { rd_LaYaKaiGuanZhuangTaiGaiBian = objListener;  rd_LaYaKaiGuanZhuangTaiGaiBian_tag = nTagNumber; }
    }
    public int rg_LaYaKaiGuanZhuangTaiGaiBian (boolean rg_KaiQiZhuangTai1) {
        re_LaYaKaiGuanZhuangTaiGaiBian objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_LaYaKaiGuanZhuangTaiGaiBian;  nTagNumber = rd_LaYaKaiGuanZhuangTaiGaiBian_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_KaiQiZhuangTai1) : 0);
    }

    public static interface re_SaoMiaoYiKaiShi { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber); }
    private re_SaoMiaoYiKaiShi rd_SaoMiaoYiKaiShi;
    private int rd_SaoMiaoYiKaiShi_tag;
    public void rl_DiGongHaoLaYaLei_SaoMiaoYiKaiShi (re_SaoMiaoYiKaiShi objListener, int nTagNumber) {
        synchronized (this) { rd_SaoMiaoYiKaiShi = objListener;  rd_SaoMiaoYiKaiShi_tag = nTagNumber; }
    }
    public int rg_SaoMiaoYiKaiShi () {
        re_SaoMiaoYiKaiShi objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_SaoMiaoYiKaiShi;  nTagNumber = rd_SaoMiaoYiKaiShi_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber) : 0);
    }

    public static interface re_SaoMiaoYiTingZhi { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber); }
    private re_SaoMiaoYiTingZhi rd_SaoMiaoYiTingZhi;
    private int rd_SaoMiaoYiTingZhi_tag;
    public void rl_DiGongHaoLaYaLei_SaoMiaoYiTingZhi (re_SaoMiaoYiTingZhi objListener, int nTagNumber) {
        synchronized (this) { rd_SaoMiaoYiTingZhi = objListener;  rd_SaoMiaoYiTingZhi_tag = nTagNumber; }
    }
    public int rg_SaoMiaoYiTingZhi () {
        re_SaoMiaoYiTingZhi objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_SaoMiaoYiTingZhi;  nTagNumber = rd_SaoMiaoYiTingZhi_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber) : 0);
    }

    public static interface re_SaoMiaoShiBai { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, int rg_CuoWuMa4); }
    private re_SaoMiaoShiBai rd_SaoMiaoShiBai;
    private int rd_SaoMiaoShiBai_tag;
    public void rl_DiGongHaoLaYaLei_SaoMiaoShiBai (re_SaoMiaoShiBai objListener, int nTagNumber) {
        synchronized (this) { rd_SaoMiaoShiBai = objListener;  rd_SaoMiaoShiBai_tag = nTagNumber; }
    }
    public int rg_SaoMiaoShiBai (int rg_CuoWuMa4) {
        re_SaoMiaoShiBai objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_SaoMiaoShiBai;  nTagNumber = rd_SaoMiaoShiBai_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_CuoWuMa4) : 0);
    }

    public static interface re_FaXianXinSheBei { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_device33, int rg_XinHaoJiangDu2, byte [] rg_SaoMiaoXinXi1); }
    private re_FaXianXinSheBei rd_FaXianXinSheBei;
    private int rd_FaXianXinSheBei_tag;
    public void rl_DiGongHaoLaYaLei_FaXianXinSheBei (re_FaXianXinSheBei objListener, int nTagNumber) {
        synchronized (this) { rd_FaXianXinSheBei = objListener;  rd_FaXianXinSheBei_tag = nTagNumber; }
    }
    public int rg_FaXianXinSheBei (cn.com.heaton.blelibrary.ble.model.BleDevice rg_device33, int rg_XinHaoJiangDu2, byte [] rg_SaoMiaoXinXi1) {
        re_FaXianXinSheBei objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_FaXianXinSheBei;  nTagNumber = rd_FaXianXinSheBei_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_device33, rg_XinHaoJiangDu2, rg_SaoMiaoXinXi1) : 0);
    }

    public static interface re_YiJieXiAnBoBao { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_device34, cn.com.heaton.blelibrary.ble.model.ScanRecord rg_JieXiXinXi); }
    private re_YiJieXiAnBoBao rd_YiJieXiAnBoBao;
    private int rd_YiJieXiAnBoBao_tag;
    public void rl_DiGongHaoLaYaLei_YiJieXiAnBoBao (re_YiJieXiAnBoBao objListener, int nTagNumber) {
        synchronized (this) { rd_YiJieXiAnBoBao = objListener;  rd_YiJieXiAnBoBao_tag = nTagNumber; }
    }
    public int rg_YiJieXiAnBoBao (cn.com.heaton.blelibrary.ble.model.BleDevice rg_device34, cn.com.heaton.blelibrary.ble.model.ScanRecord rg_JieXiXinXi) {
        re_YiJieXiAnBoBao objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_YiJieXiAnBoBao;  nTagNumber = rd_YiJieXiAnBoBao_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_device34, rg_JieXiXinXi) : 0);
    }

    public static interface re_SheBeiLianJieZhuangTaiGaiBian { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei7); }
    private re_SheBeiLianJieZhuangTaiGaiBian rd_SheBeiLianJieZhuangTaiGaiBian;
    private int rd_SheBeiLianJieZhuangTaiGaiBian_tag;
    public void rl_DiGongHaoLaYaLei_SheBeiLianJieZhuangTaiGaiBian (re_SheBeiLianJieZhuangTaiGaiBian objListener, int nTagNumber) {
        synchronized (this) { rd_SheBeiLianJieZhuangTaiGaiBian = objListener;  rd_SheBeiLianJieZhuangTaiGaiBian_tag = nTagNumber; }
    }
    public int rg_SheBeiLianJieZhuangTaiGaiBian (cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei7) {
        re_SheBeiLianJieZhuangTaiGaiBian objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_SheBeiLianJieZhuangTaiGaiBian;  nTagNumber = rd_SheBeiLianJieZhuangTaiGaiBian_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_MuBiaoSheBei7) : 0);
    }

    public static interface re_SheBeiLianJieYiQuXiao { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei8); }
    private re_SheBeiLianJieYiQuXiao rd_SheBeiLianJieYiQuXiao;
    private int rd_SheBeiLianJieYiQuXiao_tag;
    public void rl_DiGongHaoLaYaLei_SheBeiLianJieYiQuXiao (re_SheBeiLianJieYiQuXiao objListener, int nTagNumber) {
        synchronized (this) { rd_SheBeiLianJieYiQuXiao = objListener;  rd_SheBeiLianJieYiQuXiao_tag = nTagNumber; }
    }
    public int rg_SheBeiLianJieYiQuXiao (cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei8) {
        re_SheBeiLianJieYiQuXiao objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_SheBeiLianJieYiQuXiao;  nTagNumber = rd_SheBeiLianJieYiQuXiao_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_MuBiaoSheBei8) : 0);
    }

    public static interface re_SheBeiYiLianJieChengGong { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei9); }
    private re_SheBeiYiLianJieChengGong rd_SheBeiYiLianJieChengGong;
    private int rd_SheBeiYiLianJieChengGong_tag;
    public void rl_DiGongHaoLaYaLei_SheBeiYiLianJieChengGong (re_SheBeiYiLianJieChengGong objListener, int nTagNumber) {
        synchronized (this) { rd_SheBeiYiLianJieChengGong = objListener;  rd_SheBeiYiLianJieChengGong_tag = nTagNumber; }
    }
    public int rg_SheBeiYiLianJieChengGong (cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei9) {
        re_SheBeiYiLianJieChengGong objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_SheBeiYiLianJieChengGong;  nTagNumber = rd_SheBeiYiLianJieChengGong_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_MuBiaoSheBei9) : 0);
    }

    public static interface re_FaXianZhiDingFuWu { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei10, android.bluetooth.BluetoothGatt rg_GATTDuiXiang13); }
    private re_FaXianZhiDingFuWu rd_FaXianZhiDingFuWu;
    private int rd_FaXianZhiDingFuWu_tag;
    public void rl_DiGongHaoLaYaLei_FaXianZhiDingFuWu (re_FaXianZhiDingFuWu objListener, int nTagNumber) {
        synchronized (this) { rd_FaXianZhiDingFuWu = objListener;  rd_FaXianZhiDingFuWu_tag = nTagNumber; }
    }
    public int rg_FaXianZhiDingFuWu (cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei10, android.bluetooth.BluetoothGatt rg_GATTDuiXiang13) {
        re_FaXianZhiDingFuWu objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_FaXianZhiDingFuWu;  nTagNumber = rd_FaXianZhiDingFuWu_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_MuBiaoSheBei10, rg_GATTDuiXiang13) : 0);
    }

    public static interface re_SheBeiLianJieYiChang { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei11, int rg_CuoWuMa5); }
    private re_SheBeiLianJieYiChang rd_SheBeiLianJieYiChang;
    private int rd_SheBeiLianJieYiChang_tag;
    public void rl_DiGongHaoLaYaLei_SheBeiLianJieYiChang (re_SheBeiLianJieYiChang objListener, int nTagNumber) {
        synchronized (this) { rd_SheBeiLianJieYiChang = objListener;  rd_SheBeiLianJieYiChang_tag = nTagNumber; }
    }
    public int rg_SheBeiLianJieYiChang (cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei11, int rg_CuoWuMa5) {
        re_SheBeiLianJieYiChang objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_SheBeiLianJieYiChang;  nTagNumber = rd_SheBeiLianJieYiChang_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_MuBiaoSheBei11, rg_CuoWuMa5) : 0);
    }

    public static interface re_SheBeiLianJieChaoShi { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei12); }
    private re_SheBeiLianJieChaoShi rd_SheBeiLianJieChaoShi;
    private int rd_SheBeiLianJieChaoShi_tag;
    public void rl_DiGongHaoLaYaLei_SheBeiLianJieChaoShi (re_SheBeiLianJieChaoShi objListener, int nTagNumber) {
        synchronized (this) { rd_SheBeiLianJieChaoShi = objListener;  rd_SheBeiLianJieChaoShi_tag = nTagNumber; }
    }
    public int rg_SheBeiLianJieChaoShi (cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei12) {
        re_SheBeiLianJieChaoShi objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_SheBeiLianJieChaoShi;  nTagNumber = rd_SheBeiLianJieChaoShi_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_MuBiaoSheBei12) : 0);
    }

    public static interface re_SheBeiTongZhiYiGaiBian { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei13, android.bluetooth.BluetoothGattCharacteristic rg_TeZheng7); }
    private re_SheBeiTongZhiYiGaiBian rd_SheBeiTongZhiYiGaiBian;
    private int rd_SheBeiTongZhiYiGaiBian_tag;
    public void rl_DiGongHaoLaYaLei_SheBeiTongZhiYiGaiBian (re_SheBeiTongZhiYiGaiBian objListener, int nTagNumber) {
        synchronized (this) { rd_SheBeiTongZhiYiGaiBian = objListener;  rd_SheBeiTongZhiYiGaiBian_tag = nTagNumber; }
    }
    public int rg_SheBeiTongZhiYiGaiBian (cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei13, android.bluetooth.BluetoothGattCharacteristic rg_TeZheng7) {
        re_SheBeiTongZhiYiGaiBian objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_SheBeiTongZhiYiGaiBian;  nTagNumber = rd_SheBeiTongZhiYiGaiBian_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_MuBiaoSheBei13, rg_TeZheng7) : 0);
    }

    public static interface re_SheBeiTongZhiYiQuXiao { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei14); }
    private re_SheBeiTongZhiYiQuXiao rd_SheBeiTongZhiYiQuXiao;
    private int rd_SheBeiTongZhiYiQuXiao_tag;
    public void rl_DiGongHaoLaYaLei_SheBeiTongZhiYiQuXiao (re_SheBeiTongZhiYiQuXiao objListener, int nTagNumber) {
        synchronized (this) { rd_SheBeiTongZhiYiQuXiao = objListener;  rd_SheBeiTongZhiYiQuXiao_tag = nTagNumber; }
    }
    public int rg_SheBeiTongZhiYiQuXiao (cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei14) {
        re_SheBeiTongZhiYiQuXiao objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_SheBeiTongZhiYiQuXiao;  nTagNumber = rd_SheBeiTongZhiYiQuXiao_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_MuBiaoSheBei14) : 0);
    }

    public static interface re_SheBeiTongZhiYiChengGong { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei15); }
    private re_SheBeiTongZhiYiChengGong rd_SheBeiTongZhiYiChengGong;
    private int rd_SheBeiTongZhiYiChengGong_tag;
    public void rl_DiGongHaoLaYaLei_SheBeiTongZhiYiChengGong (re_SheBeiTongZhiYiChengGong objListener, int nTagNumber) {
        synchronized (this) { rd_SheBeiTongZhiYiChengGong = objListener;  rd_SheBeiTongZhiYiChengGong_tag = nTagNumber; }
    }
    public int rg_SheBeiTongZhiYiChengGong (cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei15) {
        re_SheBeiTongZhiYiChengGong objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_SheBeiTongZhiYiChengGong;  nTagNumber = rd_SheBeiTongZhiYiChengGong_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_MuBiaoSheBei15) : 0);
    }

    public static interface re_XieSheBeiShuJuChengGong { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei19, android.bluetooth.BluetoothGattCharacteristic rg_TeZheng9); }
    private re_XieSheBeiShuJuChengGong rd_XieSheBeiShuJuChengGong;
    private int rd_XieSheBeiShuJuChengGong_tag;
    public void rl_DiGongHaoLaYaLei_XieSheBeiShuJuChengGong (re_XieSheBeiShuJuChengGong objListener, int nTagNumber) {
        synchronized (this) { rd_XieSheBeiShuJuChengGong = objListener;  rd_XieSheBeiShuJuChengGong_tag = nTagNumber; }
    }
    public int rg_XieSheBeiShuJuChengGong (cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei19, android.bluetooth.BluetoothGattCharacteristic rg_TeZheng9) {
        re_XieSheBeiShuJuChengGong objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_XieSheBeiShuJuChengGong;  nTagNumber = rd_XieSheBeiShuJuChengGong_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_MuBiaoSheBei19, rg_TeZheng9) : 0);
    }

    public static interface re_XieSheBeiShuJuShiBai { int dispatch (rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei21, int rg_CuoWuMa7); }
    private re_XieSheBeiShuJuShiBai rd_XieSheBeiShuJuShiBai;
    private int rd_XieSheBeiShuJuShiBai_tag;
    public void rl_DiGongHaoLaYaLei_XieSheBeiShuJuShiBai (re_XieSheBeiShuJuShiBai objListener, int nTagNumber) {
        synchronized (this) { rd_XieSheBeiShuJuShiBai = objListener;  rd_XieSheBeiShuJuShiBai_tag = nTagNumber; }
    }
    public int rg_XieSheBeiShuJuShiBai (cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei21, int rg_CuoWuMa7) {
        re_XieSheBeiShuJuShiBai objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_XieSheBeiShuJuShiBai;  nTagNumber = rd_XieSheBeiShuJuShiBai_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_MuBiaoSheBei21, rg_CuoWuMa7) : 0);
    }
}

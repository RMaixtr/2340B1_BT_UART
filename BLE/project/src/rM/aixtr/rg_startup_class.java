
package rM.aixtr;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class rg_startup_class extends volcano.android.base.rg_win {

    public rg_startup_class ()  { }

    protected volcano.android.base.rg_XianXingBuJuQi2 rg_XianXingBuJuQi;
    protected volcano.android.base.rg_button1 rg_button;
    protected volcano.android.base.rg_JianChanLieBiaoKuang1 rg_JianChanLieBiaoKuang;
    protected volcano.android.base.rg_edit_box rg_edit_box_RiZhi;
    protected volcano.android.base.rg_edit_box rg_edit_box_FaSong;
    protected volcano.android.base.rg_XianXingBuJuQi2 rg_XianXingBuJuQi1;
    protected volcano.android.base.rg_button1 rg_button_FaSong;
    protected volcano.android.base.rg_button1 rg_button_ChuanShu;
    protected volcano.android.base.rg_button1 rg_button_YunHang;
    protected volcano.android.base.rg_button1 rg_button_ZanTing;
    protected volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaYaDuiXiang = new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei ();
    protected volcano.android.base.rg_YunHangShiQuanXianGongJuLei rg_QuanXianGongJu = new volcano.android.base.rg_YunHangShiQuanXianGongJuLei ();
    protected java.util.ArrayList<cn.com.heaton.blelibrary.ble.model.BleDevice> rg_LaYaSheBeiLieBiao = new java.util.ArrayList<cn.com.heaton.blelibrary.ble.model.BleDevice> ();
    protected cn.com.heaton.blelibrary.ble.model.BleDevice rg_DangQianXuanZhongSheBei;
    protected volcano.android.base.rg_JinDuDuiHuaKuangLei rg_JinDuDuiHuaKuang;
    protected String [] rg_FenGe;
    protected static final String rg_SERVICE_UUID = "0000fff0-0000-1000-8000-00805f9b34fb";
    protected static final String rg_CHARA_UUID = "0000fff2-0000-1000-8000-00805f9b34fb";
    protected static final String rg_CHARA_UUID1 = "0000fff1-0000-1000-8000-00805f9b34fb";

    {
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_ChuShiHuaChengGong (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_ChuShiHuaChengGong () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber) {
                return rg_DiGongHaoLaYaLei_ChuShiHuaChengGong (objSource, nTagNumber);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_ChuShiHuaShiBai (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_ChuShiHuaShiBai () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber, int rg_CuoWuMa3) {
                return rg_DiGongHaoLaYaLei_ChuShiHuaShiBai (objSource, nTagNumber, rg_CuoWuMa3);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_SaoMiaoYiKaiShi (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_SaoMiaoYiKaiShi () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber) {
                return rg_DiGongHaoLaYaLei_SaoMiaoYiKaiShi (objSource, nTagNumber);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_SaoMiaoYiTingZhi (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_SaoMiaoYiTingZhi () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber) {
                return rg_DiGongHaoLaYaLei_SaoMiaoYiTingZhi (objSource, nTagNumber);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_SaoMiaoShiBai (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_SaoMiaoShiBai () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber, int rg_CuoWuMa4) {
                return rg_DiGongHaoLaYaLei_SaoMiaoShiBai (objSource, nTagNumber, rg_CuoWuMa4);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_LaYaKaiGuanZhuangTaiGaiBian (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_LaYaKaiGuanZhuangTaiGaiBian () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber, boolean rg_KaiQiZhuangTai1) {
                return rg_DiGongHaoLaYaLei_LaYaKaiGuanZhuangTaiGaiBian (objSource, nTagNumber, rg_KaiQiZhuangTai1);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_FaXianXinSheBei (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_FaXianXinSheBei () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_device33, int rg_XinHaoJiangDu2, byte [] rg_SaoMiaoXinXi1) {
                return rg_DiGongHaoLaYaLei_FaXianXinSheBei (objSource, nTagNumber, rg_device33, rg_XinHaoJiangDu2, rg_SaoMiaoXinXi1);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_SheBeiYiLianJieChengGong (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_SheBeiYiLianJieChengGong () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei9) {
                return rg_DiGongHaoLaYaLei_SheBeiYiLianJieChengGong (objSource, nTagNumber, rg_MuBiaoSheBei9);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_SheBeiLianJieZhuangTaiGaiBian (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_SheBeiLianJieZhuangTaiGaiBian () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei7) {
                return rg_DiGongHaoLaYaLei_SheBeiLianJieZhuangTaiGaiBian (objSource, nTagNumber, rg_MuBiaoSheBei7);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_SheBeiLianJieYiQuXiao (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_SheBeiLianJieYiQuXiao () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei8) {
                return rg_DiGongHaoLaYaLei_SheBeiLianJieYiQuXiao (objSource, nTagNumber, rg_MuBiaoSheBei8);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_SheBeiLianJieChaoShi (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_SheBeiLianJieChaoShi () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei12) {
                return rg_DiGongHaoLaYaLei_SheBeiLianJieChaoShi (objSource, nTagNumber, rg_MuBiaoSheBei12);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_SheBeiLianJieYiChang (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_SheBeiLianJieYiChang () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei11, int rg_CuoWuMa5) {
                return rg_DiGongHaoLaYaLei_SheBeiLianJieYiChang (objSource, nTagNumber, rg_MuBiaoSheBei11, rg_CuoWuMa5);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_FaXianZhiDingFuWu (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_FaXianZhiDingFuWu () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei10, android.bluetooth.BluetoothGatt rg_GATTDuiXiang13) {
                return rg_DiGongHaoLaYaLei_FaXianZhiDingFuWu (objSource, nTagNumber, rg_MuBiaoSheBei10, rg_GATTDuiXiang13);
            }
        }, 0);
        rg_LaYaDuiXiang.rl_DiGongHaoLaYaLei_SheBeiTongZhiYiGaiBian (new volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei.re_SheBeiTongZhiYiGaiBian () {
            public int dispatch (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei objSource, int nTagNumber, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei13, android.bluetooth.BluetoothGattCharacteristic rg_TeZheng7) {
                return rg_DiGongHaoLaYaLei_SheBeiTongZhiYiGaiBian (objSource, nTagNumber, rg_MuBiaoSheBei13, rg_TeZheng7);
            }
        }, 0);
        rg_QuanXianGongJu.rl_YunHangShiQuanXianGongJuLei_YiShouYuQuanXian (new volcano.android.base.rg_YunHangShiQuanXianGongJuLei.re_YiShouYuQuanXian () {
            public int dispatch (volcano.android.base.rg_YunHangShiQuanXianGongJuLei objSource, int nTagNumber) {
                return rg_YunHangShiQuanXianGongJuLei_YiShouYuQuanXian (objSource, nTagNumber);
            }
        }, 0);
        rg_QuanXianGongJu.rl_YunHangShiQuanXianGongJuLei_YiJuJueQuanXian (new volcano.android.base.rg_YunHangShiQuanXianGongJuLei.re_YiJuJueQuanXian () {
            public int dispatch (volcano.android.base.rg_YunHangShiQuanXianGongJuLei objSource, int nTagNumber, java.util.List<String> rg_BeiJuQuanXian1) {
                return rg_YunHangShiQuanXianGongJuLei_YiJuJueQuanXian (objSource, nTagNumber, rg_BeiJuQuanXian1);
            }
        }, 0);
    }

    protected volcano.android.base.rg_XianXingBuJuQi2 rp_1;
    @Override public volcano.android.base.AndroidViewGroup GetAndroidActivityContainer () {
        return rp_1;
    }

    @Override
    protected boolean onInitAndroidActivity () {
        if (super.onInitAndroidActivity () == false)
            return false;

        setContentView (R.layout.rg_startup_class);
        rp_1 = new volcano.android.base.rg_XianXingBuJuQi2 (this, (LinearLayout)findViewById (R.id.rg_startup_class));
        rp_1.onInitControlContent (this, null);

        rg_XianXingBuJuQi = new volcano.android.base.rg_XianXingBuJuQi2 (this, (LinearLayout)findViewById (R.id.rg_xianxingbujuqi));
        rg_XianXingBuJuQi.onInitControlContent (this, null);
        rg_XianXingBuJuQi.rg_KeShi2 (volcano.android.base.rg_ZuJianKeShiZhuangTai.rg_WanQuanYinCang);
        rg_button = new volcano.android.base.rg_button1 (this, (Button)findViewById (R.id.rg_button));
        rg_button.onInitControlContent (this, null);
        rg_button.rl_AndroidView_clicked (new volcano.android.base.AndroidView.re_clicked () {
            public int dispatch (volcano.android.base.AndroidView objSource, int nTagNumber) {
                return rg_button_clicked ((volcano.android.base.rg_button1)objSource, nTagNumber);
            }
        }, 0);
        rg_JianChanLieBiaoKuang = new volcano.android.base.rg_JianChanLieBiaoKuang1 (this, (volcano.android.base.rg_AnZhuoZiDingYiLieBiao)findViewById (R.id.rg_jianchanliebiaokuang));
        rg_JianChanLieBiaoKuang.onInitControlContent (this, null);
        rg_JianChanLieBiaoKuang.rl_KuoPeiQiZuJianJiChuLei_XiangMuBeiChanJi (new volcano.android.base.rg_KuoPeiQiZuJianJiChuLei.re_XiangMuBeiChanJi () {
            public int dispatch (volcano.android.base.rg_KuoPeiQiZuJianJiChuLei objSource, int nTagNumber, int rg_BeiChanJiXiangMuSuoYin1) {
                return rg_JianChanLieBiaoKuang_XiangMuBeiChanJi ((volcano.android.base.rg_JianChanLieBiaoKuang1)objSource, nTagNumber, rg_BeiChanJiXiangMuSuoYin1);
            }
        }, 0);
        rg_edit_box_RiZhi = new volcano.android.base.rg_edit_box (this, (EditText)findViewById (R.id.rg_edit_box_rizhi));
        rg_edit_box_RiZhi.onInitControlContent (this, null);
        rg_edit_box_FaSong = new volcano.android.base.rg_edit_box (this, (EditText)findViewById (R.id.rg_edit_box_fasong));
        rg_edit_box_FaSong.onInitControlContent (this, null);
        rg_XianXingBuJuQi1 = new volcano.android.base.rg_XianXingBuJuQi2 (this, (LinearLayout)findViewById (R.id.rg_xianxingbujuqi1));
        rg_XianXingBuJuQi1.onInitControlContent (this, null);
        rg_button_FaSong = new volcano.android.base.rg_button1 (this, (Button)findViewById (R.id.rg_button_fasong));
        rg_button_FaSong.onInitControlContent (this, null);
        rg_button_FaSong.rl_AndroidView_clicked (new volcano.android.base.AndroidView.re_clicked () {
            public int dispatch (volcano.android.base.AndroidView objSource, int nTagNumber) {
                return rg_button_clicked ((volcano.android.base.rg_button1)objSource, nTagNumber);
            }
        }, 0);
        rg_button_ChuanShu = new volcano.android.base.rg_button1 (this, (Button)findViewById (R.id.rg_button_chuanshu));
        rg_button_ChuanShu.onInitControlContent (this, null);
        rg_button_ChuanShu.rl_AndroidView_clicked (new volcano.android.base.AndroidView.re_clicked () {
            public int dispatch (volcano.android.base.AndroidView objSource, int nTagNumber) {
                return rg_button_clicked ((volcano.android.base.rg_button1)objSource, nTagNumber);
            }
        }, 0);
        rg_button_YunHang = new volcano.android.base.rg_button1 (this, (Button)findViewById (R.id.rg_button_yunhang));
        rg_button_YunHang.onInitControlContent (this, null);
        rg_button_YunHang.rl_AndroidView_clicked (new volcano.android.base.AndroidView.re_clicked () {
            public int dispatch (volcano.android.base.AndroidView objSource, int nTagNumber) {
                return rg_button_clicked ((volcano.android.base.rg_button1)objSource, nTagNumber);
            }
        }, 0);
        rg_button_ZanTing = new volcano.android.base.rg_button1 (this, (Button)findViewById (R.id.rg_button_zanting));
        rg_button_ZanTing.onInitControlContent (this, null);
        rg_button_ZanTing.rl_AndroidView_clicked (new volcano.android.base.AndroidView.re_clicked () {
            public int dispatch (volcano.android.base.AndroidView objSource, int nTagNumber) {
                return rg_button_clicked ((volcano.android.base.rg_button1)objSource, nTagNumber);
            }
        }, 0);
        return true;
    }

    public void rg_TongZhi_BeiChuangJian (android.content.Intent rg_QiDongXinXiDuiXiang, java.lang.Object [] rg_ZaiRuCanShu, int rg_CanShuShuMu) {
        super.rg_TongZhi_BeiChuangJian (rg_QiDongXinXiDuiXiang, rg_ZaiRuCanShu, rg_CanShuShuMu);
        volcano.android.base.AndComActivity.rg_BiaoTi1 (this, "低功耗(BLE)蓝牙应用");
        rg_JinDuDuiHuaKuang = volcano.android.base.rg_JinDuDuiHuaKuangLei.rg_ChuangJian12 (this);
        rg_JinDuDuiHuaKuang.rg_NeiRong7 ("正在连接设备...");
        rg_JinDuDuiHuaKuang.rg_JinDuYangShi1 (0);
        rg_edit_box_FaSong.rg_ChanHangMoShi (true);
        rg_edit_box_RiZhi.rg_ZuiDaXianShiHangShu (10);
        if (this.checkPermission(volcano.android.base.rg_AnZhuoQuanXian.rg_HuoQuJingQueWeiZhi, android.os.Process.myPid(), android.os.Process.myUid()) == 0)
        {
            rg_LaYaDuiXiang.rg_ChuShiHua1 ();
        }
        else
        {
            if (android.os.Build.VERSION.SDK_INT >= 23)
            {
                rg_QuanXianGongJu.builder.setPermissions(volcano.android.base.rg_AnZhuoQuanXian.rg_HuoQuJingQueWeiZhi);
                rg_QuanXianGongJu.builder.setScreenOrientation(true ? android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT : android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE).check();
            }
            else
            {
                rg_LaYaDuiXiang.rg_ChuShiHua1 ();
            }
        }
    }

    protected int rg_DiGongHaoLaYaLei_ChuShiHuaChengGong (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang, int rg_BiaoJiZhi) {
        if (rg_LaYaDuiXiang.rg_ShiFouZhiChiDiGongHaoLaYa (this))
        {
            if (rg_LaYaDuiXiang.rg_LaYaKaiQiZhuangTai () == false)
            {
                volcano.android.base.rg_DiShiDuiHuaKuangLei rg_KaiQiDiShi;
                rg_KaiQiDiShi = volcano.android.base.rg_DiShiDuiHuaKuangLei.rg_ChuangJianDiShiDuiHuaKuang (this);
                rg_KaiQiDiShi.rg_BiaoJi21 = "0";
                rg_KaiQiDiShi.rg_NeiRong7 ("未开启蓝牙,是否立即开启？");
                rg_KaiQiDiShi.rg_TuiHuiJianGuanBi (false);
                rg_KaiQiDiShi.rg_JieWaiChuMoGuanBi1 (false);
                rg_KaiQiDiShi.rg_ZhiAnNiu ("确定", volcano.android.base.rg_DuiHuaKuangAnNiuLeiXing.rg_QueRenAnNiu);
                rg_KaiQiDiShi.rg_ZhiAnNiu ("退出", volcano.android.base.rg_DuiHuaKuangAnNiuLeiXing.rg_QuXiaoAnNiu);
                rg_KaiQiDiShi.rg_XianShi ();
                rg_KaiQiDiShi.rl_DiShiDuiHuaKuangLei_AnNiuBeiChanJi1 (new volcano.android.base.rg_DiShiDuiHuaKuangLei.re_AnNiuBeiChanJi1 () {
                    public int dispatch (volcano.android.base.rg_DiShiDuiHuaKuangLei objSource, int nTagNumber, int rg_AnNiuLeiXing4) {
                        return rg_DiShiDuiHuaKuangLei_AnNiuBeiChanJi (objSource, nTagNumber, rg_AnNiuLeiXing4);
                    }
                }, 0);
            }
            else
            {
                rg_XianXingBuJuQi.rg_KeShi2 (volcano.android.base.rg_ZuJianKeShiZhuangTai.rg_KeShi);
                volcano.android.base.rg_YingYongChengXu.rg_DiShiKuang ("蓝牙已开启!", false);
                rg_JianChanLieBiaoKuang.rg_KuoPeiQi ().rg_ShanChuSuoYouXiangMu (true);
                rg_LaYaSheBeiLieBiao = new java.util.ArrayList<cn.com.heaton.blelibrary.ble.model.BleDevice> ();
                java.util.List<cn.com.heaton.blelibrary.ble.model.BleDevice> rg_YiLianJieSheBei;
                rg_YiLianJieSheBei = rg_LaYaDuiXiang.rg_YiLianJieSheBei1 ();
                if (rg_YiLianJieSheBei != null && rg_YiLianJieSheBei.size () > 0)
                {
                    int rg_i = 0;
                    for (rg_i = 0; rg_i < rg_YiLianJieSheBei.size (); rg_i++)
                    {
                        rg_JianChanLieBiaoKuang.rg_KuoPeiQi ().rg_ChaRuLieBiaoXiang (rg_YiLianJieSheBei.get (rg_i).getBleName(), null, 0, false, rg_YiLianJieSheBei.get (rg_i).getBleAddress(), 0, false, -1, null, true);
                        volcano.android.device.dghlycz.rg_DiGongHaoLaYaSheBeiJiGeLei.AddCollectionElements (rg_LaYaSheBeiLieBiao,rg_YiLianJieSheBei.get (rg_i));
                    }
                }
            }
        }
        else
        {
            volcano.android.base.rg_DiShiDuiHuaKuangLei rg_CuoWuDiShi;
            rg_CuoWuDiShi = volcano.android.base.rg_DiShiDuiHuaKuangLei.rg_ChuangJianDiShiDuiHuaKuang (this);
            rg_CuoWuDiShi.rg_BiaoJi21 = "1";
            rg_CuoWuDiShi.rg_NeiRong7 ("当前设备不支持BLE蓝牙！");
            rg_CuoWuDiShi.rg_TuiHuiJianGuanBi (false);
            rg_CuoWuDiShi.rg_JieWaiChuMoGuanBi1 (false);
            rg_CuoWuDiShi.rg_ZhiAnNiu ("退出", volcano.android.base.rg_DuiHuaKuangAnNiuLeiXing.rg_QueRenAnNiu);
            rg_CuoWuDiShi.rg_XianShi ();
            rg_CuoWuDiShi.rl_DiShiDuiHuaKuangLei_AnNiuBeiChanJi1 (new volcano.android.base.rg_DiShiDuiHuaKuangLei.re_AnNiuBeiChanJi1 () {
                public int dispatch (volcano.android.base.rg_DiShiDuiHuaKuangLei objSource, int nTagNumber, int rg_AnNiuLeiXing4) {
                    return rg_DiShiDuiHuaKuangLei_AnNiuBeiChanJi (objSource, nTagNumber, rg_AnNiuLeiXing4);
                }
            }, 0);
        }
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_ChuShiHuaShiBai (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang1, int rg_BiaoJiZhi1, int rg_CuoWuMa) {
        if (rg_CuoWuMa == 2001)
        {
        }
        else
        {
            volcano.android.base.AndComActivity.rg_CuoWuXinXiKuang (this, "蓝牙初始化失败,可能当前设备不支持BLE蓝牙\n错误码 " + String.valueOf (rg_CuoWuMa));
        }
        return (0);
    }

    protected int rg_DiShiDuiHuaKuangLei_AnNiuBeiChanJi (volcano.android.base.rg_DiShiDuiHuaKuangLei rg_LaiYuanDuiXiang2, int rg_BiaoJiZhi2, int rg_AnNiuLeiXing) {
        if (volcano.Java.base.rg_WenBenXingLei.rg_WenBenXiangDeng (rg_LaiYuanDuiXiang2.rg_BiaoJi21, "0", false))
        {
            if (rg_AnNiuLeiXing == volcano.android.base.rg_DuiHuaKuangAnNiuLeiXing.rg_QueRenAnNiu)
            {
                rg_LaYaDuiXiang.rg_DaKaiLaYa_JingMo ();
            }
            else if (rg_AnNiuLeiXing == volcano.android.base.rg_DuiHuaKuangAnNiuLeiXing.rg_QuXiaoAnNiu)
            {
                volcano.android.base.AndComActivity.rg_GuanBi (this);
            }
        }
        else if (volcano.Java.base.rg_WenBenXingLei.rg_WenBenXiangDeng (rg_LaiYuanDuiXiang2.rg_BiaoJi21, "1", false))
        {
            if (rg_AnNiuLeiXing == volcano.android.base.rg_DuiHuaKuangAnNiuLeiXing.rg_QueRenAnNiu)
            {
                volcano.android.base.AndComActivity.rg_GuanBi (this);
            }
        }
        else if (volcano.Java.base.rg_WenBenXingLei.rg_WenBenXiangDeng (rg_LaiYuanDuiXiang2.rg_BiaoJi21, "2", false))
        {
            if (rg_AnNiuLeiXing == volcano.android.base.rg_DuiHuaKuangAnNiuLeiXing.rg_QueRenAnNiu)
            {
                if (rg_DangQianXuanZhongSheBei != null)
                {
                    rg_JinDuDuiHuaKuang.rg_XianShi ();
                    rg_LaYaDuiXiang.rg_LianJieSheBeiDeZhi (rg_DangQianXuanZhongSheBei.getBleAddress());
                }
            }
        }
        return (0);
    }

    protected int rg_button_clicked (volcano.android.base.rg_button1 rg_LaiYuanDuiXiang3, int rg_BiaoJiZhi3) {
        if (rg_LaiYuanDuiXiang3 == rg_button)
        {
            if (volcano.Java.base.rg_WenBenXingLei.rg_WenBenXiangDeng (String.valueOf (rg_button.GetTextView ().getText ()), "扫描设备", false))
            {
                rg_LaYaDuiXiang.rg_KaiShiSaoMiaoSheBei (0);
                rg_JianChanLieBiaoKuang.rg_KuoPeiQi ().rg_ShanChuSuoYouXiangMu (true);
                volcano.android.device.dghlycz.rg_DiGongHaoLaYaSheBeiJiGeLei.rg_ShanChuSuoYouChengYuan16 (rg_LaYaSheBeiLieBiao);
            }
            else
            {
                rg_LaYaDuiXiang.rg_TingZhiSaoMiao ();
            }
        }
        if (rg_LaiYuanDuiXiang3 == rg_button_FaSong)
        {
            volcano.android.base.rg_YingYongChengXu.rg_DiShiKuang (Integer.toHexString (rg_JiaoYanLei.rg_crc ( String.valueOf (rg_edit_box_FaSong.GetTextView ().getText ()).getBytes ())), false);
            rg_LaYaDuiXiang.rg_XieSheBeiZhiDingBiaoShiShuJu (rg_DangQianXuanZhongSheBei,  String.valueOf (rg_edit_box_FaSong.GetTextView ().getText ()).getBytes (), java.util.UUID.fromString(rg_SERVICE_UUID), java.util.UUID.fromString(rg_CHARA_UUID));
        }
        if (rg_LaiYuanDuiXiang3 == rg_button_ChuanShu)
        {
            byte [] rg_NeiRong;
            String rg_FenJuan;
            int rg_i1;
            rg_FenGe = rg_JiaoYanLei.rg_FenGe1 (String.valueOf (rg_edit_box_FaSong.GetTextView ().getText ()));
            rg_FenJuan = Integer.toHexString (rg_FenGe.length);
            rg_i1 = rg_FenJuan.length ();
            while (rg_i1 < 6)
            {
                rg_i1 = rg_i1 + 1;
                rg_FenJuan = "0" + rg_FenJuan;
            }
            rg_NeiRong = volcano.Java.base.rg_ZiJieShuZuCaoZuo.rg_GeBing9 (rg_JiaoYanLei.rg_hexStringToByteArray ("ffff"),  ("example.py" + rg_FenJuan + Integer.toHexString (rg_JiaoYanLei.rg_crc ( String.valueOf (rg_edit_box_FaSong.GetTextView ().getText ()).getBytes ()))).getBytes ());
            rg_LaYaDuiXiang.rg_XieSheBeiZhiDingBiaoShiShuJu (rg_DangQianXuanZhongSheBei, rg_NeiRong, java.util.UUID.fromString(rg_SERVICE_UUID), java.util.UUID.fromString(rg_CHARA_UUID));
        }
        if (rg_LaiYuanDuiXiang3 == rg_button_YunHang)
        {
            rg_LaYaDuiXiang.rg_XieSheBeiZhiDingBiaoShiShuJu (rg_DangQianXuanZhongSheBei, volcano.Java.base.rg_ZiJieShuZuCaoZuo.rg_GeBing9 (rg_JiaoYanLei.rg_hexStringToByteArray ("ffff10"),  "example.py".getBytes ()), java.util.UUID.fromString(rg_SERVICE_UUID), java.util.UUID.fromString(rg_CHARA_UUID));
        }
        if (rg_LaiYuanDuiXiang3 == rg_button_ZanTing)
        {
            rg_LaYaDuiXiang.rg_XieSheBeiZhiDingBiaoShiShuJu (rg_DangQianXuanZhongSheBei, rg_JiaoYanLei.rg_hexStringToByteArray ("ffff11"), java.util.UUID.fromString(rg_SERVICE_UUID), java.util.UUID.fromString(rg_CHARA_UUID));
        }
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_SaoMiaoYiKaiShi (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang4, int rg_BiaoJiZhi4) {
        rg_button.rg_NeiRong6 ("正在扫描...");
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_SaoMiaoYiTingZhi (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang5, int rg_BiaoJiZhi5) {
        rg_button.rg_NeiRong6 ("扫描设备");
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_SaoMiaoShiBai (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang6, int rg_BiaoJiZhi6, int rg_CuoWuMa1) {
        if (rg_CuoWuMa1 == 2008)
        {
            volcano.android.base.AndComActivity.rg_CuoWuXinXiKuang (this, "安卓6.0以上的设备，使用BLE蓝牙可能需要开启位置信息权限，具体原因请前往搜索引擎查看相关资料。");
        }
        else
        {
            volcano.android.base.rg_YingYongChengXu.rg_DiShiKuang ("扫描启动失败\n错误码 " + String.valueOf (rg_CuoWuMa1), false);
        }
        rg_button.rg_NeiRong6 ("扫描设备");
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_LaYaKaiGuanZhuangTaiGaiBian (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang7, int rg_BiaoJiZhi7, boolean rg_KaiQiZhuangTai) {
        if (rg_KaiQiZhuangTai)
        {
            rg_XianXingBuJuQi.rg_KeShi2 (volcano.android.base.rg_ZuJianKeShiZhuangTai.rg_KeShi);
            volcano.android.base.rg_YingYongChengXu.rg_DiShiKuang ("蓝牙已开启!", false);
        }
        else
        {
            volcano.android.base.rg_DiShiDuiHuaKuangLei rg_KaiQiDiShi1;
            rg_KaiQiDiShi1 = volcano.android.base.rg_DiShiDuiHuaKuangLei.rg_ChuangJianDiShiDuiHuaKuang (this);
            rg_KaiQiDiShi1.rg_BiaoJi21 = "0";
            rg_KaiQiDiShi1.rg_NeiRong7 ("蓝牙被关闭,是否立即开启？");
            rg_KaiQiDiShi1.rg_TuiHuiJianGuanBi (false);
            rg_KaiQiDiShi1.rg_JieWaiChuMoGuanBi1 (false);
            rg_KaiQiDiShi1.rg_ZhiAnNiu ("确定", volcano.android.base.rg_DuiHuaKuangAnNiuLeiXing.rg_QueRenAnNiu);
            rg_KaiQiDiShi1.rg_ZhiAnNiu ("退出", volcano.android.base.rg_DuiHuaKuangAnNiuLeiXing.rg_QuXiaoAnNiu);
            rg_KaiQiDiShi1.rg_XianShi ();
            rg_KaiQiDiShi1.rl_DiShiDuiHuaKuangLei_AnNiuBeiChanJi1 (new volcano.android.base.rg_DiShiDuiHuaKuangLei.re_AnNiuBeiChanJi1 () {
                public int dispatch (volcano.android.base.rg_DiShiDuiHuaKuangLei objSource, int nTagNumber, int rg_AnNiuLeiXing4) {
                    return rg_DiShiDuiHuaKuangLei_AnNiuBeiChanJi (objSource, nTagNumber, rg_AnNiuLeiXing4);
                }
            }, 0);
        }
        return (0);
    }

    public void rg_TongZhi_JiangBeiXiaoHui () {
        super.rg_TongZhi_JiangBeiXiaoHui ();
        rg_LaYaDuiXiang.rg_ShiFangSuoYouZiYuan ();
    }

    protected int rg_YunHangShiQuanXianGongJuLei_YiShouYuQuanXian (volcano.android.base.rg_YunHangShiQuanXianGongJuLei rg_LaiYuanDuiXiang8, int rg_BiaoJiZhi8) {
        rg_LaYaDuiXiang.rg_ChuShiHua1 ();
        return (0);
    }

    protected int rg_YunHangShiQuanXianGongJuLei_YiJuJueQuanXian (volcano.android.base.rg_YunHangShiQuanXianGongJuLei rg_LaiYuanDuiXiang9, int rg_BiaoJiZhi9, java.util.List<String> rg_BeiJuQuanXian) {
        volcano.android.base.AndComActivity.rg_CuoWuXinXiKuang (this, "安卓6.0以上的设备，使用BLE蓝牙可能需要开启位置信息权限，具体原因请前往搜索引擎查看相关资料。");
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_FaXianXinSheBei (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang10, int rg_BiaoJiZhi10, cn.com.heaton.blelibrary.ble.model.BleDevice rg_device, int rg_XinHaoJiangDu, byte [] rg_SaoMiaoXinXi) {
        if (volcano.Java.base.rg_WenBenXingLei.rg_WenBenShiFouWeiKong (rg_device.getBleName()) == false)
        {
            if (rg_LaYaSheBeiLieBiao != null && rg_LaYaSheBeiLieBiao.size () > 0)
            {
                int rg_i2 = 0;
                for (rg_i2 = 0; rg_i2 < rg_LaYaSheBeiLieBiao.size (); rg_i2++)
                {
                    if (volcano.Java.base.rg_WenBenXingLei.rg_WenBenXiangDeng (rg_LaYaSheBeiLieBiao.get (rg_i2).getBleAddress(), rg_device.getBleAddress(), false))
                    {
                        volcano.android.base.rg_JianChanLieBiaoXiang rg_DangQianBiaoXiang;
                        rg_DangQianBiaoXiang = rg_JianChanLieBiaoKuang.rg_KuoPeiQi ().rg_QuXiangMu2 (rg_i2);
                        rg_DangQianBiaoXiang.rg_JiaoZhuWenBen = rg_device.getBleAddress() + "\n信号:" + String.valueOf (rg_XinHaoJiangDu) + "dBm";
                        rg_JianChanLieBiaoKuang.rg_KuoPeiQi ().rg_XiuGaiXiangMu2 (rg_i2, rg_DangQianBiaoXiang, true);
                        return (0);
                    }
                }
            }
            rg_JianChanLieBiaoKuang.rg_KuoPeiQi ().rg_ChaRuLieBiaoXiang (rg_device.getBleName(), null, 0, false, rg_device.getBleAddress() + "\n信号:" + String.valueOf (rg_XinHaoJiangDu) + "dBm", 0, false, -1, null, true);
            volcano.android.device.dghlycz.rg_DiGongHaoLaYaSheBeiJiGeLei.AddCollectionElements (rg_LaYaSheBeiLieBiao,rg_device);
        }
        return (0);
    }

    protected int rg_JianChanLieBiaoKuang_XiangMuBeiChanJi (volcano.android.base.rg_JianChanLieBiaoKuang1 rg_LaiYuanDuiXiang11, int rg_BiaoJiZhi11, int rg_BeiChanJiXiangMuSuoYin) {
        if (rg_BeiChanJiXiangMuSuoYin >= rg_LaYaSheBeiLieBiao.size ())
        {
            rg_DangQianXuanZhongSheBei = null;
        }
        else
        {
            volcano.android.base.rg_DiShiDuiHuaKuangLei rg_LianJieDiShi;
            rg_LianJieDiShi = volcano.android.base.rg_DiShiDuiHuaKuangLei.rg_ChuangJianDiShiDuiHuaKuang (this);
            rg_LianJieDiShi.rg_BiaoJi21 = "2";
            rg_LianJieDiShi.rg_NeiRong7 ("是否连接到设备(地址)：" + (rg_LaYaSheBeiLieBiao.get (rg_BeiChanJiXiangMuSuoYin).getBleAddress()));
            rg_DangQianXuanZhongSheBei = rg_LaYaSheBeiLieBiao.get (rg_BeiChanJiXiangMuSuoYin);
            rg_LianJieDiShi.rg_TuiHuiJianGuanBi (false);
            rg_LianJieDiShi.rg_JieWaiChuMoGuanBi1 (false);
            rg_LianJieDiShi.rg_ZhiAnNiu ("连接", volcano.android.base.rg_DuiHuaKuangAnNiuLeiXing.rg_QueRenAnNiu);
            rg_LianJieDiShi.rg_ZhiAnNiu ("取消", volcano.android.base.rg_DuiHuaKuangAnNiuLeiXing.rg_QuXiaoAnNiu);
            rg_LianJieDiShi.rg_XianShi ();
            rg_LianJieDiShi.rl_DiShiDuiHuaKuangLei_AnNiuBeiChanJi1 (new volcano.android.base.rg_DiShiDuiHuaKuangLei.re_AnNiuBeiChanJi1 () {
                public int dispatch (volcano.android.base.rg_DiShiDuiHuaKuangLei objSource, int nTagNumber, int rg_AnNiuLeiXing4) {
                    return rg_DiShiDuiHuaKuangLei_AnNiuBeiChanJi (objSource, nTagNumber, rg_AnNiuLeiXing4);
                }
            }, 0);
        }
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_SheBeiYiLianJieChengGong (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang12, int rg_BiaoJiZhi12, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei) {
        if (rg_JinDuDuiHuaKuang.rg_ShiFouBeiXianShi1 ())
        {
            rg_JinDuDuiHuaKuang.rg_GuanBi4 ();
        }
        volcano.android.base.rg_YingYongChengXu.rg_DiShiKuang (rg_MuBiaoSheBei.getBleName() + " 已连接成功", false);
        rg_JianChanLieBiaoKuang.rg_KeShi2 (volcano.android.base.rg_ZuJianKeShiZhuangTai.rg_WanQuanYinCang);
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_SheBeiLianJieZhuangTaiGaiBian (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang13, int rg_BiaoJiZhi13, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei1) {
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_SheBeiLianJieYiQuXiao (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang14, int rg_BiaoJiZhi14, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei2) {
        if (rg_JinDuDuiHuaKuang.rg_ShiFouBeiXianShi1 ())
        {
            rg_JinDuDuiHuaKuang.rg_GuanBi4 ();
        }
        volcano.android.base.rg_YingYongChengXu.rg_DiShiKuang (rg_MuBiaoSheBei2.getBleName() + " 连接被取消", false);
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_SheBeiLianJieChaoShi (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang15, int rg_BiaoJiZhi15, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei3) {
        if (rg_JinDuDuiHuaKuang.rg_ShiFouBeiXianShi1 ())
        {
            rg_JinDuDuiHuaKuang.rg_GuanBi4 ();
        }
        volcano.android.base.AndComActivity.rg_CuoWuXinXiKuang (this, rg_MuBiaoSheBei3.getBleName() + "连接超时，请重试");
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_SheBeiLianJieYiChang (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang16, int rg_BiaoJiZhi16, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei4, int rg_CuoWuMa2) {
        if (rg_JinDuDuiHuaKuang.rg_ShiFouBeiXianShi1 ())
        {
            rg_JinDuDuiHuaKuang.rg_GuanBi4 ();
        }
        volcano.android.base.AndComActivity.rg_CuoWuXinXiKuang (this, rg_MuBiaoSheBei4.getBleName() + "连接异常\n错误码 " + String.valueOf (rg_CuoWuMa2));
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_FaXianZhiDingFuWu (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang17, int rg_BiaoJiZhi17, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei5, android.bluetooth.BluetoothGatt rg_GATTDuiXiang) {
        rg_LaYaDuiXiang.rg_ZhiSheBeiZhiDingBiaoShiTongZhi (rg_MuBiaoSheBei5, true, java.util.UUID.fromString(rg_SERVICE_UUID), java.util.UUID.fromString(rg_CHARA_UUID1));
        return (0);
    }

    protected int rg_DiGongHaoLaYaLei_SheBeiTongZhiYiGaiBian (volcano.android.device.dghlycz.rg_DiGongHaoLaYaLei rg_LaiYuanDuiXiang18, int rg_BiaoJiZhi18, cn.com.heaton.blelibrary.ble.model.BleDevice rg_MuBiaoSheBei6, android.bluetooth.BluetoothGattCharacteristic rg_TeZheng) {
        String rg_LinShiWenBen;
        byte [] rg_LinShiZiJieShuZu;
        rg_LinShiZiJieShuZu = rg_TeZheng.getValue();
        rg_LinShiWenBen = volcano.Java.base.rg_WenBenXingLei.rg_ZiJieShuZuDaoWenBen (rg_LinShiZiJieShuZu);
        rg_edit_box_RiZhi.rg_DaoWeiBuTianJiaNeiRongHang ("接收：" + rg_LinShiWenBen);
        byte [] rg_JieGuoShuZu;
        rg_JieGuoShuZu = volcano.Java.base.rg_ZiJieShuZuCaoZuo.rg_QuShuZuZuoBian1 (rg_LinShiZiJieShuZu, 2);
        if (java.util.Arrays.equals (rg_JieGuoShuZu, rg_JiaoYanLei.rg_hexStringToByteArray ("ffff")))
        {
            rg_LaYaDuiXiang.rg_XieSheBeiZhiDingBiaoShiShuJu (rg_DangQianXuanZhongSheBei, rg_JiaoYanLei.rg_hexStringToByteArray ("ffff00"), java.util.UUID.fromString(rg_SERVICE_UUID), java.util.UUID.fromString(rg_CHARA_UUID));
            volcano.Java.base.rg_XianChengJiChuLei.rg_ShuiMianDangQianXianCheng (1000);
            volcano.Java.base.rg_XianChengLei rg_XianCheng = new volcano.Java.base.rg_XianChengLei ();
            rg_XianCheng.rl_XianChengLei_XianChengQiDong (new volcano.Java.base.rg_XianChengLei.re_XianChengQiDong () {
                public int dispatch (volcano.Java.base.rg_XianChengLei objSource, int nTagNumber, java.lang.Object rg_YongHuDuiXiang18, java.lang.Object rg_YongHuDuiXiang19) {
                    return rg_XianChengLei_XianChengQiDong (objSource, nTagNumber, rg_YongHuDuiXiang18, rg_YongHuDuiXiang19);
                }
            }, 0);
            rg_XianCheng.rg_QiDong5 (null, null);
        }
        return (0);
    }

    protected int rg_XianChengLei_XianChengQiDong (volcano.Java.base.rg_XianChengLei rg_LaiYuanDuiXiang19, int rg_BiaoJiZhi19, java.lang.Object rg_YongHuDuiXiang, java.lang.Object rg_YongHuDuiXiang1) {
        int rg_i3;
        int rg_d = 0;
        rg_i3 = rg_FenGe.length;
        while (rg_i3 != 0)
        {
            rg_i3 = rg_i3 - 1;
            rg_LaYaDuiXiang.rg_XieSheBeiZhiDingBiaoShiShuJu (rg_DangQianXuanZhongSheBei, volcano.Java.base.rg_ZiJieShuZuCaoZuo.rg_GeBing9 (rg_JiaoYanLei.rg_hexStringToByteArray ("ffff"),  rg_FenGe [rg_d].getBytes ()), java.util.UUID.fromString(rg_SERVICE_UUID), java.util.UUID.fromString(rg_CHARA_UUID));
            rg_d = rg_d + 1;
            volcano.Java.base.rg_XianChengJiChuLei.rg_ShuiMianDangQianXianCheng (30);
        }
        volcano.Java.base.rg_XianChengJiChuLei.rg_ShuiMianDangQianXianCheng (50);
        rg_LaYaDuiXiang.rg_XieSheBeiZhiDingBiaoShiShuJu (rg_DangQianXuanZhongSheBei, rg_JiaoYanLei.rg_hexStringToByteArray ("ffffff"), java.util.UUID.fromString(rg_SERVICE_UUID), java.util.UUID.fromString(rg_CHARA_UUID));
        return (0);
    }
}

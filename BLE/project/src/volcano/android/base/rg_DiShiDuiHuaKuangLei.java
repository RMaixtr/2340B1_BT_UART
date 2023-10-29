
package volcano.android.base;

import android.app.AlertDialog;
import android.view.ViewGroup;

public class rg_DiShiDuiHuaKuangLei extends rg_DuiHuaKuangLei implements 
     android.content.DialogInterface.OnClickListener,
     android.content.DialogInterface.OnMultiChoiceClickListener {

    public rg_DiShiDuiHuaKuangLei ()  { }

    rg_DiShiDuiHuaKuangLei (android.app.Dialog dlg) { super (dlg); }
    @Override public void onClick (android.content.DialogInterface dialog, int which, boolean isChecked) { rg_DuoXuanXiangMuBeiXuanZhong (which, isChecked); }
    @Override public void onClick (android.content.DialogInterface dialog, int which) { rg_XiangMuBeiXuanZhong (which); }

    public static rg_DiShiDuiHuaKuangLei rg_ChuangJianDiShiDuiHuaKuang (android.app.Activity rg_ChuangKouShangXiaWen1) {
        return new rg_DiShiDuiHuaKuangLei (new AlertDialog.Builder (rg_ChuangKouShangXiaWen1).create ());
    }

    public void rg_NeiRong7 (final String rg_DiShiNeiRong4) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                ((android.app.AlertDialog) m_dlg).setMessage (rg_DiShiNeiRong4);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        ((android.app.AlertDialog) m_dlg).setMessage (rg_DiShiNeiRong4);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_ZhiAnNiu (final String rg_AnNiuBiaoTi, final int rg_AnNiuLeiXing3) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                ((android.app.AlertDialog) m_dlg).setButton ( rg_AnNiuLeiXing3, rg_AnNiuBiaoTi,
                    new android.content.DialogInterface.OnClickListener () { @Override public void onClick (android.content.DialogInterface dialog, int btnId) { rg_AnNiuBeiChanJi1 (btnId); } }
                );
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        ((android.app.AlertDialog) m_dlg).setButton ( rg_AnNiuLeiXing3, rg_AnNiuBiaoTi,
                                new android.content.DialogInterface.OnClickListener () { @Override public void onClick (android.content.DialogInterface dialog, int btnId) { rg_AnNiuBeiChanJi1 (btnId); } }
                            );
                    } catch (Exception e) { }
                } } );
        }
    }

    public static interface re_AnNiuBeiChanJi1 { int dispatch (rg_DiShiDuiHuaKuangLei objSource, int nTagNumber, int rg_AnNiuLeiXing4); }
    private re_AnNiuBeiChanJi1 rd_AnNiuBeiChanJi1;
    private int rd_AnNiuBeiChanJi1_tag;
    public void rl_DiShiDuiHuaKuangLei_AnNiuBeiChanJi1 (re_AnNiuBeiChanJi1 objListener, int nTagNumber) {
        synchronized (this) { rd_AnNiuBeiChanJi1 = objListener;  rd_AnNiuBeiChanJi1_tag = nTagNumber; }
    }
    public int rg_AnNiuBeiChanJi1 (int rg_AnNiuLeiXing4) {
        re_AnNiuBeiChanJi1 objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_AnNiuBeiChanJi1;  nTagNumber = rd_AnNiuBeiChanJi1_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_AnNiuLeiXing4) : 0);
    }

    public static interface re_DuoXuanXiangMuBeiXuanZhong { int dispatch (rg_DiShiDuiHuaKuangLei objSource, int nTagNumber, int rg_BeiXuanZhongXiangMuSuoYin1, boolean rg_ShiFouXuanZhong1); }
    private re_DuoXuanXiangMuBeiXuanZhong rd_DuoXuanXiangMuBeiXuanZhong;
    private int rd_DuoXuanXiangMuBeiXuanZhong_tag;
    public void rl_DiShiDuiHuaKuangLei_DuoXuanXiangMuBeiXuanZhong (re_DuoXuanXiangMuBeiXuanZhong objListener, int nTagNumber) {
        synchronized (this) { rd_DuoXuanXiangMuBeiXuanZhong = objListener;  rd_DuoXuanXiangMuBeiXuanZhong_tag = nTagNumber; }
    }
    public int rg_DuoXuanXiangMuBeiXuanZhong (int rg_BeiXuanZhongXiangMuSuoYin1, boolean rg_ShiFouXuanZhong1) {
        re_DuoXuanXiangMuBeiXuanZhong objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_DuoXuanXiangMuBeiXuanZhong;  nTagNumber = rd_DuoXuanXiangMuBeiXuanZhong_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_BeiXuanZhongXiangMuSuoYin1, rg_ShiFouXuanZhong1) : 0);
    }

    public static interface re_XiangMuBeiXuanZhong { int dispatch (rg_DiShiDuiHuaKuangLei objSource, int nTagNumber, int rg_BeiXuanZhongXiangMuSuoYin2); }
    private re_XiangMuBeiXuanZhong rd_XiangMuBeiXuanZhong;
    private int rd_XiangMuBeiXuanZhong_tag;
    public void rl_DiShiDuiHuaKuangLei_XiangMuBeiXuanZhong (re_XiangMuBeiXuanZhong objListener, int nTagNumber) {
        synchronized (this) { rd_XiangMuBeiXuanZhong = objListener;  rd_XiangMuBeiXuanZhong_tag = nTagNumber; }
    }
    public int rg_XiangMuBeiXuanZhong (int rg_BeiXuanZhongXiangMuSuoYin2) {
        re_XiangMuBeiXuanZhong objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_XiangMuBeiXuanZhong;  nTagNumber = rd_XiangMuBeiXuanZhong_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_BeiXuanZhongXiangMuSuoYin2) : 0);
    }
}


package volcano.android.base;

import android.view.ViewGroup;

public class rg_JinDuDuiHuaKuangLei extends rg_DiShiDuiHuaKuangLei {

    public rg_JinDuDuiHuaKuangLei ()  { }

    rg_JinDuDuiHuaKuangLei (android.app.Dialog dlg) { super (dlg); }

    public static rg_JinDuDuiHuaKuangLei rg_ChuangJian12 (android.app.Activity rg_ChuangKouShangXiaWen5) {
        android.app.ProgressDialog dlg = new android.app.ProgressDialog (rg_ChuangKouShangXiaWen5);
        dlg.setCanceledOnTouchOutside (false);
        dlg.setCancelable (false);
        return new rg_JinDuDuiHuaKuangLei (dlg);
    }

    public void rg_JinDuYangShi1 (final int rg_Can_JinDuYangShi) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                ((android.app.ProgressDialog) m_dlg).setProgressStyle (rg_Can_JinDuYangShi);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        ((android.app.ProgressDialog) m_dlg).setProgressStyle (rg_Can_JinDuYangShi);
                    } catch (Exception e) { }
                } } );
        }
    }
}

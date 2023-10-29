
package volcano.android.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class AndComActivity {

    private static final int rg_GuanBiWenBen = rM.aixtr.R.string.rg_guanbiwenben;
    private static final int rg_TuBiao1 = rM.aixtr.R.drawable.error_icon;
    private static final int rg_BiaoTiZiYuan1 = rM.aixtr.R.string.rg_biaotiziyuan1;
    public static final int rg_QianJingYanSe = android.R.attr.colorForeground;
    public static final int rg_BeiJingYanSe = android.R.attr.colorBackground;
    private static final String cs_strActivityLoadParams = "@activity_params";
    public static void sStartNewActivity (final android.content.Context context, final Class clsActivity,
            final android.os.Bundle bundle, final int nRequestCode, final int nAddFlags, final Object... params) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                _sStartNewActivity (context, clsActivity, bundle, nRequestCode, nAddFlags,  params);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        _sStartNewActivity (context, clsActivity, bundle, nRequestCode, nAddFlags,  params);
                    } catch (Exception e) { }
                } } );
        }
    }
    public static boolean _sStartNewActivity (android.content.Context context, Class clsActivity,
            android.os.Bundle bundle, int nRequestCode, int nAddFlags, Object... params) {
        rg_QuanJuShuJuCunChuQiLei objCache = rg_YingYongChengXu.sGetGlobalDataCache ();
        int nParamDataIdentifier = 0;
        try {
            android.content.Intent objIntent = new android.content.Intent (context, clsActivity);
            if (nAddFlags != 0)
                objIntent.addFlags (nAddFlags);
            if (bundle != null)
                objIntent.putExtras (bundle);
            if (params != null && params.length > 0) {
                nParamDataIdentifier = objCache.Push (params);
                objIntent.putExtra (cs_strActivityLoadParams, nParamDataIdentifier);
            }
            if (nRequestCode != 0 && context instanceof android.app.Activity)
                ((android.app.Activity)context).startActivityForResult (objIntent, nRequestCode);
            else
                context.startActivity (objIntent);
            return true;
        } catch (Exception e) { }
        objCache.Remove (nParamDataIdentifier);
        return false;
    }

    public static void rg_BiaoTi1 (final android.app.Activity rg_YuCaoZuoBenDuiXiang132, final String rg_SuoYuSheZhiBiaoTi) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                rg_YuCaoZuoBenDuiXiang132.setTitle (rg_SuoYuSheZhiBiaoTi);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        rg_YuCaoZuoBenDuiXiang132.setTitle (rg_SuoYuSheZhiBiaoTi);
                    } catch (Exception e) { }
                } } );
        }
    }

    public static void rg_GuanBi (final android.app.Activity rg_YuCaoZuoBenDuiXiang169) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                rg_YuCaoZuoBenDuiXiang169.finish ();
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        rg_YuCaoZuoBenDuiXiang169.finish ();
                    } catch (Exception e) { }
                } } );
        }
    }

    public static void rg_XinXiKuang (final android.app.Activity rg_YuCaoZuoBenDuiXiang179, int rg_YuXianShiDeTuBiaoZiYuan, final String rg_YuXianShiDeNeiRongWenBen, final String rg_YuXianShiDeBiaoTiWenBen) {
        final android.graphics.drawable.Drawable objDrawable = rg_ZiYuanGuanLiQi.rg_ZaiRuKeHuiZhiZiYuan (rg_YuXianShiDeTuBiaoZiYuan);
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                new android.app.AlertDialog.Builder (rg_YuCaoZuoBenDuiXiang179).
                       setIcon (objDrawable).setPositiveButton (rg_GuanBiWenBen, null).
                       setTitle (rg_YuXianShiDeBiaoTiWenBen).setMessage (rg_YuXianShiDeNeiRongWenBen).show ();
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        new android.app.AlertDialog.Builder (rg_YuCaoZuoBenDuiXiang179).
                                   setIcon (objDrawable).setPositiveButton (rg_GuanBiWenBen, null).
                                   setTitle (rg_YuXianShiDeBiaoTiWenBen).setMessage (rg_YuXianShiDeNeiRongWenBen).show ();
                    } catch (Exception e) { }
                } } );
        }
    }

    public static void rg_CuoWuXinXiKuang (final android.app.Activity rg_YuCaoZuoBenDuiXiang181, final String rg_YuXianShiDeNeiRongWenBen2) {
        AndComActivity.rg_XinXiKuang (rg_YuCaoZuoBenDuiXiang181, rg_TuBiao1, rg_YuXianShiDeNeiRongWenBen2, rg_ZiYuanGuanLiQi.rg_QuWenBenZiYuan (rg_BiaoTiZiYuan1, ""));
    }
}


package volcano.android.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class rg_XianXingBuJuQi2 extends AndroidViewGroup {

    public rg_XianXingBuJuQi2 ()  { }

    public rg_XianXingBuJuQi2 (android.content.Context context, LinearLayout view, Object objInitData) { super (context, view, objInitData); }
    public rg_XianXingBuJuQi2 (android.content.Context context, LinearLayout view) { this (context, view, null); }
    public LinearLayout GetLinearLayout () { return (LinearLayout)GetView (); }
    public static rg_XianXingBuJuQi2 sNewInstance (android.content.Context context) {
        return sNewInstanceAndAttachView (context, new LinearLayout (context), null);
    }
    public static rg_XianXingBuJuQi2 sNewInstance (android.content.Context context, Object objInitData) {
        return sNewInstanceAndAttachView (context, new LinearLayout (context), objInitData);
    }
    public static rg_XianXingBuJuQi2 sNewInstanceAndAttachView (android.content.Context context, LinearLayout viewAttach) {
        return sNewInstanceAndAttachView (context, viewAttach, null);
    }
    public static rg_XianXingBuJuQi2 sNewInstanceAndAttachView (android.content.Context context, LinearLayout viewAttach, Object objInitData) {
        rg_XianXingBuJuQi2 objControl = new rg_XianXingBuJuQi2 (context, viewAttach, objInitData);
        objControl.onInitControlContent (context, objInitData);
        return objControl;
    }

    public void rg_BuJuFangXiang1 (final int rg_SuoSheZhiBuJuFangXiang) {
        if ((this.GetLinearLayout ().getOrientation ()) != rg_SuoSheZhiBuJuFangXiang)
        {
            if (rg_YingYongChengXu.sIsUiThread ()) {
                try {
                    GetLinearLayout ().setOrientation (rg_SuoSheZhiBuJuFangXiang);
                } catch (Exception e) { }
            } else {
                rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                    @Override public void run () {
                        try {
                            GetLinearLayout ().setOrientation (rg_SuoSheZhiBuJuFangXiang);
                        } catch (Exception e) { }
                    } } );
            }
        }
    }

    public void rg_NeiRongChuiZhiDuiJiFangShi (final int rg_YuSheZhiDuiJiFangShi3) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                GetLinearLayout ().setVerticalGravity (rg_YuSheZhiDuiJiFangShi3);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        GetLinearLayout ().setVerticalGravity (rg_YuSheZhiDuiJiFangShi3);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_ZhiZiZuJianChuiZhiDuiJiFangShi (final AndroidView rg_ZiZuJian3, int rg_YuDiaoZhengDaoDuiJiFangShi1) {
        ViewGroup.LayoutParams pmLayout = rg_ZiZuJian3.GetView ().getLayoutParams ();
        if (pmLayout instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams pm = (LinearLayout.LayoutParams)pmLayout;
            if ((pm.gravity & (android.view.Gravity.VERTICAL_GRAVITY_MASK | android.view.Gravity.CLIP_VERTICAL)) != rg_YuDiaoZhengDaoDuiJiFangShi1) {
                pm.gravity = (pm.gravity & ~(android.view.Gravity.VERTICAL_GRAVITY_MASK | android.view.Gravity.CLIP_VERTICAL)) | rg_YuDiaoZhengDaoDuiJiFangShi1;
                final LinearLayout.LayoutParams pmNew = pm;
                if (rg_YingYongChengXu.sIsUiThread ()) {
                    try {
                        rg_ZiZuJian3.GetView ().setLayoutParams (pmNew);
                    } catch (Exception e) { }
                } else {
                    rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                        @Override public void run () {
                            try {
                                rg_ZiZuJian3.GetView ().setLayoutParams (pmNew);
                            } catch (Exception e) { }
                        } } );
                }
            } }
    }

    public void rg_ZhiZiZuJianShengYuKongJianQuanChong (final AndroidView rg_ZiZuJian4, double rg_ShengYuKongJianQuanChong) {
        ViewGroup.LayoutParams pmLayout = rg_ZiZuJian4.GetView ().getLayoutParams ();
        if (pmLayout instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams pm = (LinearLayout.LayoutParams)pmLayout;
            if (volcano.Java.base.rg_XiaoShuLei.rg_XiaoShuJinShiXiangDeng (pm.weight, rg_ShengYuKongJianQuanChong) == false) {
                pm.weight = (float)rg_ShengYuKongJianQuanChong;
                final LinearLayout.LayoutParams pmNew = pm;
                if (rg_YingYongChengXu.sIsUiThread ()) {
                    try {
                        rg_ZiZuJian4.GetView ().setLayoutParams (pmNew);
                    } catch (Exception e) { }
                } else {
                    rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                        @Override public void run () {
                            try {
                                rg_ZiZuJian4.GetView ().setLayoutParams (pmNew);
                            } catch (Exception e) { }
                        } } );
                }
            } }
    }
}

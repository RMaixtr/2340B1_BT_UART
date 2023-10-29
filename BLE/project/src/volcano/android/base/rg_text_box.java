
package volcano.android.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class rg_text_box extends AndroidView {

    public rg_text_box ()  { }

    public rg_text_box (android.content.Context context, TextView view, Object objInitData) { super (context, view, objInitData); }
    public rg_text_box (android.content.Context context, TextView view) { this (context, view, null); }
    public TextView GetTextView () { return (TextView)GetView (); }
    public static rg_text_box sNewInstance (android.content.Context context) {
        return sNewInstanceAndAttachView (context, new TextView (context), null);
    }
    public static rg_text_box sNewInstance (android.content.Context context, Object objInitData) {
        return sNewInstanceAndAttachView (context, new TextView (context), objInitData);
    }
    public static rg_text_box sNewInstanceAndAttachView (android.content.Context context, TextView viewAttach) {
        return sNewInstanceAndAttachView (context, viewAttach, null);
    }
    public static rg_text_box sNewInstanceAndAttachView (android.content.Context context, TextView viewAttach, Object objInitData) {
        rg_text_box objControl = new rg_text_box (context, viewAttach, objInitData);
        objControl.onInitControlContent (context, objInitData);
        return objControl;
    }
    protected int rg_DangQianSuoSheZhiZiTiLeiXing = 0;
    protected int rg_DangQianSuoSheZhiZiTiFengGe = 0;
    android.text.TextWatcher m_objTextWatcher;

    public void rg_ZuiDaXianShiHangShu (final int rg_HangShu1) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                GetTextView ().setMaxLines (rg_HangShu1);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        GetTextView ().setMaxLines (rg_HangShu1);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_NeiRong6 (final String rg_YuSheZhiDeWenBenNeiRong) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                GetTextView ().setText (rg_YuSheZhiDeWenBenNeiRong);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        GetTextView ().setText (rg_YuSheZhiDeWenBenNeiRong);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_ChanHangMoShi (final boolean rg_ShiFouWeiChanHangMoShi) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                GetTextView ().setSingleLine (rg_ShiFouWeiChanHangMoShi);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        GetTextView ().setSingleLine (rg_ShiFouWeiChanHangMoShi);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_WenBenZiTi1 (final int rg_YuSheZhiZiTiLeiXing4) {
        rg_DangQianSuoSheZhiZiTiLeiXing = rg_YuSheZhiZiTiLeiXing4;
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                GetTextView ().setTypeface (rg_ZiTiLei.rg_QuChangYongZiTi (rg_DangQianSuoSheZhiZiTiLeiXing), rg_DangQianSuoSheZhiZiTiFengGe);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        GetTextView ().setTypeface (rg_ZiTiLei.rg_QuChangYongZiTi (rg_DangQianSuoSheZhiZiTiLeiXing), rg_DangQianSuoSheZhiZiTiFengGe);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_WenBenZiTiFengGe1 (final int rg_YuSheZhiZiTiFengGe4) {
        rg_DangQianSuoSheZhiZiTiFengGe = rg_YuSheZhiZiTiFengGe4;
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                GetTextView ().setTypeface (rg_ZiTiLei.rg_QuChangYongZiTi (rg_DangQianSuoSheZhiZiTiLeiXing), rg_DangQianSuoSheZhiZiTiFengGe);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        GetTextView ().setTypeface (rg_ZiTiLei.rg_QuChangYongZiTi (rg_DangQianSuoSheZhiZiTiLeiXing), rg_DangQianSuoSheZhiZiTiFengGe);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_WenBenZiTiCheCun1 (final double rg_YuSheZhiZiTiCheCun4) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                GetTextView ().setTextSize ((float)rg_YuSheZhiZiTiCheCun4);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        GetTextView ().setTextSize ((float)rg_YuSheZhiZiTiCheCun4);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_WenBenYanSe1 (final int rg_YuSheZhiYanSe5) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                GetTextView ().setTextColor (rg_YuSheZhiYanSe5);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        GetTextView ().setTextColor (rg_YuSheZhiYanSe5);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_TianJiaNeiRongHang (final String rg_YuTianJiaDeWenBenNeiRong1) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                GetTextView ().append (rg_YuTianJiaDeWenBenNeiRong1 + "\n");
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        GetTextView ().append (rg_YuTianJiaDeWenBenNeiRong1 + "\n");
                    } catch (Exception e) { }
                } } );
        }
    }
}


package volcano.android.base;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class rg_TuPianKuang extends AndroidView {

    public rg_TuPianKuang ()  { }

    public rg_TuPianKuang (android.content.Context context, ImageView view, Object objInitData) { super (context, view, objInitData); }
    public rg_TuPianKuang (android.content.Context context, ImageView view) { this (context, view, null); }
    public ImageView GetImageView () { return (ImageView)GetView (); }
    public static rg_TuPianKuang sNewInstance (android.content.Context context) {
        return sNewInstanceAndAttachView (context, new ImageView (context), null);
    }
    public static rg_TuPianKuang sNewInstance (android.content.Context context, Object objInitData) {
        return sNewInstanceAndAttachView (context, new ImageView (context), objInitData);
    }
    public static rg_TuPianKuang sNewInstanceAndAttachView (android.content.Context context, ImageView viewAttach) {
        return sNewInstanceAndAttachView (context, viewAttach, null);
    }
    public static rg_TuPianKuang sNewInstanceAndAttachView (android.content.Context context, ImageView viewAttach, Object objInitData) {
        rg_TuPianKuang objControl = new rg_TuPianKuang (context, viewAttach, objInitData);
        objControl.onInitControlContent (context, objInitData);
        return objControl;
    }
    public static rg_TuPianKuang GetToImageView (android.widget.ImageView view) { if (view != null) {
        rg_TuPianKuang vol = null;
         vol = new rg_TuPianKuang (view.getContext(), view, null);
         vol.onInitControlContent (view.getContext(), null);
        return vol; }
    return null; }

    public void rg_SuFangFangShi1 (int rg_SuoYuSheZhiSuFangFangShi) {
        final ImageView.ScaleType s =
            (rg_SuoYuSheZhiSuFangFangShi == rg_TuPianSuFangFangShi.rg_SuFang ? ImageView.ScaleType.FIT_XY :
            rg_SuoYuSheZhiSuFangFangShi == rg_TuPianSuFangFangShi.rg_SuFangJuZuoShang ? ImageView.ScaleType.FIT_START :
            rg_SuoYuSheZhiSuFangFangShi == rg_TuPianSuFangFangShi.rg_SuFangJuZhong ? ImageView.ScaleType.FIT_CENTER :
            rg_SuoYuSheZhiSuFangFangShi == rg_TuPianSuFangFangShi.rg_SuFangJuYouXia ? ImageView.ScaleType.FIT_END :
            rg_SuoYuSheZhiSuFangFangShi == rg_TuPianSuFangFangShi.rg_SuFangJianQieJuZhong ? ImageView.ScaleType.CENTER_CROP :
            rg_SuoYuSheZhiSuFangFangShi == rg_TuPianSuFangFangShi.rg_BuSuFangJuZhong ? ImageView.ScaleType.CENTER :
            rg_SuoYuSheZhiSuFangFangShi == rg_TuPianSuFangFangShi.rg_NeiZhiJuZhong ? ImageView.ScaleType.CENTER_INSIDE :
            ImageView.ScaleType.FIT_CENTER);
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                GetImageView ().setScaleType (s);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        GetImageView ().setScaleType (s);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_ZhiTuPian (final android.graphics.drawable.Drawable rg_YuSheZhiDeTuXiang) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                GetImageView ().setImageDrawable (rg_YuSheZhiDeTuXiang);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        GetImageView ().setImageDrawable (rg_YuSheZhiDeTuXiang);
                    } catch (Exception e) { }
                } } );
        }
    }
}

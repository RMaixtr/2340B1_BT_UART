
package volcano.android.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class AndroidView extends volcano.Java.base.rg_BiaoJiLei {

    public AndroidView ()  { }

    public static final int rg_TianManFuZuJian = -1;
    public static final int rg_DengYuNeiRongCheCun = -2;
    protected static final int rg_HuoShanZuJianBaoCunID = rM.aixtr.R.id.rg_huoshanzujianbaocunid;
    private View m_view;
    private rg_ZuJianShuXingDongHuaBoFangQi m_animator;
    private View.OnAttachStateChangeListener m_stateChangeListener;
    private android.view.ViewTreeObserver.OnDrawListener m_drawListener;
    private android.view.ViewTreeObserver.OnGlobalLayoutListener m_layoutListener;
    public AndroidView (android.content.Context context, View view, Object objInitData) {
        m_view = view;
        m_view.setTag (rg_HuoShanZuJianBaoCunID, this);
    }
    public AndroidView (android.content.Context context, View view) {
        this (context, view, null);
    }
    public void onInitControlContent (android.content.Context context, Object objInitData) {
        OnInitView (context, objInitData);
        rg_ChuShiHuaNeiRong (context, objInitData);
    }
    public View GetView () { return m_view; }
    public static AndroidView sNewInstance (android.content.Context context) {
        return sNewInstanceAndAttachView (context, new View (context), null);
    }
    public static AndroidView sNewInstance (android.content.Context context, Object objInitData) {
        return sNewInstanceAndAttachView (context, new View (context), objInitData);
    }
    public static AndroidView sNewInstanceAndAttachView (android.content.Context context, View viewAttach) {
        return sNewInstanceAndAttachView (context, viewAttach, null);
    }
    public static AndroidView sNewInstanceAndAttachView (android.content.Context context, View viewAttach, Object objInitData) {
        AndroidView objControl = new AndroidView (context, viewAttach, objInitData);
        objControl.onInitControlContent (context, objInitData);
        return objControl;
    }
    public static AndroidView sSafeGetVolView (View view) {
        if (view != null) {
            Object obj = view.getTag (rg_HuoShanZuJianBaoCunID);
            if (obj != null && obj instanceof AndroidView)
                return (AndroidView)obj;
        }
        return null;
    }
    protected void OnInitView (android.content.Context context, Object objInitData) {
    }

    public static interface re_clicked { int dispatch (AndroidView objSource, int nTagNumber); }
    private re_clicked rd_clicked;
    private int rd_clicked_tag;
    public void rl_AndroidView_clicked (re_clicked objListener, int nTagNumber) {
        synchronized (this) { rd_clicked = objListener;  rd_clicked_tag = nTagNumber; }
    }
    public int rg_clicked () {
        re_clicked objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_clicked;  nTagNumber = rd_clicked_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber) : 0);
    }

    public void rg_ZhiChiChanJi1 (final boolean rg_ShiFouZhiChiChanJi) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                m_view.setClickable (rg_ShiFouZhiChiChanJi);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        m_view.setClickable (rg_ShiFouZhiChiChanJi);
                    } catch (Exception e) { }
                } } );
        }
        try {
            if (rg_ShiFouZhiChiChanJi) {
               m_view.setOnClickListener (new View.OnClickListener () {
                       @Override public void onClick (View v) {
                           rg_clicked ();
                       } } );
            } else {
                m_view.setOnClickListener (null);
            };
        } catch (Exception e) { }
    }

    public void rg_KeShi2 (final int rg_KeShiZhuangTai) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                m_view.setVisibility (rg_KeShiZhuangTai);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        m_view.setVisibility (rg_KeShiZhuangTai);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_KeHuoDeJiaoDian1 (final boolean rg_ShiFouKeHuoDeJiaoDian) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                m_view.setFocusable (rg_ShiFouKeHuoDeJiaoDian);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        m_view.setFocusable (rg_ShiFouKeHuoDeJiaoDian);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_ChuMoMoShiXiaKeHuoDeJiaoDian1 (final boolean rg_ShiFouKeHuoDeJiaoDian1) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                m_view.setFocusableInTouchMode (rg_ShiFouKeHuoDeJiaoDian1);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        m_view.setFocusableInTouchMode (rg_ShiFouKeHuoDeJiaoDian1);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_ZhiWaiBianJu1 (int rg_ZuoWaiBianJu, int rg_ShangWaiBianJu, int rg_YouWaiBianJu, int rg_XiaWaiBianJu) {
        ViewGroup.LayoutParams pmLayout = m_view.getLayoutParams ();
        if (pmLayout == null || pmLayout instanceof ViewGroup.MarginLayoutParams == false)
            return;
        ViewGroup.MarginLayoutParams pmMargin = (ViewGroup.MarginLayoutParams)pmLayout;
        final android.graphics.Rect rtNew = new android.graphics.Rect (
                (rg_ZuoWaiBianJu == -1 ?  pmMargin.leftMargin : rg_ZuoWaiBianJu),
                (rg_ShangWaiBianJu == -1 ?  pmMargin.topMargin : rg_ShangWaiBianJu),
                (rg_YouWaiBianJu == -1 ?  pmMargin.rightMargin : rg_YouWaiBianJu),
                (rg_XiaWaiBianJu == -1 ?  pmMargin.bottomMargin : rg_XiaWaiBianJu));
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                ViewGroup.MarginLayoutParams pmNewMargin = (ViewGroup.MarginLayoutParams)m_view.getLayoutParams ();
                pmNewMargin.setMargins (rtNew.left, rtNew.top, rtNew.right, rtNew.bottom);
                m_view.setLayoutParams (pmNewMargin);
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        ViewGroup.MarginLayoutParams pmNewMargin = (ViewGroup.MarginLayoutParams)m_view.getLayoutParams ();
                            pmNewMargin.setMargins (rtNew.left, rtNew.top, rtNew.right, rtNew.bottom);
                            m_view.setLayoutParams (pmNewMargin);
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_ZhiXuQiuCheCun (final int rg_YuSheZhiKuanDuZhi1, final int rg_YuSheZhiGaoDuZhi1) {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                ViewGroup.LayoutParams pmLayout = m_view.getLayoutParams ();
                if (pmLayout != null) {
                    pmLayout.width = rg_YuSheZhiKuanDuZhi1;
                    pmLayout.height = rg_YuSheZhiGaoDuZhi1;
                    m_view.setLayoutParams (pmLayout);
                } else {
                   m_view.setLayoutParams (new ViewGroup.LayoutParams (rg_YuSheZhiKuanDuZhi1, rg_YuSheZhiGaoDuZhi1));
                };
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        ViewGroup.LayoutParams pmLayout = m_view.getLayoutParams ();
                            if (pmLayout != null) {
                                pmLayout.width = rg_YuSheZhiKuanDuZhi1;
                                pmLayout.height = rg_YuSheZhiGaoDuZhi1;
                                m_view.setLayoutParams (pmLayout);
                            } else {
                               m_view.setLayoutParams (new ViewGroup.LayoutParams (rg_YuSheZhiKuanDuZhi1, rg_YuSheZhiGaoDuZhi1));
                            };
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_ZhiMoRenBiaoQianDuiXiang (java.lang.Object rg_SuoDuiYingDuiXiang) {
        try {
            m_view.setTag (rg_SuoDuiYingDuiXiang);
        } catch (Exception e) { }
    }

    protected void rg_ChuShiHuaNeiRong (android.content.Context rg_HuanJingDuiXiang3, java.lang.Object rg_ShuJuDuiXiang1) {
    }
}

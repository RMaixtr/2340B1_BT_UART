
package volcano.android.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class rg_JianChanLieBiaoKuang1 extends rg_LieBiaoKuangJiChuLei {

    public rg_JianChanLieBiaoKuang1 ()  { }

    public rg_JianChanLieBiaoKuang1 (android.content.Context context, rg_AnZhuoZiDingYiLieBiao view, Object objInitData) { super (context, view, objInitData); }
    public rg_JianChanLieBiaoKuang1 (android.content.Context context, rg_AnZhuoZiDingYiLieBiao view) { this (context, view, null); }
    public rg_AnZhuoZiDingYiLieBiao GetListView () { return (rg_AnZhuoZiDingYiLieBiao)GetView (); }
    public static rg_JianChanLieBiaoKuang1 sNewInstance (android.content.Context context) {
        return sNewInstanceAndAttachView (context, new rg_AnZhuoZiDingYiLieBiao (context), null);
    }
    public static rg_JianChanLieBiaoKuang1 sNewInstance (android.content.Context context, Object objInitData) {
        return sNewInstanceAndAttachView (context, new rg_AnZhuoZiDingYiLieBiao (context), objInitData);
    }
    public static rg_JianChanLieBiaoKuang1 sNewInstanceAndAttachView (android.content.Context context, rg_AnZhuoZiDingYiLieBiao viewAttach) {
        return sNewInstanceAndAttachView (context, viewAttach, null);
    }
    public static rg_JianChanLieBiaoKuang1 sNewInstanceAndAttachView (android.content.Context context, rg_AnZhuoZiDingYiLieBiao viewAttach, Object objInitData) {
        rg_JianChanLieBiaoKuang1 objControl = new rg_JianChanLieBiaoKuang1 (context, viewAttach, objInitData);
        objControl.onInitControlContent (context, objInitData);
        return objControl;
    }
    protected rg_JianChanLieBiaoKuoPeiQi rg_SuoShiYongKuoPeiQi = new rg_JianChanLieBiaoKuoPeiQi ();

    {
        rg_SuoShiYongKuoPeiQi.rl_KuoPeiQiJiChuLei_NeiRongBeiGaiBian (new rg_KuoPeiQiJiChuLei.re_NeiRongBeiGaiBian () {
            public int dispatch (rg_KuoPeiQiJiChuLei objSource, int nTagNumber) {
                return rg_KuoPeiQiJiChuLei_NeiRongBeiGaiBian (objSource, nTagNumber);
            }
        }, 0);
        rg_SuoShiYongKuoPeiQi.rl_JianChanLieBiaoKuoPeiQi_ShuaXinXianShiNeiRong (new rg_JianChanLieBiaoKuoPeiQi.re_ShuaXinXianShiNeiRong () {
            public int dispatch (rg_JianChanLieBiaoKuoPeiQi objSource, int nTagNumber) {
                return rg_JianChanLieBiaoKuoPeiQi_ShuaXinXianShiNeiRong (objSource, nTagNumber);
            }
        }, 0);
    }

    public rg_JianChanLieBiaoKuoPeiQi rg_KuoPeiQi () {
        return (rg_SuoShiYongKuoPeiQi);
    }

    protected int rg_KuoPeiQiJiChuLei_NeiRongBeiGaiBian (final rg_KuoPeiQiJiChuLei rg_LaiYuanDuiXiang21, int rg_BiaoJiZhi21) {
        if (GetListView ().getAdapter () == null)
            if (rg_YingYongChengXu.sIsUiThread ()) {
                try {
                    GetListView ().setAdapter (rg_LaiYuanDuiXiang21);
                } catch (Exception e) { }
            } else {
                rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                    @Override public void run () {
                        try {
                            GetListView ().setAdapter (rg_LaiYuanDuiXiang21);
                        } catch (Exception e) { }
                    } } );
            }
        return (0);
    }

    protected int rg_JianChanLieBiaoKuoPeiQi_ShuaXinXianShiNeiRong (rg_JianChanLieBiaoKuoPeiQi rg_LaiYuanDuiXiang22, int rg_BiaoJiZhi22) {
        rg_ShuaXinSuoYouXiangMu (true);
        return (0);
    }
}

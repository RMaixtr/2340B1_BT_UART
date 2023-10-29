
package volcano.android.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class rg_LieBiaoZuJianJiChuLei extends rg_KuoPeiQiZuJianJiChuLei {

    public rg_LieBiaoZuJianJiChuLei ()  { }

    public rg_LieBiaoZuJianJiChuLei (android.content.Context context, AbsListView view, Object objInitData) { super (context, view, objInitData); }
    public rg_LieBiaoZuJianJiChuLei (android.content.Context context, AbsListView view) { this (context, view, null); }
    public AbsListView GetAbsListView () { return (AbsListView)GetView (); }
    public static rg_LieBiaoZuJianJiChuLei sNewInstance (android.content.Context context) {
        return null;
    }
    public static rg_LieBiaoZuJianJiChuLei sNewInstance (android.content.Context context, Object objInitData) {
        return null;
    }
    @Override protected void OnInitView (android.content.Context context, Object objInitData) {
        super.OnInitView (context, objInitData);
        AbsListView list = GetAbsListView ();
        list.setOnScrollListener (new AbsListView.OnScrollListener () {
            private int nReachState = 0;
            @Override public void onScroll (AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                nReachState = 0;
                if (totalItemCount > 0) {
                    if (firstVisibleItem + visibleItemCount >= totalItemCount)
                        nReachState = 1;
                    else if (firstVisibleItem == 0)
                        nReachState = -1;
                }
                rg_ZhengZaiGunDong (firstVisibleItem, visibleItemCount, totalItemCount);
            }
            @Override public void onScrollStateChanged (AbsListView view, int scrollState) {
                rg_GunDongZhuangTaiGaiBian (scrollState, (scrollState == rg_LieBiaoKuangGunDongZhuangTai.rg_ChuMoGunDong ? 0 : nReachState));
                if (scrollState == rg_LieBiaoKuangGunDongZhuangTai.rg_WeiGunDong)
                    nReachState = 0;
            }
        } );
    }

    public static interface re_ZhengZaiGunDong { int dispatch (rg_LieBiaoZuJianJiChuLei objSource, int nTagNumber, int rg_ShouKeShiXiangMuSuoYin, int rg_KeShiXiangMuShuMu, int rg_ZongGongXiangMuShuMu); }
    private re_ZhengZaiGunDong rd_ZhengZaiGunDong;
    private int rd_ZhengZaiGunDong_tag;
    public void rl_LieBiaoZuJianJiChuLei_ZhengZaiGunDong (re_ZhengZaiGunDong objListener, int nTagNumber) {
        synchronized (this) { rd_ZhengZaiGunDong = objListener;  rd_ZhengZaiGunDong_tag = nTagNumber; }
    }
    public int rg_ZhengZaiGunDong (int rg_ShouKeShiXiangMuSuoYin, int rg_KeShiXiangMuShuMu, int rg_ZongGongXiangMuShuMu) {
        re_ZhengZaiGunDong objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_ZhengZaiGunDong;  nTagNumber = rd_ZhengZaiGunDong_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_ShouKeShiXiangMuSuoYin, rg_KeShiXiangMuShuMu, rg_ZongGongXiangMuShuMu) : 0);
    }

    public static interface re_GunDongZhuangTaiGaiBian { int dispatch (rg_LieBiaoZuJianJiChuLei objSource, int nTagNumber, int rg_DangQianGunDongZhuangTai, int rg_DangQianGunDongWeiZhi); }
    private re_GunDongZhuangTaiGaiBian rd_GunDongZhuangTaiGaiBian;
    private int rd_GunDongZhuangTaiGaiBian_tag;
    public void rl_LieBiaoZuJianJiChuLei_GunDongZhuangTaiGaiBian (re_GunDongZhuangTaiGaiBian objListener, int nTagNumber) {
        synchronized (this) { rd_GunDongZhuangTaiGaiBian = objListener;  rd_GunDongZhuangTaiGaiBian_tag = nTagNumber; }
    }
    public int rg_GunDongZhuangTaiGaiBian (int rg_DangQianGunDongZhuangTai, int rg_DangQianGunDongWeiZhi) {
        re_GunDongZhuangTaiGaiBian objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_GunDongZhuangTaiGaiBian;  nTagNumber = rd_GunDongZhuangTaiGaiBian_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_DangQianGunDongZhuangTai, rg_DangQianGunDongWeiZhi) : 0);
    }

    public void rg_ShuaXinSuoYouXiangMu (final boolean rg_YouKuoPeiQiCaiShuaXin) {
        if (rg_YouKuoPeiQiCaiShuaXin == false || GetAbsListView ().getAdapter () != null) {
            if (rg_YingYongChengXu.sIsUiThread ()) {
                try {
                    GetAbsListView ().invalidateViews ();
                } catch (Exception e) { }
            } else {
                rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                    @Override public void run () {
                        try {
                            GetAbsListView ().invalidateViews ();
                        } catch (Exception e) { }
                    } } );
            }
        }
    }
}

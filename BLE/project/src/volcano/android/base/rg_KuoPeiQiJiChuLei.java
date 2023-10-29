
package volcano.android.base;

public class rg_KuoPeiQiJiChuLei extends android.widget.BaseAdapter {

    protected java.util.ArrayList<java.lang.Object> rg_XiangMuShuJuShuZu = new java.util.ArrayList<java.lang.Object> ();
    protected java.util.ArrayList<java.lang.Long> rg_XiangMuIDShuZu = new java.util.ArrayList<java.lang.Long> ();
    public java.util.ArrayList<java.lang.Object> mItemDataList = rg_XiangMuShuJuShuZu;
    protected long rg_DangQianKeYongIDZhi;
    @Override public int getCount ()  { return rg_XiangMuShuJuShuZu.size (); }
    @Override public Object getItem (int position)  { return ((position < 0  || position >= rg_XiangMuShuJuShuZu.size ()) ? null : rg_XiangMuShuJuShuZu.get (position)); }
    @Override public long getItemId (int position)  { return ((position < 0 || position >= rg_XiangMuIDShuZu.size ()) ? 0 : rg_XiangMuIDShuZu.get (position)); }
    @Override public boolean hasStableIds ()  { return true; }
    @Override public boolean isEmpty ()  { return rg_XiangMuShuJuShuZu.isEmpty (); }
    @Override public android.view.View getView (int position, android.view.View convertView, android.view.ViewGroup parent) {
        AndroidViewGroup objViewGroup = (rg_XiangMuShuJuShuZu.isEmpty () ? null :
                rg_QuXiangMuShiTu (position, (AndroidViewGroup)AndroidView.sSafeGetVolView (convertView), parent));
        if (objViewGroup == null)
            objViewGroup = rg_XianXingBuJuQi2.sNewInstance (parent.getContext ());
        return objViewGroup.GetViewGroup ();
    }

    public static interface re_NeiRongBeiGaiBian { int dispatch (rg_KuoPeiQiJiChuLei objSource, int nTagNumber); }
    private re_NeiRongBeiGaiBian rd_NeiRongBeiGaiBian;
    private int rd_NeiRongBeiGaiBian_tag;
    public void rl_KuoPeiQiJiChuLei_NeiRongBeiGaiBian (re_NeiRongBeiGaiBian objListener, int nTagNumber) {
        synchronized (this) { rd_NeiRongBeiGaiBian = objListener;  rd_NeiRongBeiGaiBian_tag = nTagNumber; }
    }
    public int rg_NeiRongBeiGaiBian () {
        re_NeiRongBeiGaiBian objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_NeiRongBeiGaiBian;  nTagNumber = rd_NeiRongBeiGaiBian_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber) : 0);
    }

    public void rg_ShanChuSuoYouXiangMu (boolean rg_LiJiShuaXin1) {
        if (rg_XiangMuShuJuShuZu.isEmpty () == false)
        {
            volcano.Java.base.rg_DuiXiangJiGeLei.rg_ShanChuSuoYouChengYuan28 (rg_XiangMuShuJuShuZu);
            volcano.Java.base.rg_ChangZhengShuJiGeLei.rg_ShanChuSuoYouChengYuan24 (rg_XiangMuIDShuZu);
            if (rg_LiJiShuaXin1)
            {
                rg_ChuLiNeiRongBeiGaiBian ();
            }
        }
    }

    protected long rg_FenPeiID () {
        rg_DangQianKeYongIDZhi = rg_DangQianKeYongIDZhi + 1;
        return (rg_DangQianKeYongIDZhi);
    }

    protected AndroidViewGroup rg_QuXiangMuShiTu (int rg_XiangMuSuoYinWeiZhi1, AndroidViewGroup rg_XianQianSuoShiYongZuJian, android.view.ViewGroup rg_YuGuaJieDaoDeAnZhuoZuJian) {
        return (null);
    }

    public void rg_ChuLiNeiRongBeiGaiBian () {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                rg_NeiRongBeiGaiBian ();
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        rg_NeiRongBeiGaiBian ();
                    } catch (Exception e) { }
                } } );
        }
        rg_TongZhiNeiRongBeiGaiBian ();
    }

    public void rg_TongZhiNeiRongBeiGaiBian () {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                notifyDataSetChanged ();
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        notifyDataSetChanged ();
                    } catch (Exception e) { }
                } } );
        }
    }
}

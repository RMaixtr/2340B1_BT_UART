
package volcano.android.base;

import android.view.ViewGroup;
import android.widget.*;

public class rg_JianChanLieBiaoKuoPeiQi extends rg_KuoPeiQiJiChuLei {

    public rg_JianChanLieBiaoKuoPeiQi ()  { }

    public int rg_TuPianKuanDu = AndroidView.rg_DengYuNeiRongCheCun;
    public int rg_TuPianGaoDu = AndroidView.rg_DengYuNeiRongCheCun;
    public int rg_TuPianSuFangFangShi1 = rg_TuPianSuFangFangShi.rg_NeiZhiJuZhong;
    public int rg_TuPianZuoWaiBianJu;
    public int rg_TuPianYouWaiBianJu = 10;
    public int rg_TuPianDingWaiBianJu;
    public int rg_TuPianDeWaiBianJu;
    public int rg_BiaoTiZuoWaiBianJu;
    public int rg_BiaoTiYouWaiBianJu;
    public int rg_BiaoTiDingWaiBianJu;
    public int rg_BiaoTiDeWaiBianJu;
    public int rg_NeiRongChuiZhiDuiJiFangShi1 = rg_ChuiZhiDuiJiFangShi.rg_JuZhong4;
    protected boolean rg_ShiFouYouXuanZeKuang;
    protected rg_ZiTiXinXi rg_BiaoTiZiTiXinXi = new rg_ZiTiXinXi ();
    protected rg_ZiTiXinXi rg_JiaoZhuZiTiXinXi = new rg_ZiTiXinXi ();

    public static interface re_XuanZhongZhuangTaiBeiGaiBian { int dispatch (rg_JianChanLieBiaoKuoPeiQi objSource, int nTagNumber, int rg_XiangMuSuoYinWeiZhi8); }
    private re_XuanZhongZhuangTaiBeiGaiBian rd_XuanZhongZhuangTaiBeiGaiBian;
    private int rd_XuanZhongZhuangTaiBeiGaiBian_tag;
    public void rl_JianChanLieBiaoKuoPeiQi_XuanZhongZhuangTaiBeiGaiBian (re_XuanZhongZhuangTaiBeiGaiBian objListener, int nTagNumber) {
        synchronized (this) { rd_XuanZhongZhuangTaiBeiGaiBian = objListener;  rd_XuanZhongZhuangTaiBeiGaiBian_tag = nTagNumber; }
    }
    public int rg_XuanZhongZhuangTaiBeiGaiBian (int rg_XiangMuSuoYinWeiZhi8) {
        re_XuanZhongZhuangTaiBeiGaiBian objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_XuanZhongZhuangTaiBeiGaiBian;  nTagNumber = rd_XuanZhongZhuangTaiBeiGaiBian_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_XiangMuSuoYinWeiZhi8) : 0);
    }

    public static interface re_ShuaXinXianShiNeiRong { int dispatch (rg_JianChanLieBiaoKuoPeiQi objSource, int nTagNumber); }
    private re_ShuaXinXianShiNeiRong rd_ShuaXinXianShiNeiRong;
    private int rd_ShuaXinXianShiNeiRong_tag;
    public void rl_JianChanLieBiaoKuoPeiQi_ShuaXinXianShiNeiRong (re_ShuaXinXianShiNeiRong objListener, int nTagNumber) {
        synchronized (this) { rd_ShuaXinXianShiNeiRong = objListener;  rd_ShuaXinXianShiNeiRong_tag = nTagNumber; }
    }
    public int rg_ShuaXinXianShiNeiRong () {
        re_ShuaXinXianShiNeiRong objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_ShuaXinXianShiNeiRong;  nTagNumber = rd_ShuaXinXianShiNeiRong_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber) : 0);
    }

    public void rg_ChaRuLieBiaoXiang (String rg_BiaoTiWenBen1, android.graphics.drawable.Drawable rg_TuXiang1, int rg_BiaoTiWenBenYanSe1, boolean rg_BiaoTiCuTi1, String rg_JiaoZhuWenBen1, int rg_JiaoZhuWenBenYanSe1, boolean rg_ShiFouBeiXuanZhong3, int rg_YuChaRuSuoYinWeiZhi10, java.lang.Object rg_FuJiaShuJu1, boolean rg_LiKeShuaXin7) {
        rg_JianChanLieBiaoXiang rg_LieBiaoXiangXinXi = new rg_JianChanLieBiaoXiang ();
        rg_LieBiaoXiangXinXi.rg_BiaoTiWenBen = rg_BiaoTiWenBen1;
        rg_LieBiaoXiangXinXi.rg_BiaoTiWenBenYanSe = rg_BiaoTiWenBenYanSe1;
        rg_LieBiaoXiangXinXi.rg_BiaoTiCuTi = rg_BiaoTiCuTi1;
        rg_LieBiaoXiangXinXi.rg_TuXiang = rg_TuXiang1;
        rg_LieBiaoXiangXinXi.rg_JiaoZhuWenBen = rg_JiaoZhuWenBen1;
        rg_LieBiaoXiangXinXi.rg_JiaoZhuWenBenYanSe = rg_JiaoZhuWenBenYanSe1;
        rg_LieBiaoXiangXinXi.rg_ShiFouBeiXuanZhong = rg_ShiFouBeiXuanZhong3;
        rg_LieBiaoXiangXinXi.rg_FuJiaShuJu = rg_FuJiaShuJu1;
        rg_ChaRuXiangMu2 (rg_LieBiaoXiangXinXi, rg_YuChaRuSuoYinWeiZhi10, rg_LiKeShuaXin7);
    }

    protected AndroidViewGroup rg_QuXiangMuShiTu (int rg_XiangMuSuoYinWeiZhi9, AndroidViewGroup rg_XianQianSuoShiYongZuJian4, android.view.ViewGroup rg_YuGuaJieDaoDeAnZhuoZuJian4) {
        rg_JianChanLieBiaoXiang rg_LieBiaoXiangXinXi2;
        rg_XianXingBuJuQi2 rg_HengXiangBuJuQiZuJian;
        rg_TuPianKuang rg_TuPianKuangZuJian;
        rg_text_box rg_BiaoTiWenBenKuang;
        rg_LieBiaoXiangXinXi2 = rg_QuXiangMu2 (rg_XiangMuSuoYinWeiZhi9);
        rg_HengXiangBuJuQiZuJian = rg_XianXingBuJuQi2.sNewInstance (rg_YuGuaJieDaoDeAnZhuoZuJian4.getContext (), null);
        if (rg_LieBiaoXiangXinXi2.rg_TuXiang != null)
        {
            rg_TuPianKuangZuJian = rg_TuPianKuang.sNewInstance (rg_YuGuaJieDaoDeAnZhuoZuJian4.getContext (), null);
            rg_TuPianKuangZuJian.rg_ZhiTuPian (rg_LieBiaoXiangXinXi2.rg_TuXiang);
            rg_HengXiangBuJuQiZuJian.rg_TianJiaZiZuJian (rg_TuPianKuangZuJian, null);
            rg_TuPianKuangZuJian.rg_ZhiWaiBianJu1 (rg_TuPianZuoWaiBianJu, rg_TuPianDingWaiBianJu, rg_TuPianYouWaiBianJu, rg_TuPianDeWaiBianJu);
            rg_TuPianKuangZuJian.rg_ZhiXuQiuCheCun (rg_TuPianKuanDu, rg_TuPianGaoDu);
            rg_TuPianKuangZuJian.rg_SuFangFangShi1 (rg_TuPianSuFangFangShi1);
        }
        else
        {
            rg_TuPianKuangZuJian = null;
        }
        if (volcano.Java.base.rg_WenBenXingLei.rg_WenBenShiFouWeiKong (rg_LieBiaoXiangXinXi2.rg_BiaoTiWenBen) == false)
        {
            rg_BiaoTiWenBenKuang = rg_text_box.sNewInstance (rg_YuGuaJieDaoDeAnZhuoZuJian4.getContext (), null);
            rg_BiaoTiZiTiXinXi.rg_ZhiRuWenBenKuang (rg_BiaoTiWenBenKuang);
            if (rg_LieBiaoXiangXinXi2.rg_BiaoTiWenBenYanSe != 0)
            {
                rg_BiaoTiWenBenKuang.rg_WenBenYanSe1 (rg_LieBiaoXiangXinXi2.rg_BiaoTiWenBenYanSe);
            }
            if (rg_LieBiaoXiangXinXi2.rg_BiaoTiCuTi)
            {
                rg_BiaoTiWenBenKuang.rg_WenBenZiTi1 (rg_ChangYongZiTiLeiXing.rg_TongChangCuTi);
            }
            rg_BiaoTiWenBenKuang.rg_NeiRong6 (rg_LieBiaoXiangXinXi2.rg_BiaoTiWenBen);
        }
        else
        {
            rg_BiaoTiWenBenKuang = null;
        }
        if (volcano.Java.base.rg_WenBenXingLei.rg_WenBenShiFouWeiKong (rg_LieBiaoXiangXinXi2.rg_JiaoZhuWenBen) == false)
        {
            rg_text_box rg_JiaoZhuWenBenKuang;
            rg_JiaoZhuWenBenKuang = rg_text_box.sNewInstance (rg_YuGuaJieDaoDeAnZhuoZuJian4.getContext (), null);
            rg_JiaoZhuZiTiXinXi.rg_ZhiRuWenBenKuang (rg_JiaoZhuWenBenKuang);
            if (rg_LieBiaoXiangXinXi2.rg_JiaoZhuWenBenYanSe != 0)
            {
                rg_JiaoZhuWenBenKuang.rg_WenBenYanSe1 (rg_LieBiaoXiangXinXi2.rg_JiaoZhuWenBenYanSe);
            }
            rg_JiaoZhuWenBenKuang.rg_NeiRong6 (rg_LieBiaoXiangXinXi2.rg_JiaoZhuWenBen);
            if (rg_BiaoTiWenBenKuang != null)
            {
                rg_XianXingBuJuQi2 rg_ZongXiangBuJuQiZuJian;
                rg_ZongXiangBuJuQiZuJian = rg_XianXingBuJuQi2.sNewInstance (rg_YuGuaJieDaoDeAnZhuoZuJian4.getContext (), null);
                rg_ZongXiangBuJuQiZuJian.rg_BuJuFangXiang1 (rg_XianXingBuJuFangXiang.rg_ZongXiang2);
                rg_ZongXiangBuJuQiZuJian.rg_TianJiaZiZuJian (rg_BiaoTiWenBenKuang, null);
                rg_ZongXiangBuJuQiZuJian.rg_TianJiaZiZuJian (rg_JiaoZhuWenBenKuang, null);
                rg_BiaoTiWenBenKuang.rg_ZhiWaiBianJu1 (rg_BiaoTiZuoWaiBianJu, rg_BiaoTiDingWaiBianJu, rg_BiaoTiYouWaiBianJu, rg_BiaoTiDeWaiBianJu);
                rg_ZongXiangBuJuQiZuJian.rg_ZhiZiZuJianShengYuKongJianQuanChong (rg_BiaoTiWenBenKuang, 1);
                rg_HengXiangBuJuQiZuJian.rg_TianJiaZiZuJian (rg_ZongXiangBuJuQiZuJian, null);
                rg_HengXiangBuJuQiZuJian.rg_ZhiZiZuJianShengYuKongJianQuanChong (rg_ZongXiangBuJuQiZuJian, 1);
            }
            else
            {
                rg_HengXiangBuJuQiZuJian.rg_TianJiaZiZuJian (rg_JiaoZhuWenBenKuang, null);
                rg_HengXiangBuJuQiZuJian.rg_ZhiZiZuJianShengYuKongJianQuanChong (rg_JiaoZhuWenBenKuang, 1);
            }
        }
        else if (rg_BiaoTiWenBenKuang != null)
        {
            rg_HengXiangBuJuQiZuJian.rg_TianJiaZiZuJian (rg_BiaoTiWenBenKuang, null);
            rg_BiaoTiWenBenKuang.rg_ZhiWaiBianJu1 (rg_BiaoTiZuoWaiBianJu, rg_BiaoTiDingWaiBianJu, rg_BiaoTiYouWaiBianJu, rg_BiaoTiDeWaiBianJu);
            rg_HengXiangBuJuQiZuJian.rg_ZhiZiZuJianShengYuKongJianQuanChong (rg_BiaoTiWenBenKuang, 1);
        }
        else if (rg_TuPianKuangZuJian != null)
        {
            rg_HengXiangBuJuQiZuJian.rg_ZhiZiZuJianShengYuKongJianQuanChong (rg_TuPianKuangZuJian, 1);
        }
        rg_HengXiangBuJuQiZuJian.rg_NeiRongChuiZhiDuiJiFangShi (rg_NeiRongChuiZhiDuiJiFangShi1);
        if (rg_ShiFouYouXuanZeKuang)
        {
            rg_DuoXuanKuang rg_XuanZeKuangZuJian;
            rg_XuanZeKuangZuJian = rg_DuoXuanKuang.sNewInstance (rg_YuGuaJieDaoDeAnZhuoZuJian4.getContext (), null);
            rg_XuanZeKuangZuJian.rg_ChuMoMoShiXiaKeHuoDeJiaoDian1 (false);
            rg_XuanZeKuangZuJian.rg_KeHuoDeJiaoDian1 (false);
            rg_XuanZeKuangZuJian.rg_ZhiWaiBianJu1 (10, -1, -1, -1);
            rg_XuanZeKuangZuJian.rg_XuanZhong3 (rg_LieBiaoXiangXinXi2.rg_ShiFouBeiXuanZhong);
            rg_XuanZeKuangZuJian.rg_ZhiMoRenBiaoQianDuiXiang (rg_LieBiaoXiangXinXi2);
            rg_XuanZeKuangZuJian.rl_ZuGeAnNiuJiChuLei_XuanZhongZhuangTaiBeiGaiBian1 (new rg_ZuGeAnNiuJiChuLei.re_XuanZhongZhuangTaiBeiGaiBian1 () {
                public int dispatch (rg_ZuGeAnNiuJiChuLei objSource, int nTagNumber, boolean rg_DangQianXuanZhongZhuangTai1) {
                    return rg_DuoXuanKuang_XuanZhongZhuangTaiBeiGaiBian ((rg_DuoXuanKuang)objSource, nTagNumber, rg_DangQianXuanZhongZhuangTai1);
                }
            }, 0);
            rg_HengXiangBuJuQiZuJian.rg_TianJiaZiZuJian (rg_XuanZeKuangZuJian, null);
            rg_HengXiangBuJuQiZuJian.rg_ZhiZiZuJianChuiZhiDuiJiFangShi (rg_XuanZeKuangZuJian, rg_ChuiZhiDuiJiFangShi.rg_JuZhong4);
        }
        return (rg_HengXiangBuJuQiZuJian);
    }

    protected int rg_DuoXuanKuang_XuanZhongZhuangTaiBeiGaiBian (rg_DuoXuanKuang rg_LaiYuanDuiXiang20, int rg_BiaoJiZhi20, boolean rg_DangQianXuanZhongZhuangTai) {
        rg_JianChanLieBiaoXiang rg_LieBiaoXiangXinXi3;
        int rg_XiangMuSuoYinWeiZhi10;
        rg_LieBiaoXiangXinXi3 = (rg_JianChanLieBiaoXiang)(rg_LaiYuanDuiXiang20.GetView ().getTag ());
        rg_LieBiaoXiangXinXi3.rg_ShiFouBeiXuanZhong = rg_DangQianXuanZhongZhuangTai;
        rg_XiangMuSuoYinWeiZhi10 = rg_ChaZhaoXiangMuSuoYin2 (rg_LieBiaoXiangXinXi3);
        if (rg_XiangMuSuoYinWeiZhi10 != -1)
        {
            rg_XuanZhongZhuangTaiBeiGaiBian (rg_XiangMuSuoYinWeiZhi10);
        }
        return (0);
    }

    public rg_JianChanLieBiaoXiang rg_QuXiangMu2 (int rg_XiangMuSuoYinWeiZhi11) {
        return (rg_JianChanLieBiaoXiang)rg_XiangMuShuJuShuZu.get (rg_XiangMuSuoYinWeiZhi11);
    }

    public int rg_ChaZhaoXiangMuSuoYin2 (rg_JianChanLieBiaoXiang rg_YuChaZhaoXiangMuShuJu2) {
        return rg_XiangMuShuJuShuZu.indexOf ((Object)rg_YuChaZhaoXiangMuShuJu2);
    }

    public void rg_XiuGaiXiangMu2 (int rg_YuXiuGaiXiangMuSuoYinWeiZhi2, rg_JianChanLieBiaoXiang rg_XiangMuShuJu4, boolean rg_LiJiShuaXin8) {
        volcano.Java.base.rg_DuiXiangLieBiaoLei.rg_TiHuanChengYuan30 (rg_XiangMuShuJuShuZu, rg_YuXiuGaiXiangMuSuoYinWeiZhi2, (Object)rg_XiangMuShuJu4);
        volcano.Java.base.rg_ChangZhengShuLieBiaoLei.rg_TiHuanChengYuan25 (rg_XiangMuIDShuZu, rg_YuXiuGaiXiangMuSuoYinWeiZhi2, Long.valueOf (rg_FenPeiID ()));
        if (rg_LiJiShuaXin8)
        {
            rg_ChuLiNeiRongBeiGaiBian ();
        }
    }

    public void rg_ChaRuXiangMu2 (rg_JianChanLieBiaoXiang rg_XiangMuShuJu5, int rg_YuChaRuSuoYinWeiZhi12, boolean rg_LiJiShuaXin9) {
        if (rg_YuChaRuSuoYinWeiZhi12 < 0)
        {
            volcano.Java.base.rg_DuiXiangJiGeLei.AddCollectionElements (rg_XiangMuShuJuShuZu,(Object)rg_XiangMuShuJu5);
            volcano.Java.base.rg_ChangZhengShuJiGeLei.AddCollectionElements (rg_XiangMuIDShuZu,Long.valueOf (rg_FenPeiID ()));
        }
        else
        {
            volcano.Java.base.rg_DuiXiangLieBiaoLei.rg_ChaRuChengYuan89 (rg_XiangMuShuJuShuZu, rg_YuChaRuSuoYinWeiZhi12, (Object)rg_XiangMuShuJu5);
            volcano.Java.base.rg_ChangZhengShuLieBiaoLei.rg_ChaRuChengYuan79 (rg_XiangMuIDShuZu, rg_YuChaRuSuoYinWeiZhi12, Long.valueOf (rg_FenPeiID ()));
        }
        if (rg_LiJiShuaXin9)
        {
            rg_ChuLiNeiRongBeiGaiBian ();
        }
    }
}

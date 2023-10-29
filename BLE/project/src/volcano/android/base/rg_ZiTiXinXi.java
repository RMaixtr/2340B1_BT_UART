
package volcano.android.base;

public class rg_ZiTiXinXi {

    public rg_ZiTiXinXi ()  { }

    public int rg_LeiXing8;
    public int rg_FengGe1;
    public double rg_CheCun2;

    public void rg_ZhiRuWenBenKuang (rg_text_box rg_YuZhiRuWenBenKuang) {
        rg_YuZhiRuWenBenKuang.rg_WenBenZiTi1 (rg_LeiXing8);
        rg_YuZhiRuWenBenKuang.rg_WenBenZiTiFengGe1 (rg_FengGe1);
        if (rg_CheCun2 > 0.0000001)
        {
            rg_YuZhiRuWenBenKuang.rg_WenBenZiTiCheCun1 (rg_CheCun2);
        }
    }
}


package volcano.android.base;

public class rg_ZiTiLei {

    public static android.graphics.Typeface rg_QuChangYongZiTi (int rg_LeiXing) {
        switch (rg_LeiXing) {
            case rg_ChangYongZiTiLeiXing.rg_TongChangCuTi:    return android.graphics.Typeface.DEFAULT_BOLD;
            case rg_ChangYongZiTiLeiXing.rg_MoChenXianZiTi:  return android.graphics.Typeface.SANS_SERIF;
            case rg_ChangYongZiTiLeiXing.rg_YouChenXianZiTi:  return android.graphics.Typeface.SERIF;
            case rg_ChangYongZiTiLeiXing.rg_DengKuanZiTi:    return android.graphics.Typeface.MONOSPACE;
            default:  return android.graphics.Typeface.DEFAULT;
        }
    }
}

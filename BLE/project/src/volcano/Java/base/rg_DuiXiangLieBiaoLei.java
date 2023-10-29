
package volcano.Java.base;

public final class rg_DuiXiangLieBiaoLei {

    public static boolean rg_ChaRuChengYuan89 (java.util.List<java.lang.Object> rg_YuCaoZuoLieBiaoDuiXiang298, int rg_YuChaRuSuoYinWeiZhi75, java.lang.Object rg_YuChaRuChengYuan30) {
        if (rg_YuChaRuSuoYinWeiZhi75 < 0)
        {
            try {
                rg_YuCaoZuoLieBiaoDuiXiang298.add (rg_YuChaRuChengYuan30);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        else
        {
            try {
                rg_YuCaoZuoLieBiaoDuiXiang298.add (rg_YuChaRuSuoYinWeiZhi75, rg_YuChaRuChengYuan30);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static boolean rg_TiHuanChengYuan30 (java.util.List<java.lang.Object> rg_YuCaoZuoLieBiaoDuiXiang307, int rg_YuTiHuanChengYuanSuoChuSuoYinWeiZhi30, java.lang.Object rg_YuTiHuanChengYuan60) {
        try {
            rg_YuCaoZuoLieBiaoDuiXiang307.set (rg_YuTiHuanChengYuanSuoChuSuoYinWeiZhi30, rg_YuTiHuanChengYuan60);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

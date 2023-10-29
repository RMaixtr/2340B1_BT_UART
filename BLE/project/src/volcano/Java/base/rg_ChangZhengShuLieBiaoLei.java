
package volcano.Java.base;

public final class rg_ChangZhengShuLieBiaoLei {

    public static boolean rg_ChaRuChengYuan79 (java.util.List<java.lang.Long> rg_YuCaoZuoLieBiaoDuiXiang248, int rg_YuChaRuSuoYinWeiZhi65, java.lang.Long rg_YuChaRuChengYuan25) {
        if (rg_YuChaRuSuoYinWeiZhi65 < 0)
        {
            try {
                rg_YuCaoZuoLieBiaoDuiXiang248.add (rg_YuChaRuChengYuan25);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        else
        {
            try {
                rg_YuCaoZuoLieBiaoDuiXiang248.add (rg_YuChaRuSuoYinWeiZhi65, rg_YuChaRuChengYuan25);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static boolean rg_TiHuanChengYuan25 (java.util.List<java.lang.Long> rg_YuCaoZuoLieBiaoDuiXiang257, int rg_YuTiHuanChengYuanSuoChuSuoYinWeiZhi25, java.lang.Long rg_YuTiHuanChengYuan55) {
        try {
            rg_YuCaoZuoLieBiaoDuiXiang257.set (rg_YuTiHuanChengYuanSuoChuSuoYinWeiZhi25, rg_YuTiHuanChengYuan55);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


package volcano.Java.base;

public final class rg_WenBenXingLei {

    public static boolean rg_WenBenShiFouWeiKong (String rg_DaiChuLiWenBen1) {
        return (rg_DaiChuLiWenBen1 == null || rg_DaiChuLiWenBen1.isEmpty ());
    }

    public static String rg_ZiJieShuZuDaoWenBen (byte [] rg_ZiJieShuJu1) {
        try {
            return new String (rg_ZiJieShuJu1);
        } catch (Exception e) {
            return "";
        }
    }

    public static boolean rg_WenBenXiangDeng (String rg_string11, String rg_string12, boolean rg_ShiFouHuLueDaXiaoXie1) {
        return (rg_ShiFouHuLueDaXiaoXie1 ? rg_string11.equalsIgnoreCase (rg_string12) :  rg_string11.equals (rg_string12));
    }
}

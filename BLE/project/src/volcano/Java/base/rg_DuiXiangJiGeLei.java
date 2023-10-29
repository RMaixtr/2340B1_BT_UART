
package volcano.Java.base;

public final class rg_DuiXiangJiGeLei {

    public static <T> Boolean AddCollectionElements (java.util.Collection<T> c, T... arr) {
        try {
            for (T arrs : arr) {
                if (!c.add (arrs))  return false;
            }
            return true;
        } catch (Exception e) { return false; }
    }

    public static void rg_ShanChuSuoYouChengYuan28 (java.util.Collection<java.lang.Object> rg_YuCaoZuoJiGeDuiXiang142) {
        try {
            rg_YuCaoZuoJiGeDuiXiang142.clear ();
        } catch (Exception e) { }
    }
}

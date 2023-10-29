
package volcano.Java.base;

public final class rg_ChangZhengShuJiGeLei {

    public static <T> Boolean AddCollectionElements (java.util.Collection<T> c, T... arr) {
        try {
            for (T arrs : arr) {
                if (!c.add (arrs))  return false;
            }
            return true;
        } catch (Exception e) { return false; }
    }

    public static void rg_ShanChuSuoYouChengYuan24 (java.util.Collection<java.lang.Long> rg_YuCaoZuoJiGeDuiXiang122) {
        try {
            rg_YuCaoZuoJiGeDuiXiang122.clear ();
        } catch (Exception e) { }
    }
}

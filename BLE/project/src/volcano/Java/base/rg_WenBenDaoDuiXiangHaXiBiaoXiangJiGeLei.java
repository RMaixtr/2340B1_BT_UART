
package volcano.Java.base;

public final class rg_WenBenDaoDuiXiangHaXiBiaoXiangJiGeLei {

    public static <T> Boolean AddCollectionElements (java.util.Collection<T> c, T... arr) {
        try {
            for (T arrs : arr) {
                if (!c.add (arrs))  return false;
            }
            return true;
        } catch (Exception e) { return false; }
    }
}

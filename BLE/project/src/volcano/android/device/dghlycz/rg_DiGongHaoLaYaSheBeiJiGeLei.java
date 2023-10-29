
package volcano.android.device.dghlycz;

public final class rg_DiGongHaoLaYaSheBeiJiGeLei {

    public static <T> Boolean AddCollectionElements (java.util.Collection<T> c, T... arr) {
        try {
            for (T arrs : arr) {
                if (!c.add (arrs))  return false;
            }
            return true;
        } catch (Exception e) { return false; }
    }

    public static void rg_ShanChuSuoYouChengYuan16 (java.util.Collection<cn.com.heaton.blelibrary.ble.model.BleDevice> rg_YuCaoZuoJiGeDuiXiang82) {
        try {
            rg_YuCaoZuoJiGeDuiXiang82.clear ();
        } catch (Exception e) { }
    }
}

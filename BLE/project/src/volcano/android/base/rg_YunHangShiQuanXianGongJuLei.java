
package volcano.android.base;

public class rg_YunHangShiQuanXianGongJuLei {

    public rg_YunHangShiQuanXianGongJuLei ()  { }

    public com.voldp.runtimepermission.VolPermission.Builder builder = com.voldp.runtimepermission.VolPermission.with(rg_YingYongChengXu.sGetApp ()).setPermissionListener(new com.voldp.runtimepermission.PermissionListener(){
        @Override
        public void onPermissionGranted() {
            rg_YiShouYuQuanXian();
        }
        @Override
        public void onPermissionDenied(java.util.List<String> deniedPermissions) {
            rg_YiJuJueQuanXian(deniedPermissions);
        }
        @Override public void showWindowPermissionDenyDialog() {}
    });

    public static interface re_YiShouYuQuanXian { int dispatch (rg_YunHangShiQuanXianGongJuLei objSource, int nTagNumber); }
    private re_YiShouYuQuanXian rd_YiShouYuQuanXian;
    private int rd_YiShouYuQuanXian_tag;
    public void rl_YunHangShiQuanXianGongJuLei_YiShouYuQuanXian (re_YiShouYuQuanXian objListener, int nTagNumber) {
        synchronized (this) { rd_YiShouYuQuanXian = objListener;  rd_YiShouYuQuanXian_tag = nTagNumber; }
    }
    public int rg_YiShouYuQuanXian () {
        re_YiShouYuQuanXian objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_YiShouYuQuanXian;  nTagNumber = rd_YiShouYuQuanXian_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber) : 0);
    }

    public static interface re_YiJuJueQuanXian { int dispatch (rg_YunHangShiQuanXianGongJuLei objSource, int nTagNumber, java.util.List<String> rg_BeiJuQuanXian1); }
    private re_YiJuJueQuanXian rd_YiJuJueQuanXian;
    private int rd_YiJuJueQuanXian_tag;
    public void rl_YunHangShiQuanXianGongJuLei_YiJuJueQuanXian (re_YiJuJueQuanXian objListener, int nTagNumber) {
        synchronized (this) { rd_YiJuJueQuanXian = objListener;  rd_YiJuJueQuanXian_tag = nTagNumber; }
    }
    public int rg_YiJuJueQuanXian (java.util.List<String> rg_BeiJuQuanXian1) {
        re_YiJuJueQuanXian objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_YiJuJueQuanXian;  nTagNumber = rd_YiJuJueQuanXian_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_BeiJuQuanXian1) : 0);
    }
}

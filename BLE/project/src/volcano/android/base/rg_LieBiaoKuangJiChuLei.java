
package volcano.android.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class rg_LieBiaoKuangJiChuLei extends rg_LieBiaoZuJianJiChuLei {

    public rg_LieBiaoKuangJiChuLei ()  { }

    public rg_LieBiaoKuangJiChuLei (android.content.Context context, ListView view, Object objInitData) { super (context, view, objInitData); }
    public rg_LieBiaoKuangJiChuLei (android.content.Context context, ListView view) { this (context, view, null); }
    public ListView GetListView () { return (ListView)GetView (); }
    public static rg_LieBiaoKuangJiChuLei sNewInstance (android.content.Context context) {
        return null;
    }
    public static rg_LieBiaoKuangJiChuLei sNewInstance (android.content.Context context, Object objInitData) {
        return null;
    }
}

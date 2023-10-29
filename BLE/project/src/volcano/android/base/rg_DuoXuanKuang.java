
package volcano.android.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class rg_DuoXuanKuang extends rg_ZuGeAnNiuJiChuLei {

    public rg_DuoXuanKuang ()  { }

    public rg_DuoXuanKuang (android.content.Context context, CheckBox view, Object objInitData) { super (context, view, objInitData); }
    public rg_DuoXuanKuang (android.content.Context context, CheckBox view) { this (context, view, null); }
    public CheckBox GetCheckBox () { return (CheckBox)GetView (); }
    public static rg_DuoXuanKuang sNewInstance (android.content.Context context) {
        return sNewInstanceAndAttachView (context, new CheckBox (context), null);
    }
    public static rg_DuoXuanKuang sNewInstance (android.content.Context context, Object objInitData) {
        return sNewInstanceAndAttachView (context, new CheckBox (context), objInitData);
    }
    public static rg_DuoXuanKuang sNewInstanceAndAttachView (android.content.Context context, CheckBox viewAttach) {
        return sNewInstanceAndAttachView (context, viewAttach, null);
    }
    public static rg_DuoXuanKuang sNewInstanceAndAttachView (android.content.Context context, CheckBox viewAttach, Object objInitData) {
        rg_DuoXuanKuang objControl = new rg_DuoXuanKuang (context, viewAttach, objInitData);
        objControl.onInitControlContent (context, objInitData);
        return objControl;
    }
}

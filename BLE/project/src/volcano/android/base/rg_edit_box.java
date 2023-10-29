
package volcano.android.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class rg_edit_box extends rg_text_box {

    public rg_edit_box ()  { }

    public rg_edit_box (android.content.Context context, EditText view, Object objInitData) { super (context, view, objInitData); }
    public rg_edit_box (android.content.Context context, EditText view) { this (context, view, null); }
    public EditText GetEditText () { return (EditText)GetView (); }
    public static rg_edit_box sNewInstance (android.content.Context context) {
        return sNewInstanceAndAttachView (context, new EditText (context), null);
    }
    public static rg_edit_box sNewInstance (android.content.Context context, Object objInitData) {
        return sNewInstanceAndAttachView (context, new EditText (context), objInitData);
    }
    public static rg_edit_box sNewInstanceAndAttachView (android.content.Context context, EditText viewAttach) {
        return sNewInstanceAndAttachView (context, viewAttach, null);
    }
    public static rg_edit_box sNewInstanceAndAttachView (android.content.Context context, EditText viewAttach, Object objInitData) {
        rg_edit_box objControl = new rg_edit_box (context, viewAttach, objInitData);
        objControl.onInitControlContent (context, objInitData);
        return objControl;
    }

    public void rg_GuangBiaoDaoWenBenWei () {
        if (rg_YingYongChengXu.sIsUiThread ()) {
            try {
                GetEditText ().setSelection (GetEditText ().getText ().length ());
            } catch (Exception e) { }
        } else {
            rg_YingYongChengXu.sRunOnUiThread (new Runnable () {
                @Override public void run () {
                    try {
                        GetEditText ().setSelection (GetEditText ().getText ().length ());
                    } catch (Exception e) { }
                } } );
        }
    }

    public void rg_DaoWeiBuTianJiaNeiRongHang (String rg_YuTianJiaDeWenBenNeiRong2) {
        rg_TianJiaNeiRongHang (rg_YuTianJiaDeWenBenNeiRong2);
        rg_GuangBiaoDaoWenBenWei ();
    }
}

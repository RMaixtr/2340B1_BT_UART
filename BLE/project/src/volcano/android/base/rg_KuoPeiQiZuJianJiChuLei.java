
package volcano.android.base;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class rg_KuoPeiQiZuJianJiChuLei extends AndroidViewGroup {

    public rg_KuoPeiQiZuJianJiChuLei ()  { }

    public rg_KuoPeiQiZuJianJiChuLei (android.content.Context context, AdapterView<? extends Adapter> view, Object objInitData) { super (context, view, objInitData); }
    public rg_KuoPeiQiZuJianJiChuLei (android.content.Context context, AdapterView<? extends Adapter> view) { this (context, view, null); }
    public AdapterView<? extends Adapter> GetAdapterView () { return (AdapterView<? extends Adapter>)GetView (); }
    public static rg_KuoPeiQiZuJianJiChuLei sNewInstance (android.content.Context context) {
        return null;
    }
    public static rg_KuoPeiQiZuJianJiChuLei sNewInstance (android.content.Context context, Object objInitData) {
        return null;
    }
    @Override protected void OnInitView (android.content.Context context, Object objInitData) {
        super.OnInitView (context, objInitData);
        GetAdapterView ().setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
                @Override public void onItemSelected (AdapterView<?> parent, View view, int position, long id) {
                    rg_XiangMuBeiXuanZe (position);
                }
                @Override public void onNothingSelected (AdapterView<?> parent) {
                    rg_XiangMuBeiXuanZe (-1);
                } } );
        try {
            GetAdapterView ().setOnItemClickListener (new AdapterView.OnItemClickListener () {
                    @Override public void onItemClick (AdapterView<?> parent, View view, int position, long id) {
                        rg_XiangMuBeiChanJi (position);
                    } } );
        } catch (Exception e) { }
        GetAdapterView ().setOnItemLongClickListener (new AdapterView.OnItemLongClickListener () {
                @Override public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    return (rg_XiangMuBeiChangAn (position) == 1);
                } } );
    }

    public static interface re_XiangMuBeiXuanZe { int dispatch (rg_KuoPeiQiZuJianJiChuLei objSource, int nTagNumber, int rg_BeiXuanZeXiangMuSuoYin); }
    private re_XiangMuBeiXuanZe rd_XiangMuBeiXuanZe;
    private int rd_XiangMuBeiXuanZe_tag;
    public void rl_KuoPeiQiZuJianJiChuLei_XiangMuBeiXuanZe (re_XiangMuBeiXuanZe objListener, int nTagNumber) {
        synchronized (this) { rd_XiangMuBeiXuanZe = objListener;  rd_XiangMuBeiXuanZe_tag = nTagNumber; }
    }
    public int rg_XiangMuBeiXuanZe (int rg_BeiXuanZeXiangMuSuoYin) {
        re_XiangMuBeiXuanZe objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_XiangMuBeiXuanZe;  nTagNumber = rd_XiangMuBeiXuanZe_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_BeiXuanZeXiangMuSuoYin) : 0);
    }

    public static interface re_XiangMuBeiChanJi { int dispatch (rg_KuoPeiQiZuJianJiChuLei objSource, int nTagNumber, int rg_BeiChanJiXiangMuSuoYin1); }
    private re_XiangMuBeiChanJi rd_XiangMuBeiChanJi;
    private int rd_XiangMuBeiChanJi_tag;
    public void rl_KuoPeiQiZuJianJiChuLei_XiangMuBeiChanJi (re_XiangMuBeiChanJi objListener, int nTagNumber) {
        synchronized (this) { rd_XiangMuBeiChanJi = objListener;  rd_XiangMuBeiChanJi_tag = nTagNumber; }
    }
    public int rg_XiangMuBeiChanJi (int rg_BeiChanJiXiangMuSuoYin1) {
        re_XiangMuBeiChanJi objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_XiangMuBeiChanJi;  nTagNumber = rd_XiangMuBeiChanJi_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_BeiChanJiXiangMuSuoYin1) : 0);
    }

    public static interface re_XiangMuBeiChangAn { int dispatch (rg_KuoPeiQiZuJianJiChuLei objSource, int nTagNumber, int rg_BeiChangAnXiangMuSuoYin); }
    private re_XiangMuBeiChangAn rd_XiangMuBeiChangAn;
    private int rd_XiangMuBeiChangAn_tag;
    public void rl_KuoPeiQiZuJianJiChuLei_XiangMuBeiChangAn (re_XiangMuBeiChangAn objListener, int nTagNumber) {
        synchronized (this) { rd_XiangMuBeiChangAn = objListener;  rd_XiangMuBeiChangAn_tag = nTagNumber; }
    }
    public int rg_XiangMuBeiChangAn (int rg_BeiChangAnXiangMuSuoYin) {
        re_XiangMuBeiChangAn objDispatcher;  int nTagNumber;
        synchronized (this) { objDispatcher = rd_XiangMuBeiChangAn;  nTagNumber = rd_XiangMuBeiChangAn_tag; }
        return (objDispatcher != null ? objDispatcher.dispatch (this, nTagNumber, rg_BeiChangAnXiangMuSuoYin) : 0);
    }
}

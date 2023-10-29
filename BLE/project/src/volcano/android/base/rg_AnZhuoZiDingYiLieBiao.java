
package volcano.android.base;

public class rg_AnZhuoZiDingYiLieBiao extends android.widget.ListView {

    private boolean isMeasure = false;
    public rg_AnZhuoZiDingYiLieBiao(android.content.Context context) {
        super(context);
    }
    public rg_AnZhuoZiDingYiLieBiao(android.content.Context context, android.util.AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public rg_AnZhuoZiDingYiLieBiao(android.content.Context context, android.util.AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isMeasure){
            int measureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, measureSpec);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
    public void setMeasure(boolean is){
        this.isMeasure = is;
    }
    public boolean getMeasure(){
        return isMeasure;
    }
}

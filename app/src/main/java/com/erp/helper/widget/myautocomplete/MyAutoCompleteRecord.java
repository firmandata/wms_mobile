package com.erp.helper.widget.myautocomplete;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.erp.wms.R;

public class MyAutoCompleteRecord {

    private long mId;

    protected Context mContext;

    private int mPaddingLeft;
    private int mPaddingTop;
    private int mPaddingRight;
    private int mPaddingBottom;

    public MyAutoCompleteRecord(Context context) {
        mContext = context;

        this.setPadding(
                (int) context.getResources().getDimension(R.dimen.my_autocompleteview_record_padding_left),
                (int) context.getResources().getDimension(R.dimen.my_autocompleteview_record_padding_top),
                (int) context.getResources().getDimension(R.dimen.my_autocompleteview_record_padding_right),
                (int) context.getResources().getDimension(R.dimen.my_autocompleteview_record_padding_bottom)
        );
    }

    public void setId(long id) {
        mId = id;
    }

    public long getId() {
        return mId;
    }

    public LinearLayout getView(){
        LinearLayout llSelf = new LinearLayout(mContext);
        llSelf.setOrientation(LinearLayout.HORIZONTAL);
        llSelf.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom);
        llSelf.addView(getRecord());

        return llSelf;
    }

    public RelativeLayout getRecord() {
        RelativeLayout rlSelf = new RelativeLayout(mContext);

        return rlSelf;
    }

    public void setPadding(int left, int top, int right, int bottom) {
        mPaddingLeft = left;
        mPaddingTop = top;
        mPaddingRight = right;
        mPaddingBottom = bottom;
    }

    public MyAutoCompleteIModel getData(){
        return null;
    }
}

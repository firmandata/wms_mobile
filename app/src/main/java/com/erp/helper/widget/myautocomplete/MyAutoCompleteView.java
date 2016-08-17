package com.erp.helper.widget.myautocomplete;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

public class MyAutoCompleteView extends AutoCompleteTextView {
    private static final int MESSAGE_TEXT_CHANGED = 100;
    private static final int DEFAULT_AUTOCOMPLETE_DELAY = 750;

    private int mAutoCompleteDelay = DEFAULT_AUTOCOMPLETE_DELAY;
    private ProgressBar mLoadingIndicator;

    private MyAutoCompleteViewListener mListener;

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            MyAutoCompleteView.super.performFiltering((CharSequence) msg.obj, msg.arg1);
        }
    };

    public MyAutoCompleteView(Context context) {
        super(context);

        mListener = null;
    }

    public MyAutoCompleteView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setLoadingIndicator(ProgressBar progressBar) {
        mLoadingIndicator = progressBar;
    }

    public void setAutoCompleteDelay(int autoCompleteDelay) {
        mAutoCompleteDelay = autoCompleteDelay;
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        if (mLoadingIndicator != null) {
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }
        mHandler.removeMessages(MESSAGE_TEXT_CHANGED);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_TEXT_CHANGED, text), mAutoCompleteDelay);

        if (mListener != null)
            mListener.onPerformFiltering();
    }

    @Override
    public void onFilterComplete(int count) {
        if (mLoadingIndicator != null) {
            mLoadingIndicator.setVisibility(View.GONE);
        }
        super.onFilterComplete(count);
    }

    public void setListener(MyAutoCompleteViewListener onPerformFiltering) {
        mListener = onPerformFiltering;
    }

    public interface MyAutoCompleteViewListener{
        void onPerformFiltering();
    }
}

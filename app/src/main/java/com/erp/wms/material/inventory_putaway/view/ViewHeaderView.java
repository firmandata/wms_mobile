package com.erp.wms.material.inventory_putaway.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.erp.helper.utils.DateTimeUtil;
import com.erp.wms.R;
import com.erp.wms.material.inventory_putaway.model.HeaderModel;

public class ViewHeaderView extends ScrollView {

    private Context mContext;

    private HeaderModel mData;

    private TextView mTvNo;
    private TextView mTvDate;
    private TextView mTvNotes;

    public ViewHeaderView(Context context) {
        super(context);
        mContext = context;

        this.setScrollBarStyle(ScrollView.SCROLLBARS_OUTSIDE_OVERLAY);
        this.setClipToPadding(false);
        this.setPadding(
                (int) context.getResources().getDimension(R.dimen.layout_padding_left),
                (int) context.getResources().getDimension(R.dimen.layout_padding_top),
                (int) context.getResources().getDimension(R.dimen.layout_padding_right),
                (int) context.getResources().getDimension(R.dimen.layout_padding_bottom)
        );

        LinearLayout scrollViewContainer = new LinearLayout(mContext);
        scrollViewContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        scrollViewContainer.setOrientation(LinearLayout.VERTICAL);

            TextView lblNo = new TextView(mContext);
            lblNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblNo.setText("No");
            scrollViewContainer.addView(lblNo);

            mTvNo = new TextView(mContext);
            mTvNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvNo);

            TextView lblDate = new TextView(mContext);
            lblDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblDate.setText("Date");
            lblDate.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblDate);

            mTvDate = new TextView(mContext);
            mTvDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvDate);

            TextView lblNotes = new TextView(mContext);
            lblNotes.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblNotes.setText("Notes");
            lblNotes.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblNotes);

            mTvNotes = new TextView(mContext);
            mTvNotes.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvNotes);

        this.addView(scrollViewContainer);
    }

    public ViewHeaderView(Context context, HeaderModel data) {
        this(context);
        setData(data);
    }

    public void setData(HeaderModel data) {
        mData = data;

        mTvNo.setText(mData.getPutawayCode());
        mTvDate.setText(DateTimeUtil.ToDateDisplayString(mData.getPutawayDate()));
        mTvNotes.setText(mData.getNotes());
    }

    public HeaderModel getData() {
        return mData;
    }
}

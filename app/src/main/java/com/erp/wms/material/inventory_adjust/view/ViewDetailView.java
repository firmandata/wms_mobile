package com.erp.wms.material.inventory_adjust.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.erp.helper.utils.GeneratorUtil;
import com.erp.helper.widget.mylist.MyListRecord;
import com.erp.wms.R;
import com.erp.wms.material.inventory_adjust.model.DetailModel;

public class ViewDetailView extends MyListRecord {

    private DetailModel mData;

    public ViewDetailView(Context context, DetailModel data) {
        super(context);
        mData = data;

        setId(mData.getInventoryAdjustDetailId());
    }

    @Override
    public void setId(long id) {
        super.setId(id);
        mData.setInventoryAdjustDetailId(id);
    }

    @Override
    public RelativeLayout getRecord() {
        RelativeLayout rlSelf = new RelativeLayout(mContext);

        LinearLayout firstRowFirstCol = firstRowFirstCol();
        firstRowFirstCol.setId(GeneratorUtil.generateViewId());

        TextView firstRowSecondCol = firstRowSecondCol();
        firstRowSecondCol.setId(GeneratorUtil.generateViewId());
        firstRowSecondCol.setGravity(Gravity.CENTER);

		TextView secondRow = secondRow();
        secondRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		
        RelativeLayout.LayoutParams firstRowFirstColLayout = new RelativeLayout.LayoutParams(0, RelativeLayout.LayoutParams.WRAP_CONTENT);
        firstRowFirstColLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        firstRowFirstColLayout.addRule(RelativeLayout.LEFT_OF, firstRowSecondCol.getId());
        firstRowFirstCol.setLayoutParams(firstRowFirstColLayout);

        RelativeLayout.LayoutParams firstRowSecondColLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        firstRowSecondColLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        firstRowSecondCol.setLayoutParams(firstRowSecondColLayout);

		RelativeLayout.LayoutParams secondRowLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        secondRowLayout.addRule(RelativeLayout.BELOW, firstRowFirstCol.getId());
        secondRow.setLayoutParams(secondRowLayout);
		
        rlSelf.addView(firstRowFirstCol);
        rlSelf.addView(firstRowSecondCol);
		rlSelf.addView(secondRow);

        return rlSelf;
    }

    protected LinearLayout firstRowFirstCol() {
        LinearLayout tlSelf = new LinearLayout(mContext);
        tlSelf.setOrientation(LinearLayout.VERTICAL);

        // -- First Line
		TextView tvBarcode = new TextView(mContext);
        tvBarcode.setText(mData.getBarcode());
        tvBarcode.setTypeface(null, Typeface.BOLD);
        tlSelf.addView(tvBarcode);
		
        // -- Second Line
		TextView tvPalletGrid = new TextView(mContext);
        tvPalletGrid.setText(mData.getPallet() + " - " + mData.getGridCode());
        tlSelf.addView(tvPalletGrid);
		
        return tlSelf;
    }

    protected TextView firstRowSecondCol() {
        TextView tvSelf = new TextView(mContext);
        tvSelf.setText(String.valueOf(mData.getQuantityBoxTo()) + "\n" + String.valueOf(mData.getQuantityTo()));
        tvSelf.setTypeface(null, Typeface.BOLD);

        Drawable drawableBorder = mContext.getResources().getDrawable(R.drawable.mylist_quantity_border);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            tvSelf.setBackgroundDrawable(drawableBorder);
        } else {
            tvSelf.setBackground(drawableBorder);
        }

        return tvSelf;
    }

	protected TextView secondRow() {
        TextView tvSelf = new TextView(mContext);
        tvSelf.setText(mData.getProductCode() + " - " + mData.getProductName());
        return tvSelf;
    }

    @Override
    public DetailModel getData() {
        return mData;
    }
}

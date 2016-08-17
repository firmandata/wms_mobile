package com.erp.wms.material.inventory_putaway.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.erp.helper.utils.GeneratorUtil;
import com.erp.helper.widget.mylist.MyListRecord;
import com.erp.wms.R;
import com.erp.wms.material.inventory_putaway.model.DetailModel;

public class ViewDetailView extends MyListRecord {

    private DetailModel mData;

    public ViewDetailView(Context context, DetailModel data) {
        super(context);
        mData = data;

        setId(mData.getInventoryPutawayDetailId());
    }

    @Override
    public void setId(long id) {
        super.setId(id);
        mData.setInventoryPutawayDetailId(id);
    }

    @Override
    public RelativeLayout getRecord() {
        RelativeLayout rlSelf = new RelativeLayout(mContext);

        LinearLayout firstRowFirstCol = firstRowFirstCol();
        firstRowFirstCol.setId(GeneratorUtil.generateViewId());

        TextView firstRowSecondCol = firstRowSecondCol();
        firstRowSecondCol.setId(GeneratorUtil.generateViewId());
        firstRowSecondCol.setGravity(Gravity.CENTER);

        RelativeLayout.LayoutParams firstRowFirstColLayout = new RelativeLayout.LayoutParams(0, RelativeLayout.LayoutParams.WRAP_CONTENT);
        firstRowFirstColLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        firstRowFirstColLayout.addRule(RelativeLayout.LEFT_OF, firstRowSecondCol.getId());
        firstRowFirstCol.setLayoutParams(firstRowFirstColLayout);

        RelativeLayout.LayoutParams firstRowSecondColLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        firstRowSecondColLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        firstRowSecondCol.setLayoutParams(firstRowSecondColLayout);

        rlSelf.addView(firstRowFirstCol);
        rlSelf.addView(firstRowSecondCol);

        return rlSelf;
    }

    protected LinearLayout firstRowFirstCol() {
        LinearLayout tlSelf = new LinearLayout(mContext);
        tlSelf.setOrientation(LinearLayout.VERTICAL);

        // -- First Line
        TextView tvPallet = new TextView(mContext);
        tvPallet.setText(mData.getPallet());
        tvPallet.setTypeface(null, Typeface.BOLD);
        tvPallet.setSingleLine();
        tlSelf.addView(tvPallet);

        // -- Second Line
        TextView tvGridToCode = new TextView(mContext);
        tvGridToCode.setText(mData.getGridToCode());
        tlSelf.addView(tvGridToCode);

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

    @Override
    public DetailModel getData() {
        return mData;
    }
}

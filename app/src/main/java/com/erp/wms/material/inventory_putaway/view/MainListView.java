package com.erp.wms.material.inventory_putaway.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.erp.helper.utils.DateTimeUtil;
import com.erp.helper.utils.GeneratorUtil;
import com.erp.helper.widget.mylist.MyListRecord;
import com.erp.wms.R;
import com.erp.wms.material.inventory_putaway.model.HeaderModel;

public class MainListView extends MyListRecord {

    private HeaderModel mData;

    public MainListView(Context context, HeaderModel data) {
        super(context);
        mData = data;

        setId(mData.getInventoryPutawayId());
    }

    @Override
    public void setId(long id) {
        super.setId(id);
        mData.setInventoryPutawayId(id);
    }

    @Override
    public RelativeLayout getRecord() {
        RelativeLayout rlSelf = new RelativeLayout(mContext);

        // -- First Row --
        TableLayout firstRowFirstCol = firstRowFirstCol();
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

    protected TableLayout firstRowFirstCol() {
        TableLayout tlSelf = new TableLayout(mContext);
        tlSelf.setStretchAllColumns(true);
        tlSelf.setShrinkAllColumns(true);

        // -- First Line
        TableRow trLine1 = new TableRow(mContext);
        trLine1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView tvPutawayCode = new TextView(mContext);
        tvPutawayCode.setText(mData.getPutawayCode());
        tvPutawayCode.setTypeface(null, Typeface.BOLD);
        tvPutawayCode.setSingleLine();
        trLine1.addView(tvPutawayCode);

        TextView tvPutawayDate = new TextView(mContext);
        tvPutawayDate.setText(DateTimeUtil.ToDateDisplayString(mData.getPutawayDate()));
        tvPutawayDate.setSingleLine();
        trLine1.addView(tvPutawayDate);

        tlSelf.addView(trLine1);

        // -- Second Line
        TableRow trLine2 = new TableRow(mContext);
        trLine2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView tvPallet = new TextView(mContext);
        tvPallet.setText("Pallet Count : " + mData.getPallet());
        trLine2.addView(tvPallet);

        tlSelf.addView(trLine2);

        return tlSelf;
    }

    protected TextView firstRowSecondCol() {
        TextView tvSelf = new TextView(mContext);
        tvSelf.setText(mData.getQuantityBoxTo() + "\n" + mData.getQuantityTo());
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
    public HeaderModel getData() {
        return mData;
    }
}
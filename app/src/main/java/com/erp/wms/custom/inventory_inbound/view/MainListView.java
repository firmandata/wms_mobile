package com.erp.wms.custom.inventory_inbound.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.erp.helper.utils.DateTimeUtil;
import com.erp.helper.utils.GeneratorUtil;
import com.erp.helper.widget.mylist.MyListRecord;
import com.erp.wms.R;
import com.erp.wms.custom.inventory_inbound.model.Model;

public class MainListView extends MyListRecord {

    private Model mData;

    public MainListView(Context context, Model data) {
        super(context);
        mData = data;

        setId(mData.getCusInventoryInboundDetailId());
    }

    @Override
    public void setId(long id) {
        super.setId(id);
        mData.setCusInventoryInboundDetailId(id);
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

        // -- Second Row --
        TextView secondRow = secondRow();
        secondRow.setId(GeneratorUtil.generateViewId());
        secondRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        RelativeLayout.LayoutParams secondRowLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        secondRowLayout.addRule(RelativeLayout.BELOW, firstRowFirstCol.getId());
        secondRow.setLayoutParams(secondRowLayout);

        // -- Third Row --
        TextView thirdRow = thirdRow();
        thirdRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        RelativeLayout.LayoutParams thirdRowLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        thirdRowLayout.addRule(RelativeLayout.BELOW, secondRow.getId());
        thirdRow.setLayoutParams(thirdRowLayout);

        rlSelf.addView(firstRowFirstCol);
        rlSelf.addView(firstRowSecondCol);
        rlSelf.addView(secondRow);
        rlSelf.addView(thirdRow);

        return rlSelf;
    }

    protected TableLayout firstRowFirstCol() {
        TableLayout tlSelf = new TableLayout(mContext);
        tlSelf.setStretchAllColumns(true);
        tlSelf.setShrinkAllColumns(true);

        // -- First Line
        TableRow trLine1 = new TableRow(mContext);
        trLine1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView tvBarcode = new TextView(mContext);
        tvBarcode.setText(mData.getBarcode());
        tvBarcode.setTypeface(null, Typeface.BOLD);
        trLine1.addView(tvBarcode);

        tlSelf.addView(trLine1);

        // -- Second Line
        TableRow trLine2 = new TableRow(mContext);
        trLine2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView tvPallet = new TextView(mContext);
        tvPallet.setText(mData.getPallet());
        trLine2.addView(tvPallet);

        TextView tvCreatedDate = new TextView(mContext);
        tvCreatedDate.setText(DateTimeUtil.ToDateDisplayString(mData.getCreatedDate()));
        tvCreatedDate.setSingleLine();
        trLine2.addView(tvCreatedDate);

        tlSelf.addView(trLine2);

        return tlSelf;
    }

    protected TextView firstRowSecondCol() {
        TextView tvSelf = new TextView(mContext);
        tvSelf.setText(String.valueOf(mData.getQuantity()));
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
        String productDescription = mData.getProductCode()
                + " - " + mData.getProductName();

        TextView tvSelf = new TextView(mContext);
        tvSelf.setText(productDescription);

        return tvSelf;
    }

    protected TextView thirdRow() {
        String volume = "L : " + String.valueOf(mData.getVolumeLength()) + "m"
                + ", W : " + String.valueOf(mData.getVolumeWidth()) + "m"
                + ", H : " + String.valueOf(mData.getVolumeHeight()) + "m";

        TextView tvSelf = new TextView(mContext);
        tvSelf.setText(volume);

        return tvSelf;
    }

    @Override
    public Model getData(){
        return mData;
    }
}
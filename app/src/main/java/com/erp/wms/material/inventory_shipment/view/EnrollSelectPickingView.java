package com.erp.wms.material.inventory_shipment.view;

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
import com.erp.wms.material.inventory_shipment.model.PickingModel;

public class EnrollSelectPickingView extends MyListRecord {

    private PickingModel mData;

    public EnrollSelectPickingView(Context context, PickingModel data) {
        super(context);
        mData = data;

        setId(mData.getInventoryPickingId());
    }

    @Override
    public void setId(long id) {
        super.setId(id);
        mData.setInventoryPickingId(id);
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
        secondRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        RelativeLayout.LayoutParams secondRowLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        secondRowLayout.addRule(RelativeLayout.BELOW, firstRowFirstCol.getId());
        secondRow.setLayoutParams(secondRowLayout);

        rlSelf.addView(firstRowFirstCol);
        rlSelf.addView(firstRowSecondCol);
        rlSelf.addView(secondRow);

        return rlSelf;
    }

    protected TableLayout firstRowFirstCol() {
        TableLayout tlSelf = new TableLayout(mContext);
        tlSelf.setStretchAllColumns(true);
        tlSelf.setShrinkAllColumns(true);

        // -- First Line
        TableRow trLine1 = new TableRow(mContext);
        trLine1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView tvInventoryPickingCode = new TextView(mContext);
        tvInventoryPickingCode.setText(mData.getInventoryPickingCode());
        tvInventoryPickingCode.setTypeface(null, Typeface.BOLD);
        tvInventoryPickingCode.setSingleLine();
        trLine1.addView(tvInventoryPickingCode);

        TextView tvInventoryPickingDate = new TextView(mContext);
        tvInventoryPickingDate.setText(DateTimeUtil.ToDateDisplayString(mData.getInventoryPickingDate()));
        tvInventoryPickingDate.setSingleLine();
        trLine1.addView(tvInventoryPickingDate);

        tlSelf.addView(trLine1);

        // -- Second Line
        TableRow trLine2 = new TableRow(mContext);
        trLine2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView tvRequestArriveDate = new TextView(mContext);
        tvRequestArriveDate.setText("RAD : " + DateTimeUtil.ToDateDisplayString(mData.getOrderOutRequestArriveDate()));
        tvRequestArriveDate.setSingleLine();
        trLine2.addView(tvRequestArriveDate);

        tlSelf.addView(trLine2);

        // -- Third Line
        TableRow trLine3 = new TableRow(mContext);
        trLine3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView tvOrderOutCode = new TextView(mContext);
        tvOrderOutCode.setText(mData.getOrderOutCode());
        trLine3.addView(tvOrderOutCode);

        TextView tvOrderOutDate = new TextView(mContext);
        tvOrderOutDate.setText(DateTimeUtil.ToDateDisplayString(mData.getOrderOutDate()));
        tvOrderOutDate.setSingleLine();
        trLine3.addView(tvOrderOutDate);

        tlSelf.addView(trLine3);

        return tlSelf;
    }

    protected TextView firstRowSecondCol() {
        TextView tvSelf = new TextView(mContext);
        tvSelf.setText(mData.getQuantityBox() + "\n" + mData.getQuantity());
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
        tvSelf.setText(mData.getBusinessPartner());
        return tvSelf;
    }

    @Override
    public PickingModel getData() {
        return mData;
    }
}

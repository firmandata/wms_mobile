package com.erp.wms.material.inventory_picking.view;

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
import com.erp.wms.material.inventory_picking.model.HeaderModel;

public class MainListView extends MyListRecord {

    private HeaderModel mData;

    public MainListView(Context context, HeaderModel data) {
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

        TableLayout firstRowFirstCol = firstRowFirstCol();
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

    protected TableLayout firstRowFirstCol() {
        TableLayout tlSelf = new TableLayout(mContext);
        tlSelf.setStretchAllColumns(true);
        tlSelf.setShrinkAllColumns(true);

        // -- First Line
        TableRow trLine1 = new TableRow(mContext);
        trLine1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView tvPickingCode = new TextView(mContext);
        tvPickingCode.setText(mData.getPickingCode());
        tvPickingCode.setTypeface(null, Typeface.BOLD);
        tvPickingCode.setSingleLine();
        trLine1.addView(tvPickingCode);

        TextView tvPickingDate = new TextView(mContext);
        tvPickingDate.setText(DateTimeUtil.ToDateDisplayString(mData.getPickingDate()));
        tvPickingDate.setSingleLine();
        trLine1.addView(tvPickingDate);

        tlSelf.addView(trLine1);

        // -- Second Line
        TableRow trLine2 = new TableRow(mContext);
        trLine2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

        TextView tvOrderOutCode = new TextView(mContext);
        tvOrderOutCode.setText(mData.getOrderOutCode());
        trLine2.addView(tvOrderOutCode);

        TextView tvOrderOutDate = new TextView(mContext);
        tvOrderOutDate.setText(DateTimeUtil.ToDateDisplayString(mData.getOrderOutDate()));
        tvOrderOutDate.setSingleLine();
        trLine2.addView(tvOrderOutDate);

        tlSelf.addView(trLine2);

        return tlSelf;
    }

    protected TextView firstRowSecondCol() {
        TextView tvSelf = new TextView(mContext);
        tvSelf.setText(mData.getStatusInventoryShipment());
        tvSelf.setTextSize(10);

        Drawable drawableBorder = mContext.getResources().getDrawable(R.drawable.mylist_status_border);
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
    public HeaderModel getData() {
        return mData;
    }
}
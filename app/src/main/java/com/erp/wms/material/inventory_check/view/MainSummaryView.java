package com.erp.wms.material.inventory_check.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.erp.helper.utils.DateTimeUtil;
import com.erp.wms.R;
import com.erp.wms.material.inventory_check.model.MainSummaryModel;

public class MainSummaryView extends ScrollView {

    private Context mContext;

    private TextView mTvLine1ExistBox;
    private TextView mTvLine1ExistQuantity;
    private TextView mTvLine2AllocatedBox;
    private TextView mTvLine2AllocatedQuantity;
    private TextView mTvLine3PickedBox;
    private TextView mTvLine3PickedQuantity;
    private TextView mTvLine4OnhandBox;
    private TextView mTvLine4OnhandQuantity;

    public MainSummaryView(Context context) {
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

            TableLayout tlSelf = new TableLayout(mContext);
            tlSelf.setStretchAllColumns(true);
            tlSelf.setShrinkAllColumns(true);

                // -- Header
                TableRow trLineH = new TableRow(mContext);
                trLineH.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView tvLineHCaption = new TextView(mContext);
                tvLineHCaption.setText("");
                trLineH.addView(tvLineHCaption);

                TextView tvLineHBox = new TextView(mContext);
                tvLineHBox.setText("Box");
                trLineH.addView(tvLineHBox);

                TextView tvLineHQuantity = new TextView(mContext);
                tvLineHQuantity.setText("Quantity");
                trLineH.addView(tvLineHQuantity);

            tlSelf.addView(trLineH);

                // -- First Line - Exist
                TableRow trLine1 = new TableRow(mContext);
                trLine1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView tvLine1Exist = new TextView(mContext);
                tvLine1Exist.setText("Exist");
                trLine1.addView(tvLine1Exist);

                mTvLine1ExistBox = new TextView(mContext);
                trLine1.addView(mTvLine1ExistBox);

                mTvLine1ExistQuantity = new TextView(mContext);
                trLine1.addView(mTvLine1ExistQuantity);

            tlSelf.addView(trLine1);

                // -- Second Line - Allocated
                TableRow trLine2 = new TableRow(mContext);
                trLine2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView tvLine2Allocated = new TextView(mContext);
                tvLine2Allocated.setText("Allocated");
                trLine2.addView(tvLine2Allocated);

                mTvLine2AllocatedBox = new TextView(mContext);
                trLine2.addView(mTvLine2AllocatedBox);

                mTvLine2AllocatedQuantity = new TextView(mContext);
                trLine2.addView(mTvLine2AllocatedQuantity);

            tlSelf.addView(trLine2);

                // -- Third Line - Picked
                TableRow trLine3 = new TableRow(mContext);
                trLine3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView tvLine3Picked = new TextView(mContext);
                tvLine3Picked.setText("Picked");
                trLine3.addView(tvLine3Picked);

                mTvLine3PickedBox = new TextView(mContext);
                trLine3.addView(mTvLine3PickedBox);

                mTvLine3PickedQuantity = new TextView(mContext);
                trLine3.addView(mTvLine3PickedQuantity);

            tlSelf.addView(trLine3);

                // -- Fourth Line - Onhand
                TableRow trLine4 = new TableRow(mContext);
                trLine4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView tvLine4Onhand = new TextView(mContext);
                tvLine4Onhand.setText("Onhand");
                trLine4.addView(tvLine4Onhand);

                mTvLine4OnhandBox = new TextView(mContext);
                trLine4.addView(mTvLine4OnhandBox);

                mTvLine4OnhandQuantity = new TextView(mContext);
                trLine4.addView(mTvLine4OnhandQuantity);

            tlSelf.addView(trLine4);

        scrollViewContainer.addView(tlSelf);

        this.addView(scrollViewContainer);
    }

    public void setRecord(MainSummaryModel data) {
        mTvLine1ExistBox.setText(String.valueOf(data.getQuantityBoxExist()));
        mTvLine1ExistQuantity.setText(String.valueOf(data.getQuantityExist()));
        mTvLine2AllocatedBox.setText(String.valueOf(data.getQuantityBoxAllocated()));
        mTvLine2AllocatedQuantity.setText(String.valueOf(data.getQuantityAllocated()));
        mTvLine3PickedBox.setText(String.valueOf(data.getQuantityBoxPicked()));
        mTvLine3PickedQuantity.setText(String.valueOf(data.getQuantityPicked()));
        mTvLine4OnhandBox.setText(String.valueOf(data.getQuantityBoxOnhand()));
        mTvLine4OnhandQuantity.setText(String.valueOf(data.getQuantityOnhand()));
    }
}
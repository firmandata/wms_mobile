package com.erp.wms.material.inventory_check.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.erp.helper.utils.DateTimeUtil;
import com.erp.helper.utils.GeneratorUtil;
import com.erp.helper.widget.mylist.MyListRecord;
import com.erp.wms.R;
import com.erp.wms.material.inventory_check.model.MainDetailModel;

public class MainDetailView extends MyListRecord {

    private MainDetailModel mData;

    public MainDetailView(Context context, MainDetailModel data) {
        super(context);
        mData = data;
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    @Override
    public MainDetailModel getData() {
        return mData;
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
        secondRow.setId(GeneratorUtil.generateViewId());
        secondRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView thirdRow = thirdRow();
        thirdRow.setId(GeneratorUtil.generateViewId());
        thirdRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView fourthRow = fourthRow();
        fourthRow.setId(GeneratorUtil.generateViewId());
        fourthRow.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

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

        RelativeLayout.LayoutParams thirdRowLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        thirdRowLayout.addRule(RelativeLayout.BELOW, secondRow.getId());
        thirdRow.setLayoutParams(thirdRowLayout);

        RelativeLayout.LayoutParams fourthRowLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        fourthRowLayout.addRule(RelativeLayout.BELOW, thirdRow.getId());
        fourthRow.setLayoutParams(fourthRowLayout);

        rlSelf.addView(firstRowFirstCol);
        rlSelf.addView(firstRowSecondCol);
        rlSelf.addView(secondRow);
        rlSelf.addView(thirdRow);
        rlSelf.addView(fourthRow);

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
        TextView tvPalletCartonNo = new TextView(mContext);
        tvPalletCartonNo.setText(mData.getPallet() + "/" + mData.getCartonNo());
        tlSelf.addView(tvPalletCartonNo);

        // -- Third Line
        TextView tvProductCodeProductUom = new TextView(mContext);
        tvProductCodeProductUom.setText(mData.getProductCode() + " - " + mData.getProductName());
        tlSelf.addView(tvProductCodeProductUom);

        return tlSelf;
    }

    protected TextView firstRowSecondCol() {
        TextView tvSelf = new TextView(mContext);
        tvSelf.setText(mData.getQuantityBoxExist() + "\n" + mData.getQuantityExist());
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
        tvSelf.setText(mData.getWarehouseName() + " - " + mData.getGridCode());
        return tvSelf;
    }

    protected TextView thirdRow() {
        String thirdRowDescription = "Pack." + DateTimeUtil.ToDateDisplayString(mData.getPackedDate())
                + " Exp." + DateTimeUtil.ToDateDisplayString(mData.getExpiredDate());

        TextView tvSelf = new TextView(mContext);
        tvSelf.setText(thirdRowDescription);
        tvSelf.setSingleLine();
        return tvSelf;
    }

    protected TextView fourthRow() {
        String fourthRowDescription = " Vol.(L:" + String.valueOf(mData.getVolumeLength()) + "m"
                + ",W:" + String.valueOf(mData.getVolumeWidth()) + "m"
                + ",H:" + String.valueOf(mData.getVolumeHeight()) + "m)";

        TextView tvSelf = new TextView(mContext);
        tvSelf.setText(fourthRowDescription);
        tvSelf.setSingleLine();
        return tvSelf;
    }
}

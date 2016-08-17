package com.erp.wms.material.inventory_inbound.view;

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
import com.erp.wms.material.inventory_inbound.model.DetailModel;

public class ViewDetailView extends MyListRecord {

    private DetailModel mData;

    public ViewDetailView(Context context, DetailModel data) {
        super(context);
        mData = data;

        setId(mData.getInventoryInboundDetailId());
    }

    @Override
    public void setId(long id) {
        super.setId(id);
        mData.setInventoryInboundDetailId(id);
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
        TextView tvPalletCartonNo = new TextView(mContext);
        tvPalletCartonNo.setText("Plt." + mData.getPallet() + " Ctn." + mData.getCartonNo());
        tlSelf.addView(tvPalletCartonNo);

        // -- Third Line
        TextView tvProductCodeProductUom = new TextView(mContext);
        tvProductCodeProductUom.setText(mData.getProductCode() + "(" + mData.getProductUom() + ")");
        tlSelf.addView(tvProductCodeProductUom);

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
        tvSelf.setText(mData.getProductName());
        return tvSelf;
    }

    @Override
    public DetailModel getData() {
        return mData;
    }
}

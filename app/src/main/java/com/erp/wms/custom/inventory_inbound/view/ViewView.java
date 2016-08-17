package com.erp.wms.custom.inventory_inbound.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.erp.helper.utils.DateTimeUtil;
import com.erp.wms.R;
import com.erp.wms.custom.inventory_inbound.model.Model;

public class ViewView extends ScrollView {

    private Context mContext;

    private Model mData;

    private TextView mTvBarcode;
    private TextView mTvProductCode;
    private TextView mTvProductName;
    private TextView mTvProductUom;
    private TextView mTvGridCode;
    private TextView mTvPallet;
    private TextView mTvCartonNo;
    private TextView mTvQuantity;
    private TextView mTvLotNo;
    private TextView mTvCondition;
    private TextView mTvPackedDate;
    private TextView mTvExpiredDate;
    private TextView mTvVolumeLength;
    private TextView mTvVolumeWidth;
    private TextView mTvVolumeHeight;
    private TextView mTvCreatedDate;

    public ViewView(Context context) {
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

            TextView lblBarcode = new TextView(mContext);
            lblBarcode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblBarcode.setText("Barcode");
            scrollViewContainer.addView(lblBarcode);

            mTvBarcode = new TextView(mContext);
            mTvBarcode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvBarcode);

            TextView lblProductCode = new TextView(mContext);
            lblProductCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblProductCode.setText("Product Code");
            lblProductCode.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblProductCode);

            mTvProductCode = new TextView(mContext);
            mTvProductCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvProductCode);

            TextView lblProductName = new TextView(mContext);
            lblProductName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblProductName.setText("Product Name");
            lblProductName.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblProductName);

            mTvProductName = new TextView(mContext);
            mTvProductName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvProductName);

            TextView lblProductUom = new TextView(mContext);
            lblProductUom.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblProductUom.setText("Product UOM");
            lblProductUom.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblProductUom);

            mTvProductUom = new TextView(mContext);
            mTvProductUom.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvProductUom);

            TextView lblGridCode = new TextView(mContext);
            lblGridCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblGridCode.setText("Grid");
            lblGridCode.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblGridCode);

            mTvGridCode = new TextView(mContext);
            mTvGridCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvGridCode);

            TextView lblPallet = new TextView(mContext);
            lblPallet.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblPallet.setText("Pallet");
            lblPallet.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblPallet);

            mTvPallet = new TextView(mContext);
            mTvPallet.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvPallet);

            TextView lblCartonNo = new TextView(mContext);
            lblCartonNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblCartonNo.setText("Carton No");
            lblCartonNo.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblCartonNo);

            mTvCartonNo = new TextView(mContext);
            mTvCartonNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvCartonNo);

            TextView lblQuantity = new TextView(mContext);
            lblQuantity.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblQuantity.setText("Quantity");
            lblQuantity.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblQuantity);

            mTvQuantity = new TextView(mContext);
            mTvQuantity.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvQuantity);

            TextView lblLotNo = new TextView(mContext);
            lblLotNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblLotNo.setText("Lot No");
            lblLotNo.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblLotNo);

            mTvLotNo = new TextView(mContext);
            mTvLotNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvLotNo);

            TextView lblVolume = new TextView(mContext);
            lblVolume.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblVolume.setText("Volume");
            lblVolume.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblVolume);

            LinearLayout containerVolume = new LinearLayout(mContext);
            containerVolume.setOrientation(LinearLayout.HORIZONTAL);

                TextView lblVolumeLength = new TextView(mContext);
                lblVolumeLength.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                lblVolumeLength.setText("L :");
                lblVolumeLength.setPadding(0, 0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0);
                containerVolume.addView(lblVolumeLength);

                mTvVolumeLength = new TextView(mContext);
                mTvVolumeLength.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                containerVolume.addView(mTvVolumeLength);

                TextView lblVolumeWidth = new TextView(mContext);
                lblVolumeWidth.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                lblVolumeWidth.setText("W :");
                lblVolumeWidth.setPadding(0, 0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0);
                containerVolume.addView(lblVolumeWidth);

                mTvVolumeWidth = new TextView(mContext);
                mTvVolumeWidth.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                containerVolume.addView(mTvVolumeWidth);

                TextView lblVolumeHeight = new TextView(mContext);
                lblVolumeHeight.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                lblVolumeHeight.setText("H :");
                lblVolumeHeight.setPadding(0, 0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0);
                containerVolume.addView(lblVolumeHeight);

                mTvVolumeHeight = new TextView(mContext);
                mTvVolumeHeight.setLayoutParams(new TableLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
                containerVolume.addView(mTvVolumeHeight);

            containerVolume.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(containerVolume);

            TextView lblCondition = new TextView(mContext);
            lblCondition.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblCondition.setText("Condition");
            lblCondition.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblCondition);

            mTvCondition = new TextView(mContext);
            mTvCondition.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvCondition);

            TextView lblPackedDate = new TextView(mContext);
            lblPackedDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblPackedDate.setText("Packed Date");
            lblPackedDate.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblPackedDate);

            mTvPackedDate = new TextView(mContext);
            mTvPackedDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvPackedDate);

            TextView lblExpiredDate = new TextView(mContext);
            lblExpiredDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblExpiredDate.setText("Expired Date");
            lblExpiredDate.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblExpiredDate);

            mTvExpiredDate = new TextView(mContext);
            mTvExpiredDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvExpiredDate);

            TextView lblCreatedDate = new TextView(mContext);
            lblCreatedDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblCreatedDate.setText("Created Date");
            lblCreatedDate.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblCreatedDate);

            mTvCreatedDate = new TextView(mContext);
            mTvCreatedDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvCreatedDate);

        this.addView(scrollViewContainer);
    }

    public ViewView(Context context, Model data) {
        this(context);
        setData(data);
    }

    public void setData(Model data) {
        mData = data;

        mTvBarcode.setText(mData.getBarcode());
        mTvProductCode.setText(mData.getProductCode());
        mTvProductName.setText(mData.getProductName());
        mTvProductUom.setText(mData.getProductUom());
        mTvGridCode.setText(mData.getGridCode());
        mTvPallet.setText(mData.getPallet());
        mTvCartonNo.setText(mData.getCartonNo());
        mTvQuantity.setText(String.valueOf(mData.getQuantity()));
        mTvLotNo.setText(mData.getLotNo());
        mTvCondition.setText(mData.getCondition());
        mTvPackedDate.setText(DateTimeUtil.ToDateDisplayString(mData.getPackedDate()));
        mTvExpiredDate.setText(DateTimeUtil.ToDateDisplayString(mData.getExpiredDate()));

        String volumeLength = String.valueOf(mData.getVolumeLength()) + "m";
        mTvVolumeLength.setText(volumeLength);

        String volumeWidth = String.valueOf(mData.getVolumeWidth()) + "m";
        mTvVolumeWidth.setText(volumeWidth);

        String volumeHeight = String.valueOf(mData.getVolumeHeight()) + "m";
        mTvVolumeHeight.setText(volumeHeight);

        mTvCreatedDate.setText(DateTimeUtil.ToDateDisplayString(mData.getCreatedDate()));
    }

    public Model getData() {
        return mData;
    }
}

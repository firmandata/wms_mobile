package com.erp.wms.material.inventory_shipment.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.erp.helper.utils.DateTimeUtil;
import com.erp.helper.widget.DataPickerView;
import com.erp.wms.R;
import com.erp.wms.material.inventory_shipment.model.HeaderModel;

import java.util.Calendar;

public class ViewHeaderView extends ScrollView {

    private Context mContext;

    private HeaderModel mData;

    private TextView mTvNo;
    private TextView mTvDate;
    private TextView mTvShipmentType;
    private TextView mTvRequestArriveDate;
    private TextView mTvEstimatedTimeArrive;
    private TextView mTvShipmentTo;
    private TextView mTvVehicleNo;
    private TextView mTvVehicleDriver;
    private TextView mTvTransportMode;
    private TextView mTvPoliceName;
    private TextView mTvNotes;

    public ViewHeaderView(Context context) {
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

            TextView lblNo = new TextView(mContext);
            lblNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblNo.setText("No");
            scrollViewContainer.addView(lblNo);

            mTvNo = new TextView(mContext);
            mTvNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvNo);

            TextView lblDate = new TextView(mContext);
            lblDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblDate.setText("Date");
            lblDate.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblDate);

            mTvDate = new TextView(mContext);
            mTvDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvDate);

            TextView lblShipmentType = new TextView(mContext);
            lblShipmentType.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblShipmentType.setText("Shipment Type");
            lblShipmentType.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblShipmentType);

            mTvShipmentType = new TextView(mContext);
            mTvShipmentType.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvShipmentType);

            TextView lblRequestArriveDate = new TextView(mContext);
            lblRequestArriveDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblRequestArriveDate.setText("Request Arrive Date");
            lblRequestArriveDate.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblRequestArriveDate);

            mTvRequestArriveDate = new TextView(mContext);
            mTvRequestArriveDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvRequestArriveDate);

            TextView lblEstimatedTimeArrive = new TextView(mContext);
            lblEstimatedTimeArrive.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblEstimatedTimeArrive.setText("Estimated Time Arrive");
            lblEstimatedTimeArrive.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblEstimatedTimeArrive);

            mTvEstimatedTimeArrive = new TextView(mContext);
            mTvEstimatedTimeArrive.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvEstimatedTimeArrive);

            TextView lblShipmentTo = new TextView(mContext);
            lblShipmentTo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblShipmentTo.setText("Shipment To");
            lblShipmentTo.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblShipmentTo);

            mTvShipmentTo = new TextView(mContext);
            mTvShipmentTo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvShipmentTo);

            TextView lblVehicleNo = new TextView(mContext);
            lblVehicleNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblVehicleNo.setText("Vehicle No");
            lblVehicleNo.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblVehicleNo);

            mTvVehicleNo = new TextView(mContext);
            mTvVehicleNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvVehicleNo);

            TextView lblVehicleDriver = new TextView(mContext);
            lblVehicleDriver.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblVehicleDriver.setText("Vehicle Driver");
            lblVehicleDriver.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblVehicleDriver);

            mTvVehicleDriver = new TextView(mContext);
            mTvVehicleDriver.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvVehicleDriver);

            TextView lblTransportMode = new TextView(mContext);
            lblTransportMode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblTransportMode.setText("Transport Mode");
            lblTransportMode.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblTransportMode);

            mTvTransportMode = new TextView(mContext);
            mTvTransportMode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvTransportMode);

            TextView lblPoliceName = new TextView(mContext);
            lblPoliceName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblPoliceName.setText("Police Name");
            lblPoliceName.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblPoliceName);

            mTvPoliceName = new TextView(mContext);
            mTvPoliceName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvPoliceName);

            TextView lblNotes = new TextView(mContext);
            lblNotes.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblNotes.setText("Notes");
            lblNotes.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblNotes);

            mTvNotes = new TextView(mContext);
            mTvNotes.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mTvNotes);

        this.addView(scrollViewContainer);
    }

    public ViewHeaderView(Context context, HeaderModel data) {
        this(context);
        setData(data);
    }

    public void setData(HeaderModel data) {
        mData = data;

        mTvNo.setText(mData.getShipmentCode());
        mTvDate.setText(DateTimeUtil.ToDateDisplayString(mData.getShipmentDate()));
        mTvShipmentType.setText(mData.getShipmentType());
        mTvRequestArriveDate.setText(DateTimeUtil.ToDateDisplayString(mData.getRequestArriveDate()));
        mTvEstimatedTimeArrive.setText(DateTimeUtil.ToDateDisplayString(mData.getEstimatedTimeArrive()));
        mTvShipmentTo.setText(mData.getShipmentTo());
        mTvVehicleNo.setText(mData.getVehicleNo());
        mTvVehicleDriver.setText(mData.getVehicleDriver());
        mTvTransportMode.setText(mData.getTransportMode());
        mTvPoliceName.setText(mData.getPoliceName());
        mTvNotes.setText(mData.getNotes());
    }

    public HeaderModel getData() {
        return mData;
    }
}

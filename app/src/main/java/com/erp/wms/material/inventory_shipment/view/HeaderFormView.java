package com.erp.wms.material.inventory_shipment.view;

import android.content.Context;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.erp.helper.utils.DrawableUtil;
import com.erp.helper.widget.DataPickerView;
import com.erp.wms.R;
import com.erp.wms.material.inventory_shipment.model.HeaderModel;

public class HeaderFormView extends ScrollView {

    private Context mContext;

    private EditText mEtNo;
    private DataPickerView mDpDate;
    private Spinner mSpShipmentType;
    private DataPickerView mDpRequestArriveDate;
    private DataPickerView mDpEstimatedTimeArrive;
    private EditText mEtShipmentTo;
    private EditText mEtVehicleNo;
    private EditText mEtVehicleDriver;
    private Spinner mSpTransportMode;
    private EditText mEtPoliceName;
    private EditText mEtNotes;

    private Button mButtonSubmit;

    public HeaderFormView(Context context) {
        super(context);
        mContext = context;

        this.setScrollBarStyle(LinearLayout.SCROLLBARS_OUTSIDE_OVERLAY);
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

            mEtNo = new EditText(mContext);
            mEtNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtNo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtNo.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            scrollViewContainer.addView(mEtNo);

            TextView lblDate = new TextView(mContext);
            lblDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblDate.setText("Date");
            lblDate.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblDate);

            mDpDate = new DataPickerView(mContext);
            mDpDate.setLayoutParams(new DataPickerView.LayoutParams(DataPickerView.LayoutParams.MATCH_PARENT, DataPickerView.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mDpDate);

            TextView lblShipmentType = new TextView(mContext);
            lblShipmentType.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblShipmentType.setText("Shipment Type");
            lblShipmentType.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblShipmentType);

            String shipmentTypes[] = {"", "LOCO", "FRANCO"};
            ArrayAdapter<String> spShipmentTypeAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, shipmentTypes);
            spShipmentTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mSpShipmentType = new Spinner(mContext);
            mSpShipmentType.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mSpShipmentType.setAdapter(spShipmentTypeAdapter);
            scrollViewContainer.addView(mSpShipmentType);

            TextView lblRequestArriveDate = new TextView(mContext);
            lblRequestArriveDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblRequestArriveDate.setText("Request Arrive Date");
            lblRequestArriveDate.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblRequestArriveDate);

            mDpRequestArriveDate = new DataPickerView(mContext);
            mDpRequestArriveDate.setLayoutParams(new DataPickerView.LayoutParams(DataPickerView.LayoutParams.MATCH_PARENT, DataPickerView.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mDpRequestArriveDate);

            TextView lblEstimatedTimeArrive = new TextView(mContext);
            lblEstimatedTimeArrive.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblEstimatedTimeArrive.setText("Estimated Time Arrive");
            lblEstimatedTimeArrive.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblEstimatedTimeArrive);

            mDpEstimatedTimeArrive = new DataPickerView(mContext);
            mDpEstimatedTimeArrive.setLayoutParams(new DataPickerView.LayoutParams(DataPickerView.LayoutParams.MATCH_PARENT, DataPickerView.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mDpEstimatedTimeArrive);

            TextView lblShipmentTo = new TextView(mContext);
            lblShipmentTo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblShipmentTo.setText("Shipment To");
            lblShipmentTo.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblShipmentTo);

            mEtShipmentTo = new EditText(mContext);
            mEtShipmentTo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtShipmentTo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            mEtShipmentTo.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            scrollViewContainer.addView(mEtShipmentTo);

            TextView lblVehicleNo = new TextView(mContext);
            lblVehicleNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblVehicleNo.setText("Vehicle No");
            lblVehicleNo.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblVehicleNo);

            mEtVehicleNo = new EditText(mContext);
            mEtVehicleNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtVehicleNo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtVehicleNo.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            scrollViewContainer.addView(mEtVehicleNo);

            TextView lblVehicleDriver = new TextView(mContext);
            lblVehicleDriver.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblVehicleDriver.setText("Vehicle Driver");
            lblVehicleDriver.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblVehicleDriver);

            mEtVehicleDriver = new EditText(mContext);
            mEtVehicleDriver.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtVehicleDriver.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            mEtVehicleDriver.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            scrollViewContainer.addView(mEtVehicleDriver);

            TextView lblTransportMode = new TextView(mContext);
            lblTransportMode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblTransportMode.setText("Transport Mode");
            lblTransportMode.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblTransportMode);

            String transportModes[] = {"", "CD4", "CD6", "CNT", "BU"};
            ArrayAdapter<String> spTransportModeAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, transportModes);
            spTransportModeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mSpTransportMode = new Spinner(mContext);
            mSpTransportMode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mSpTransportMode.setAdapter(spTransportModeAdapter);
            scrollViewContainer.addView(mSpTransportMode);

            TextView lblPoliceName = new TextView(mContext);
            lblPoliceName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblPoliceName.setText("Police Name");
            lblPoliceName.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblPoliceName);

            mEtPoliceName = new EditText(mContext);
            mEtPoliceName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtPoliceName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
            mEtPoliceName.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            scrollViewContainer.addView(mEtPoliceName);

            TextView lblNotes = new TextView(mContext);
            lblNotes.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblNotes.setText("Notes");
            lblNotes.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblNotes);

            mEtNotes = new EditText(mContext);
            mEtNotes.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtNotes.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            scrollViewContainer.addView(mEtNotes);

            LinearLayout submitLine = new LinearLayout(context);
            submitLine.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            submitLine.setOrientation(LinearLayout.HORIZONTAL);
            submitLine.setGravity(Gravity.CENTER);

                mButtonSubmit = new Button(context);
                mButtonSubmit.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                mButtonSubmit.setText("Save");
                mButtonSubmit.setCompoundDrawablesWithIntrinsicBounds(DrawableUtil.resizeIcon(mContext, mContext.getResources().getDrawable(R.drawable.save), 25, 25), null, null, null);

            submitLine.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            submitLine.addView(mButtonSubmit);
            scrollViewContainer.addView(submitLine);

        this.addView(scrollViewContainer);
    }

    public HeaderModel getData() {
        HeaderModel data = new HeaderModel(0);

        data.setShipmentCode(mEtNo.getText().toString());
        data.setShipmentDate(mDpDate.getCalendar());
        data.setShipmentType(mSpShipmentType.getSelectedItem().toString());
        data.setRequestArriveDate(mDpRequestArriveDate.getCalendar());
        data.setEstimatedTimeArrive(mDpEstimatedTimeArrive.getCalendar());
        data.setShipmentTo(mEtShipmentTo.getText().toString());
        data.setVehicleNo(mEtVehicleNo.getText().toString());
        data.setVehicleDriver(mEtVehicleDriver.getText().toString());
        data.setTransportMode(mSpTransportMode.getSelectedItem().toString());
        data.setPoliceName(mEtPoliceName.getText().toString());
        data.setNotes(mEtNotes.getText().toString());

        return data;
    }

    public void setData(HeaderModel headerModel) {
        mEtNo.setText(headerModel.getShipmentCode());
        mDpDate.setDate(headerModel.getShipmentDate());
        if (headerModel.getShipmentType() != null) {
            if (headerModel.getShipmentType().equals("LOCO"))
                mSpShipmentType.setSelection(1, false);
            else if (headerModel.getShipmentType().equals("FRANCO"))
                mSpShipmentType.setSelection(2, false);
            else
                mSpShipmentType.setSelection(0, false);
        }
        mDpRequestArriveDate.setDate(headerModel.getRequestArriveDate());
        mDpEstimatedTimeArrive.setDate(headerModel.getEstimatedTimeArrive());
        mEtShipmentTo.setText(headerModel.getShipmentTo());
        mEtVehicleNo.setText(headerModel.getVehicleNo());
        mEtVehicleDriver.setText(headerModel.getVehicleDriver());
        if (headerModel.getTransportMode() != null) {
            if (headerModel.getTransportMode().equals("CD4"))
                mSpTransportMode.setSelection(1, false);
            else if (headerModel.getTransportMode().equals("CD6"))
                mSpTransportMode.setSelection(2, false);
            else if (headerModel.getTransportMode().equals("CNT"))
                mSpTransportMode.setSelection(3, false);
            else if (headerModel.getTransportMode().equals("BU"))
                mSpTransportMode.setSelection(4, false);
            else
                mSpTransportMode.setSelection(0, false);
        }
        mEtPoliceName.setText(headerModel.getPoliceName());
        mEtNotes.setText(headerModel.getNotes());
    }

    public void setOnSubmitClick(OnClickListener listener) {
        mButtonSubmit.setOnClickListener(listener);
    }
}

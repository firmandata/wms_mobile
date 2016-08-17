package com.erp.wms.custom.inventory_inbound.view;

import android.content.Context;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DrawableUtil;
import com.erp.helper.widget.DataPickerView;
import com.erp.wms.R;
import com.erp.wms.api.CustomInventoryInboundAPI;
import com.erp.wms.custom.inventory_inbound.model.Model;

import org.json.JSONException;

import java.util.Calendar;

public class EnrollView extends ScrollView {
    private Context mContext;

    private TextView mLblProductName;

    private EditText mEtProductCode;
    private EditText mEtBarcode;
    private EditText mEtQuantity;
    private EditText mEtPallet;
    private EditText mEtGrid;
    private EditText mEtCartonNo;
    private DataPickerView mDpPackedDate;
    private DataPickerView mDpExpiredDate;
    private EditText mEtLotNo;
    private Spinner mSpCondition;

    private View mBarcodeRequestView;
    private RequestBarcodeListener mBarcodeRequestListener;

    private Button mButtonSubmit;

    public EnrollView(Context context) {
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

            TextView lblProductCode = new TextView(mContext);
            lblProductCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblProductCode.setText("Product Code");
            scrollViewContainer.addView(lblProductCode);

            mEtProductCode = new EditText(mContext);
            mEtProductCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtProductCode.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtProductCode.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtProductCode.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtProductCode);
                        mBarcodeRequestView = mEtProductCode;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtProductCode);
                    }
                    if (!hasFocus) {
                        productProperties(mEtProductCode.getText().toString());
                        parseBarcode(mEtProductCode.getText().toString(), mEtBarcode.getText().toString());
                    }
                }
            });
            scrollViewContainer.addView(mEtProductCode);

            mLblProductName = new TextView(mContext);
            mLblProductName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scrollViewContainer.addView(mLblProductName);

            TextView lblBarcode = new TextView(mContext);
            lblBarcode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblBarcode.setText("Barcode");
            lblBarcode.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblBarcode);

            mEtBarcode = new EditText(mContext);
            mEtBarcode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtBarcode.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtBarcode.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtBarcode.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtBarcode);
                        mBarcodeRequestView = mEtBarcode;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtBarcode);
                    }
                    if (!hasFocus) {
                        parseBarcode(mEtProductCode.getText().toString(), mEtBarcode.getText().toString());
                    }
                }
            });
            scrollViewContainer.addView(mEtBarcode);

            TextView lblQuantity = new TextView(mContext);
            lblQuantity.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblQuantity.setText("Quantity");
            lblQuantity.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblQuantity);

            mEtQuantity = new EditText(mContext);
            mEtQuantity.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtQuantity.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            mEtQuantity.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtQuantity.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtQuantity);
                        mBarcodeRequestView = mEtQuantity;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtQuantity);
                    }
                }
            });
            scrollViewContainer.addView(mEtQuantity);

            TextView lblPallet = new TextView(mContext);
            lblPallet.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblPallet.setText("Pallet");
            lblPallet.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblPallet);

            mEtPallet = new EditText(mContext);
            mEtPallet.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtPallet.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtPallet.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtPallet.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtPallet);
                        mBarcodeRequestView = mEtPallet;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtPallet);
                    }
                }
            });
            scrollViewContainer.addView(mEtPallet);

            TextView lblGrid = new TextView(mContext);
            lblGrid.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblGrid.setText("Grid");
            lblGrid.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblGrid);

            mEtGrid = new EditText(mContext);
            mEtGrid.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtGrid.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtGrid.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtGrid.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtGrid);
                        mBarcodeRequestView = mEtGrid;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtGrid);
                    }
                }
            });
            scrollViewContainer.addView(mEtGrid);

            TextView lblCartonNo = new TextView(mContext);
            lblCartonNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblCartonNo.setText("Carton No");
            lblCartonNo.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblCartonNo);

            mEtCartonNo = new EditText(mContext);
            mEtCartonNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtCartonNo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtCartonNo.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtCartonNo.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtCartonNo);
                        mBarcodeRequestView = mEtCartonNo;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtCartonNo);
                    }
                }
            });
            scrollViewContainer.addView(mEtCartonNo);

            TextView lblPackedDate = new TextView(mContext);
            lblPackedDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblPackedDate.setText("Packed Date");
            lblPackedDate.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblPackedDate);

            mDpPackedDate = new DataPickerView(mContext);
            mDpPackedDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mDpPackedDate.setFormatDisplay("yyMMdd");
            scrollViewContainer.addView(mDpPackedDate);

            TextView lblExpiredDate = new TextView(mContext);
            lblExpiredDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblExpiredDate.setText("Expired Date");
            lblExpiredDate.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblExpiredDate);

            mDpExpiredDate = new DataPickerView(mContext);
            mDpExpiredDate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mDpExpiredDate.setFormatDisplay("yyMMdd");
            scrollViewContainer.addView(mDpExpiredDate);

            TextView lblLotNo = new TextView(mContext);
            lblLotNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblLotNo.setText("Lot No");
            lblLotNo.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblLotNo);

            mEtLotNo = new EditText(mContext);
            mEtLotNo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtLotNo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtLotNo.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtLotNo.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtLotNo);
                        mBarcodeRequestView = mEtLotNo;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtLotNo);
                    }
                }
            });
            scrollViewContainer.addView(mEtLotNo);

            TextView lblCondition = new TextView(mContext);
            lblCondition.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblCondition.setText("Condition");
            lblCondition.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblCondition);

            String conditions[] = {"OK", "BIG SALE", "GOOD", "BAD", "STATUS"};
            ArrayAdapter<String> spConditionAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, conditions);
            spConditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mSpCondition = new Spinner(mContext);
            mSpCondition.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mSpCondition.setAdapter(spConditionAdapter);
            scrollViewContainer.addView(mSpCondition);

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

    public Model getData() {
        Model data = new Model(0);

        data.setProductCode(mEtProductCode.getText().toString());
        data.setBarcode(mEtBarcode.getText().toString());
        data.setPallet(mEtPallet.getText().toString());
        try {
            data.setQuantity(Double.parseDouble(mEtQuantity.getText().toString()));
        } catch (Exception ex) {
            data.setQuantity(0);
        }
        data.setCartonNo(mEtCartonNo.getText().toString());
        data.setLotNo(mEtLotNo.getText().toString());
        data.setCondition(mSpCondition.getSelectedItem().toString());
        data.setPackedDate(mDpPackedDate.getCalendar());
        data.setExpiredDate(mDpExpiredDate.getCalendar());
        data.setGridCode(mEtGrid.getText().toString());

        return data;
    }

    public void clearData() {
        mEtBarcode.setText("");
        mEtQuantity.setText("");
        mEtCartonNo.setText("");
        mDpPackedDate.setDate((Calendar) null);
        mDpExpiredDate.setDate((Calendar) null);
        mLblProductName.setText("");
    }

    public void setFocusAtFirst() {
        mEtProductCode.requestFocus();
    }

    private void parseBarcode(String productCode, String barcode) {
        if (barcode == null || barcode.equals("")) {
            mEtQuantity.setText("");
            mEtCartonNo.setText("");
            mDpPackedDate.setDate((Calendar)null);
            return;
        }

        CustomInventoryInboundAPI.ParseBarcode(productCode, barcode, new RestfulResponse(mContext) {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    boolean is_response_ok = false;
                    if (!response.isNull("response"))
                        is_response_ok = response.getBoolean("response");

                    if (is_response_ok) {
                        org.json.JSONObject value = response.getJSONObject("value");

                        if (!value.isNull("quantity"))
                            mEtQuantity.setText(String.valueOf(value.getDouble("quantity")));
                        else
                            mEtQuantity.setText("");
                        if (!value.isNull("carton_no"))
                            mEtCartonNo.setText(value.getString("carton_no"));
                        else
                            mEtCartonNo.setText("");
                        if (!value.isNull("packed_date"))
                            mDpPackedDate.setDate(value.getString("packed_date"));
                        else
                            mDpPackedDate.setDate((Calendar) null);
                    } else {
                        mEtQuantity.setText("");
                        mEtCartonNo.setText("");
                        mDpPackedDate.setDate((Calendar) null);
                    }
                } catch (JSONException e) {
                    mEtQuantity.setText("");
                    mEtCartonNo.setText("");
                    mDpPackedDate.setDate((Calendar) null);
                } catch (Exception e) {
                    mEtQuantity.setText("");
                    mEtCartonNo.setText("");
                    mDpPackedDate.setDate((Calendar) null);
                }
            }
        });
    }

    private void productProperties(String productCode) {
        if (productCode == null || productCode.equals("")) {
            mLblProductName.setText("");
            return;
        }

        CustomInventoryInboundAPI.ProductProperties(productCode, new RestfulResponse(mContext) {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    boolean is_response_ok = false;
                    if (!response.isNull("response"))
                        is_response_ok = response.getBoolean("response");

                    if (is_response_ok) {
                        org.json.JSONObject value = response.getJSONObject("value");

                        if (!value.isNull("name"))
                            mLblProductName.setText(value.getString("name"));
                        else
                            mLblProductName.setText("");
                    } else {
                        mLblProductName.setText("");
                    }
                } catch (JSONException e) {
                    mLblProductName.setText("");
                } catch (Exception e) {
                    mLblProductName.setText("");
                }
            }
        });
    }

    public void setBarcode(String barcode) throws Exception {
        EditText et;

        if (getRequestBarcodeView() != null) {
            try {
                et = (EditText)getRequestBarcodeView();
            } catch (Exception ex){
                et = null;
            }
        } else {
            try {
                et = (EditText)findFocus();
            } catch (Exception ex) {
                et = null;
            }
        }

        if (et != null)
            et.setText(barcode);
        else
            throw new Exception("No cursor placed focus in a text box.");
    }

    public View getRequestBarcodeView() {
        return mBarcodeRequestView;
    }

    public void setOnRequestBarcode(RequestBarcodeListener listener) {
        mBarcodeRequestListener = listener;
    }

    public interface RequestBarcodeListener {
        void onBeforeRequestBarcode(View view);
        void onAfterRequestBarcode(View view);
    }

    public void setOnSubmitClick(OnClickListener listener) {
        mButtonSubmit.setOnClickListener(listener);
    }
}

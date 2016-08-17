package com.erp.wms.material.inventory_picking.view;

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

import com.erp.helper.utils.DrawableUtil;
import com.erp.wms.R;
import com.erp.wms.material.inventory_picking.model.DetailModel;

public class EnrollSetSpecificView extends ScrollView {
    private Context mContext;

    private EditText mEtBarcode;
    private EditText mEtPallet;
    private EditText mEtGrid;
    private Spinner mSpCondition;
    private EditText mEtQuantityBox;

    private EditText mEtPackedGroup;

    private View mBarcodeRequestView;
    private RequestBarcodeListener mBarcodeRequestListener;

    private Button mButtonSubmit;

    public EnrollSetSpecificView(Context context) {
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

            LinearLayout criteriaViewTitleContainer = new LinearLayout(mContext);
            criteriaViewTitleContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            criteriaViewTitleContainer.setOrientation(LinearLayout.HORIZONTAL);
            criteriaViewTitleContainer.setGravity(Gravity.CENTER);

                TextView lblCriteriaTitle = new TextView(mContext);
                lblCriteriaTitle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                lblCriteriaTitle.setText("CRITERIA");
                lblCriteriaTitle.setTextSize(20);
                criteriaViewTitleContainer.addView(lblCriteriaTitle);

            scrollViewContainer.addView(criteriaViewTitleContainer);

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
                }
            });
            scrollViewContainer.addView(mEtBarcode);

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

            TextView lblCondition = new TextView(mContext);
            lblCondition.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblCondition.setText("Condition");
            lblCondition.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblCondition);

            String conditions[] = {"", "OK", "DEFECT"};
            ArrayAdapter<String> spConditionAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, conditions);
            spConditionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            mSpCondition = new Spinner(mContext);
            mSpCondition.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mSpCondition.setAdapter(spConditionAdapter);
            scrollViewContainer.addView(mSpCondition);

            TextView lblQuantityBox = new TextView(mContext);
            lblQuantityBox.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblQuantityBox.setText("Box");
            lblQuantityBox.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblQuantityBox);

            mEtQuantityBox = new EditText(mContext);
            mEtQuantityBox.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtQuantityBox.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            mEtQuantityBox.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtQuantityBox.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtQuantityBox);
                        mBarcodeRequestView = mEtQuantityBox;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtQuantityBox);
                    }
                }
            });
            scrollViewContainer.addView(mEtQuantityBox);

            LinearLayout setToViewTitleContainer = new LinearLayout(mContext);
            setToViewTitleContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            setToViewTitleContainer.setOrientation(LinearLayout.HORIZONTAL);
            setToViewTitleContainer.setGravity(Gravity.CENTER);

                TextView lblSetToTitle = new TextView(mContext);
                lblSetToTitle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                lblSetToTitle.setText("SET TO");
                lblSetToTitle.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
                lblSetToTitle.setTextSize(20);
                lblSetToTitle.setGravity(Gravity.CENTER_HORIZONTAL);
                setToViewTitleContainer.addView(lblSetToTitle);

            scrollViewContainer.addView(setToViewTitleContainer);

            TextView lblPackedGroup = new TextView(mContext);
            lblPackedGroup.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblPackedGroup.setText("Packed Group");
            lblPackedGroup.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblPackedGroup);

            mEtPackedGroup = new EditText(mContext);
            mEtPackedGroup.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtPackedGroup.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtPackedGroup.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtPackedGroup.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtPackedGroup);
                        mBarcodeRequestView = mEtPackedGroup;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtPackedGroup);
                    }
                }
            });
            scrollViewContainer.addView(mEtPackedGroup);

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

    public DetailModel getData() {
        DetailModel data = new DetailModel(0, 0, 0);

        data.setBarcode(mEtBarcode.getText().toString());
        data.setPallet(mEtPallet.getText().toString());
        try {
            data.setQuantityBox(Integer.parseInt(mEtQuantityBox.getText().toString()));
        } catch (Exception ex) {
            data.setQuantityBox(0);
        }
        data.setGridCode(mEtGrid.getText().toString());
        data.setCondition(mSpCondition.getSelectedItem().toString());
        data.setPackedGroup(mEtPackedGroup.getText().toString());

        return data;
    }

    public void clearData() {
        mEtBarcode.setText("");
        mEtPallet.setText("");
        mEtQuantityBox.setText("");
        mEtGrid.setText("");
        mSpCondition.setSelection(0, false);
    }

    public void setFocusAtFirst() {
        mEtBarcode.requestFocus();
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

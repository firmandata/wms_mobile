package com.erp.wms.material.inventory_move.view;

import android.content.Context;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.erp.helper.utils.DrawableUtil;
import com.erp.wms.R;
import com.erp.wms.material.inventory_move.model.DetailModel;

public class EnrollView extends ScrollView {
    private Context mContext;

    private EditText mEtProductCode;
    private EditText mEtBarcode;
    private EditText mEtPalletFrom;
    private EditText mEtGridFromCode;
    private EditText mEtPalletTo;
    private EditText mEtGridToCode;

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

            LinearLayout fromViewTitleContainer = new LinearLayout(mContext);
            fromViewTitleContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            fromViewTitleContainer.setOrientation(LinearLayout.HORIZONTAL);
            fromViewTitleContainer.setGravity(Gravity.CENTER);

                TextView lblFromTitle = new TextView(mContext);
                lblFromTitle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                lblFromTitle.setText("FROM");
                lblFromTitle.setTextSize(20);
                fromViewTitleContainer.addView(lblFromTitle);

            scrollViewContainer.addView(fromViewTitleContainer);

            TextView lblProductCode = new TextView(mContext);
            lblProductCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblProductCode.setText("Product Code");
            lblProductCode.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
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
                }
            });
            scrollViewContainer.addView(mEtProductCode);

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

            TextView lblPalletFrom = new TextView(mContext);
            lblPalletFrom.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblPalletFrom.setText("Pallet");
            lblPalletFrom.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblPalletFrom);

            mEtPalletFrom = new EditText(mContext);
            mEtPalletFrom.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtPalletFrom.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtPalletFrom.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtPalletFrom.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtPalletFrom);
                        mBarcodeRequestView = mEtPalletFrom;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtPalletFrom);
                    }
                }
            });
            scrollViewContainer.addView(mEtPalletFrom);

            TextView lblGridFromCode = new TextView(mContext);
            lblGridFromCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblGridFromCode.setText("Grid");
            lblGridFromCode.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblGridFromCode);

            mEtGridFromCode = new EditText(mContext);
            mEtGridFromCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtGridFromCode.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtGridFromCode.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtGridFromCode.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtGridFromCode);
                        mBarcodeRequestView = mEtGridFromCode;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtGridFromCode);
                    }
                }
            });
            scrollViewContainer.addView(mEtGridFromCode);

            LinearLayout toViewTitleContainer = new LinearLayout(mContext);
            toViewTitleContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            toViewTitleContainer.setOrientation(LinearLayout.HORIZONTAL);
            toViewTitleContainer.setGravity(Gravity.CENTER);

                TextView lblToTitle = new TextView(mContext);
                lblToTitle.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                lblToTitle.setText("TO");
                lblToTitle.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
                lblToTitle.setTextSize(20);
                lblToTitle.setGravity(Gravity.CENTER_HORIZONTAL);
                toViewTitleContainer.addView(lblToTitle);

            scrollViewContainer.addView(toViewTitleContainer);

            TextView lblPalletTo = new TextView(mContext);
            lblPalletTo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblPalletTo.setText("Pallet");
            lblPalletTo.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblPalletTo);

            mEtPalletTo = new EditText(mContext);
            mEtPalletTo.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtPalletTo.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtPalletTo.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtPalletTo.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtPalletTo);
                        mBarcodeRequestView = mEtPalletTo;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtPalletTo);
                    }
                }
            });
            scrollViewContainer.addView(mEtPalletTo);

            TextView lblGridToCode = new TextView(mContext);
            lblGridToCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblGridToCode.setText("Grid");
            lblGridToCode.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblGridToCode);

            mEtGridToCode = new EditText(mContext);
            mEtGridToCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtGridToCode.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtGridToCode.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtGridToCode.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtGridToCode);
                        mBarcodeRequestView = mEtGridToCode;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtGridToCode);
                    }
                }
            });
            scrollViewContainer.addView(mEtGridToCode);

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
        DetailModel data = new DetailModel(0, 0);

        data.setProductCode(mEtProductCode.getText().toString());
        data.setBarcode(mEtBarcode.getText().toString());
        data.setPalletFrom(mEtPalletFrom.getText().toString());
        data.setGridFromCode(mEtGridFromCode.getText().toString());

        data.setPalletTo(mEtPalletTo.getText().toString());
        data.setGridToCode(mEtGridToCode.getText().toString());

        return data;
    }

    public void clearData() {

        mEtProductCode.setText("");
        mEtBarcode.setText("");
        mEtPalletFrom.setText("");
        mEtGridFromCode.setText("");
        mEtPalletTo.setText("");
        mEtGridToCode.setText("");
    }

    public void setFocusAtFirst() {
        mEtProductCode.requestFocus();
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

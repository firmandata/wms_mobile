package com.erp.wms.material.inventory_putaway.view;

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
import com.erp.wms.material.inventory_putaway.model.DetailModel;

public class EnrollView extends ScrollView {
    private Context mContext;

    private EditText mEtPallet;
    private EditText mEtGrid;

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

            TextView lblPallet = new TextView(mContext);
            lblPallet.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblPallet.setText("Pallet");
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

        data.setPallet(mEtPallet.getText().toString());
        data.setGridToCode(mEtGrid.getText().toString());

        return data;
    }

    public void clearData() {
        mEtPallet.setText("");
        mEtGrid.setText("");
    }

    public void setGridCode(String gridCode) {
        mEtGrid.setText(gridCode);
    }

    public void setFocusAtFirst() {
        mEtPallet.requestFocus();
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

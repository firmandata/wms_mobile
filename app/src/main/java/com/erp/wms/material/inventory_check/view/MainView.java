package com.erp.wms.material.inventory_check.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DrawableUtil;
import com.erp.helper.widget.myautocomplete.MyAutoCompleteAdapter;
import com.erp.helper.widget.myautocomplete.MyAutoCompleteIModel;
import com.erp.helper.widget.myautocomplete.MyAutoCompleteRecord;
import com.erp.helper.widget.myautocomplete.MyAutoCompleteView;
import com.erp.wms.R;
import com.erp.wms.api.JsonAPIFilter;
import com.erp.wms.api.MaterialProductAPI;
import com.erp.wms.material.inventory_check.model.MainModel;
import com.erp.wms.material.product.model.ProductModel;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainView extends ScrollView {
    private Context mContext;

    private MyAutoCompleteView mEtProductCode;
    private TextView mLblProductName;
    private EditText mEtBarcode;
    private EditText mEtPallet;
    private EditText mEtGridCode;

    private View mBarcodeRequestView;
    private RequestBarcodeListener mBarcodeRequestListener;

    private Button mButtonSubmit;

    public MainView(Context context) {
        super(context);

        mContext = context;
        mBarcodeRequestListener = null;

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
            lblProductCode.setText("Product");
            scrollViewContainer.addView(lblProductCode);

            mEtProductCode = new MyAutoCompleteView(mContext);
            mEtProductCode.setAdapter(new MyAutoCompleteAdapter<ProductAutoCompleteView>(mContext){
                @Override
                protected List<ProductAutoCompleteView> onFindRecords(Context context, String keyWords) {
                    final List<ProductAutoCompleteView> results = new ArrayList<>();

                    JsonAPIFilter filter = new JsonAPIFilter("OR");
                    filter.Add("pro.code", keyWords, "cn");
                    filter.Add("pro.name", keyWords, "cn");

                    MaterialProductAPI.GetListSynch(MAX_RESULTS, filter, new RestfulResponse(context) {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess(org.json.JSONObject response) {
                            try {
                                org.json.JSONArray jrecords = response.getJSONArray("data");
                                for (int jrecord_idx = 0; jrecord_idx < jrecords.length(); jrecord_idx++) {
                                    org.json.JSONObject record = jrecords.getJSONObject(jrecord_idx);

                                    ProductModel recordData = new ProductModel();

                                    if (!record.isNull("id"))
                                        recordData.setId(record.getInt("id"));
                                    if (!record.isNull("code"))
                                        recordData.setCode(record.getString("code"));
                                    if (!record.isNull("name"))
                                        recordData.setName(record.getString("name"));
                                    if (!record.isNull("uom"))
                                        recordData.setUom(record.getString("uom"));
                                    if (!record.isNull("pack"))
                                        recordData.setPack(record.getInt("pack"));

                                    if (!record.isNull("m_productgroup_id"))
                                        recordData.setProductGroupId(record.getInt("m_productgroup_id"));
                                    if (!record.isNull("m_productgroup_name"))
                                        recordData.setProductGroupName(record.getString("m_productgroup_name"));

                                    results.add(new ProductAutoCompleteView(mContext, recordData));
                                }

                            } catch (JSONException e) {

                            } catch (Exception e) {

                            }
                        }
                    });

                    return results;
                }
            });
            mEtProductCode.setThreshold(3);
            mEtProductCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ProductAutoCompleteView productView = (ProductAutoCompleteView) parent.getItemAtPosition(position);
                    if (productView != null) {
                        ProductModel productModel = (ProductModel)productView.getData();
                        if (productModel != null) {
                            mEtProductCode.setText(productModel.getCode());
                            mEtProductCode.setTextColor(Color.parseColor("#000000"));
                            mLblProductName.setText(productModel.getName());
                            mEtBarcode.requestFocus();
                        }
                    }
                }
            });
            mEtProductCode.setListener(new MyAutoCompleteView.MyAutoCompleteViewListener() {
                @Override
                public void onPerformFiltering() {
                    mLblProductName.setText("");
                }
            });
            mEtProductCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtProductCode.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtProductCode.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_ACTION_SEARCH);
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

            TextView lblGridCode = new TextView(mContext);
            lblGridCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            lblGridCode.setText("Grid");
            lblGridCode.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            scrollViewContainer.addView(lblGridCode);

            mEtGridCode = new EditText(mContext);
            mEtGridCode.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mEtGridCode.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
            mEtGridCode.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            mEtGridCode.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        mBarcodeRequestListener.onBeforeRequestBarcode(mEtGridCode);
                        mBarcodeRequestView = mEtGridCode;
                    } else {
                        mBarcodeRequestView = null;
                        mBarcodeRequestListener.onAfterRequestBarcode(mEtGridCode);
                    }
                }
            });
            scrollViewContainer.addView(mEtGridCode);

            LinearLayout submitLine = new LinearLayout(context);
            submitLine.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            submitLine.setOrientation(LinearLayout.HORIZONTAL);
            submitLine.setGravity(Gravity.CENTER);

                mButtonSubmit = new Button(context);
                mButtonSubmit.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                mButtonSubmit.setText("Check");
                mButtonSubmit.setCompoundDrawablesWithIntrinsicBounds(DrawableUtil.resizeIcon(mContext, mContext.getResources().getDrawable(R.drawable.checkmark), 25, 25), null, null, null);

            submitLine.setPadding(0, (int) context.getResources().getDimension(R.dimen.layout_form_field_padding_top), 0, 0);
            submitLine.addView(mButtonSubmit);
            scrollViewContainer.addView(submitLine);

        this.addView(scrollViewContainer);
    }

    public MainModel getData() {
        MainModel data = new MainModel();

        data.setProductCode(mEtProductCode.getText().toString());
        data.setBarcode(mEtBarcode.getText().toString());
        data.setPallet(mEtPallet.getText().toString());
        data.setGridCode(mEtGridCode.getText().toString());

        return data;
    }

    public void clearData() {
        mEtProductCode.setText("");
        mEtBarcode.setText("");
        mEtPallet.setText("");
        mEtGridCode.setText("");
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

    public class ProductAutoCompleteView extends MyAutoCompleteRecord {

        private ProductModel mData;

        public ProductAutoCompleteView(Context context, ProductModel productModel) {
            super(context);

            mData = productModel;
            setId(mData.getId());
        }

        @Override
        public RelativeLayout getRecord() {
            RelativeLayout rlSelf = new RelativeLayout(mContext);

                LinearLayout containerView = new LinearLayout(mContext);
                containerView.setOrientation(LinearLayout.VERTICAL);

                    TextView lblFirst = new TextView(mContext);
                    lblFirst.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    lblFirst.setText(mData.getCode());
                    lblFirst.setTypeface(null, Typeface.BOLD);
                    containerView.addView(lblFirst);

                    TextView lblSecond = new TextView(mContext);
                    lblSecond.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    lblSecond.setText(mData.getName());
                    containerView.addView(lblSecond);

                rlSelf.addView(containerView);

            return rlSelf;
        }

        @Override
        public MyAutoCompleteIModel getData(){
            return mData;
        }
    }
}
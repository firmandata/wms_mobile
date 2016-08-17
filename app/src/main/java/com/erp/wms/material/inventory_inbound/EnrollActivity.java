package com.erp.wms.material.inventory_inbound;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DateTimeUtil;
import com.erp.helper.utils.GeneratorUtil;
import com.erp.helper.widget.mylist.MyListView;
import com.erp.wms.R;
import com.erp.wms.api.JsonAPIFilter;
import com.erp.wms.api.MaterialInventoryInboundAPI;
import com.erp.wms.material.inventory_inbound.model.ReceiveModel;
import com.erp.wms.material.inventory_inbound.view.EnrollSelectReceiveView;
import com.erp.wms.material.inventory_inbound.view.EnrollSetSpecificView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;

import java.util.ArrayList;

public class EnrollActivity extends SherlockActivity implements ActionBar.TabListener {

    private Context mContext;

    private long mInventoryInboundId;
    private String mSearchKeyword;

    private RelativeLayout mSelectReceiveContainer;
    private EditText mEtSelectReceiveSearchKeyword;
    private MyListView<EnrollSelectReceiveView> mSelectReceiveView;
    private long mInventoryReceiveDetailId;
    private boolean mIsSelectReceiveViewLoaded;

    private EnrollSetSpecificView mSetSpecificView;
    private ReceiveModel mCacheReceiveModel;

    private ActionBar.Tab mTabReceive;
    private ActionBar.Tab mTabSpecific;
    private ActionBar.Tab mCurrentTab;

    private IntentIntegrator mBarcodeIntentIntegrator;
    private MenuItem mBarcodeMenuItem;

    private int mActivityResultCode = RESULT_CANCELED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Enroll Inbound";

        mContext = this;
        mBarcodeIntentIntegrator = new IntentIntegrator(this);
        mBarcodeMenuItem = null;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            mInventoryInboundId = extras.getLong("m_inventory_inbound_id");
            mSearchKeyword = extras.getString("search_keyword");
        }
        this.setTitle(title);

        createReceiveView();
        createSpecificView();

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.inbox);
        createTab(actionBar);
    }

    private void createReceiveView() {
        mIsSelectReceiveViewLoaded = false;

        mSelectReceiveContainer = new RelativeLayout(this);
        mSelectReceiveContainer.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        mEtSelectReceiveSearchKeyword = new EditText(this);
        mEtSelectReceiveSearchKeyword.setId(GeneratorUtil.generateViewId());
            RelativeLayout.LayoutParams tvSelectReceiveSearchKeywordLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            tvSelectReceiveSearchKeywordLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            tvSelectReceiveSearchKeywordLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mEtSelectReceiveSearchKeyword.setLayoutParams(tvSelectReceiveSearchKeywordLayout);
        mEtSelectReceiveSearchKeyword.setHint("Search Keyword");
        mEtSelectReceiveSearchKeyword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        mEtSelectReceiveSearchKeyword.setImeOptions(EditorInfo.IME_ACTION_GO);
        mEtSelectReceiveSearchKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO || (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    setSelectReceiveViewRecords(1, String.valueOf(mEtSelectReceiveSearchKeyword.getText()));
                    return true;
                }
                return false;
            }
        });
        mEtSelectReceiveSearchKeyword.setText(mSearchKeyword);
        mSelectReceiveContainer.addView(mEtSelectReceiveSearchKeyword);

        mSelectReceiveView = new MyListView<>(this);
        mSelectReceiveView.setId(GeneratorUtil.generateViewId());
            RelativeLayout.LayoutParams selectReceiveViewLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            selectReceiveViewLayout.addRule(RelativeLayout.BELOW, mEtSelectReceiveSearchKeyword.getId());
            selectReceiveViewLayout.addRule(RelativeLayout.ALIGN_RIGHT, mEtSelectReceiveSearchKeyword.getId());
            selectReceiveViewLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mSelectReceiveView.setLayoutParams(selectReceiveViewLayout);
        mSelectReceiveView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectReceiveView.setSelected(position);
                mInventoryReceiveDetailId = id;
                mTabSpecific.select();

                mSetSpecificView.clearData();
                mSetSpecificView.setInventoryReceiveDetailId(mInventoryReceiveDetailId);
                mSetSpecificView.setInventoryInboundId(mInventoryInboundId);

                EnrollSelectReceiveView enrollSelectReceiveView = mSelectReceiveView.getRecordByPosition(position);
                if (enrollSelectReceiveView != null) {
                    ReceiveModel receiveModel = enrollSelectReceiveView.getData();
                    if (receiveModel != null)
                        mSetSpecificView.setProductInfo(receiveModel);
                    mCacheReceiveModel = receiveModel;
                }
            }
        });
        mSelectReceiveView.setOnLoadList(new MyListView.LoadListListener() {
            @Override
            public void onReloadAfterRemove(int page) {
                setSelectReceiveViewRecords(page, String.valueOf(mEtSelectReceiveSearchKeyword.getText()));
            }

            @Override
            public void onLoadMore(int page) {
                setSelectReceiveViewRecords(page, String.valueOf(mEtSelectReceiveSearchKeyword.getText()));
            }
        });
        mSelectReceiveContainer.addView(mSelectReceiveView);
    }

    private void setSelectReceiveViewRecords(int page, String keyword) {
        JsonAPIFilter jsonAPIFilter = new JsonAPIFilter("OR");
        if (!keyword.isEmpty()){
            jsonAPIFilter.Add("ir.code", keyword, "cn");
            jsonAPIFilter.Add("pro.code", keyword, "cn");
            jsonAPIFilter.Add("pro.name", keyword, "cn");
        }

        MaterialInventoryInboundAPI.GetReceiveDetails(page, jsonAPIFilter, new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    ArrayList<EnrollSelectReceiveView> records = new ArrayList<>();

                    org.json.JSONArray jrecords = response.getJSONArray("data");
                    for (int jrecord_idx = 0; jrecord_idx < jrecords.length(); jrecord_idx++) {
                        org.json.JSONObject record = jrecords.getJSONObject(jrecord_idx);

                        ReceiveModel recordData = new ReceiveModel(record.getLong("id"));
                        if (!record.isNull("m_inventory_receive_code"))
                            recordData.setInventoryReceiveCode(record.getString("m_inventory_receive_code"));
                        if (!record.isNull("m_inventory_receive_date"))
                            recordData.setInventoryReceiveDate(DateTimeUtil.FromDateString(record.getString("m_inventory_receive_date")));
                        if (!record.isNull("condition"))
                            recordData.setInventoryReceiveCondition(record.getString("condition"));
                        if (!record.isNull("m_inventory_receive_vehicle_no"))
                            recordData.setInventoryReceiveVehicleNo(record.getString("m_inventory_receive_vehicle_no"));
                        if (!record.isNull("m_inventory_receive_vehicle_driver"))
                            recordData.setInventoryReceiveVehicleDriver(record.getString("m_inventory_receive_vehicle_driver"));
                        if (!record.isNull("m_inventory_receive_transport_mode"))
                            recordData.setInventoryReceiveTransportMode(record.getString("m_inventory_receive_transport_mode"));

                        if (!record.isNull("m_product_id"))
                            recordData.setProductId(record.getInt("m_product_id"));
                        if (!record.isNull("m_product_code"))
                            recordData.setProductCode(record.getString("m_product_code"));
                        if (!record.isNull("m_product_name"))
                            recordData.setProductName(record.getString("m_product_name"));
                        if (!record.isNull("m_product_uom"))
                            recordData.setProductUom(record.getString("m_product_uom"));
                        if (!record.isNull("m_product_volume_length"))
                            recordData.setProductVolumeLength(record.getDouble("m_product_volume_length"));
                        if (!record.isNull("m_product_volume_width"))
                            recordData.setProductVolumeWidth(record.getDouble("m_product_volume_width"));
                        if (!record.isNull("m_product_volume_height"))
                            recordData.setProductVolumeHeight(record.getDouble("m_product_volume_height"));

                        if (!record.isNull("quantity_box"))
                            recordData.setBox(record.getInt("quantity_box"));
                        if (!record.isNull("quantity"))
                            recordData.setQuantity(record.getDouble("quantity"));

                        if (!record.isNull("c_orderin_id"))
                            recordData.setOrderInId(record.getLong("c_orderin_id"));
                        if (!record.isNull("c_orderin_code"))
                            recordData.setOrderInCode(record.getString("c_orderin_code"));
                        if (!record.isNull("c_orderin_date"))
                            recordData.setOrderInDate(DateTimeUtil.FromDateString(record.getString("c_orderin_date")));

                        if (!record.isNull("c_businesspartner_id"))
                            recordData.setBusinessPartnerId(record.getInt("c_businesspartner_id"));
                        if (!record.isNull("c_businesspartner_name"))
                            recordData.setBusinessPartner(record.getString("c_businesspartner_name"));

                        records.add(new EnrollSelectReceiveView(mContext, recordData));
                    }

                    EnrollSelectReceiveView[] recordViewsData = new EnrollSelectReceiveView[records.size()];
                    records.toArray(recordViewsData);

                    int records_total = 0;
                    if (!response.isNull("records"))
                        records_total = response.getInt("records");
                    mSelectReceiveView.setRecordTotal(records_total);

                    int page_current = 1;
                    if (!response.isNull("page"))
                        page_current = response.getInt("page");
                    mSelectReceiveView.setPageCurrent(page_current);

                    int page_total = 0;
                    if (!response.isNull("total"))
                        page_total = response.getInt("total");
                    mSelectReceiveView.setPageTotal(page_total);

                    if (page_current == 1)
                        mSelectReceiveView.setRecords(recordViewsData);
                    else
                        mSelectReceiveView.addRecords(recordViewsData);

                    mIsSelectReceiveViewLoaded = true;
                } catch (JSONException e) {
                    Toast.makeText(EnrollActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(EnrollActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createSpecificView() {
        mSetSpecificView = new EnrollSetSpecificView(this);
        mSetSpecificView.setLayoutParams(new EnrollSetSpecificView.LayoutParams(EnrollSetSpecificView.LayoutParams.MATCH_PARENT, EnrollSetSpecificView.LayoutParams.MATCH_PARENT));
        mSetSpecificView.setOnRequestBarcode(new EnrollSetSpecificView.RequestBarcodeListener() {
            @Override
            public void onBeforeRequestBarcode(View view) {
                if (mBarcodeMenuItem != null)
                    mBarcodeMenuItem.setVisible(true);
            }

            @Override
            public void onAfterRequestBarcode(View view) {
                if (mBarcodeMenuItem != null)
                    mBarcodeMenuItem.setVisible(false);
            }
        });
        mSetSpecificView.setOnSubmitClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    private void createTab(ActionBar actionBar) {
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mTabReceive = actionBar.newTab();
        mTabReceive.setTag("SelectReceived");
        mTabReceive.setText("Select Received");
        mTabReceive.setTabListener(this);
        actionBar.addTab(mTabReceive, true);

        mTabSpecific = actionBar.newTab();
        mTabSpecific.setTag("SetSpecific");
        mTabSpecific.setText("Set Specific");
        mTabSpecific.setTabListener(this);
        actionBar.addTab(mTabSpecific);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Save")
                .setIcon(R.drawable.save)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        mBarcodeMenuItem = menu.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "Scan");
        mBarcodeMenuItem.setIcon(R.drawable.barcode_scanner);
        mBarcodeMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        if (mSetSpecificView == null)
            mBarcodeMenuItem.setVisible(false);
        else {
            if (mSetSpecificView.getRequestBarcodeView() == null)
                mBarcodeMenuItem.setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                closeActivity();
                return true;

            case Menu.FIRST:
                save();
                return true;

            case Menu.FIRST + 1:
                scanBarcode();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void scanBarcode() {
        hideKeyboard();
        mBarcodeIntentIntegrator.initiateScan();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        hideKeyboard();

        String tag = tab.getTag().toString();
        if (tag.equals("SelectReceived")) {
            setContentView(mSelectReceiveContainer);
            if (!mIsSelectReceiveViewLoaded)
                setSelectReceiveViewRecords(1, String.valueOf(mEtSelectReceiveSearchKeyword.getText()));
            if (mBarcodeMenuItem != null)
                mBarcodeMenuItem.setVisible(false);
        }
        else if (tag.equals("SetSpecific")) {
            setContentView(mSetSpecificView);
            mSetSpecificView.setFocusAtFirst();
            if (mSetSpecificView.getRequestBarcodeView() == null)
                mBarcodeMenuItem.setVisible(false);
            else
                mBarcodeMenuItem.setVisible(true);
        }

        mCurrentTab = tab;
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    protected void save() {
        hideKeyboard();
        MaterialInventoryInboundAPI.InsertDetail(mInventoryInboundId, mInventoryReceiveDetailId, mSetSpecificView.getData(), new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    boolean is_success = false;
                    if (!response.isNull("response"))
                        is_success = response.getBoolean("response");
                    if (!is_success) {
                        String error_message = "Unknown Error.";
                        if (!response.isNull("value"))
                            error_message = response.getString("value");
                        Toast.makeText(EnrollActivity.this, error_message, Toast.LENGTH_LONG).show();
                    } else {
                        int counter = 0;
                        if (!response.isNull("value")) {
                            org.json.JSONObject response_value = response.getJSONObject("value");
                            if (!response_value.isNull("counter"))
                                counter = response_value.getInt("counter");
                        }

                        mActivityResultCode = RESULT_OK;
                        mSetSpecificView.clearData();
                        mSetSpecificView.setPalletCounter(counter);
                        if (mCacheReceiveModel != null)
                            mSetSpecificView.setProductInfo(mCacheReceiveModel);
                        mSetSpecificView.setFocusAtFirst();
                    }
                } catch (JSONException e) {
                    Toast.makeText(EnrollActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(EnrollActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                try {
                    String tag = mCurrentTab.getTag().toString();
                    if (tag.equals("SetSpecific"))
                        mSetSpecificView.setBarcode(result.getContents());
                } catch (Exception ex) {
                    Toast.makeText(EnrollActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        closeActivity();
    }

    protected void closeActivity() {
        Intent data = new Intent();
        data.putExtra("m_inventory_inbound_id", mInventoryInboundId);
        setResult(mActivityResultCode, data);

        finish();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

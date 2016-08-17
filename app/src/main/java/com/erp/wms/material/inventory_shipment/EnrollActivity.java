package com.erp.wms.material.inventory_shipment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
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
import com.erp.wms.api.MaterialInventoryShipmentAPI;
import com.erp.wms.material.inventory_shipment.model.PickingModel;
import com.erp.wms.material.inventory_shipment.view.EnrollSelectPickingView;
import com.erp.wms.material.inventory_shipment.view.EnrollSetSpecificView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;

import java.util.ArrayList;

public class EnrollActivity extends SherlockActivity implements ActionBar.TabListener {

    private Context mContext;

    private long mInventoryShipmentId;
    private String mSearchKeyword;

    private RelativeLayout mSelectPickingContainer;
    private EditText mEtSelectPickingSearchKeyword;
    private MyListView<EnrollSelectPickingView> mSelectPickingView;
    private long mInventoryPickingId;
    private boolean mIsSelectPickingViewLoaded;

    private EnrollSetSpecificView mSetSpecificView;

    private ActionBar.Tab mTabPicking;
    private ActionBar.Tab mTabSpecific;
    private ActionBar.Tab mCurrentTab;

    private IntentIntegrator mBarcodeIntentIntegrator;
    private MenuItem mBarcodeMenuItem;

    private int mActivityResultCode = RESULT_CANCELED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Enroll Shipment";

        mContext = this;
        mBarcodeIntentIntegrator = new IntentIntegrator(this);
        mBarcodeMenuItem = null;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            mInventoryShipmentId = extras.getLong("m_inventory_shipment_id");
            mSearchKeyword = extras.getString("search_keyword");
        }
        this.setTitle(title);

        createPickingView();
        createSpecificView();

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.delivery);
        createTab(actionBar);
    }

    private void createPickingView() {
        mIsSelectPickingViewLoaded = false;

        mSelectPickingContainer = new RelativeLayout(this);
        mSelectPickingContainer.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        mEtSelectPickingSearchKeyword = new EditText(this);
        mEtSelectPickingSearchKeyword.setId(GeneratorUtil.generateViewId());
            RelativeLayout.LayoutParams tvSelectPickingSearchKeywordLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            tvSelectPickingSearchKeywordLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            tvSelectPickingSearchKeywordLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mEtSelectPickingSearchKeyword.setLayoutParams(tvSelectPickingSearchKeywordLayout);
        mEtSelectPickingSearchKeyword.setHint("Search Keyword");
        mEtSelectPickingSearchKeyword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        mEtSelectPickingSearchKeyword.setImeOptions(EditorInfo.IME_ACTION_GO);
        mEtSelectPickingSearchKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO || (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    setSelectPickingViewRecords(1, String.valueOf(mEtSelectPickingSearchKeyword.getText()));
                    return true;
                }
                return false;
            }
        });
        mEtSelectPickingSearchKeyword.setText(mSearchKeyword);
        mSelectPickingContainer.addView(mEtSelectPickingSearchKeyword);

        mSelectPickingView = new MyListView<>(this);
        mSelectPickingView.setId(GeneratorUtil.generateViewId());
            RelativeLayout.LayoutParams selectPickingViewLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            selectPickingViewLayout.addRule(RelativeLayout.BELOW, mEtSelectPickingSearchKeyword.getId());
            selectPickingViewLayout.addRule(RelativeLayout.ALIGN_RIGHT, mEtSelectPickingSearchKeyword.getId());
            selectPickingViewLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mSelectPickingView.setLayoutParams(selectPickingViewLayout);
        mSelectPickingView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectPickingView.setSelected(position);
                mInventoryPickingId = id;
                mTabSpecific.select();
            }
        });
        mSelectPickingView.setOnLoadList(new MyListView.LoadListListener() {
            @Override
            public void onReloadAfterRemove(int page) {
                setSelectPickingViewRecords(page, String.valueOf(mEtSelectPickingSearchKeyword.getText()));
            }

            @Override
            public void onLoadMore(int page) {
                setSelectPickingViewRecords(page, String.valueOf(mEtSelectPickingSearchKeyword.getText()));
            }
        });
        mSelectPickingContainer.addView(mSelectPickingView);
    }

    private void setSelectPickingViewRecords(int page, String keyword) {
        JsonAPIFilter jsonAPIFilter = new JsonAPIFilter("OR");
        if (!keyword.isEmpty()){
            jsonAPIFilter.Add("ipg.code", keyword, "cn");
            jsonAPIFilter.Add("oo.code", keyword, "cn");
            jsonAPIFilter.Add("bp.name", keyword, "cn");
        }

        MaterialInventoryShipmentAPI.GetPickingDetails(page, jsonAPIFilter, new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    ArrayList<EnrollSelectPickingView> records = new ArrayList<>();

                    org.json.JSONArray jrecords = response.getJSONArray("data");
                    for (int jrecord_idx = 0; jrecord_idx < jrecords.length(); jrecord_idx++) {
                        org.json.JSONObject record = jrecords.getJSONObject(jrecord_idx);

                        PickingModel recordData = new PickingModel(record.getLong("id"));
                        if (!record.isNull("code"))
                            recordData.setInventoryPickingCode(record.getString("code"));
                        if (!record.isNull("picking_date"))
                            recordData.setInventoryPickingDate(DateTimeUtil.FromDateString(record.getString("picking_date")));

                        if (!record.isNull("m_inventory_picklist_id"))
                            recordData.setInventoryPicklistId(record.getLong("m_inventory_picklist_id"));
                        if (!record.isNull("m_inventory_picklist_code"))
                            recordData.setInventoryPicklistCode(record.getString("m_inventory_picklist_code"));
                        if (!record.isNull("m_inventory_picklist_date"))
                            recordData.setInventoryPicklistDate(DateTimeUtil.FromDateString(record.getString("m_inventory_picklist_date")));

                        if (!record.isNull("c_orderout_id"))
                            recordData.setOrderOutId(record.getLong("c_orderout_id"));
                        if (!record.isNull("c_orderout_code"))
                            recordData.setOrderOutCode(record.getString("c_orderout_code"));
                        if (!record.isNull("c_orderout_date"))
                            recordData.setOrderOutDate(DateTimeUtil.FromDateString(record.getString("c_orderout_date")));
                        if (!record.isNull("c_orderout_request_arrive_date"))
                            recordData.setOrderOutRequestArriveDate(DateTimeUtil.FromDateString(record.getString("c_orderout_request_arrive_date")));

                        if (!record.isNull("quantity_box"))
                            recordData.setQuantityBox(record.getInt("quantity_box"));
                        if (!record.isNull("quantity"))
                            recordData.setQuantity(record.getDouble("quantity"));

                        if (!record.isNull("c_businesspartner_id"))
                            recordData.setBusinessPartnerId(record.getInt("c_businesspartner_id"));
                        if (!record.isNull("c_businesspartner_name"))
                            recordData.setBusinessPartner(record.getString("c_businesspartner_name"));

                        if (!record.isNull("c_project_id"))
                            recordData.setProjectId(record.getInt("c_project_id"));
                        if (!record.isNull("c_project_name"))
                            recordData.setProjectName(record.getString("c_project_name"));

                        records.add(new EnrollSelectPickingView(mContext, recordData));
                    }

                    EnrollSelectPickingView[] recordViewsData = new EnrollSelectPickingView[records.size()];
                    records.toArray(recordViewsData);

                    int records_total = 0;
                    if (!response.isNull("records"))
                        records_total = response.getInt("records");
                    mSelectPickingView.setRecordTotal(records_total);

                    int page_current = 1;
                    if (!response.isNull("page"))
                        page_current = response.getInt("page");
                    mSelectPickingView.setPageCurrent(page_current);

                    int page_total = 0;
                    if (!response.isNull("total"))
                        page_total = response.getInt("total");
                    mSelectPickingView.setPageTotal(page_total);

                    if (page_current == 1)
                        mSelectPickingView.setRecords(recordViewsData);
                    else
                        mSelectPickingView.addRecords(recordViewsData);

                    mIsSelectPickingViewLoaded = true;
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

        mTabPicking = actionBar.newTab();
        mTabPicking.setTag("SelectPicking");
        mTabPicking.setText("Select Picking");
        mTabPicking.setTabListener(this);
        actionBar.addTab(mTabPicking, true);

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
        if (tag.equals("SelectPicking")) {
            setContentView(mSelectPickingContainer);
            if (!mIsSelectPickingViewLoaded)
                setSelectPickingViewRecords(1, String.valueOf(mEtSelectPickingSearchKeyword.getText()));
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
        MaterialInventoryShipmentAPI.InsertDetail(mInventoryShipmentId, mInventoryPickingId, mSetSpecificView.getData(), new RestfulResponse(this) {
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
                        mActivityResultCode = RESULT_OK;
                        mSetSpecificView.clearData();
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
        data.putExtra("m_inventory_shipment_id", mInventoryShipmentId);
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
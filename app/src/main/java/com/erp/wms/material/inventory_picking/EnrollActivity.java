package com.erp.wms.material.inventory_picking;

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
import com.erp.wms.api.MaterialInventoryPickingAPI;
import com.erp.wms.material.inventory_picking.model.PicklistModel;
import com.erp.wms.material.inventory_picking.view.EnrollSelectPicklistView;
import com.erp.wms.material.inventory_picking.view.EnrollSetSpecificView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;

import java.util.ArrayList;

public class EnrollActivity extends SherlockActivity implements ActionBar.TabListener {

    private Context mContext;

    private long mInventoryPickingId;
    private String mSearchKeyword;

    private RelativeLayout mSelectPicklistContainer;
    private EditText mEtSelectPicklistSearchKeyword;
    private MyListView<EnrollSelectPicklistView> mSelectPicklistView;
    private long mInventoryPicklistId;
    private boolean mIsSelectPicklistViewLoaded;

    private EnrollSetSpecificView mSetSpecificView;

    private ActionBar.Tab mTabPicklist;
    private ActionBar.Tab mTabSpecific;
    private ActionBar.Tab mCurrentTab;

    private IntentIntegrator mBarcodeIntentIntegrator;
    private MenuItem mBarcodeMenuItem;

    private int mActivityResultCode = RESULT_CANCELED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Enroll Picking";

        mContext = this;
        mBarcodeIntentIntegrator = new IntentIntegrator(this);
        mBarcodeMenuItem = null;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            mInventoryPickingId = extras.getLong("m_inventory_picking_id");
            mSearchKeyword = extras.getString("search_keyword");
        }
        this.setTitle(title);

        createPicklistView();
        createSpecificView();

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.shopping_cart_loaded);
        createTab(actionBar);
    }

    private void createPicklistView() {
        mIsSelectPicklistViewLoaded = false;

        mSelectPicklistContainer = new RelativeLayout(this);
        mSelectPicklistContainer.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));

        mEtSelectPicklistSearchKeyword = new EditText(this);
        mEtSelectPicklistSearchKeyword.setId(GeneratorUtil.generateViewId());
            RelativeLayout.LayoutParams tvSelectPicklistSearchKeywordLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            tvSelectPicklistSearchKeywordLayout.addRule(RelativeLayout.ALIGN_PARENT_TOP);
            tvSelectPicklistSearchKeywordLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mEtSelectPicklistSearchKeyword.setLayoutParams(tvSelectPicklistSearchKeywordLayout);
        mEtSelectPicklistSearchKeyword.setHint("Search Keyword");
        mEtSelectPicklistSearchKeyword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
        mEtSelectPicklistSearchKeyword.setImeOptions(EditorInfo.IME_ACTION_GO);
        mEtSelectPicklistSearchKeyword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_GO || (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    setSelectPicklistViewRecords(1, String.valueOf(mEtSelectPicklistSearchKeyword.getText()));
                    return true;
                }
                return false;
            }
        });
        mEtSelectPicklistSearchKeyword.setText(mSearchKeyword);
        mSelectPicklistContainer.addView(mEtSelectPicklistSearchKeyword);

        mSelectPicklistView = new MyListView<>(this);
        mSelectPicklistView.setId(GeneratorUtil.generateViewId());
            RelativeLayout.LayoutParams selectPicklistViewLayout = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            selectPicklistViewLayout.addRule(RelativeLayout.BELOW, mEtSelectPicklistSearchKeyword.getId());
            selectPicklistViewLayout.addRule(RelativeLayout.ALIGN_RIGHT, mEtSelectPicklistSearchKeyword.getId());
            selectPicklistViewLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        mSelectPicklistView.setLayoutParams(selectPicklistViewLayout);
        mSelectPicklistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mSelectPicklistView.setSelected(position);
                mInventoryPicklistId = id;
                mTabSpecific.select();
            }
        });
        mSelectPicklistView.setOnLoadList(new MyListView.LoadListListener() {
            @Override
            public void onReloadAfterRemove(int page) {
                setSelectPicklistViewRecords(page, String.valueOf(mEtSelectPicklistSearchKeyword.getText()));
            }

            @Override
            public void onLoadMore(int page) {
                setSelectPicklistViewRecords(page, String.valueOf(mEtSelectPicklistSearchKeyword.getText()));
            }
        });
        mSelectPicklistContainer.addView(mSelectPicklistView);
    }

    private void setSelectPicklistViewRecords(int page, String keyword) {
        JsonAPIFilter jsonAPIFilter = new JsonAPIFilter("OR");
        if (!keyword.isEmpty()){
            jsonAPIFilter.Add("ipl.code", keyword, "cn");
            jsonAPIFilter.Add("oo.code", keyword, "cn");
            jsonAPIFilter.Add("bp.name", keyword, "cn");
        }

        MaterialInventoryPickingAPI.GetPicklistDetails(page, jsonAPIFilter, new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    ArrayList<EnrollSelectPicklistView> records = new ArrayList<>();

                    org.json.JSONArray jrecords = response.getJSONArray("data");
                    for (int jrecord_idx = 0; jrecord_idx < jrecords.length(); jrecord_idx++) {
                        org.json.JSONObject record = jrecords.getJSONObject(jrecord_idx);

                        PicklistModel recordData = new PicklistModel(record.getLong("id"));
                        if (!record.isNull("code"))
                            recordData.setInventoryPicklistCode(record.getString("code"));
                        if (!record.isNull("picklist_date"))
                            recordData.setInventoryPicklistDate(DateTimeUtil.FromDateString(record.getString("picklist_date")));

                        if (!record.isNull("c_orderout_id"))
                            recordData.setOrderOutId(record.getLong("c_orderout_id"));
                        if (!record.isNull("c_orderout_code"))
                            recordData.setOrderOutCode(record.getString("c_orderout_code"));
                        if (!record.isNull("c_orderout_date"))
                            recordData.setOrderOutDate(DateTimeUtil.FromDateString(record.getString("c_orderout_date")));
                        if (!record.isNull("c_orderout_request_arrive_date"))
                            recordData.setRequestArriveDate(DateTimeUtil.FromDateString(record.getString("c_orderout_request_arrive_date")));

                        if (!record.isNull("quantity_box"))
                            recordData.setQuantityBox(record.getInt("quantity_box"));
                        if (!record.isNull("quantity"))
                            recordData.setQuantity(record.getDouble("quantity"));

                        if (!record.isNull("c_project_id"))
                            recordData.setProjectId(record.getInt("c_project_id"));
                        if (!record.isNull("c_project_name"))
                            recordData.setProjectName(record.getString("c_project_name"));

                        if (!record.isNull("c_businesspartner_id"))
                            recordData.setBusinessPartnerId(record.getInt("c_businesspartner_id"));
                        if (!record.isNull("c_businesspartner_name"))
                            recordData.setBusinessPartner(record.getString("c_businesspartner_name"));

                        records.add(new EnrollSelectPicklistView(mContext, recordData));
                    }

                    EnrollSelectPicklistView[] recordViewsData = new EnrollSelectPicklistView[records.size()];
                    records.toArray(recordViewsData);

                    int records_total = 0;
                    if (!response.isNull("records"))
                        records_total = response.getInt("records");
                    mSelectPicklistView.setRecordTotal(records_total);

                    int page_current = 1;
                    if (!response.isNull("page"))
                        page_current = response.getInt("page");
                    mSelectPicklistView.setPageCurrent(page_current);

                    int page_total = 0;
                    if (!response.isNull("total"))
                        page_total = response.getInt("total");
                    mSelectPicklistView.setPageTotal(page_total);

                    if (page_current == 1)
                        mSelectPicklistView.setRecords(recordViewsData);
                    else
                        mSelectPicklistView.addRecords(recordViewsData);

                    mIsSelectPicklistViewLoaded = true;
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

        mTabPicklist = actionBar.newTab();
        mTabPicklist.setTag("SelectPicklist");
        mTabPicklist.setText("Select Picklist");
        mTabPicklist.setTabListener(this);
        actionBar.addTab(mTabPicklist, true);

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
        if (tag.equals("SelectPicklist")) {
            setContentView(mSelectPicklistContainer);
            if (!mIsSelectPicklistViewLoaded)
                setSelectPicklistViewRecords(1, String.valueOf(mEtSelectPicklistSearchKeyword.getText()));
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
        MaterialInventoryPickingAPI.InsertDetail(mInventoryPickingId, mInventoryPicklistId, mSetSpecificView.getData(), new RestfulResponse(this) {
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
        data.putExtra("m_inventory_picking_id", mInventoryPickingId);
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
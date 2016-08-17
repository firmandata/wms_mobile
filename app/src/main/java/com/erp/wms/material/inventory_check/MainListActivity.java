package com.erp.wms.material.inventory_check;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DateTimeUtil;
import com.erp.helper.widget.mylist.MyListView;
import com.erp.wms.R;
import com.erp.wms.api.MaterialInventoryAPI;
import com.erp.wms.material.inventory_check.model.MainDetailModel;
import com.erp.wms.material.inventory_check.model.MainModel;
import com.erp.wms.material.inventory_check.model.MainSummaryModel;
import com.erp.wms.material.inventory_check.view.MainDetailView;
import com.erp.wms.material.inventory_check.view.MainSummaryView;

import java.util.ArrayList;

public class MainListActivity extends SherlockActivity implements ActionBar.TabListener {

    private Context mContext;

    private MainModel mMainModel;

    private MainSummaryView mMainSummaryView;
    private boolean mIsMainSummaryViewLoaded;

    private MyListView<MainDetailView> mDetailView;
    private boolean mIsDetailViewLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Inventory Result";

        mContext = this;
        mMainModel = new MainModel();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");

            mMainModel.setProductCode(extras.getString("m_product_code"));
            mMainModel.setBarcode(extras.getString("barcode"));
            mMainModel.setPallet(extras.getString("pallet"));
            mMainModel.setGridCode(extras.getString("m_grid_code"));
        }
        this.setTitle(title);

        createSummaryView();
        createDetailView();

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.accept_database);
        createTab(actionBar);
    }

    private void createSummaryView() {
        mIsMainSummaryViewLoaded = false;

        mMainSummaryView = new MainSummaryView(mContext);
        mMainSummaryView.setLayoutParams(new MainSummaryView.LayoutParams(MainSummaryView.LayoutParams.MATCH_PARENT, MainSummaryView.LayoutParams.MATCH_PARENT));
    }

    private void createDetailView() {
        mIsDetailViewLoaded = false;

        mDetailView = new MyListView<>(this);
        mDetailView.setLayoutParams(new MyListView.LayoutParams(MyListView.LayoutParams.MATCH_PARENT, MyListView.LayoutParams.MATCH_PARENT));
        mDetailView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDetailView.setSelected(position);
            }
        });
        mDetailView.setOnLoadList(new MyListView.LoadListListener() {
            @Override
            public void onReloadAfterRemove(int page) {
                setMainViewDetailRecords(page);
            }

            @Override
            public void onLoadMore(int page) {
                setMainViewDetailRecords(page);
            }
        });
    }

    private void createTab(ActionBar actionBar) {
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tabHeader = actionBar.newTab();
        tabHeader.setTag("Summary");
        tabHeader.setText("Summary");
        tabHeader.setTabListener(this);
        actionBar.addTab(tabHeader, true);

        ActionBar.Tab tabDetail = actionBar.newTab();
        tabDetail.setTag("Detail");
        tabDetail.setText("Detail");
        tabDetail.setTabListener(this);
        actionBar.addTab(tabDetail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setMainViewSummaries() {
        MaterialInventoryAPI.GetCheckSummaries(mMainModel, new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    MainSummaryModel recordData = new MainSummaryModel();

                    org.json.JSONArray jrecords = response.getJSONArray("data");
                    for (int jrecord_idx = 0; jrecord_idx < jrecords.length(); jrecord_idx++) {
                        org.json.JSONObject record = jrecords.getJSONObject(jrecord_idx);

                        if (!record.isNull("quantity_box_exist"))
                            recordData.setQuantityBoxExist(record.getInt("quantity_box_exist"));
                        if (!record.isNull("quantity_box_allocated"))
                            recordData.setQuantityBoxAllocated(record.getInt("quantity_box_allocated"));
                        if (!record.isNull("quantity_box_picked"))
                            recordData.setQuantityBoxPicked(record.getInt("quantity_box_picked"));
                        if (!record.isNull("quantity_box_onhand"))
                            recordData.setQuantityBoxOnhand(record.getInt("quantity_box_onhand"));

                        if (!record.isNull("quantity_exist"))
                            recordData.setQuantityExist(record.getDouble("quantity_exist"));
                        if (!record.isNull("quantity_allocated"))
                            recordData.setQuantityAllocated(record.getDouble("quantity_allocated"));
                        if (!record.isNull("quantity_picked"))
                            recordData.setQuantityPicked(record.getDouble("quantity_picked"));
                        if (!record.isNull("quantity_onhand"))
                            recordData.setQuantityOnhand(record.getDouble("quantity_onhand"));
                    }
                    mMainSummaryView.setRecord(recordData);
                    mIsMainSummaryViewLoaded = true;
                } catch (org.json.JSONException e) {
                    Toast.makeText(MainListActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainListActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setMainViewDetailRecords(int page) {
        MaterialInventoryAPI.GetChecks(page, mMainModel, new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    ArrayList<MainDetailView> records = new ArrayList<>();

                    org.json.JSONArray jrecords = response.getJSONArray("data");
                    for (int jrecord_idx = 0; jrecord_idx < jrecords.length(); jrecord_idx++) {
                        org.json.JSONObject record = jrecords.getJSONObject(jrecord_idx);

                        MainDetailModel recordData = new MainDetailModel();
                        if (!record.isNull("m_product_id"))
                            recordData.setProductId(record.getInt("m_product_id"));
                        if (!record.isNull("m_product_code"))
                            recordData.setProductCode(record.getString("m_product_code"));
                        if (!record.isNull("m_product_name"))
                            recordData.setProductName(record.getString("m_product_name"));
                        if (!record.isNull("m_product_uom"))
                            recordData.setProductUom(record.getString("m_product_uom"));
                        if (!record.isNull("m_product_pack"))
                            recordData.setProductPack(record.getInt("m_product_pack"));

                        if (!record.isNull("m_grid_id"))
                            recordData.setGridId(record.getInt("m_grid_id"));
                        if (!record.isNull("m_grid_code"))
                            recordData.setGridCode(record.getString("m_grid_code"));

                        if (!record.isNull("m_warehouse_id"))
                            recordData.setWarehouseId(record.getInt("m_warehouse_id"));
                        if (!record.isNull("m_warehouse_name"))
                            recordData.setWarehouseName(record.getString("m_warehouse_name"));
                        if (!record.isNull("m_warehouse_code"))
                            recordData.setWarehouseCode(record.getString("m_warehouse_code"));

                        if (!record.isNull("m_productgroup_id"))
                            recordData.setProductGroupId(record.getInt("m_productgroup_id"));
                        if (!record.isNull("m_productgroup_name"))
                            recordData.setProductGroupName(record.getString("m_productgroup_name"));

                        if (!record.isNull("barcode"))
                            recordData.setBarcode(record.getString("barcode"));
                        if (!record.isNull("pallet"))
                            recordData.setPallet(record.getString("pallet"));
                        if (!record.isNull("carton_no"))
                            recordData.setCartonNo(record.getString("carton_no"));
                        if (!record.isNull("packed_date"))
                            recordData.setPackedDate(DateTimeUtil.FromDateString(record.getString("packed_date")));
                        if (!record.isNull("expired_date"))
                            recordData.setExpiredDate(DateTimeUtil.FromDateString(record.getString("expired_date")));
                        if (!record.isNull("condition"))
                            recordData.setCondition(record.getString("condition"));
                        if (!record.isNull("lot_no"))
                            recordData.setLotNo(record.getString("lot_no"));

                        if (!record.isNull("volume_length"))
                            recordData.setVolumeLength(record.getDouble("volume_length"));
                        if (!record.isNull("volume_width"))
                            recordData.setVolumeWidth(record.getDouble("volume_width"));
                        if (!record.isNull("volume_height"))
                            recordData.setVolumeHeight(record.getDouble("volume_height"));

                        if (!record.isNull("c_project_id"))
                            recordData.setProjectId(record.getInt("c_project_id"));
                        if (!record.isNull("c_project_name"))
                            recordData.setProjectName(record.getString("c_project_name"));

                        if (!record.isNull("inventory_age"))
                            recordData.setAge(record.getInt("inventory_age"));

                        if (!record.isNull("quantity_box_exist"))
                            recordData.setQuantityBoxExist(record.getInt("quantity_box_exist"));
                        if (!record.isNull("quantity_box_allocated"))
                            recordData.setQuantityBoxAllocated(record.getInt("quantity_box_allocated"));
                        if (!record.isNull("quantity_box_picked"))
                            recordData.setQuantityBoxPicked(record.getInt("quantity_box_picked"));
                        if (!record.isNull("quantity_box_onhand"))
                            recordData.setQuantityBoxOnhand(record.getInt("quantity_box_onhand"));

                        if (!record.isNull("quantity_exist"))
                            recordData.setQuantityExist(record.getDouble("quantity_exist"));
                        if (!record.isNull("quantity_allocated"))
                            recordData.setQuantityAllocated(record.getDouble("quantity_allocated"));
                        if (!record.isNull("quantity_picked"))
                            recordData.setQuantityPicked(record.getDouble("quantity_picked"));
                        if (!record.isNull("quantity_onhand"))
                            recordData.setQuantityOnhand(record.getDouble("quantity_onhand"));

                        records.add(new MainDetailView(mContext, recordData));
                    }

                    MainDetailView[] recordViewsData = new MainDetailView[records.size()];
                    records.toArray(recordViewsData);

                    int records_total = 0;
                    if (!response.isNull("records"))
                        records_total = response.getInt("records");
                    mDetailView.setRecordTotal(records_total);

                    int page_current = 1;
                    if (!response.isNull("page"))
                        page_current = response.getInt("page");
                    mDetailView.setPageCurrent(page_current);

                    int page_total = 0;
                    if (!response.isNull("total"))
                        page_total = response.getInt("total");
                    mDetailView.setPageTotal(page_total);

                    if (page_current == 1)
                        mDetailView.setRecords(recordViewsData);
                    else
                        mDetailView.addRecords(recordViewsData);

                    mIsDetailViewLoaded = true;
                } catch (org.json.JSONException e) {
                    Toast.makeText(MainListActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainListActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        String tag = tab.getTag().toString();
        if (tag.equals("Summary")) {
            setContentView(mMainSummaryView);
            if (!mIsMainSummaryViewLoaded)
                setMainViewSummaries();
        }
        else if (tag.equals("Detail")) {
            setContentView(mDetailView);
            if (!mIsDetailViewLoaded)
                setMainViewDetailRecords(1);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
package com.erp.wms.material.inventory_picking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DateTimeUtil;
import com.erp.helper.widget.mylist.MyListView;
import com.erp.wms.R;
import com.erp.wms.api.JsonAPIFilter;
import com.erp.wms.api.MaterialInventoryPickingAPI;
import com.erp.wms.api.SessionAPI;
import com.erp.wms.material.inventory_picking.model.HeaderModel;
import com.erp.wms.material.inventory_picking.view.MainListView;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends SherlockActivity implements ActionBar.OnNavigationListener {

    private Context mContext;
    private MyListView<MainListView> mMainView;

    private String[] mStatuses = {"Pending", "All"};
    private int mStatusSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Inventory Picking";

        mContext = this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
        }
        this.setTitle(title);

        // -- Begin status filter --
        Context context = getSupportActionBar().getThemedContext();
        ArrayAdapter<String> list = new ArrayAdapter<>(context, R.layout.sherlock_spinner_item, mStatuses);
        list.setDropDownViewResource(R.layout.sherlock_spinner_dropdown_item);

        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setListNavigationCallbacks(list, this);
        // -- End status filter --

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.shopping_cart_loaded);

        mMainView = new MyListView<>(this);
        mMainView.setLayoutParams(new MyListView.LayoutParams(MyListView.LayoutParams.MATCH_PARENT, MyListView.LayoutParams.MATCH_PARENT));
        mMainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMainView.setSelected(position);
                showDetailActivity(id);
            }
        });
        mMainView.setOnLoadList(new MyListView.LoadListListener() {
            @Override
            public void onReloadAfterRemove(int page) {
                setMainViewRecords(page, mStatusSelected);
            }

            @Override
            public void onLoadMore(int page) {
                setMainViewRecords(page, mStatusSelected);
            }
        });
        setContentView(mMainView);

        //setMainViewRecords(1, mStatusSelected);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (SessionAPI.IsAllowAccess(mContext, "material/inventory_picking", "insert")) {
            menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "New")
                    .setIcon(R.drawable.plus)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;

            case Menu.FIRST:
                showHeaderActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setMainViewRecords(int page, int statusSelected) {
        JsonAPIFilter jsonAPIFilter = new JsonAPIFilter("OR");
        if (statusSelected == 0){
            jsonAPIFilter.Add("ipl.status_inventory_picking", "COMPLETE", "ne");
            jsonAPIFilter.Add("ipl.status_inventory_picking", "NULL", "nu");
        }

        MaterialInventoryPickingAPI.GetHeaders(page, jsonAPIFilter, 0, 0, 0, 0, new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    ArrayList<MainListView> recordViews = new ArrayList<>();

                    org.json.JSONArray jrecords = response.getJSONArray("data");
                    for(int jrecord_idx = 0; jrecord_idx < jrecords.length(); jrecord_idx++) {
                        org.json.JSONObject record = jrecords.getJSONObject(jrecord_idx);

                        HeaderModel recordData = new HeaderModel(record.getLong("id"));
                        if (!record.isNull("code"))
                            recordData.setPickingCode(record.getString("code"));
                        if (!record.isNull("picking_date"))
                            recordData.setPickingDate(DateTimeUtil.FromDateString(record.getString("picking_date")));

                        if (!record.isNull("m_inventory_picklist_id"))
                            recordData.setInventoryPicklistId(record.getLong("m_inventory_picklist_id"));
                        if (!record.isNull("m_inventory_picklist_code"))
                            recordData.setInventoryPicklistCode(record.getString("m_inventory_picklist_code"));
                        if (!record.isNull("m_inventory_picklist_date"))
                            recordData.setInventoryPicklistDate(DateTimeUtil.FromDateString(record.getString("m_inventory_picklist_date")));

                        if (!record.isNull("status_inventory_shipment"))
                            recordData.setStatusInventoryShipment(record.getString("status_inventory_shipment"));

                        if (!record.isNull("c_orderout_id"))
                            recordData.setOrderOutId(record.getLong("c_orderout_id"));
                        if (!record.isNull("c_orderout_code"))
                            recordData.setOrderOutCode(record.getString("c_orderout_code"));
                        if (!record.isNull("c_orderout_date"))
                            recordData.setOrderOutDate(DateTimeUtil.FromDateString(record.getString("c_orderout_date")));
                        if (!record.isNull("c_orderout_request_arrive_date"))
                            recordData.setOrderOutRequestArriveDate(DateTimeUtil.FromDateString(record.getString("c_orderout_request_arrive_date")));

                        if (!record.isNull("c_businesspartner_id"))
                            recordData.setBusinessPartnerId(record.getInt("c_businesspartner_id"));
                        if (!record.isNull("c_businesspartner_name"))
                            recordData.setBusinessPartner(record.getString("c_businesspartner_name"));

                        if (!record.isNull("c_project_id"))
                            recordData.setProjectId(record.getInt("c_project_id"));
                        if (!record.isNull("c_project_name"))
                            recordData.setProjectName(record.getString("c_project_name"));

                        recordViews.add(new MainListView(mContext, recordData));
                    }

                    MainListView[] recordViewsData = new MainListView[recordViews.size()];
                    recordViews.toArray(recordViewsData);

                    int records_total = 0;
                    if (!response.isNull("records"))
                        records_total = response.getInt("records");
                    mMainView.setRecordTotal(records_total);

                    int page_current = 1;
                    if (!response.isNull("page"))
                        page_current = response.getInt("page");
                    mMainView.setPageCurrent(page_current);

                    int page_total = 0;
                    if (!response.isNull("total"))
                        page_total = response.getInt("total");
                    mMainView.setPageTotal(page_total);

                    if (page_current == 1)
                        mMainView.setRecords(recordViewsData);
                    else
                        mMainView.addRecords(recordViewsData);
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void showHeaderActivity() {
        Intent intent = new Intent(this, HeaderActivity.class);
        intent.putExtra("title", "Create Picking");
        this.startActivityForResult(intent, 1);
    }

    private void showEnrollActivity(long m_inventory_picking_id, String searchKeyword) {
        Intent intent = new Intent(this, EnrollActivity.class);
        intent.putExtra("title", "Enroll Picking");
        intent.putExtra("m_inventory_picking_id", m_inventory_picking_id);
        intent.putExtra("search_keyword", searchKeyword);
        this.startActivityForResult(intent, 2);
    }

    private void showDetailActivity(long m_inventory_picking_id) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("title", "Picking Detail");
        intent.putExtra("m_inventory_picking_id", m_inventory_picking_id);
        this.startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;

        Bundle extra = data.getExtras();
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    if (extra != null) {
                        long m_inventory_picking_id = extra.getLong("m_inventory_picking_id");
                        String searchKeyword = extra.getString("search_keyword");
                        showEnrollActivity(m_inventory_picking_id, searchKeyword);
                    }

                    break;
                case 2:
                case 3:
                    setMainViewRecords(1, mStatusSelected);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
        mStatusSelected = itemPosition;
        setMainViewRecords(1, mStatusSelected);
        return true;
    }
}
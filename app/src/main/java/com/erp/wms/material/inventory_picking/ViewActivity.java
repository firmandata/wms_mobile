package com.erp.wms.material.inventory_picking;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.erp.wms.api.MaterialInventoryPickingAPI;
import com.erp.wms.api.SessionAPI;
import com.erp.wms.material.inventory_picking.model.DetailModel;
import com.erp.wms.material.inventory_picking.model.HeaderModel;
import com.erp.wms.material.inventory_picking.view.ViewDetailView;
import com.erp.wms.material.inventory_picking.view.ViewHeaderView;

import org.json.JSONException;

import java.util.ArrayList;

public class ViewActivity extends SherlockActivity implements ActionBar.TabListener {

    private Context mContext;

    private long mInventoryPickingId;
    private String mInventoryPickingCode;

    private ViewHeaderView mHeaderView;
    private boolean mIsHeaderViewLoaded;

    private MyListView<ViewDetailView> mDetailView;
    private DetailModel mSelectedDetailModel;
    private boolean mIsDetailViewLoaded;

    private ActionBar.Tab mCurrentTab;

    private int mActivityResultCode = RESULT_CANCELED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Picking Detail";

        mContext = this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            mInventoryPickingId = extras.getLong("m_inventory_picking_id");
        }
        this.setTitle(title);

        createHeaderView();
        createDetailView();

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.shopping_cart_loaded);
        createTab(actionBar);
    }

    private void createHeaderView() {
        mIsHeaderViewLoaded = false;

        mHeaderView = new ViewHeaderView(this);
        mHeaderView.setLayoutParams(new ViewHeaderView.LayoutParams(ViewHeaderView.LayoutParams.MATCH_PARENT, ViewHeaderView.LayoutParams.MATCH_PARENT));
    }

    private void setHeaderRecord() {
        MaterialInventoryPickingAPI.GetHeader(mInventoryPickingId, new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    boolean is_response_ok = false;
                    if (!response.isNull("response"))
                        is_response_ok = response.getBoolean("response");

                    if (is_response_ok) {
                        org.json.JSONObject jrecord = response.getJSONObject("value");

                        HeaderModel record = new HeaderModel(jrecord.getLong("id"));
                        if (!jrecord.isNull("code")) {
                            record.setPickingCode(jrecord.getString("code"));
                            mInventoryPickingCode = jrecord.getString("code");
                        }
                        if (!jrecord.isNull("picking_date"))
                            record.setPickingDate(DateTimeUtil.FromDateString(jrecord.getString("picking_date")));
                        if (!jrecord.isNull("notes"))
                            record.setNotes(jrecord.getString("notes"));

                        mHeaderView.setData(record);
                        mIsHeaderViewLoaded = true;
                    } else {
                        String response_message = null;
                        if (!response.isNull("value"))
                            response_message = response.getString("value");

                        Toast.makeText(ViewActivity.this, response_message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createDetailView() {
        mIsDetailViewLoaded = false;

        mDetailView = new MyListView<>(this);
        mDetailView.setLayoutParams(new MyListView.LayoutParams(MyListView.LayoutParams.MATCH_PARENT, MyListView.LayoutParams.MATCH_PARENT));
        mDetailView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDetailView.setSelected(position);

                ViewDetailView viewDetailView = mDetailView.getRecordByPosition(position);
                if (viewDetailView != null)
                    mSelectedDetailModel = viewDetailView.getData();
            }
        });
        mDetailView.setOnLoadList(new MyListView.LoadListListener() {
            @Override
            public void onReloadAfterRemove(int page) {
                setDetailViewRecords(page);
            }

            @Override
            public void onLoadMore(int page) {
                setDetailViewRecords(page);
            }
        });
    }

    private void setDetailViewRecords(int page) {
        MaterialInventoryPickingAPI.GetDetails(page, mInventoryPickingId, new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    ArrayList<ViewDetailView> records = new ArrayList<>();

                    org.json.JSONArray jrecords = response.getJSONArray("data");
                    for (int jrecord_idx = 0; jrecord_idx < jrecords.length(); jrecord_idx++) {
                        org.json.JSONObject record = jrecords.getJSONObject(jrecord_idx);

                        DetailModel recordData = new DetailModel(record.getLong("id"), mInventoryPickingId, record.getLong("m_inventory_picklist_id"));

                        if (!record.isNull("m_inventory_picklist_code"))
                            recordData.setInventoryPicklistCode(record.getString("m_inventory_picklist_code"));
                        if (!record.isNull("m_inventory_picklist_date"))
                            recordData.setInventoryPicklistDate(DateTimeUtil.FromDateString(record.getString("m_inventory_picklist_date")));

                        if (!record.isNull("barcode"))
                            recordData.setBarcode(record.getString("barcode"));
                        if (!record.isNull("pallet"))
                            recordData.setPallet(record.getString("pallet"));
                        if (!record.isNull("carton_no"))
                            recordData.setCartonNo(record.getString("carton_no"));
                        if (!record.isNull("lot_no"))
                            recordData.setLotNo(record.getString("lot_no"));
                        if (!record.isNull("condition"))
                            recordData.setCondition(record.getString("condition"));
                        if (!record.isNull("packed_group"))
                            recordData.setPackedGroup(record.getString("packed_group"));

                        if (!record.isNull("m_product_id"))
                            recordData.setProductId(record.getInt("m_product_id"));
                        if (!record.isNull("m_product_code"))
                            recordData.setProductCode(record.getString("m_product_code"));
                        if (!record.isNull("m_product_name"))
                            recordData.setProductName(record.getString("m_product_name"));
                        if (!record.isNull("m_product_uom"))
                            recordData.setProductUom(record.getString("m_product_uom"));

                        if (!record.isNull("quantity_box"))
                            recordData.setQuantityBox(record.getInt("quantity_box"));
                        if (!record.isNull("quantity"))
                            recordData.setQuantity(record.getDouble("quantity"));

                        if (!record.isNull("quantity_box_used"))
                            recordData.setQuantityBoxUsed(record.getInt("quantity_box_used"));
                        if (!record.isNull("quantity_used"))
                            recordData.setQuantityUsed(record.getDouble("quantity_used"));

                        if (!record.isNull("status_inventory_shipment"))
                            recordData.setStatusInventoryShipment(record.getString("status_inventory_shipment"));

                        if (!record.isNull("m_grid_id"))
                            recordData.setGridId(record.getInt("m_grid_id"));
                        if (!record.isNull("m_grid_code"))
                            recordData.setGridCode(record.getString("m_grid_code"));

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

                        records.add(new ViewDetailView(mContext, recordData));
                    }

                    ViewDetailView[] recordViewsData = new ViewDetailView[records.size()];
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
                } catch (JSONException e) {
                    Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void showHeaderActivity() {
        Intent intent = new Intent(this, HeaderActivity.class);
        intent.putExtra("title", "Edit Picking");
        intent.putExtra("m_inventory_picking_id", mInventoryPickingId);
        this.startActivityForResult(intent, 1);
    }

    private void showEnrollActivity() {
        Intent intent = new Intent(this, EnrollActivity.class);
        intent.putExtra("title", "Enroll Picking");
        intent.putExtra("m_inventory_picking_id", mInventoryPickingId);
        intent.putExtra("search_keyword", mInventoryPickingCode);
        this.startActivityForResult(intent, 2);
    }

    private void deleteHeader() {
        MaterialInventoryPickingAPI.DeleteHeader(mInventoryPickingId, new RestfulResponse(this) {
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
                        Toast.makeText(ViewActivity.this, error_message, Toast.LENGTH_LONG).show();
                    } else {
                        mActivityResultCode = RESULT_OK;
                        closeActivity();
                    }
                } catch (JSONException e) {
                    Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void deleteDetail(DetailModel detailModel) {
        MaterialInventoryPickingAPI.DeleteDetail(detailModel, new RestfulResponse(this) {
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
                        Toast.makeText(ViewActivity.this, error_message, Toast.LENGTH_LONG).show();
                    } else {
                        mSelectedDetailModel = null;
                        mActivityResultCode = RESULT_OK;
                        mIsDetailViewLoaded = false;

                        if (mCurrentTab.getTag().toString().equals("Detail")) {
                            setDetailViewRecords(1);
                        }
                    }
                } catch (JSONException e) {
                    Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void createTab(ActionBar actionBar) {
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tabHeader = actionBar.newTab();
        tabHeader.setTag("Header");
        tabHeader.setText("Header");
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
        if (SessionAPI.IsAllowAccess(mContext, "material/inventory_picking", "update")) {
            menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Edit")
                    .setIcon(R.drawable.edit)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }
        if (SessionAPI.IsAllowAccess(mContext, "material/inventory_picking", "insert")) {
            menu.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "Enroll")
                    .setIcon(R.drawable.to_do)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        }
        if (SessionAPI.IsAllowAccess(mContext, "material/inventory_picking", "delete")) {
            menu.add(Menu.NONE, Menu.FIRST + 2, Menu.NONE, "Delete")
                    .setIcon(R.drawable.delete)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
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
                showHeaderActivity();
                return true;

            case Menu.FIRST + 1:
                showEnrollActivity();
                return true;

            case Menu.FIRST + 2:
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                String currentTabTag = mCurrentTab.getTag().toString();
                if (currentTabTag.equals("Header")) {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:
                                    deleteHeader();
                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                default:
                                    break;
                            }
                        }
                    };
                    builder.setMessage("Are you sure?")
                        .setPositiveButton("Delete", dialogClickListener)
                        .setNegativeButton("Cancel", dialogClickListener)
                        .show();
                }
                else if (currentTabTag.equals("Detail")) {
                    if (mSelectedDetailModel != null) {
                        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case DialogInterface.BUTTON_POSITIVE:
                                        deleteDetail(mSelectedDetailModel);
                                        break;
                                    case DialogInterface.BUTTON_NEGATIVE:
                                    default:
                                        break;
                                }
                            }
                        };
                        builder.setMessage("Are you sure?")
                            .setPositiveButton("Delete", dialogClickListener)
                            .setNegativeButton("Cancel", dialogClickListener)
                            .show();
                    }
                    else
                        Toast.makeText(ViewActivity.this, "Please select the data.", Toast.LENGTH_LONG).show();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        String tag = tab.getTag().toString();
        if (tag.equals("Header")) {
            setContentView(mHeaderView);
            if (!mIsHeaderViewLoaded)
                setHeaderRecord();
        }
        else if (tag.equals("Detail")) {
            setContentView(mDetailView);
            if (!mIsDetailViewLoaded)
                setDetailViewRecords(1);
        }

        mCurrentTab = tab;
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;

        String currentTabTag = mCurrentTab.getTag().toString();

        Bundle extra = data.getExtras();
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    mActivityResultCode = resultCode;
                    mIsHeaderViewLoaded = false;
                    if (currentTabTag.equals("Header")) {
                        setHeaderRecord();
                    }
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    mActivityResultCode = resultCode;
                    mIsDetailViewLoaded = false;
                    if (currentTabTag.equals("Detail")) {
                        setDetailViewRecords(1);
                    }
                }
                break;
            default:
                break;
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
}

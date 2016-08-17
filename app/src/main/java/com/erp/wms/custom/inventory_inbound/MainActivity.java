package com.erp.wms.custom.inventory_inbound;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.erp.wms.api.CustomInventoryInboundAPI;
import com.erp.wms.api.SessionAPI;
import com.erp.wms.custom.inventory_inbound.model.Model;
import com.erp.wms.custom.inventory_inbound.view.MainListView;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends SherlockActivity {

    private Context mContext;
    private MyListView<MainListView> mMainView;
    private MainListView mMainViewSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Inventory Inbound Live";

        mContext = this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
        }
        this.setTitle(title);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.rack);

        mMainView = new MyListView<>(this);
        mMainView.setLayoutParams(new MyListView.LayoutParams(MyListView.LayoutParams.MATCH_PARENT, MyListView.LayoutParams.MATCH_PARENT));
        mMainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMainView.setSelected(position);
                mMainViewSelected = mMainView.getRecordByPosition(position);
                showViewActivity(id);
            }
        });
        mMainView.setOnLoadList(new MyListView.LoadListListener() {
            @Override
            public void onReloadAfterRemove(int page) {
                setMainViewRecords(page);
            }

            @Override
            public void onLoadMore(int page) {
                setMainViewRecords(page);
            }
        });
        setContentView(mMainView);

        setMainViewRecords(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (SessionAPI.IsAllowAccess(mContext, "custom/inventory_inbound", "insert")) {
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
                showEnrollActivity();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setMainViewRecords(int page) {
        CustomInventoryInboundAPI.Gets(page, null, null, new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    ArrayList<MainListView> recordViews = new ArrayList<>();

                    org.json.JSONArray jrecords = response.getJSONArray("data");
                    for(int jrecord_idx = 0; jrecord_idx < jrecords.length(); jrecord_idx++) {
                        org.json.JSONObject record = jrecords.getJSONObject(jrecord_idx);

                        Model recordData = new Model(record.getLong("id"));
						
						if (!record.isNull("barcode"))
							recordData.setBarcode(record.getString("barcode"));

                        if (!record.isNull("m_product_id"))
                            recordData.setProductId(record.getInt("m_product_id"));
						if (!record.isNull("product_code"))
							recordData.setProductCode(record.getString("product_code"));
						if (!record.isNull("product_name"))
							recordData.setProductName(record.getString("product_name"));
						if (!record.isNull("product_uom"))
							recordData.setProductUom(record.getString("product_uom"));

                        if (!record.isNull("m_grid_id"))
							recordData.setGridId(record.getInt("m_grid_id"));
                        if (!record.isNull("grid_code"))
                            recordData.setGridCode(record.getString("grid_code"));

                        if (!record.isNull("pallet"))
							recordData.setPallet(record.getString("pallet"));
						if (!record.isNull("carton_no"))
							recordData.setCartonNo(record.getString("carton_no"));
						if (!record.isNull("quantity"))
							recordData.setQuantity(record.getDouble("quantity"));
						if (!record.isNull("lot_no"))
							recordData.setLotNo(record.getString("lot_no"));
						if (!record.isNull("condition"))
							recordData.setCondition(record.getString("condition"));
						if (!record.isNull("packed_date"))
							recordData.setPackedDate(DateTimeUtil.FromDateString(record.getString("packed_date")));
						if (!record.isNull("expired_date"))
							recordData.setExpiredDate(DateTimeUtil.FromDateString(record.getString("expired_date")));
                        if (!record.isNull("created"))
                            recordData.setCreatedDate(DateTimeUtil.FromDateString(record.getString("created")));

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

    private void showEnrollActivity() {
        Intent intent = new Intent(this, EnrollActivity.class);
        intent.putExtra("title", "Enroll Inbound Live");
        this.startActivityForResult(intent, 1);
    }

    private void showViewActivity(long cus_m_inventory_inbound_detail_id) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("title", "View Inbound Live");
        intent.putExtra("cus_m_inventory_inbound_detail_id", cus_m_inventory_inbound_detail_id);
        this.startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null)
            return;

        Bundle extra = data.getExtras();
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    setMainViewRecords(1);
                    break;

                case 2:
                    String action = extra.getString("action");
                    if (!action.isEmpty()) {
                        if (action.equals("DELETE")){
                            if (mMainViewSelected != null){
                                mMainView.removeRecord(mMainViewSelected);
                                mMainViewSelected = null;
                            }
                        }
                    }

                    break;
                default:
                    break;
            }
        }
    }
}
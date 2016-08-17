package com.erp.wms.material.inventory_move;

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
import com.erp.wms.api.MaterialInventoryMoveAPI;
import com.erp.wms.api.SessionAPI;
import com.erp.wms.material.inventory_move.model.HeaderModel;
import com.erp.wms.material.inventory_move.view.MainListView;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends SherlockActivity {

    private Context mContext;
    private MyListView<MainListView> mMainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Inventory Move";

        mContext = this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
        }
        this.setTitle(title);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.move_by_trolley);

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
        if (SessionAPI.IsAllowAccess(mContext, "material/inventory_move", "insert")) {
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

    private void setMainViewRecords(int page) {
        MaterialInventoryMoveAPI.GetHeaders(page, 0, 0, 0, 0, new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    ArrayList<MainListView> recordViews = new ArrayList<>();

                    org.json.JSONArray jrecords = response.getJSONArray("data");
                    for(int jrecord_idx = 0; jrecord_idx < jrecords.length(); jrecord_idx++) {
                        org.json.JSONObject record = jrecords.getJSONObject(jrecord_idx);

                        HeaderModel recordData = new HeaderModel(record.getLong("id"));

                        if (!record.isNull("code"))
                            recordData.setMoveCode(record.getString("code"));
                        if (!record.isNull("move_date"))
                            recordData.setMoveDate(DateTimeUtil.FromDateString(record.getString("move_date")));
						if (!record.isNull("product"))
                            recordData.setProduct(record.getInt("product"));
                        if (!record.isNull("quantity_box_to"))
                            recordData.setQuantityBoxTo(record.getInt("quantity_box_to"));
                        if (!record.isNull("quantity_to"))
                            recordData.setQuantityTo(record.getDouble("quantity_to"));
                        
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
        intent.putExtra("title", "Create Move");
        this.startActivityForResult(intent, 1);
    }

    private void showEnrollActivity(long m_inventory_move_id) {
        Intent intent = new Intent(this, EnrollActivity.class);
        intent.putExtra("title", "Enroll Move");
        intent.putExtra("m_inventory_move_id", m_inventory_move_id);
        this.startActivityForResult(intent, 2);
    }

    private void showDetailActivity(long m_inventory_move_id) {
        Intent intent = new Intent(this, ViewActivity.class);
        intent.putExtra("title", "Move Detail");
        intent.putExtra("m_inventory_move_id", m_inventory_move_id);
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
                        long m_inventory_move_id = extra.getLong("m_inventory_move_id");
                        showEnrollActivity(m_inventory_move_id);
                    }

                    break;
                case 2:
                case 3:
                    setMainViewRecords(1);
                    break;
                default:
                    break;
            }
        }
    }
}
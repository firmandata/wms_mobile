package com.erp.wms.material.inventory_shipment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DateTimeUtil;
import com.erp.wms.R;
import com.erp.wms.api.MaterialInventoryShipmentAPI;
import com.erp.wms.material.inventory_shipment.model.HeaderModel;
import com.erp.wms.material.inventory_shipment.view.HeaderFormView;

import org.json.JSONException;

import java.util.ArrayList;

public class HeaderActivity extends SherlockActivity {

    private HeaderFormView mLayout;
    private long mInventoryShipmentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Create Shipment";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            mInventoryShipmentId = extras.getLong("m_inventory_shipment_id");
        }
        this.setTitle(title);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.delivery);

        createView();
        setContentView(mLayout);

        if (mInventoryShipmentId > 0)
            setViewRecord();
    }

    private void createView() {
        mLayout = new HeaderFormView(this);
        mLayout.setLayoutParams(new HeaderFormView.LayoutParams(HeaderFormView.LayoutParams.MATCH_PARENT, HeaderFormView.LayoutParams.MATCH_PARENT));
        mLayout.setOnSubmitClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInventoryShipmentId > 0)
                    saveUpdate();
                else
                    saveInsert();
            }
        });
    }

    private void setViewRecord() {
        MaterialInventoryShipmentAPI.GetHeader(mInventoryShipmentId, new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    boolean is_response_ok = false;
                    if (!response.isNull("response"))
                        is_response_ok = response.getBoolean("response");

                    if (is_response_ok) {
                        org.json.JSONObject jrecord = response.getJSONObject("value");

                        HeaderModel record = new HeaderModel(jrecord.getLong("id"));
                        if (!jrecord.isNull("code"))
                            record.setShipmentCode(jrecord.getString("code"));
                        if (!jrecord.isNull("shipment_date"))
                            record.setShipmentDate(DateTimeUtil.FromDateString(jrecord.getString("shipment_date")));
                        if (!jrecord.isNull("shipment_type"))
                            record.setShipmentType(jrecord.getString("shipment_type"));
                        if (!jrecord.isNull("request_arrive_date"))
                            record.setRequestArriveDate(DateTimeUtil.FromDateString(jrecord.getString("request_arrive_date")));
                        if (!jrecord.isNull("estimated_time_arrive"))
                            record.setEstimatedTimeArrive(DateTimeUtil.FromDateString(jrecord.getString("estimated_time_arrive")));
                        if (!jrecord.isNull("shipment_to"))
                            record.setShipmentTo(jrecord.getString("shipment_to"));
                        if (!jrecord.isNull("vehicle_no"))
                            record.setVehicleNo(jrecord.getString("vehicle_no"));
                        if (!jrecord.isNull("vehicle_driver"))
                            record.setVehicleDriver(jrecord.getString("vehicle_driver"));
                        if (!jrecord.isNull("transport_mode"))
                            record.setTransportMode(jrecord.getString("transport_mode"));
                        if (!jrecord.isNull("police_name"))
                            record.setPoliceName(jrecord.getString("police_name"));
                        if (!jrecord.isNull("notes"))
                            record.setNotes(jrecord.getString("notes"));

                        mLayout.setData(record);
                    } else {
                        String response_message = null;
                        if (!response.isNull("value"))
                            response_message = response.getString("value");

                        Toast.makeText(HeaderActivity.this, response_message, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(HeaderActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(HeaderActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Save")
                .setIcon(R.drawable.save)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                closeActivity(RESULT_CANCELED, new Intent());
                return true;

            case Menu.FIRST:
                if (mInventoryShipmentId > 0)
                    saveUpdate();
                else
                    saveInsert();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void saveInsert() {
        hideKeyboard();
        MaterialInventoryShipmentAPI.InsertHeader(mLayout.getData(), new RestfulResponse(this) {
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
                        Toast.makeText(HeaderActivity.this, error_message, Toast.LENGTH_LONG).show();
                    } else {
                        long m_inventory_shipment_id = 0;
                        if (!response.isNull("value"))
                            m_inventory_shipment_id = response.getLong("value");

                        Intent data = new Intent();
                        data.putExtra("m_inventory_shipment_id", m_inventory_shipment_id);
                        data.putExtra("search_keyword", mLayout.getData().getShipmentCode());
                        closeActivity(RESULT_OK, data);
                    }
                } catch (JSONException e) {
                    Toast.makeText(HeaderActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(HeaderActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    protected void saveUpdate() {
        hideKeyboard();
        MaterialInventoryShipmentAPI.UpdateHeader(mInventoryShipmentId, mLayout.getData(), new RestfulResponse(this) {
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
                        Toast.makeText(HeaderActivity.this, error_message, Toast.LENGTH_LONG).show();
                    } else {
                        Intent data = new Intent();
                        data.putExtra("m_inventory_shipment_id", mInventoryShipmentId);
                        data.putExtra("search_keyword", mLayout.getData().getShipmentCode());
                        closeActivity(RESULT_OK, data);
                    }
                } catch (JSONException e) {
                    Toast.makeText(HeaderActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(HeaderActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    protected void closeActivity(int activityResultCode, Intent data) {
        setResult(activityResultCode, data);
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

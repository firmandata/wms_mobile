package com.erp.wms.material.inventory_picking;

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
import com.erp.wms.api.MaterialInventoryPickingAPI;
import com.erp.wms.material.inventory_picking.model.HeaderModel;
import com.erp.wms.material.inventory_picking.view.HeaderFormView;

import org.json.JSONException;

import java.util.ArrayList;

public class HeaderActivity extends SherlockActivity {

    private HeaderFormView mLayout;
    private long mInventoryPickingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Create Picking";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            mInventoryPickingId = extras.getLong("m_inventory_picking_id");
        }
        this.setTitle(title);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.shopping_cart_loaded);

        createView();
        setContentView(mLayout);

        if (mInventoryPickingId > 0)
            setViewRecord();
    }

    private void createView() {
        mLayout = new HeaderFormView(this);
        mLayout.setLayoutParams(new HeaderFormView.LayoutParams(HeaderFormView.LayoutParams.MATCH_PARENT, HeaderFormView.LayoutParams.MATCH_PARENT));
        mLayout.setOnSubmitClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInventoryPickingId > 0)
                    saveUpdate();
                else
                    saveInsert();
            }
        });
    }

    private void setViewRecord() {
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
                        if (!jrecord.isNull("code"))
                            record.setPickingCode(jrecord.getString("code"));
                        if (!jrecord.isNull("picking_date"))
                            record.setPickingDate(DateTimeUtil.FromDateString(jrecord.getString("picking_date")));
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
                if (mInventoryPickingId > 0)
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
        MaterialInventoryPickingAPI.InsertHeader(mLayout.getData(), new RestfulResponse(this) {
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
                        long m_inventory_picking_id = 0;
                        if (!response.isNull("value"))
                            m_inventory_picking_id = response.getLong("value");

                        Intent data = new Intent();
                        data.putExtra("m_inventory_picking_id", m_inventory_picking_id);
                        data.putExtra("search_keyword", mLayout.getData().getPickingCode());
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
        MaterialInventoryPickingAPI.UpdateHeader(mInventoryPickingId, mLayout.getData(), new RestfulResponse(this) {
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
                        data.putExtra("m_inventory_picking_id", mInventoryPickingId);
                        data.putExtra("search_keyword", mLayout.getData().getPickingCode());
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
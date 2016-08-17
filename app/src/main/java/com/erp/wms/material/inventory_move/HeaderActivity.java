package com.erp.wms.material.inventory_move;

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
import com.erp.wms.api.MaterialInventoryMoveAPI;
import com.erp.wms.material.inventory_move.model.HeaderModel;
import com.erp.wms.material.inventory_move.view.HeaderFormView;

import org.json.JSONException;

public class HeaderActivity extends SherlockActivity {

    private HeaderFormView mLayout;
    private long mInventoryMoveId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Create Move";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            mInventoryMoveId = extras.getLong("m_inventory_move_id");
        }
        this.setTitle(title);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.move_by_trolley);

        createView();
        setContentView(mLayout);

        if (mInventoryMoveId > 0)
            setViewRecord();
    }

    private void createView() {
        mLayout = new HeaderFormView(this);
        mLayout.setLayoutParams(new HeaderFormView.LayoutParams(HeaderFormView.LayoutParams.MATCH_PARENT, HeaderFormView.LayoutParams.MATCH_PARENT));
        mLayout.setOnSubmitClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInventoryMoveId > 0)
                    saveUpdate();
                else
                    saveInsert();
            }
        });
    }

    private void setViewRecord() {
        MaterialInventoryMoveAPI.GetHeader(mInventoryMoveId, new RestfulResponse(this) {
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
                            record.setMoveCode(jrecord.getString("code"));
                        if (!jrecord.isNull("move_date"))
                            record.setMoveDate(DateTimeUtil.FromDateString(jrecord.getString("move_date")));
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
                if (mInventoryMoveId > 0)
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
        MaterialInventoryMoveAPI.InsertHeader(mLayout.getData(), new RestfulResponse(this) {
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
                        long m_inventory_move_id = 0;
                        if (!response.isNull("value"))
                            m_inventory_move_id = response.getLong("value");

                        Intent data = new Intent();
                        data.putExtra("m_inventory_move_id", m_inventory_move_id);
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
        MaterialInventoryMoveAPI.UpdateHeader(mInventoryMoveId, mLayout.getData(), new RestfulResponse(this) {
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
                        data.putExtra("m_inventory_move_id", mInventoryMoveId);
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
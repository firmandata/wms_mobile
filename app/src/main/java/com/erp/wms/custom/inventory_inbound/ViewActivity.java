package com.erp.wms.custom.inventory_inbound;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DateTimeUtil;
import com.erp.wms.R;
import com.erp.wms.api.CustomInventoryInboundAPI;
import com.erp.wms.api.SessionAPI;
import com.erp.wms.custom.inventory_inbound.model.Model;
import com.erp.wms.custom.inventory_inbound.view.ViewView;

import org.json.JSONException;

public class ViewActivity extends SherlockActivity {

    private Context mContext;

    private long mCusInventoryInboundDetailId;

    private ViewView mView;

    private int mActivityResultCode = RESULT_CANCELED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Inbound Detail Live";

        mContext = this;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
            mCusInventoryInboundDetailId = extras.getLong("cus_m_inventory_inbound_detail_id");
        }
        this.setTitle(title);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.rack);

        createView();
        this.setContentView(mView);

		setRecord();
    }

    private void createView() {
        mView = new ViewView(this);
        mView.setLayoutParams(new ViewView.LayoutParams(ViewView.LayoutParams.MATCH_PARENT, ViewView.LayoutParams.MATCH_PARENT));
    }

    private void setRecord() {
        CustomInventoryInboundAPI.Get(mCusInventoryInboundDetailId, new RestfulResponse(this) {
            @Override
            public void onSuccess(org.json.JSONObject response) {
                try {
                    boolean is_response_ok = false;
                    if (!response.isNull("response"))
                        is_response_ok = response.getBoolean("response");

                    if (is_response_ok) {
                        org.json.JSONObject jrecord = response.getJSONObject("value");

                        Model record = new Model(mCusInventoryInboundDetailId);

						if (!jrecord.isNull("barcode"))
							record.setBarcode(jrecord.getString("barcode"));
						if (!jrecord.isNull("product_code"))
							record.setProductCode(jrecord.getString("product_code"));
						if (!jrecord.isNull("product_name"))
							record.setProductName(jrecord.getString("product_name"));
						if (!jrecord.isNull("product_uom"))
							record.setProductUom(jrecord.getString("product_uom"));
						if (!jrecord.isNull("grid_code"))
							record.setGridCode(jrecord.getString("grid_code"));
						if (!jrecord.isNull("pallet"))
							record.setPallet(jrecord.getString("pallet"));
						if (!jrecord.isNull("carton_no"))
							record.setCartonNo(jrecord.getString("carton_no"));
						if (!jrecord.isNull("quantity"))
							record.setQuantity(jrecord.getDouble("quantity"));
						if (!jrecord.isNull("lot_no"))
							record.setLotNo(jrecord.getString("lot_no"));
						if (!jrecord.isNull("condition"))
							record.setCondition(jrecord.getString("condition"));
						if (!jrecord.isNull("packed_date"))
							record.setPackedDate(DateTimeUtil.FromDateString(jrecord.getString("packed_date")));
						if (!jrecord.isNull("expired_date"))
							record.setExpiredDate(DateTimeUtil.FromDateString(jrecord.getString("expired_date")));
                        if (!jrecord.isNull("created"))
                            record.setCreatedDate(DateTimeUtil.FromDateString(jrecord.getString("created")));

                        mView.setData(record);
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

    private void delete() {
        CustomInventoryInboundAPI.Delete(mCusInventoryInboundDetailId, new RestfulResponse(this) {
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
                        closeActivity("DELETE");
                    }
                } catch (JSONException e) {
                    Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(ViewActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (SessionAPI.IsAllowAccess(mContext, "custom/inventory_inbound", "delete")) {
            menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Delete")
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
                closeActivity(null);
                return true;

            case Menu.FIRST:
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
							case DialogInterface.BUTTON_POSITIVE:
								delete();
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

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        closeActivity(null);
    }

    protected void closeActivity(String action) {
        Intent data = new Intent();
        data.putExtra("action", action);
        data.putExtra("cus_m_inventory_inbound_detail_id", mCusInventoryInboundDetailId);
        setResult(mActivityResultCode, data);

        finish();
    }
}

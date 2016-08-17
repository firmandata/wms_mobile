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
import com.erp.wms.R;
import com.erp.wms.api.MaterialInventoryMoveAPI;
import com.erp.wms.material.inventory_move.view.EnrollView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;

public class EnrollActivity extends SherlockActivity {

    private Context mContext;

    private long mInventoryMoveId;

    private EnrollView mView;

    private IntentIntegrator mBarcodeIntentIntegrator;
    private MenuItem mBarcodeMenuItem;

    private int mActivityResultCode = RESULT_CANCELED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Enroll Move";

        mContext = this;
        mBarcodeIntentIntegrator = new IntentIntegrator(this);
        mBarcodeMenuItem = null;

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
        this.setContentView(mView);
    }

    private void createView() {
        mView = new EnrollView(this);
        mView.setLayoutParams(new EnrollView.LayoutParams(EnrollView.LayoutParams.MATCH_PARENT, EnrollView.LayoutParams.MATCH_PARENT));
        mView.setOnRequestBarcode(new EnrollView.RequestBarcodeListener() {
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
        mView.setOnSubmitClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Save")
                .setIcon(R.drawable.save)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        mBarcodeMenuItem = menu.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "Scan");
        mBarcodeMenuItem.setIcon(R.drawable.barcode_scanner);
        mBarcodeMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        if (mView == null)
            mBarcodeMenuItem.setVisible(false);
        else {
            if (mView.getRequestBarcodeView() == null)
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

    protected void save() {
        hideKeyboard();
        MaterialInventoryMoveAPI.InsertDetail(mInventoryMoveId, mView.getData(), new RestfulResponse(this) {
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
                        mView.clearData();
                        mView.setFocusAtFirst();
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
                    mView.setBarcode(result.getContents());
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
        data.putExtra("m_inventory_move_id", mInventoryMoveId);
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
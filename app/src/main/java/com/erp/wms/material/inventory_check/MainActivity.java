package com.erp.wms.material.inventory_check;

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
import com.erp.helper.widget.mylist.MyListView;
import com.erp.wms.R;
import com.erp.wms.material.inventory_check.model.MainModel;
import com.erp.wms.material.inventory_check.view.MainView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends SherlockActivity {

    private Context mContext;
    private MainView mMainView;

    private IntentIntegrator mBarcodeIntentIntegrator;
    private MenuItem mBarcodeMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String title = "Inventory Check";

        mContext = this;
        mBarcodeIntentIntegrator = new IntentIntegrator(this);
        mBarcodeMenuItem = null;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            title = extras.getString("title");
        }
        this.setTitle(title);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.accept_database);

        mMainView = new MainView(this);
        mMainView.setLayoutParams(new MyListView.LayoutParams(MyListView.LayoutParams.MATCH_PARENT, MyListView.LayoutParams.MATCH_PARENT));
        mMainView.setOnRequestBarcode(new MainView.RequestBarcodeListener() {
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
        mMainView.setOnSubmitClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListActivity();
            }
        });
        setContentView(mMainView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Check")
                .setIcon(R.drawable.checkmark)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        mBarcodeMenuItem = menu.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "Scan");
        mBarcodeMenuItem.setIcon(R.drawable.barcode_scanner);
        mBarcodeMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        if (mMainView == null)
            mBarcodeMenuItem.setVisible(false);
        else {
            if (mMainView.getRequestBarcodeView() == null)
                mBarcodeMenuItem.setVisible(false);
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
                showListActivity();
                return true;

            case Menu.FIRST + 1:
                scanBarcode();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showListActivity() {
        MainModel mainModel = mMainView.getData();

        if ((mainModel.getProductCode() == null || mainModel.getProductCode().equals("")) &&
                (mainModel.getBarcode() == null || mainModel.getBarcode().equals("")) &&
                (mainModel.getPallet() == null || mainModel.getPallet().equals("")) &&
                (mainModel.getGridCode() == null || mainModel.getGridCode().equals(""))) {
            Toast.makeText(MainActivity.this, "Please entry the criteria.", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(this, MainListActivity.class);
            intent.putExtra("title", "Inventory Result");

            intent.putExtra("m_product_code", mainModel.getProductCode());
            intent.putExtra("barcode", mainModel.getBarcode());
            intent.putExtra("pallet", mainModel.getPallet());
            intent.putExtra("m_grid_code", mainModel.getGridCode());

            this.startActivityForResult(intent, 1);
        }
    }

    private void scanBarcode() {
        hideKeyboard();
        mBarcodeIntentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                try {
                    mMainView.setBarcode(result.getContents());
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
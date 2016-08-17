package com.erp.wms.system.home.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<HashMap<String, Object>> mMenus;

    public MainViewAdapter(Context c, ArrayList<HashMap<String, Object>> menus) {
        mContext = c;
        mMenus = menus;
    }

    public int getCount() {
        return mMenus.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View gridCell;

        if (convertView == null) {
            HashMap<String, Object> menu = mMenus.get(position);

            TextView cell = new TextView(mContext);
            cell.setGravity(Gravity.CENTER);
            cell.setText(menu.get("title").toString());
            cell.setCompoundDrawablesWithIntrinsicBounds(0, (int)menu.get("icon"), 0, 0);
            cell.setTag(menu.get("id").toString());

            gridCell = cell;
        }
        else {
            gridCell = convertView;
        }

        return gridCell;
    }
}

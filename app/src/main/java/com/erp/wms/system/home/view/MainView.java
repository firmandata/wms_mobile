package com.erp.wms.system.home.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.erp.wms.R;
import com.erp.wms.api.SessionAPI;
import com.erp.wms.config.Restful;
import com.erp.wms.system.home.adapter.MainViewAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainView extends GridView {

    public MainView(Context context) {
        super(context);

        SharedPreferences sessionStorage = context.getSharedPreferences(Restful.Session_Storage, Context.MODE_PRIVATE);

        ArrayList<String> pageAllows = new ArrayList<>();
        String sessionData = sessionStorage.getString(Restful.Session_Data, null);
        try {
            JSONObject userData = new JSONObject(sessionData);
            if (!userData.isNull("accesscontrols")) {
                JSONObject accessControls = userData.getJSONObject("accesscontrols");
                Iterator<String> pages = accessControls.keys();
                while (pages.hasNext()) {
                    String page = pages.next();
                    if (SessionAPI.IsAllowAccess(context, page, "index"))
                        pageAllows.add(page);
                }
            }
        } catch (JSONException e) {
            // do anything here
        } catch (Exception e) {
            // do anything here
        }

        String[] menu_ids = pageAllows.toArray(new String[pageAllows.size()]);
        ArrayList<HashMap<String, Object>> menus = getMenuList(menu_ids);
        this.setAdapter(new MainViewAdapter(context, menus));

        this.setNumColumns(GridView.AUTO_FIT);
        this.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        this.setColumnWidth((int) context.getResources().getDimension(R.dimen.menu_grid_column_width));
        this.setVerticalSpacing((int) context.getResources().getDimension(R.dimen.menu_grid_vertical_spacing));
        this.setHorizontalSpacing((int) context.getResources().getDimension(R.dimen.menu_grid_horizontal_spacing));
        this.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showPage(view.getTag().toString());
            }
        });

        this.setScrollBarStyle(GridView.SCROLLBARS_OUTSIDE_OVERLAY);
        this.setClipToPadding(false);
        this.setPadding(
                (int) context.getResources().getDimension(R.dimen.menu_grid_padding_left),
                (int) context.getResources().getDimension(R.dimen.menu_grid_padding_top),
                (int) context.getResources().getDimension(R.dimen.menu_grid_padding_right),
                (int) context.getResources().getDimension(R.dimen.menu_grid_padding_bottom)
        );
    }

    public void showPage(String id) {
        HashMap<String, Object> menu = getMenu(id);
        if (menu == null)
            return;

        Intent intent;

        switch (id)
        {
            case "material/inventory_inbound":
                intent = new Intent(this.getContext(), com.erp.wms.material.inventory_inbound.MainActivity.class);
                intent.putExtra("title", menu.get("title").toString());
                this.getContext().startActivity(intent);
                break;

            case "material/inventory_putaway":
                intent = new Intent(this.getContext(), com.erp.wms.material.inventory_putaway.MainActivity.class);
                intent.putExtra("title", menu.get("title").toString());
                this.getContext().startActivity(intent);
                break;

            case "custom/inventory_inbound":
                intent = new Intent(this.getContext(), com.erp.wms.custom.inventory_inbound.MainActivity.class);
                intent.putExtra("title", menu.get("title").toString());
                this.getContext().startActivity(intent);
                break;

            case "material/inventory_move":
                intent = new Intent(this.getContext(), com.erp.wms.material.inventory_move.MainActivity.class);
                intent.putExtra("title", menu.get("title").toString());
                this.getContext().startActivity(intent);
                break;

            case "material/inventory_adjust":
                intent = new Intent(this.getContext(), com.erp.wms.material.inventory_adjust.MainActivity.class);
                intent.putExtra("title", menu.get("title").toString());
                this.getContext().startActivity(intent);
                break;

            case "material/inventory_picking":
                intent = new Intent(this.getContext(), com.erp.wms.material.inventory_picking.MainActivity.class);
                intent.putExtra("title", menu.get("title").toString());
                this.getContext().startActivity(intent);
                break;

            case "material/inventory_shipment":
                intent = new Intent(this.getContext(), com.erp.wms.material.inventory_shipment.MainActivity.class);
                intent.putExtra("title", menu.get("title").toString());
                this.getContext().startActivity(intent);
                break;

            case "material/inventory":
                intent = new Intent(this.getContext(), com.erp.wms.material.inventory_check.MainActivity.class);
                intent.putExtra("title", menu.get("title").toString());
                this.getContext().startActivity(intent);
                break;

            default:
                Toast.makeText(this.getContext(), "You selected: " + id, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public ArrayList<HashMap<String, Object>> menuList() {
        ArrayList<HashMap<String, Object>> menus = new ArrayList<>();
        HashMap<String, Object> menu;

        menu = new HashMap<>();
        menu.put("id", "material/inventory_inbound");
        menu.put("title", "Inbound");
        menu.put("icon", R.drawable.inbox);
        menus.add(menu);

        menu = new HashMap<>();
        menu.put("id", "material/inventory_putaway");
        menu.put("title", "Putaway");
        menu.put("icon", R.drawable.grid);
        menus.add(menu);

        menu = new HashMap<>();
        menu.put("id", "custom/inventory_inbound");
        menu.put("title", "Inbound Live");
        menu.put("icon", R.drawable.rack);
        menus.add(menu);

        menu = new HashMap<>();
        menu.put("id", "material/inventory_move");
        menu.put("title", "Move");
        menu.put("icon", R.drawable.move_by_trolley);
        menus.add(menu);

        menu = new HashMap<>();
        menu.put("id", "material/inventory_adjust");
        menu.put("title", "Adjust");
        menu.put("icon", R.drawable.coins);
        menus.add(menu);

        menu = new HashMap<>();
        menu.put("id", "material/inventory_picking");
        menu.put("title", "Picking");
        menu.put("icon", R.drawable.shopping_cart_loaded);
        menus.add(menu);

        menu = new HashMap<>();
        menu.put("id", "material/inventory_shipment");
        menu.put("title", "Shipment");
        menu.put("icon", R.drawable.delivery);
        menus.add(menu);

        menu = new HashMap<>();
        menu.put("id", "material/inventory");
        menu.put("title", "Inventory Check");
        menu.put("icon", R.drawable.accept_database);
        menus.add(menu);

        return menus;
    }

    public ArrayList<HashMap<String, Object>> getMenuList(String[] menuIds) {
        ArrayList<HashMap<String, Object>> menus = new ArrayList<>();
        ArrayList<HashMap<String, Object>> menuAll = menuList();

        for (HashMap<String, Object> menu : menuAll) {
            boolean isFound = false;
            for (String menuId : menuIds) {
                for (Map.Entry<String, Object> mapEntry : menu.entrySet()) {
                    String key = mapEntry.getKey();
                    if (key.equals("id")) {
                        Object value = mapEntry.getValue();
                        if (value.equals(menuId)) {
                            menus.add(menu);
                            isFound = true;
                            break;
                        }
                    }
                }
                if (isFound)
                    break;
            }
        }

        return menus;
    }

    public HashMap<String, Object> getMenu(String id) {
        HashMap<String, Object> menu = null;
        ArrayList<HashMap<String, Object>> menuAll = menuList();

        for (HashMap<String, Object> _menu : menuAll) {
            boolean isFound = false;

            for (Map.Entry<String, Object> mapEntry : _menu.entrySet()) {
                String key = mapEntry.getKey();
                if (key.equals("id")) {
                    Object value = mapEntry.getValue();
                    if (value.equals(id)) {
                        menu = _menu;
                        isFound = true;
                        break;
                    }
                }
            }
            if (isFound)
                break;
        }

        return menu;
    }
}

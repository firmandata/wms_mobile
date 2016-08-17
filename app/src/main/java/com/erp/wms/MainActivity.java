package com.erp.wms;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.erp.helper.restful.RestfulResponse;
import com.erp.wms.api.SessionAPI;
import com.erp.wms.config.Restful;

import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.SherlockActivity;

public class MainActivity extends SherlockActivity {

    private SharedPreferences sessionStorage;
    private com.erp.wms.system.view.SplashView splashView;
    private com.erp.wms.system.login.view.MainView loginView;
    private com.erp.wms.system.home.view.MainView homeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionStorage = this.getSharedPreferences(com.erp.wms.config.Restful.Session_Storage, Context.MODE_PRIVATE);

        splashView = createSplashView();
        showSplash();

        loginView = createLoginView();
        homeView = createHomeView();

        sessionCheck();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, "Logout")
                .setIcon(R.drawable.unlock)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_WITH_TEXT);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case Menu.FIRST:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        return currentView.equals(homeView);
//    }

    private com.erp.wms.system.view.SplashView createSplashView() {
        com.erp.wms.system.view.SplashView layout = new com.erp.wms.system.view.SplashView(this);
        layout.setLayoutParams(new com.erp.wms.system.view.SplashView.LayoutParams(com.erp.wms.system.view.SplashView.LayoutParams.MATCH_PARENT, com.erp.wms.system.view.SplashView.LayoutParams.MATCH_PARENT));
        layout.setGravity(Gravity.CENTER);
        return layout;
    }

    private com.erp.wms.system.login.view.MainView createLoginView() {
        com.erp.wms.system.login.view.MainView layout = new com.erp.wms.system.login.view.MainView(this, new RestfulResponse(this) {

            @Override
            public void onStart() {
                hideKeyboard();
                super.onStart();
            }

            @Override
            public void onSuccess(org.json.JSONObject response) {
                String sessionId = null;
                String sessionData = null;

                try {
                    Boolean is_success = false;
                    if (!response.isNull("response"))
                        is_success = response.getBoolean("response");

                    String value = null;
                    if (!response.isNull("value"))
                        value = response.getString("value");

                    if (is_success) {
                        sessionId = value;
                        if (!response.isNull("data"))
                            sessionData = response.getString("data");
                    }
                    else
                        Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } finally {
                    if (sessionId != null) {
                        SharedPreferences.Editor editor = sessionStorage.edit();
                        editor.putString(com.erp.wms.config.Restful.Session_Id, sessionId);
                        editor.putString(com.erp.wms.config.Restful.Session_Data, sessionData);
                        editor.apply();

                        showHome();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, org.json.JSONObject errorResponse) {
                showLogin();
            }
        });
        layout.setLayoutParams(new com.erp.wms.system.login.view.MainView.LayoutParams(com.erp.wms.system.login.view.MainView.LayoutParams.MATCH_PARENT, com.erp.wms.system.login.view.MainView.LayoutParams.MATCH_PARENT));
        return layout;
    }

    private com.erp.wms.system.home.view.MainView createHomeView() {
        com.erp.wms.system.home.view.MainView layout =  new com.erp.wms.system.home.view.MainView(this);
        layout.setLayoutParams(new com.erp.wms.system.home.view.MainView.LayoutParams(com.erp.wms.system.home.view.MainView.LayoutParams.MATCH_PARENT, com.erp.wms.system.home.view.MainView.LayoutParams.MATCH_PARENT));
        return layout;
    }

    private void showSplash() {
        setContentView(splashView);

//        this.invalidateOptionsMenu();
        this.getSupportActionBar().hide();
    }

    private void showLogin() {
        setContentView(loginView);

//        this.invalidateOptionsMenu();
        this.getSupportActionBar().hide();
    }

    private void showHome() {
        homeView = createHomeView();

        setContentView(homeView);

//        this.invalidateOptionsMenu();
        this.getSupportActionBar().show();

        String sessionData = sessionStorage.getString(Restful.Session_Data, null);
        try {
            JSONObject data = new JSONObject(sessionData);

            String name = null;
            if (!data.isNull("name"))
                name = data.getString("name");

            Toast.makeText(MainActivity.this, "Welcome " + name + "!", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void sessionCheck() {
        final String storageSessionId = sessionStorage.getString(com.erp.wms.config.Restful.Session_Id, null);

        SessionAPI.SessionCheck(storageSessionId, new RestfulResponse(this) {
            @Override
            public void onStart() {
                // Disable progressbar using onStart
                // For show the progressbar, delete this function
            }

            @Override
            public void onSuccess(org.json.JSONObject response) {
                Boolean isLogin = false;
                String sessionId = null;
                String sessionData = null;

                try {
                    if (!response.isNull("response"))
                        isLogin = response.getBoolean("response");

                    String value = null;
                    if (!response.isNull("value"))
                        value = response.getString("value");

                    if (isLogin) {
                        sessionId = value;
                        if (!response.isNull("data"))
                            sessionData = response.getString("data");
                    } else {
                        Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } finally {
                    if (isLogin) {
                        SharedPreferences.Editor editor = sessionStorage.edit();
                        editor.putString(com.erp.wms.config.Restful.Session_Id, sessionId);
                        editor.putString(com.erp.wms.config.Restful.Session_Data, sessionData);
                        editor.commit();

                        showHome();
                    } else {
                        showLogin();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, org.json.JSONObject errorResponse) {
                if (storageSessionId != null)
                    showHome();
                else
                    showLogin();
            }
        });
    }

    private void logout() {
        SharedPreferences.Editor editor = sessionStorage.edit();
        editor.remove(Restful.Session_Id);
        editor.remove(Restful.Session_Data);
        editor.apply();

        SessionAPI.Logout(new RestfulResponse(this) {
            @Override
            public void onStart() {
                // Disable progressbar using onStart
                // For show the progressbar, delete this function
            }

            @Override
            public void onSuccess(org.json.JSONObject response) {
                Toast.makeText(MainActivity.this, "You have signed off", Toast.LENGTH_SHORT).show();
                showLogin();
            }

            @Override
            public void onFailure(int statusCode, org.json.JSONObject errorResponse) {
                Toast.makeText(MainActivity.this, "You have signed off", Toast.LENGTH_SHORT).show();
                showLogin();
            }
        });
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}

package com.erp.wms.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.erp.helper.restful.RestfulParam;
import com.erp.helper.restful.RestfulRequest;
import com.erp.helper.restful.RestfulResponse;

import com.erp.wms.config.Restful;

import org.json.JSONException;
import org.json.JSONObject;

public final class SessionAPI {
    public static void Login(String userName, String password, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.put("username", userName);
        requestParam.put("password", password);

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/system_login/authentication", requestParam, response);
    }

    public static void SessionCheck(String sessionId, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add(Restful.Session_Id, sessionId);

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/system_login/is_login", requestParam, response);
    }

    public static boolean IsAllowAccess(Context context, String page, String action) {
        boolean isAllow = false;

        SharedPreferences sessionStorage = context.getSharedPreferences(Restful.Session_Storage, Context.MODE_PRIVATE);

        String sessionData = sessionStorage.getString(Restful.Session_Data, null);
        try {
            JSONObject userData = new JSONObject(sessionData);
            if (!userData.isNull("accesscontrols")) {
                JSONObject accessControls = userData.getJSONObject("accesscontrols");

                if (!accessControls.isNull(page)) {
                    JSONObject pageProperties = accessControls.getJSONObject(page);
                    if (!pageProperties.isNull(action)) {
                        isAllow = pageProperties.getBoolean(action);
                    }
                }
                else {
                    isAllow = false;
                }
            }
        } catch (JSONException e) {
            isAllow = false;
        }

        return isAllow;
    }

    public static void Logout(RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/system_login/logout", requestParam, response);
    }
}

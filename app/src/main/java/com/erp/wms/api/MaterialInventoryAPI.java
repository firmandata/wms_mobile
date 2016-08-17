package com.erp.wms.api;

import com.erp.helper.restful.RestfulParam;
import com.erp.helper.restful.RestfulRequest;
import com.erp.helper.restful.RestfulResponse;
import com.erp.wms.material.inventory_check.model.MainModel;

public final class MaterialInventoryAPI {
    public static void GetChecks(int page, MainModel mainModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
		requestParam.add("_search", String.valueOf(true));
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "m_product_code");
        requestParam.add("sord", "asc");
		requestParam.add("filters", getRequestJson(mainModel));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory/check_list", requestParam, response);
    }

    public static void GetCheckSummaries(MainModel mainModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("_search", String.valueOf(true));
        requestParam.add("page", String.valueOf(1));
        requestParam.add("rows", String.valueOf(0));
        requestParam.add("filters", getRequestJson(mainModel));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory/check_summary", requestParam, response);
    }

    private static String getRequestJson(MainModel mainModel) {
        String jsonRequestString = null;

        org.json.JSONObject jsonRequest = new org.json.JSONObject();
        try {
            jsonRequest.put("groupOp", "AND");
            org.json.JSONArray jsonRequestRules = new org.json.JSONArray();
            if (mainModel.getProductCode() != null && !mainModel.getProductCode().equals("")) {
                org.json.JSONObject jsonRequestRule = new org.json.JSONObject();
                jsonRequestRule.put("field", "pro.code");
                jsonRequestRule.put("op", "eq");
                jsonRequestRule.put("data", mainModel.getProductCode());
                jsonRequestRules.put(jsonRequestRule);
            }
            if (mainModel.getBarcode() != null && !mainModel.getBarcode().equals("")) {
                org.json.JSONObject jsonRequestRule = new org.json.JSONObject();
                jsonRequestRule.put("field", "inv.barcode");
                jsonRequestRule.put("op", "eq");
                jsonRequestRule.put("data", mainModel.getBarcode());
                jsonRequestRules.put(jsonRequestRule);
            }
            if (mainModel.getPallet() != null && !mainModel.getPallet().equals("")) {
                org.json.JSONObject jsonRequestRule = new org.json.JSONObject();
                jsonRequestRule.put("field", "inv.pallet");
                jsonRequestRule.put("op", "eq");
                jsonRequestRule.put("data", mainModel.getPallet());
                jsonRequestRules.put(jsonRequestRule);
            }
            if (mainModel.getGridCode() != null && !mainModel.getGridCode().equals("")) {
                org.json.JSONObject jsonRequestRule = new org.json.JSONObject();
                jsonRequestRule.put("field", "grd.code");
                jsonRequestRule.put("op", "eq");
                jsonRequestRule.put("data", mainModel.getGridCode());
                jsonRequestRules.put(jsonRequestRule);
            }
            jsonRequest.put("rules", jsonRequestRules);
            jsonRequestString = jsonRequest.toString();
        } catch (org.json.JSONException e) {
            jsonRequestString = null;
        }

        return jsonRequestString;
    }
}

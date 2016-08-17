package com.erp.wms.api;

public class JsonAPIFilter {
    private org.json.JSONObject mJsonRequest = new org.json.JSONObject();
    private org.json.JSONArray mJsonRequestRules = new org.json.JSONArray();
    private org.json.JSONArray mJsonRequestGroups = new org.json.JSONArray();
    private String mGroupOperator;

    public JsonAPIFilter() {
        this("AND");
    }

    public JsonAPIFilter(String groupOperator) {
        mGroupOperator = groupOperator;
    }

    public void AddGroup(JsonAPIFilter jsonAPIFilter){
        mJsonRequestGroups.put(jsonAPIFilter.GetJsonObject());
    }

    public void Add(String field, String value){
        this.Add(field, value, "eq");
    }

    public void Add(String field, String value, String operator){
        try {
            org.json.JSONObject jsonRequestRule = new org.json.JSONObject();
            jsonRequestRule.put("field", field);
            jsonRequestRule.put("op", operator);
            jsonRequestRule.put("data", value);
            mJsonRequestRules.put(jsonRequestRule);
        } catch (org.json.JSONException e) {
        }
    }

    private void execute() {
        try {
            mJsonRequest.put("groupOp", mGroupOperator);
            mJsonRequest.put("rules", mJsonRequestRules);
            mJsonRequest.put("groups", mJsonRequestGroups);
        } catch (org.json.JSONException e) {

        }
    }

    public org.json.JSONObject GetJsonObject() {
        this.execute();
        return mJsonRequest;
    }

    public String GetJsonString(){
        this.execute();
        return mJsonRequest.toString();
    }
}
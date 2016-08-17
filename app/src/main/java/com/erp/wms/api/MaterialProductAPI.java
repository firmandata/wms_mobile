package com.erp.wms.api;

import com.erp.helper.restful.RestfulParam;
import com.erp.helper.restful.RestfulRequest;
import com.erp.helper.restful.RestfulRequestSynch;
import com.erp.helper.restful.RestfulResponse;

public final class MaterialProductAPI {
    public static void GetList(int page, JsonAPIFilter filter, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "code");
        requestParam.add("sord", "asc");

        requestParam.add("_search", String.valueOf(true));
        requestParam.add("filters", filter.GetJsonString());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/product/list", requestParam, response);
    }

    public static void GetListSynch(int rows, JsonAPIFilter filter, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(1));
        requestParam.add("rows", String.valueOf(rows));
        requestParam.add("sidx", "code");
        requestParam.add("sord", "asc");

        requestParam.add("_search", String.valueOf(true));
        requestParam.add("filters", filter.GetJsonString());

        RestfulRequestSynch client = new RestfulRequestSynch(response.getContext());
        client.get("/product/list", requestParam, response);
    }
}

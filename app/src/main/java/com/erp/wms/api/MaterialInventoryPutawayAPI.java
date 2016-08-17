package com.erp.wms.api;

import com.erp.helper.restful.RestfulParam;
import com.erp.helper.restful.RestfulRequest;
import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DateTimeUtil;
import com.erp.wms.material.inventory_putaway.model.DetailModel;
import com.erp.wms.material.inventory_putaway.model.HeaderModel;

public final class MaterialInventoryPutawayAPI {
    public static void GetHeaders(int page, int from_month, int from_year, int to_month, int to_year, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "putaway_date");
        requestParam.add("sord", "desc");

        requestParam.add("from_month", String.valueOf(from_month));
        requestParam.add("from_year", String.valueOf(from_year));
        requestParam.add("to_month", String.valueOf(to_month));
        requestParam.add("to_year", String.valueOf(to_year));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_putaway/list", requestParam, response);
    }

    public static void GetHeader(long m_inventory_putaway_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_putaway_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_putaway/detail", requestParam, response);
    }

    public static void GetDetails(int page, long m_inventory_putaway_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "m_inventory_putawaydetail_id");
        requestParam.add("sord", "asc");

        requestParam.add("id", String.valueOf(m_inventory_putaway_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_putaway/detail_list", requestParam, response);
    }

    public static void InsertHeader(HeaderModel headerModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("code", headerModel.getPutawayCode());
        requestParam.add("putaway_date", DateTimeUtil.ToDateString(headerModel.getPutawayDate()));
        requestParam.add("notes", headerModel.getNotes());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_putaway/insert", requestParam, response);
    }

    public static void UpdateHeader(long m_inventory_putaway_id, HeaderModel headerModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_putaway_id));
        requestParam.add("code", headerModel.getPutawayCode());
        requestParam.add("putaway_date", DateTimeUtil.ToDateString(headerModel.getPutawayDate()));
        requestParam.add("notes", headerModel.getNotes());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_putaway/update", requestParam, response);
    }

    public static void DeleteHeader(long m_inventory_putaway_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_putaway_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_putaway/delete", requestParam, response);
    }

    public static void InsertDetail(long m_inventory_putaway_id, DetailModel detailModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_putaway_id", String.valueOf(m_inventory_putaway_id));
        requestParam.add("pallet", detailModel.getPallet());
        requestParam.add("m_gridto_code", detailModel.getGridToCode());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_putaway/insert_detail", requestParam, response);
    }

    public static void DeleteDetail(DetailModel detailModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_putaway_id", String.valueOf(detailModel.getInventoryPutawayId()));
        requestParam.add("pallet", detailModel.getPallet());
        requestParam.add("m_gridto_id", String.valueOf(detailModel.getGridToId()));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_putaway/delete_detail", requestParam, response);
    }

    public static void GridDefault(String pallet, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("pallet", pallet);

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_putaway/grid_default", requestParam, response);
    }
}

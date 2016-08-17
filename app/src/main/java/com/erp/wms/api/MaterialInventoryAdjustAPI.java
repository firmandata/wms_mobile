package com.erp.wms.api;

import com.erp.helper.restful.RestfulParam;
import com.erp.helper.restful.RestfulRequest;
import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DateTimeUtil;
import com.erp.wms.material.inventory_adjust.model.DetailModel;
import com.erp.wms.material.inventory_adjust.model.HeaderModel;

public final class MaterialInventoryAdjustAPI {
    public static void GetHeaders(int page, int from_month, int from_year, int to_month, int to_year, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "adjust_date");
        requestParam.add("sord", "desc");

        requestParam.add("from_month", String.valueOf(from_month));
        requestParam.add("from_year", String.valueOf(from_year));
        requestParam.add("to_month", String.valueOf(to_month));
        requestParam.add("to_year", String.valueOf(to_year));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_adjust/list", requestParam, response);
    }

    public static void GetHeader(long m_inventory_adjust_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_adjust_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_adjust/detail", requestParam, response);
    }

    public static void GetDetails(int page, long m_inventory_adjust_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "m_inventory_adjustdetail_id");
        requestParam.add("sord", "asc");

        requestParam.add("id", String.valueOf(m_inventory_adjust_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_adjust/detail_list", requestParam, response);
    }

    public static void InsertHeader(HeaderModel headerModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("code", headerModel.getAdjustCode());
        requestParam.add("adjust_date", DateTimeUtil.ToDateString(headerModel.getAdjustDate()));
		requestParam.add("adjust_type", headerModel.getAdjustType());
        requestParam.add("notes", headerModel.getNotes());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_adjust/insert", requestParam, response);
    }

    public static void UpdateHeader(long m_inventory_adjust_id, HeaderModel headerModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_adjust_id));
        requestParam.add("code", headerModel.getAdjustCode());
        requestParam.add("adjust_date", DateTimeUtil.ToDateString(headerModel.getAdjustDate()));
		requestParam.add("adjust_type", headerModel.getAdjustType());
        requestParam.add("notes", headerModel.getNotes());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_adjust/update", requestParam, response);
    }

    public static void DeleteHeader(long m_inventory_adjust_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_adjust_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_adjust/delete", requestParam, response);
    }

    public static void InsertDetail(long m_inventory_adjust_id, DetailModel detailModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_adjust_id", String.valueOf(m_inventory_adjust_id));
		requestParam.add("barcode", detailModel.getBarcode());
        requestParam.add("pallet", detailModel.getPallet());
		requestParam.add("quantity", String.valueOf(detailModel.getQuantityTo()));
		requestParam.add("m_product_code", detailModel.getProductCode());
        requestParam.add("m_grid_code", detailModel.getGridCode());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_adjust/insert_detail", requestParam, response);
    }

    public static void DeleteDetail(DetailModel detailModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_adjust_id", String.valueOf(detailModel.getInventoryAdjustId()));
		requestParam.add("barcode", detailModel.getBarcode());
        requestParam.add("pallet", detailModel.getPallet());
		requestParam.add("m_product_id", String.valueOf(detailModel.getProductId()));
        requestParam.add("m_grid_id", String.valueOf(detailModel.getGridId()));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_adjust/delete_detail", requestParam, response);
    }
}

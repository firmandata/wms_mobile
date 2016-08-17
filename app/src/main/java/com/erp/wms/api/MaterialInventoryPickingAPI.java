package com.erp.wms.api;

import com.erp.helper.restful.RestfulParam;
import com.erp.helper.restful.RestfulRequest;
import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DateTimeUtil;
import com.erp.wms.material.inventory_picking.model.DetailModel;
import com.erp.wms.material.inventory_picking.model.HeaderModel;

public final class MaterialInventoryPickingAPI {
    public static void GetHeaders(int page, JsonAPIFilter filter, int from_month, int from_year, int to_month, int to_year, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "picking_date");
        requestParam.add("sord", "desc");

        requestParam.add("_search", String.valueOf(true));
        requestParam.add("filters", filter.GetJsonString());

        requestParam.add("from_month", String.valueOf(from_month));
        requestParam.add("from_year", String.valueOf(from_year));
        requestParam.add("to_month", String.valueOf(to_month));
        requestParam.add("to_year", String.valueOf(to_year));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_picking/list", requestParam, response);
    }

    public static void GetHeader(long m_inventory_picking_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_picking_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_picking/detail", requestParam, response);
    }

    public static void GetDetails(int page, long m_inventory_picking_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "id");
        requestParam.add("sord", "asc");

        requestParam.add("id", String.valueOf(m_inventory_picking_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_picking/detail_list", requestParam, response);
    }

    public static void GetPicklistDetails(int page, JsonAPIFilter filter, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "id");
        requestParam.add("sord", "asc");

        requestParam.add("_search", String.valueOf(true));
        requestParam.add("filters", filter.GetJsonString());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_picking/detail_picklist", requestParam, response);
    }

    public static void InsertHeader(HeaderModel headerModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("code", headerModel.getPickingCode());
        requestParam.add("picking_date", DateTimeUtil.ToDateString(headerModel.getPickingDate()));
        requestParam.add("notes", headerModel.getNotes());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_picking/insert", requestParam, response);
    }

    public static void UpdateHeader(long m_inventory_picking_id, HeaderModel headerModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_picking_id));
        requestParam.add("code", headerModel.getPickingCode());
        requestParam.add("picking_date", DateTimeUtil.ToDateString(headerModel.getPickingDate()));
        requestParam.add("notes", headerModel.getNotes());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_picking/update", requestParam, response);
    }

    public static void DeleteHeader(long m_inventory_picking_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_picking_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_picking/delete", requestParam, response);
    }

    public static void InsertDetail(long m_inventory_picking_id, long m_inventory_picklist_id, DetailModel detailModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_picking_id", String.valueOf(m_inventory_picking_id));
        requestParam.add("m_inventory_picklist_id", String.valueOf(m_inventory_picklist_id));
        requestParam.add("barcode", detailModel.getBarcode());
        requestParam.add("pallet", detailModel.getPallet());
        requestParam.add("grid_code", detailModel.getGridCode());
        requestParam.add("condition", detailModel.getCondition());
		requestParam.add("quantity_box", String.valueOf(detailModel.getQuantityBox()));
        requestParam.add("packed_group", detailModel.getPackedGroup());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_picking/insert_detail", requestParam, response);
    }

    public static void DeleteDetail(DetailModel detailModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_picking_id", String.valueOf(detailModel.getInventoryPickingId()));
		requestParam.add("m_inventory_picklist_id", String.valueOf(detailModel.getInventoryPicklistId()));
		requestParam.add("m_product_id", String.valueOf(detailModel.getProductId()));
		requestParam.add("m_grid_id", String.valueOf(detailModel.getGridId()));
		requestParam.add("barcode", detailModel.getBarcode());
		requestParam.add("pallet", detailModel.getPallet());
		requestParam.add("condition", detailModel.getCondition());
        requestParam.add("packed_group", detailModel.getPackedGroup());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_picking/delete_detail", requestParam, response);
    }
}

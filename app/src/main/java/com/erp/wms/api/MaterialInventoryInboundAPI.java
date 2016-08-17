package com.erp.wms.api;

import com.erp.helper.restful.RestfulParam;
import com.erp.helper.restful.RestfulRequest;
import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DateTimeUtil;
import com.erp.wms.material.inventory_inbound.model.DetailModel;
import com.erp.wms.material.inventory_inbound.model.HeaderModel;

public final class MaterialInventoryInboundAPI {
    public static void GetHeaders(int page, JsonAPIFilter filter, int from_month, int from_year, int to_month, int to_year, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "inbound_date");
        requestParam.add("sord", "desc");

        requestParam.add("_search", String.valueOf(true));
        requestParam.add("filters", filter.GetJsonString());

        requestParam.add("from_month", String.valueOf(from_month));
        requestParam.add("from_year", String.valueOf(from_year));
        requestParam.add("to_month", String.valueOf(to_month));
        requestParam.add("to_year", String.valueOf(to_year));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_inbound/list", requestParam, response);
    }

    public static void GetHeader(long m_inventory_inbound_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_inbound_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_inbound/detail", requestParam, response);
    }

    public static void GetDetails(int page, long m_inventory_inbound_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "id");
        requestParam.add("sord", "asc");

        requestParam.add("id", String.valueOf(m_inventory_inbound_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_inbound/detail_list", requestParam, response);
    }

    public static void GetReceiveDetails(int page, JsonAPIFilter filter, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "id");
        requestParam.add("sord", "asc");

        requestParam.add("_search", String.valueOf(true));
        requestParam.add("filters", filter.GetJsonString());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_inbound/detail_receive", requestParam, response);
    }

    public static void InsertHeader(HeaderModel headerModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("code", headerModel.getInboundCode());
        requestParam.add("inbound_date", DateTimeUtil.ToDateString(headerModel.getInboundDate()));
        requestParam.add("notes", headerModel.getNotes());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_inbound/insert", requestParam, response);
    }

    public static void UpdateHeader(long m_inventory_inbound_id, HeaderModel headerModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_inbound_id));
        requestParam.add("code", headerModel.getInboundCode());
        requestParam.add("inbound_date", DateTimeUtil.ToDateString(headerModel.getInboundDate()));
        requestParam.add("notes", headerModel.getNotes());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_inbound/update", requestParam, response);
    }

    public static void DeleteHeader(long m_inventory_inbound_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_inbound_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_inbound/delete", requestParam, response);
    }

    public static void InsertDetail(long m_inventory_inbound_id, long m_inventory_receive_detail_id, DetailModel detailModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_inbound_id", String.valueOf(m_inventory_inbound_id));
        requestParam.add("m_inventory_receivedetail_id", String.valueOf(m_inventory_receive_detail_id));
        requestParam.add("barcode", detailModel.getBarcode());
        requestParam.add("pallet", detailModel.getPallet());
        requestParam.add("quantity", String.valueOf(detailModel.getQuantity()));
        requestParam.add("quantity_box", String.valueOf(detailModel.getQuantityBox()));
        requestParam.add("carton_no", detailModel.getCartonNo());
        requestParam.add("lot_no", detailModel.getLotNo());
        requestParam.add("condition", detailModel.getCondition());
        requestParam.add("packed_date", DateTimeUtil.ToDateString(detailModel.getPackedDate()));
        requestParam.add("expired_date", DateTimeUtil.ToDateString(detailModel.getExpiredDate()));
        requestParam.add("grid_code", detailModel.getGridCode());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_inbound/insert_detail", requestParam, response);
    }

    public static void DeleteDetail(long m_inventory_inbound_detail_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_inbound_detail_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_inbound/delete_detail", requestParam, response);
    }

    public static void ParseBarcode(long m_inventory_receivedetail_id, String barcode, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_receivedetail_id", String.valueOf(m_inventory_receivedetail_id));
        requestParam.add("barcode", barcode);

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_inbound/parse_barcode", requestParam, response);
    }

    public static void GetPalletCounter(long m_inventory_inbound_id, String pallet, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_inbound_id", String.valueOf(m_inventory_inbound_id));
        requestParam.add("pallet", pallet);

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_inbound/get_detail_counter", requestParam, response);
    }
}

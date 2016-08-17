package com.erp.wms.api;

import com.erp.helper.restful.RestfulParam;
import com.erp.helper.restful.RestfulRequest;
import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DateTimeUtil;
import com.erp.wms.material.inventory_shipment.model.DetailModel;
import com.erp.wms.material.inventory_shipment.model.HeaderModel;

public final class MaterialInventoryShipmentAPI {
    public static void GetHeaders(int page, JsonAPIFilter filter, int from_month, int from_year, int to_month, int to_year, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "shipment_date");
        requestParam.add("sord", "desc");

        requestParam.add("_search", String.valueOf(true));
        requestParam.add("filters", filter.GetJsonString());

        requestParam.add("from_month", String.valueOf(from_month));
        requestParam.add("from_year", String.valueOf(from_year));
        requestParam.add("to_month", String.valueOf(to_month));
        requestParam.add("to_year", String.valueOf(to_year));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_shipment/list", requestParam, response);
    }

    public static void GetHeader(long m_inventory_shipment_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_shipment_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_shipment/detail", requestParam, response);
    }

    public static void GetDetails(int page, long m_inventory_shipment_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "id");
        requestParam.add("sord", "asc");

        requestParam.add("id", String.valueOf(m_inventory_shipment_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_shipment/detail_list", requestParam, response);
    }

    public static void GetPickingDetails(int page, JsonAPIFilter filter, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "id");
        requestParam.add("sord", "asc");

        requestParam.add("_search", String.valueOf(true));
        requestParam.add("filters", filter.GetJsonString());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_shipment/detail_picking", requestParam, response);
    }

    public static void InsertHeader(HeaderModel headerModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("code", headerModel.getShipmentCode());
        requestParam.add("shipment_date", DateTimeUtil.ToDateString(headerModel.getShipmentDate()));
		requestParam.add("shipment_type", headerModel.getShipmentType());
		requestParam.add("request_arrive_date", DateTimeUtil.ToDateString(headerModel.getRequestArriveDate()));
		requestParam.add("estimated_time_arrive", DateTimeUtil.ToDateString(headerModel.getEstimatedTimeArrive()));
		requestParam.add("shipment_to", headerModel.getShipmentTo());
		requestParam.add("vehicle_no", headerModel.getVehicleNo());
		requestParam.add("vehicle_driver", headerModel.getVehicleDriver());
		requestParam.add("transport_mode", headerModel.getTransportMode());
		requestParam.add("police_name", headerModel.getPoliceName());
        requestParam.add("notes", headerModel.getNotes());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_shipment/insert", requestParam, response);
    }

    public static void UpdateHeader(long m_inventory_shipment_id, HeaderModel headerModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_shipment_id));
        requestParam.add("code", headerModel.getShipmentCode());
        requestParam.add("shipment_date", DateTimeUtil.ToDateString(headerModel.getShipmentDate()));
		requestParam.add("shipment_type", headerModel.getShipmentType());
		requestParam.add("request_arrive_date", DateTimeUtil.ToDateString(headerModel.getRequestArriveDate()));
		requestParam.add("estimated_time_arrive", DateTimeUtil.ToDateString(headerModel.getEstimatedTimeArrive()));
		requestParam.add("shipment_to", headerModel.getShipmentTo());
		requestParam.add("vehicle_no", headerModel.getVehicleNo());
		requestParam.add("vehicle_driver", headerModel.getVehicleDriver());
		requestParam.add("transport_mode", headerModel.getTransportMode());
		requestParam.add("police_name", headerModel.getPoliceName());
        requestParam.add("notes", headerModel.getNotes());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_shipment/update", requestParam, response);
    }

    public static void DeleteHeader(long m_inventory_shipment_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_shipment_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_shipment/delete", requestParam, response);
    }

    public static void InsertDetail(long m_inventory_shipment_id, long m_inventory_picking_id, DetailModel detailModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_shipment_id", String.valueOf(m_inventory_shipment_id));
        requestParam.add("m_inventory_picking_id", String.valueOf(m_inventory_picking_id));
        requestParam.add("barcode", detailModel.getBarcode());
        requestParam.add("pallet", detailModel.getPallet());
        requestParam.add("condition", detailModel.getCondition());
        requestParam.add("packed_group", detailModel.getPackedGroup());
        requestParam.add("quantity_box", String.valueOf(detailModel.getQuantityBox()));
        requestParam.add("repacked_group", detailModel.getRePackedGroup());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_shipment/insert_detail", requestParam, response);
    }

    public static void DeleteDetail(DetailModel detailModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_shipment_id", String.valueOf(detailModel.getInventoryShipmentId()));
		requestParam.add("m_inventory_picking_id", String.valueOf(detailModel.getInventoryPickingId()));
		requestParam.add("m_product_id", String.valueOf(detailModel.getProductId()));
		requestParam.add("barcode", detailModel.getBarcode());
		requestParam.add("pallet", detailModel.getPallet());
		requestParam.add("condition", detailModel.getCondition());
        requestParam.add("packed_group", detailModel.getPackedGroup());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_shipment/delete_detail", requestParam, response);
    }
}

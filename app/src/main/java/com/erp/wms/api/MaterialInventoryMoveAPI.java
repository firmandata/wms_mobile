package com.erp.wms.api;

import com.erp.helper.restful.RestfulParam;
import com.erp.helper.restful.RestfulRequest;
import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DateTimeUtil;
import com.erp.wms.material.inventory_move.model.DetailModel;
import com.erp.wms.material.inventory_move.model.HeaderModel;

public final class MaterialInventoryMoveAPI {
    public static void GetHeaders(int page, int from_month, int from_year, int to_month, int to_year, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "move_date");
        requestParam.add("sord", "desc");

        requestParam.add("from_month", String.valueOf(from_month));
        requestParam.add("from_year", String.valueOf(from_year));
        requestParam.add("to_month", String.valueOf(to_month));
        requestParam.add("to_year", String.valueOf(to_year));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_move/list", requestParam, response);
    }

    public static void GetHeader(long m_inventory_move_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_move_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_move/detail", requestParam, response);
    }

    public static void GetDetails(int page, long m_inventory_move_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "m_inventory_movedetail_id");
        requestParam.add("sord", "asc");

        requestParam.add("id", String.valueOf(m_inventory_move_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/inventory_move/detail_list", requestParam, response);
    }

    public static void InsertHeader(HeaderModel headerModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("code", headerModel.getMoveCode());
        requestParam.add("move_date", DateTimeUtil.ToDateString(headerModel.getMoveDate()));
        requestParam.add("notes", headerModel.getNotes());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_move/insert", requestParam, response);
    }

    public static void UpdateHeader(long m_inventory_move_id, HeaderModel headerModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_move_id));
        requestParam.add("code", headerModel.getMoveCode());
        requestParam.add("move_date", DateTimeUtil.ToDateString(headerModel.getMoveDate()));
        requestParam.add("notes", headerModel.getNotes());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_move/update", requestParam, response);
    }

    public static void DeleteHeader(long m_inventory_move_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(m_inventory_move_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_move/delete", requestParam, response);
    }

    public static void InsertDetail(long m_inventory_move_id, DetailModel detailModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_move_id", String.valueOf(m_inventory_move_id));
		requestParam.add("barcode", detailModel.getBarcode());
        requestParam.add("pallet_from", detailModel.getPalletFrom());
		requestParam.add("pallet_to", detailModel.getPalletTo());
		requestParam.add("m_product_code", detailModel.getProductCode());
		requestParam.add("m_gridfrom_code", detailModel.getGridFromCode());
        requestParam.add("m_gridto_code", detailModel.getGridToCode());

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_move/insert_detail", requestParam, response);
    }

    public static void DeleteDetail(DetailModel detailModel, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("m_inventory_move_id", String.valueOf(detailModel.getInventoryMoveId()));
		requestParam.add("barcode", detailModel.getBarcode());
        requestParam.add("pallet_from", detailModel.getPalletFrom());
		requestParam.add("pallet_to", detailModel.getPalletTo());
		requestParam.add("m_product_id", String.valueOf(detailModel.getProductId()));
		requestParam.add("m_gridfrom_id", String.valueOf(detailModel.getGridFromId()));
        requestParam.add("m_gridto_id", String.valueOf(detailModel.getGridToId()));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/inventory_move/delete_detail", requestParam, response);
    }
}

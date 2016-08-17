package com.erp.wms.api;

import com.erp.helper.restful.RestfulParam;
import com.erp.helper.restful.RestfulRequest;
import com.erp.helper.restful.RestfulResponse;
import com.erp.helper.utils.DateTimeUtil;
import com.erp.wms.custom.inventory_inbound.model.Model;

import java.util.Calendar;

public final class CustomInventoryInboundAPI {
    public static void Gets(int page, Calendar from, Calendar to, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("page", String.valueOf(page));
        requestParam.add("rows", String.valueOf(50));
        requestParam.add("sidx", "id");
        requestParam.add("sord", "desc");

        requestParam.add("from", DateTimeUtil.ToDateString(from));
        requestParam.add("to", DateTimeUtil.ToDateString(to));
        
        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/custom_inventory_inbound/list", requestParam, response);
    }
	
	public static void Get(long cus_m_inventory_inbound_detail_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(cus_m_inventory_inbound_detail_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/custom_inventory_inbound/detail", requestParam, response);
    }
	
    public static void ParseBarcode(String productCode, String barcode, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("product_code", productCode);
		requestParam.add("barcode", barcode);

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/custom_inventory_inbound/parse_barcode", requestParam, response);
    }

    public static void ProductProperties(String productCode, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("product_code", productCode);

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.get("/custom_inventory_inbound/product_properties", requestParam, response);
    }

    public static void Insert(Model model, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
		requestParam.add("product_code", model.getProductCode());
		requestParam.add("grid_code", model.getGridCode());
		requestParam.add("barcode", model.getBarcode());
		requestParam.add("pallet", model.getPallet());
		requestParam.add("quantity", String.valueOf(model.getQuantity()));
		requestParam.add("carton_no", model.getCartonNo());
		requestParam.add("lot_no", model.getLotNo());
        requestParam.add("volume_length", String.valueOf(model.getVolumeLength()));
        requestParam.add("volume_width", String.valueOf(model.getVolumeWidth()));
        requestParam.add("volume_height", String.valueOf(model.getVolumeHeight()));
		requestParam.add("condition", model.getCondition());
		requestParam.add("packed_date", DateTimeUtil.ToDateString(model.getPackedDate()));
        requestParam.add("expired_date", DateTimeUtil.ToDateString(model.getExpiredDate()));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/custom_inventory_inbound/insert", requestParam, response);
    }

    public static void Delete(long cus_m_inventory_inbound_detail_id, RestfulResponse response) {
        RestfulParam requestParam = new RestfulParam();
        requestParam.add("id", String.valueOf(cus_m_inventory_inbound_detail_id));

        RestfulRequest client = new RestfulRequest(response.getContext());
        client.post("/custom_inventory_inbound/delete", requestParam, response);
    }
}

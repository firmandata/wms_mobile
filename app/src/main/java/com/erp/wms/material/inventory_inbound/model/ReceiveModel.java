package com.erp.wms.material.inventory_inbound.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class ReceiveModel implements MyListIModel {
    private long mInventoryReceiveDetailId;
    private String mInventoryReceiveCode;
    private Calendar mInventoryReceiveDate;
    private String mInventoryReceiveCondition;
    private String mInventoryReceiveVehicleNo;
    private String mInventoryReceiveVehicleDriver;
    private String mInventoryReceiveTransportMode;

    private int mProductId;
    private String mProductCode;
    private String mProductName;
    private String mProductUom;
    private double mProductVolumeLength;
    private double mProductVolumeWidth;
    private double mProductVolumeHeight;

    private long mOrderInId;
    private String mOrderInCode;
    private Calendar mOrderInDate;

    private int mBox;
    private double mQuantity;

    private int mBusinessPartnerId;
    private String mBusinessPartner;

    public ReceiveModel(long inventoryReceiveDetailId) {
        setInventoryReceiveDetailId(inventoryReceiveDetailId);
    }

    public void setInventoryReceiveDetailId(long inventoryReceiveDetailId) {
        mInventoryReceiveDetailId = inventoryReceiveDetailId;
    }

    public long getInventoryReceiveDetailId() {
        return mInventoryReceiveDetailId;
    }

    public void setInventoryReceiveCode(String inventoryReceiveCode) {
        mInventoryReceiveCode = inventoryReceiveCode;
    }

    public String getInventoryReceiveCode() {
        return mInventoryReceiveCode;
    }

    public void setInventoryReceiveDate(Calendar inventoryReceiveDate) {
        mInventoryReceiveDate = inventoryReceiveDate;
    }

    public Calendar getInventoryReceiveDate() {
        return mInventoryReceiveDate;
    }

    public void setInventoryReceiveCondition(String inventoryReceiveCondition) {
        mInventoryReceiveCondition = inventoryReceiveCondition;
    }

    public String getInventoryReceiveCondition() {
        return mInventoryReceiveCondition;
    }

    public void setInventoryReceiveVehicleNo(String inventoryReceiveVehicleNo) {
        mInventoryReceiveVehicleNo = inventoryReceiveVehicleNo;
    }

    public String getInventoryReceiveVehicleNo() {
        return mInventoryReceiveVehicleNo;
    }

    public void setInventoryReceiveVehicleDriver(String inventoryReceiveVehicleDriver) {
        mInventoryReceiveVehicleDriver = inventoryReceiveVehicleDriver;
    }

    public String getInventoryReceiveVehicleDriver() {
        return mInventoryReceiveVehicleDriver;
    }

    public void setInventoryReceiveTransportMode(String inventoryReceiveTransportMode) {
        mInventoryReceiveTransportMode = inventoryReceiveTransportMode;
    }

    public String getInventoryReceiveTransportMode() {
        return mInventoryReceiveTransportMode;
    }

    public void setProductId(int productId) {
        mProductId = productId;
    }

    public int getProductId() {
        return mProductId;
    }

    public void setProductCode(String productCode) {
        mProductCode = productCode;
    }

    public String getProductCode() {
        return mProductCode;
    }

    public void setProductName(String productName) {
        mProductName = productName;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductUom(String productUom) {
        mProductUom = productUom;
    }

    public String getProductUom() {
        return mProductUom;
    }

    public void setProductVolumeLength(double productVolumeLength) {
        mProductVolumeLength = productVolumeLength;
    }

    public double getProductVolumeLength() {
        return mProductVolumeLength;
    }

    public void setProductVolumeWidth(double productVolumeWidth) {
        mProductVolumeWidth = productVolumeWidth;
    }

    public double getProductVolumeWidth() {
        return mProductVolumeWidth;
    }

    public void setProductVolumeHeight(double productVolumeHeight) {
        mProductVolumeHeight = productVolumeHeight;
    }

    public double getProductVolumeHeight() {
        return mProductVolumeHeight;
    }

    public void setOrderInId(long orderInId) {
        mOrderInId = orderInId;
    }

    public long getOrderInId() {
        return mOrderInId;
    }

    public void setOrderInCode(String orderInCode) {
        mOrderInCode = orderInCode;
    }

    public String getOrderInCode() {
        return mOrderInCode;
    }

    public void setOrderInDate(Calendar orderInDate) {
        mOrderInDate = orderInDate;
    }

    public Calendar getOrderInDate() {
        return mOrderInDate;
    }

    public void setBox(int box) {
        mBox = box;
    }

    public int getBox() {
        return mBox;
    }

    public void setQuantity(double quantity) {
        mQuantity = quantity;
    }

    public double getQuantity() {
        return mQuantity;
    }

    public void setBusinessPartnerId(int businessPartnerId) {
        mBusinessPartnerId = businessPartnerId;
    }

    public int getBusinessPartnerId() {
        return mBusinessPartnerId;
    }

    public void setBusinessPartner(String businessPartner) {
        mBusinessPartner = businessPartner;
    }

    public String getBusinessPartner() {
        return mBusinessPartner;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
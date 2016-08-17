package com.erp.wms.material.inventory_inbound.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class DetailModel implements MyListIModel {
    private long mInventoryInboundDetailId;
    private String mBarcode;
    private String mPallet;
    private String mCartonNo;

    private int mProductId;
    private String mProductCode;
    private String mProductName;
    private String mProductUom;

    private double mQuantity;
    private int mQuantityBox;
    private String mLotNo;
    private double mVolumeLength;
    private double mVolumeWidth;
    private double mVolumeHeight;
    private String mCondition;
    private Calendar mPackedDate;
    private Calendar mExpiredDate;

    private long mInventoryReceiveId;
    private String mInventoryReceiveCode;
    private Calendar mInventoryReceiveDate;
    private String mInventoryReceiveVehicleNo;
    private String mInventoryReceiveVehicleDriver;
    private String mInventoryReceiveTransportMode;

    private long mOrderInId;
    private String mOrderInCode;
    private Calendar mOrderInDate;

    private int mBusinessPartnerId;
    private String mBusinessPartner;

    private int mGridId;
    private String mGridCode;

    private int mProjectId;
    private String mProjectName;

    public DetailModel(long inventoryInboundDetailId) {
        setInventoryInboundDetailId(inventoryInboundDetailId);
    }

    public void setInventoryInboundDetailId(long inventoryInboundDetailId) {
        mInventoryInboundDetailId = inventoryInboundDetailId;
    }

    public long getInventoryInboundDetailId(){
        return mInventoryInboundDetailId;
    }

    public void setBarcode(String barcode) {
        mBarcode = barcode;
    }

    public String getBarcode() {
        return mBarcode;
    }

    public void setPallet(String pallet) {
        mPallet = pallet;
    }

    public String getPallet() {
        return mPallet;
    }

    public void setCartonNo(String cartonNo) {
        mCartonNo = cartonNo;
    }

    public String getCartonNo() {
        return mCartonNo;
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

    public void setQuantity(double quantity) {
        mQuantity = quantity;
    }

    public double getQuantity() {
        return mQuantity;
    }

    public void setQuantityBox(int quantityBox) {
        mQuantityBox = quantityBox;
    }

    public int getQuantityBox() {
        return mQuantityBox;
    }

    public void setLotNo(String lotNo) {
        mLotNo = lotNo;
    }

    public String getLotNo() {
        return mLotNo;
    }

    public void setVolumeLength(double volumeLength) {
        mVolumeLength = volumeLength;
    }

    public double getVolumeLength() {
        return mVolumeLength;
    }

    public void setVolumeWidth(double volumeWidth) {
        mVolumeWidth = volumeWidth;
    }

    public double getVolumeWidth() {
        return mVolumeWidth;
    }

    public void setVolumeHeight(double volumeHeight) {
        mVolumeHeight = volumeHeight;
    }

    public double getVolumeHeight() {
        return mVolumeHeight;
    }

    public void setCondition(String condition) {
        mCondition = condition;
    }

    public String getCondition() {
        return mCondition;
    }

    public void setPackedDate(Calendar packedDate) {
        mPackedDate = packedDate;
    }

    public Calendar getPackedDate() {
        return mPackedDate;
    }

    public void setExpiredDate(Calendar expiredDate) {
        mExpiredDate = expiredDate;
    }

    public Calendar getExpiredDate() {
        return mExpiredDate;
    }

    public void setInventoryReceiveId(long inventoryReceiveId) {
        mInventoryReceiveId = inventoryReceiveId;
    }

    public long getInventoryReceiveId() {
        return mInventoryReceiveId;
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

    public void setGridId(int gridId) {
        mGridId = gridId;
    }

    public int getGridId() {
        return mGridId;
    }

    public void setGridCode(String gridCode) {
        mGridCode = gridCode;
    }

    public String getGridCode() {
        return mGridCode;
    }

    public void setProjectId(int projectId) {
        mProjectId = projectId;
    }

    public int getProjectId() {
        return mProjectId;
    }

    public void setProjectName(String projectName) {
        mProjectName = projectName;
    }

    public String getProjectName() {
        return mProjectName;
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
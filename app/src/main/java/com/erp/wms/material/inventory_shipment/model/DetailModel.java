package com.erp.wms.material.inventory_shipment.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class DetailModel implements MyListIModel {
    private long mInventoryShipmentDetailId;
	private long mInventoryShipmentId;

    private String mBarcode;
    private String mPallet;
    private String mCondition;
    private String mRePackedGroup;
    private String mPackedGroup;

    private int mProductId;
    private String mProductCode;
    private String mProductName;
    private String mProductUom;

    private int mQuantityBox;
    private double mQuantity;

    private long mOrderOutId;
    private String mOrderOutCode;
    private Calendar mOrderOutDate;
    private Calendar mOrderOutRequestArriveDate;

    private long mInventoryPickingId;
    private String mInventoryPickingCode;
    private Calendar mInventoryPickingDate;

    private long mInventoryPicklistId;
    private String mInventoryPicklistCode;
    private Calendar mInventoryPicklistDate;

    private int mBusinessPartnerId;
    private String mBusinessPartner;

    private int mProjectId;
    private String mProjectName;

    public DetailModel(long inventoryShipmentDetailId, long inventoryShipmentId, long inventoryPickingId) {
		setInventoryShipmentDetailId(inventoryShipmentDetailId);
        setInventoryShipmentId(inventoryShipmentId);
        setInventoryPickingId(inventoryPickingId);
    }

	public void setInventoryShipmentDetailId(long inventoryShipmentDetailId) {
        mInventoryShipmentDetailId = inventoryShipmentDetailId;
    }

    public long getInventoryShipmentDetailId(){
        return mInventoryShipmentDetailId;
    }

    public void setInventoryShipmentId(long inventoryShipmentId) {
        mInventoryShipmentId = inventoryShipmentId;
    }

    public long getInventoryShipmentId(){
        return mInventoryShipmentId;
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

    public void setCondition(String condition) {
        mCondition = condition;
    }

    public String getCondition() {
        return mCondition;
    }

    public void setRePackedGroup(String rePackedGroup) {
        mRePackedGroup = rePackedGroup;
    }

    public String getRePackedGroup() {
        return mRePackedGroup;
    }

    public void setPackedGroup(String packedGroup) {
        mPackedGroup = packedGroup;
    }

    public String getPackedGroup() {
        return mPackedGroup;
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

    public void setQuantityBox(int quantity_box) {
        mQuantityBox = quantity_box;
    }

    public double getQuantityBox() {
        return mQuantityBox;
    }

	public void setQuantity(double quantity) {
        mQuantity = quantity;
    }

    public double getQuantity() {
        return mQuantity;
    }

    public void setOrderOutId(long orderOutId) {
        mOrderOutId = orderOutId;
    }

    public long getOrderOutId() {
        return mOrderOutId;
    }

    public void setOrderOutCode(String orderOutCode) {
        mOrderOutCode = orderOutCode;
    }

    public String getOrderOutCode() {
        return mOrderOutCode;
    }

    public void setOrderOutDate(Calendar orderOutDate) {
        mOrderOutDate = orderOutDate;
    }

    public Calendar getOrderOutDate() {
        return mOrderOutDate;
    }

    public void setOrderOutRequestArriveDate(Calendar orderOutRequestArriveDate) {
        mOrderOutRequestArriveDate = orderOutRequestArriveDate;
    }

    public Calendar getOrderOutRequestArriveDate() {
        return mOrderOutRequestArriveDate;
    }

    public void setInventoryPickingId(long inventoryPickingId) {
        mInventoryPickingId = inventoryPickingId;
    }

    public long getInventoryPickingId(){
        return mInventoryPickingId;
    }

    public void setInventoryPickingCode(String inventoryPickingCode) {
        mInventoryPickingCode = inventoryPickingCode;
    }

    public String getInventoryPickingCode() {
        return mInventoryPickingCode;
    }

    public void setInventoryPickingDate(Calendar inventoryPickingDate) {
        mInventoryPickingDate = inventoryPickingDate;
    }

    public Calendar getInventoryPickingDate() {
        return mInventoryPickingDate;
    }

    public void setInventoryPicklistId(long inventoryPicklistId) {
        mInventoryPicklistId = inventoryPicklistId;
    }

    public long getInventoryPicklistId() {
        return mInventoryPicklistId;
    }

    public void setInventoryPicklistCode(String inventoryPicklistCode) {
        mInventoryPicklistCode = inventoryPicklistCode;
    }

    public String getInventoryPicklistCode() {
        return mInventoryPicklistCode;
    }

    public void setInventoryPicklistDate(Calendar inventoryPicklistDate) {
        mInventoryPicklistDate = inventoryPicklistDate;
    }

    public Calendar getInventoryPicklistDate() {
        return mInventoryPicklistDate;
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
package com.erp.wms.material.inventory_picking.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class DetailModel implements MyListIModel {
    private long mInventoryPickingDetailId;
    private long mInventoryPickingId;

    private long mInventoryPicklistId;
    private String mInventoryPicklistCode;
    private Calendar mInventoryPicklistDate;

    private String mBarcode;
    private String mPallet;
    private String mCartonNo;
    private String mLotNo;
    private String mCondition;
    private String mPackedGroup;

    private int mProductId;
    private String mProductCode;
    private String mProductName;
    private String mProductUom;

	private int mQuantityBox;
    private double mQuantity;

    private int mQuantityBoxUsed;
    private double mQuantityUsed;

    private String mStatusInventoryShipment;

    private int mGridId;
    private String mGridCode;

    private long mOrderOutId;
    private String mOrderOutCode;
    private Calendar mOrderOutDate;
    private Calendar mOrderOutRequestArriveDate;

    private int mBusinessPartnerId;
    private String mBusinessPartner;

    private int mProjectId;
    private String mProjectName;

    public DetailModel(long inventoryPickingDetailId, long inventoryPickingId, long inventoryPicklistId) {
        setInventoryPickingDetailId(inventoryPickingDetailId);
        setInventoryPickingId(inventoryPickingId);
		setInventoryPicklistId(inventoryPicklistId);
    }

    public void setInventoryPickingDetailId(long inventoryPickingDetailId) {
        mInventoryPickingDetailId = inventoryPickingDetailId;
    }

    public long getInventoryPickingDetailId(){
        return mInventoryPickingDetailId;
    }

    public void setInventoryPickingId(long inventoryPickingId) {
        mInventoryPickingId = inventoryPickingId;
    }

    public long getInventoryPickingId(){
        return mInventoryPickingId;
    }
	
	public void setInventoryPicklistId(long inventoryPicklistId) {
        mInventoryPicklistId = inventoryPicklistId;
    }

    public long getInventoryPicklistId(){
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

    public void setLotNo(String lotNo) {
        mLotNo = lotNo;
    }

    public String getLotNo() {
        return mLotNo;
    }

    public void setCondition(String condition) {
        mCondition = condition;
    }

    public String getCondition() {
        return mCondition;
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

    public void setQuantityBox(int quantityBox) {
        mQuantityBox = quantityBox;
    }

    public int getQuantityBox() {
        return mQuantityBox;
    }

	public void setQuantity(double quantity) {
        mQuantity = quantity;
    }

    public double getQuantity() {
        return mQuantity;
    }

    public void setQuantityBoxUsed(int quantityBoxUsed) {
        mQuantityBoxUsed = quantityBoxUsed;
    }

    public double getQuantityBoxUsed() {
        return mQuantityBoxUsed;
    }

    public void setQuantityUsed(double quantityUsed) {
        mQuantityUsed = quantityUsed;
    }

    public double getQuantityUsed() {
        return mQuantityUsed;
    }

    public void setStatusInventoryShipment(String statusInventoryShipment) {
        mStatusInventoryShipment = statusInventoryShipment;
    }

    public String getStatusInventoryShipment() {
        return mStatusInventoryShipment;
    }

    public void setGridCode(String gridCode) {
        mGridCode = gridCode;
    }

    public String getGridCode() {
        return mGridCode;
    }
	
	public void setGridId(int gridId) {
        mGridId = gridId;
    }

    public int getGridId() {
        return mGridId;
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
package com.erp.wms.material.inventory_shipment.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class HeaderModel implements MyListIModel {

    private long mInventoryShipmentId;
    private String mShipmentCode;
    private Calendar mShipmentDate;
	private String mShipmentType;
	private Calendar mRequestArriveDate;
	private Calendar mEstimatedTimeArrive;
	private String mShipmentTo;
	private String mVehicleNo;
	private String mVehicleDriver;
	private String mTransportMode;
	private String mPoliceName;

    private long mOrderOutId;
    private String mOrderOutCode;
    private Calendar mOrderOutDate;

    private int mQuantityBox;
    private double mQuantity;

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

    private String mNotes;

    public HeaderModel(long inventoryShipmentId) {
        setInventoryShipmentId(inventoryShipmentId);
    }

    public void setInventoryShipmentId(long inventoryShipmentId) {
        mInventoryShipmentId = inventoryShipmentId;
    }

    public long getInventoryShipmentId() {
        return mInventoryShipmentId;
    }

    public void setShipmentCode(String shipmentCode) {
        mShipmentCode = shipmentCode;
    }

    public String getShipmentCode() {
        return mShipmentCode;
    }

    public void setShipmentDate(Calendar shipmentDate) {
        mShipmentDate = shipmentDate;
    }

    public Calendar getShipmentDate() {
        return mShipmentDate;
    }

    public void setShipmentType(String shipmentType) {
        mShipmentType = shipmentType;
    }

    public String getShipmentType() {
        return mShipmentType;
    }

    public void setRequestArriveDate(Calendar requestArriveDate) {
        mRequestArriveDate = requestArriveDate;
    }

    public Calendar getRequestArriveDate() {
        return mRequestArriveDate;
    }
	
	public void setEstimatedTimeArrive(Calendar estimatedTimeArrive) {
        mEstimatedTimeArrive = estimatedTimeArrive;
    }

    public Calendar getEstimatedTimeArrive() {
        return mEstimatedTimeArrive;
    }

    public void setShipmentTo(String shipmentTo) {
        mShipmentTo = shipmentTo;
    }

    public String getShipmentTo() {
        return mShipmentTo;
    }

    public void setVehicleNo(String vehicleNo) {
        mVehicleNo = vehicleNo;
    }

    public String getVehicleNo() {
        return mVehicleNo;
    }
	
	public void setVehicleDriver(String vehicleDriver) {
        mVehicleDriver = vehicleDriver;
    }

    public String getVehicleDriver() {
        return mVehicleDriver;
    }
	
	public void setTransportMode(String transportMode) {
        mTransportMode = transportMode;
    }

    public String getTransportMode() {
        return mTransportMode;
    }
	
	public void setPoliceName(String policeName) {
        mPoliceName = policeName;
    }

    public String getPoliceName() {
        return mPoliceName;
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

    public void setInventoryPickingId(long inventoryPickingId) {
        mInventoryPickingId = inventoryPickingId;
    }

    public long getInventoryPickingId() {
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

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public String getNotes() {
        return mNotes;
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
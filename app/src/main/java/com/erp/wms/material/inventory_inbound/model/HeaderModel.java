package com.erp.wms.material.inventory_inbound.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class HeaderModel implements MyListIModel {

    private long mInventoryInboundId;
    private String mInboundCode;
    private Calendar mInboundDate;

    private long mInventoryReceiveId;
    private String mInventoryReceiveCode;
    private Calendar mInventoryReceiveDate;
    private String mInventoryReceiveVehicleNo;
    private String mInventoryReceiveVehicleDriver;
    private String mInventoryReceiveTransportMode;

    private long mOrderInId;
    private String mOrderInCode;
    private Calendar mOrderInDate;

    private int mBox;
    private double mQuantity;

    private int mBusinessPartnerId;
    private String mBusinessPartner;

    private int mProjectId;
    private String mProjectName;

    private String mNotes;

    public HeaderModel(long inventoryInboundId) {
        setInventoryInboundId(inventoryInboundId);
    }

    public void setInventoryInboundId(long inventoryInboundId) {
        mInventoryInboundId = inventoryInboundId;
    }

    public long getInventoryInboundId() {
        return mInventoryInboundId;
    }

    public void setInboundCode(String inboundCode) {
        mInboundCode = inboundCode;
    }

    public String getInboundCode() {
        return mInboundCode;
    }

    public void setInboundDate(Calendar inboundDate) {
        mInboundDate = inboundDate;
    }

    public Calendar getInboundDate() {
        return mInboundDate;
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
package com.erp.wms.material.inventory_picking.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class HeaderModel implements MyListIModel {

    private long mInventoryPickingId;
    private String mPickingCode;
    private Calendar mPickingDate;

    private String mStatusInventoryShipment;

    private long mInventoryPicklistId;
    private String mInventoryPicklistCode;
    private Calendar mInventoryPicklistDate;

    private long mOrderOutId;
    private String mOrderOutCode;
    private Calendar mOrderOutDate;
    private Calendar mOrderOutRequestArriveDate;

    private int mBusinessPartnerId;
    private String mBusinessPartner;

    private int mProjectId;
    private String mProjectName;

    private String mNotes;

    public HeaderModel(long inventoryPickingId) {
        setInventoryPickingId(inventoryPickingId);
    }

    public void setInventoryPickingId(long inventoryPickingId) {
        mInventoryPickingId = inventoryPickingId;
    }

    public long getInventoryPickingId() {
        return mInventoryPickingId;
    }

    public void setPickingCode(String pickingCode) {
        mPickingCode = pickingCode;
    }

    public String getPickingCode() {
        return mPickingCode;
    }

    public void setPickingDate(Calendar pickingDate) {
        mPickingDate = pickingDate;
    }

    public Calendar getPickingDate() {
        return mPickingDate;
    }

    public void setStatusInventoryShipment(String statusInventoryShipment) {
        mStatusInventoryShipment = statusInventoryShipment;
    }

    public String getStatusInventoryShipment() {
        return mStatusInventoryShipment;
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
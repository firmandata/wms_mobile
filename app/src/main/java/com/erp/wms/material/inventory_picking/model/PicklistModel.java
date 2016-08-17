package com.erp.wms.material.inventory_picking.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class PicklistModel implements MyListIModel {
    private long mInventoryPicklistId;
    private String mInventoryPicklistCode;
    private Calendar mInventoryPicklistDate;

    private long mOrderOutId;
    private String mOrderOutCode;
    private Calendar mOrderOutDate;

    private int mQuantityBox;
    private double mQuantity;

    private int mProjectId;
    private String mProjectName;

    private int mBusinessPartnerId;
    private String mBusinessPartner;

    private Calendar mRequestArriveDate;

    public PicklistModel(long inventoryPicklistId) {
        setInventoryPicklistId(inventoryPicklistId);
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

    public void setRequestArriveDate(Calendar requestArriveDate) {
        mRequestArriveDate = requestArriveDate;
    }

    public Calendar getRequestArriveDate() {
        return mRequestArriveDate;
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
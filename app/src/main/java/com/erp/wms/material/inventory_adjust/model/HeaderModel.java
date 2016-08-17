package com.erp.wms.material.inventory_adjust.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class HeaderModel implements MyListIModel {

    private long mInventoryAdjustId;
    private String mAdjustCode;
    private Calendar mAdjustDate;
	private String mAdjustType;
	private int mProduct;
	private int mQuantityBoxFrom;
    private int mQuantityBoxTo;
    private double mQuantityFrom;
	private double mQuantityTo;
    private String mNotes;

    public HeaderModel(long inventoryAdjustId) {
        setInventoryAdjustId(inventoryAdjustId);
    }

    public void setInventoryAdjustId(long inventoryAdjustId) {
        mInventoryAdjustId = inventoryAdjustId;
    }

    public long getInventoryAdjustId() {
        return mInventoryAdjustId;
    }

    public void setAdjustCode(String adjustCode) {
        mAdjustCode = adjustCode;
    }

    public String getAdjustCode() {
        return mAdjustCode;
    }

    public void setAdjustDate(Calendar adjustDate) {
        mAdjustDate = adjustDate;
    }

    public Calendar getAdjustDate() {
        return mAdjustDate;
    }

    public void setAdjustType(String adjustType) {
        mAdjustType = adjustType;
    }

    public String getAdjustType() {
        return mAdjustType;
    }

    public void setProduct(int product) {
        mProduct = product;
    }

    public int getProduct() {
        return mProduct;
    }

    public void setQuantityBoxFrom(int quantityBoxFrom) {
        mQuantityBoxFrom = quantityBoxFrom;
    }

    public int getQuantityBoxFrom() {
        return mQuantityBoxFrom;
    }

    public void setQuantityBoxTo(int quantityBoxTo) {
        mQuantityBoxTo = quantityBoxTo;
    }

    public int getQuantityBoxTo() {
        return mQuantityBoxTo;
    }

    public void setQuantityFrom(double quantityFrom) {
        mQuantityFrom = quantityFrom;
    }

    public double getQuantityFrom() {
        return mQuantityFrom;
    }

    public void setQuantityTo(double quantityTo) {
        mQuantityTo = quantityTo;
    }

    public double getQuantityTo() {
        return mQuantityTo;
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
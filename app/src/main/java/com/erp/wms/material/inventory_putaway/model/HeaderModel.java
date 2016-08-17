package com.erp.wms.material.inventory_putaway.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class HeaderModel implements MyListIModel {

    private long mInventoryPutawayId;
    private String mPutawayCode;
    private Calendar mPutawayDate;
	private int mPallet;
    private int mQuantityBoxTo;
    private double mQuantityTo;
    private String mNotes;

    public HeaderModel(long inventoryPutawayId) {
        setInventoryPutawayId(inventoryPutawayId);
    }

    public void setInventoryPutawayId(long inventoryPutawayId) {
        mInventoryPutawayId = inventoryPutawayId;
    }

    public long getInventoryPutawayId() {
        return mInventoryPutawayId;
    }

    public void setPutawayCode(String putawayCode) {
        mPutawayCode = putawayCode;
    }

    public String getPutawayCode() {
        return mPutawayCode;
    }

    public void setPutawayDate(Calendar putawayDate) {
        mPutawayDate = putawayDate;
    }

    public Calendar getPutawayDate() {
        return mPutawayDate;
    }

    public void setPallet(int pallet) {
        mPallet = pallet;
    }

    public int getPallet() {
        return mPallet;
    }

    public void setQuantityBoxTo(int quantityBoxTo) {
        mQuantityBoxTo = quantityBoxTo;
    }

    public int getQuantityBoxTo() {
        return mQuantityBoxTo;
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
package com.erp.wms.material.inventory_move.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class HeaderModel implements MyListIModel {

    private long mInventoryMoveId;
    private String mMoveCode;
    private Calendar mMoveDate;
	private int mProduct;
    private int mQuantityBoxTo;
    private double mQuantityTo;
    private String mNotes;

    public HeaderModel(long inventoryMoveId) {
        setInventoryMoveId(inventoryMoveId);
    }

    public void setInventoryMoveId(long inventoryMoveId) {
        mInventoryMoveId = inventoryMoveId;
    }

    public long getInventoryMoveId() {
        return mInventoryMoveId;
    }

    public void setMoveCode(String moveCode) {
        mMoveCode = moveCode;
    }

    public String getMoveCode() {
        return mMoveCode;
    }

    public void setMoveDate(Calendar moveDate) {
        mMoveDate = moveDate;
    }

    public Calendar getMoveDate() {
        return mMoveDate;
    }

    public void setProduct(int product) {
        mProduct = product;
    }

    public int getProduct() {
        return mProduct;
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
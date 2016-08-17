package com.erp.wms.material.inventory_putaway.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class DetailModel implements MyListIModel {
    private long mInventoryPutawayId;
	private long mInventoryPutawayDetailId;
    private String mPallet;
	private int mQuantityBoxTo;
    private double mQuantityTo;
	private int mGridToId;
    private String mGridToCode;
	private Calendar mCreatedDate;
	
    public DetailModel(long inventoryPutawayId, long inventoryPutawayDetailId) {
        setInventoryPutawayId(inventoryPutawayId);
		setInventoryPutawayDetailId(inventoryPutawayDetailId);
    }

    public void setInventoryPutawayId(long inventoryPutawayId) {
        mInventoryPutawayId = inventoryPutawayId;
    }

    public long getInventoryPutawayId(){
        return mInventoryPutawayId;
    }

    public void setInventoryPutawayDetailId(long inventoryPutawayDetailId) {
        mInventoryPutawayDetailId = inventoryPutawayDetailId;
    }

    public long getInventoryPutawayDetailId(){
        return mInventoryPutawayDetailId;
    }

    public void setPallet(String pallet) {
        mPallet = pallet;
    }

    public String getPallet() {
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

    public void setGridToId(int gridToId) {
        mGridToId = gridToId;
    }

    public int getGridToId() {
        return mGridToId;
    }
	
	public void setGridToCode(String gridToCode) {
        mGridToCode = gridToCode;
    }

    public String getGridToCode() {
        return mGridToCode;
    }
	
	public void setCreatedDate(Calendar createdDate) {
        mCreatedDate = createdDate;
    }

    public Calendar getCreatedDate() {
        return mCreatedDate;
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
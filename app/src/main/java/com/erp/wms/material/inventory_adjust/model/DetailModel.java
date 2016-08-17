package com.erp.wms.material.inventory_adjust.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class DetailModel implements MyListIModel {
    private long mInventoryAdjustId;
	private long mInventoryAdjustDetailId;
    private String mBarcode;
	private String mPallet;
	private int mProductId;
    private String mProductCode;
	private String mProductName;
	private int mGridId;
    private String mGridCode;
	private int mQuantityBoxFrom;
	private int mQuantityBoxTo;
    private double mQuantityFrom;
    private double mQuantityTo;
	private Calendar mCreatedDate;
	
    public DetailModel(long inventoryAdjustId, long inventoryAdjustDetailId) {
        setInventoryAdjustId(inventoryAdjustId);
		setInventoryAdjustDetailId(inventoryAdjustDetailId);
    }

    public void setInventoryAdjustId(long inventoryAdjustId) {
        mInventoryAdjustId = inventoryAdjustId;
    }

    public long getInventoryAdjustId(){
        return mInventoryAdjustId;
    }

    public void setInventoryAdjustDetailId(long inventoryAdjustDetailId) {
        mInventoryAdjustDetailId = inventoryAdjustDetailId;
    }

    public long getInventoryAdjustDetailId(){
        return mInventoryAdjustDetailId;
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
	
    public void setGridId(int gridId) {
        mGridId = gridId;
    }

    public int getGridId() {
        return mGridId;
    }
	
	public void setGridCode(String gridCode) {
        mGridCode = gridCode;
    }

    public String getGridCode() {
        return mGridCode;
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
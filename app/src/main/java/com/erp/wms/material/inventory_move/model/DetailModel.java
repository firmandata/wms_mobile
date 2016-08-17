package com.erp.wms.material.inventory_move.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class DetailModel implements MyListIModel {
    private long mInventoryMoveId;
	private long mInventoryMoveDetailId;
    private String mPalletFrom;
	private String mPalletTo;
	private String mBarcode;
	private int mProductId;
    private String mProductCode;
	private String mProductName;
	private int mQuantityBoxTo;
    private double mQuantityTo;
	private int mGridFromId;
    private String mGridFromCode;
	private int mGridToId;
	private String mGridToCode;
	private Calendar mCreatedDate;
	
    public DetailModel(long inventoryMoveId, long inventoryMoveDetailId) {
        setInventoryMoveId(inventoryMoveId);
		setInventoryMoveDetailId(inventoryMoveDetailId);
    }

    public void setInventoryMoveId(long inventoryMoveId) {
        mInventoryMoveId = inventoryMoveId;
    }

    public long getInventoryMoveId(){
        return mInventoryMoveId;
    }

    public void setInventoryMoveDetailId(long inventoryMoveDetailId) {
        mInventoryMoveDetailId = inventoryMoveDetailId;
    }

    public long getInventoryMoveDetailId(){
        return mInventoryMoveDetailId;
    }

    public void setPalletFrom(String palletFrom) {
        mPalletFrom = palletFrom;
    }

    public String getPalletFrom() {
        return mPalletFrom;
    }
	
	public void setPalletTo(String palletTo) {
        mPalletTo = palletTo;
    }

    public String getPalletTo() {
        return mPalletTo;
    }

    public void setBarcode(String barcode) {
        mBarcode = barcode;
    }

    public String getBarcode() {
        return mBarcode;
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

    public void setGridFromId(int gridFromId) {
        mGridFromId = gridFromId;
    }

    public int getGridFromId() {
        return mGridFromId;
    }
	
	public void setGridFromCode(String gridFromCode) {
        mGridFromCode = gridFromCode;
    }

    public String getGridFromCode() {
        return mGridFromCode;
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
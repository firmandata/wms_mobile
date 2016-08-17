package com.erp.wms.custom.inventory_inbound.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class Model implements MyListIModel {
    private long mCusInventoryInboundDetailId;
    private String mBarcode;

    private int mProductId;
    private String mProductCode;
    private String mProductName;
    private String mProductUom;

    private int mGridId;
    private String mGridCode;

    private String mPallet;
    private String mCartonNo;
    private double mQuantity;
    private String mLotNo;
    private double mVolumeLength;
    private double mVolumeWidth;
    private double mVolumeHeight;
    private String mCondition;
    private Calendar mPackedDate;
    private Calendar mExpiredDate;
    private Calendar mCreatedDate;

    public Model(long cusInventoryInboundDetailId) {
        setCusInventoryInboundDetailId(cusInventoryInboundDetailId);
    }

    public void setCusInventoryInboundDetailId(long cusInventoryInboundDetailId) {
        mCusInventoryInboundDetailId = cusInventoryInboundDetailId;
    }

    public long getCusInventoryInboundDetailId(){
        return mCusInventoryInboundDetailId;
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

    public void setProductUom(String productUom) {
        mProductUom = productUom;
    }

    public String getProductUom() {
        return mProductUom;
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
	
    public void setPallet(String pallet) {
        mPallet = pallet;
    }

    public String getPallet() {
        return mPallet;
    }

    public void setCartonNo(String cartonNo) {
        mCartonNo = cartonNo;
    }

    public String getCartonNo() {
        return mCartonNo;
    }

    public void setQuantity(double quantity) {
        mQuantity = quantity;
    }

    public double getQuantity() {
        return mQuantity;
    }

    public void setLotNo(String lotNo) {
        mLotNo = lotNo;
    }

    public String getLotNo() {
        return mLotNo;
    }

    public void setVolumeLength(double volumeLength) {
        mVolumeLength = volumeLength;
    }

    public double getVolumeLength() {
        return mVolumeLength;
    }

    public void setVolumeWidth(double volumeWidth) {
        mVolumeWidth = volumeWidth;
    }

    public double getVolumeWidth() {
        return mVolumeWidth;
    }

    public void setVolumeHeight(double volumeHeight) {
        mVolumeHeight = volumeHeight;
    }

    public double getVolumeHeight() {
        return mVolumeHeight;
    }

    public void setCondition(String condition) {
        mCondition = condition;
    }

    public String getCondition() {
        return mCondition;
    }

    public void setPackedDate(Calendar packedDate) {
        mPackedDate = packedDate;
    }

    public Calendar getPackedDate() {
        return mPackedDate;
    }

    public void setExpiredDate(Calendar expiredDate) {
        mExpiredDate = expiredDate;
    }

    public Calendar getExpiredDate() {
        return mExpiredDate;
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
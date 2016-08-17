package com.erp.wms.material.inventory_check.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;

public class MainDetailModel implements MyListIModel {
    private int mProductId;
    private String mProductCode;
    private String mProductName;
    private String mProductUom;
    private int mProductPack;

    private int mProductGroupId;
    private String mProductGroupName;

    private String mBarcode;
    private String mPallet;
    private String mLotNo;
    private double mVolumeLength;
    private double mVolumeWidth;
    private double mVolumeHeight;
    private String mCartonNo;
    private String mCondition;

    private int mGridId;
    private String mGridCode;

    private int mWarehouseId;
    private String mWarehouseCode;
    private String mWarehouseName;

    private int mProjectId;
    private String mProjectName;

    private Calendar mPackedDate;
    private Calendar mExpiredDate;
    private int mAge;

    private int mQuantityBoxExist;
    private int mQuantityBoxAllocated;
    private int mQuantityBoxPicked;
    private int mQuantityBoxOnhand;

    private double mQuantityExist;
    private double mQuantityAllocated;
    private double mQuantityPicked;
    private double mQuantityOnhand;

    public MainDetailModel() {
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

    public void setProductPack(int productPack) {
        mProductPack = productPack;
    }

    public int getProductPack() {
        return mProductPack;
    }

    public void setProductGroupId(int productGroupId) {
        mProductGroupId = productGroupId;
    }

    public int getProductGroupId() {
        return mProductGroupId;
    }

    public void setProductGroupName(String productGroupName) {
        mProductGroupName = productGroupName;
    }

    public String getProductGroupName() {
        return mProductGroupName;
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

    public void setCartonNo(String cartonNo) {
        mCartonNo = cartonNo;
    }

    public String getCartonNo() {
        return mCartonNo;
    }

    public void setCondition(String condition) {
        mCondition = condition;
    }

    public String getCondition() {
        return mCondition;
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

    public void setWarehouseId(int warehouseId) {
        mWarehouseId = warehouseId;
    }

    public int getWarehouseId() {
        return mWarehouseId;
    }

    public void setWarehouseCode(String warehouseCode) {
        mWarehouseCode = warehouseCode;
    }

    public String getWarehouseCode() {
        return mWarehouseCode;
    }

    public void setWarehouseName(String warehouseName) {
        mWarehouseName = warehouseName;
    }

    public String getWarehouseName() {
        return mWarehouseName;
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

    public void setAge(int age) {
        mAge = age;
    }

    public int getAge() {
        return mAge;
    }

    public void setQuantityBoxExist(int quantityBoxExist) {
        mQuantityBoxExist = quantityBoxExist;
    }

    public int getQuantityBoxExist() {
        return mQuantityBoxExist;
    }

    public void setQuantityBoxAllocated(int quantityBoxAllocated) {
        mQuantityBoxAllocated = quantityBoxAllocated;
    }

    public int getQuantityBoxAllocated() {
        return mQuantityBoxAllocated;
    }

    public void setQuantityBoxPicked(int quantityBoxPicked) {
        mQuantityBoxPicked = quantityBoxPicked;
    }

    public int getQuantityBoxPicked() {
        return mQuantityBoxPicked;
    }

    public void setQuantityBoxOnhand(int quantityBoxOnhand) {
        mQuantityBoxOnhand = quantityBoxOnhand;
    }

    public int getQuantityBoxOnhand() {
        return mQuantityBoxOnhand;
    }

    public void setQuantityExist(double quantityExist) {
        mQuantityExist = quantityExist;
    }

    public double getQuantityExist() {
        return mQuantityExist;
    }

    public void setQuantityAllocated(double quantityAllocated) {
        mQuantityAllocated = quantityAllocated;
    }

    public double getQuantityAllocated() {
        return mQuantityAllocated;
    }

    public void setQuantityPicked(double quantityPicked) {
        mQuantityPicked = quantityPicked;
    }

    public double getQuantityPicked() {
        return mQuantityPicked;
    }

    public void setQuantityOnhand(double quantityOnhand) {
        mQuantityOnhand = quantityOnhand;
    }

    public double getQuantityOnhand() {
        return mQuantityOnhand;
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
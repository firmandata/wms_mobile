package com.erp.wms.material.inventory_check.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MainModel implements MyListIModel {
    private String mProductCode;
    private String mBarcode;
    private String mPallet;
	private String mGridCode;

    public MainModel() {
    }

    public void setProductCode(String productCode) {
        mProductCode = productCode;
    }

    public String getProductCode() {
        return mProductCode;
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
	
	public void setGridCode(String gridCode) {
        mGridCode = gridCode;
    }

    public String getGridCode() {
        return mGridCode;
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
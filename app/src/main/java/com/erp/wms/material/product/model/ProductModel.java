package com.erp.wms.material.product.model;

import com.erp.helper.widget.myautocomplete.MyAutoCompleteIModel;
import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ProductModel implements MyListIModel, MyAutoCompleteIModel {
    private int mId;
    private String mCode;
    private String mName;
    private String mUom;
    private int mPack;

    private int mProductGroupId;
    private String mProductGroupName;

    public ProductModel() {

    }

    public void setId(int id) {
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setCode(String code) {
        mCode = code;
    }

    public String getCode() {
        return mCode;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setUom(String uom) {
        mUom = uom;
    }

    public String getUom() {
        return mUom;
    }

    public void setPack(int pack) {
        mPack = pack;
    }

    public int getPack() {
        return mPack;
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

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}

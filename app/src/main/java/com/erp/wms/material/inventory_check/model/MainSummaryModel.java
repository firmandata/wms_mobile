package com.erp.wms.material.inventory_check.model;

import com.erp.helper.widget.mylist.MyListIModel;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MainSummaryModel implements MyListIModel {
    private int mQuantityBoxExist;
    private int mQuantityBoxAllocated;
    private int mQuantityBoxPicked;
	private int mQuantityBoxOnhand;

    private double mQuantityExist;
    private double mQuantityAllocated;
    private double mQuantityPicked;
    private double mQuantityOnhand;

    public MainSummaryModel() {
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
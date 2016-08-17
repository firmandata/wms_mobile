package com.erp.helper.widget.mylist;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MyListAdapter<T extends MyListRecord> extends BaseAdapter {

    private int mSelectedPosition = -1;

    protected MyListView mMyListView;
    protected T[] mRecords;

    public MyListAdapter(MyListView myListView, T[] records) {
        mMyListView = myListView;
        setRecords(records);
    }

    public void setRecords(T[] records) {
        mRecords = records;
    }

    public T[] getRecords() {
        return mRecords;
    }

    @Override
    public int getCount() {
        if (mRecords == null)
            return 0;

        return mRecords.length;
    }

    @Override
    public Object getItem(int position) {
        if (mRecords == null)
            return null;

        if (mRecords.length - 1 >= position)
            return mRecords[position];
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        if (mRecords == null)
            return 0;

        if (mRecords.length - 1 >= position)
            return mRecords[position].getId();
        else
            return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mRecords == null)
            return null;

        if (mRecords.length - 1 >= position) {
            T record = mRecords[position];
            View recordView = record.getView();

            if (mSelectedPosition > -1) {
                if (mSelectedPosition == position) {
                    mMyListView.setSelectedView(recordView);
                } else {
                    mMyListView.setUnSelectedView(recordView);
                }
            }

            return recordView;
        }
        else
            return null;
    }

    public void setSelected(int position) {
        mSelectedPosition = position;
    }

    public int getSelected() {
        return mSelectedPosition;
    }
}
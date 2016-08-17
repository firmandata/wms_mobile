package com.erp.helper.widget.mylist;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.erp.wms.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyListView<T extends MyListRecord> extends ListView {

    private Context mContext;
    private MyListAdapter<T> mAdapter = null;
    private List<T> mRecords;
    private int mSelectedPosition = -1;
    private int mChoiceMode = 0;

    private int mPageCurrent;
    private int mPageTotal;
    private int mRecordTotal;

    private LoadListListener mLoadListListener;

    public final static int CHOICE_MODE_SINGLE = 0;
    public final static int CHOICE_MODE_DOUBLE = 1;

    public MyListView(Context context) {
        super(context);
        mContext = context;

        mRecords = new ArrayList<T>();

        //this.setChoiceMode(ListView.CHOICE_MODE_NONE);
        this.setScrollBarStyle(ListView.SCROLLBARS_OUTSIDE_OVERLAY);
        this.setPadding(
                (int) getResources().getDimension(R.dimen.my_listview_padding_left),
                (int) getResources().getDimension(R.dimen.my_listview_padding_top),
                (int) getResources().getDimension(R.dimen.my_listview_padding_right),
                (int) getResources().getDimension(R.dimen.my_listview_padding_bottom)
        );
        this.setClipToPadding(false);

        setPageCurrent(1);

        mLoadListListener = null;
        this.setOnScrollListener(new AbsListView.OnScrollListener() {
            private int preLast;

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                final int lastItem = firstVisibleItem + visibleItemCount;
                if (lastItem == totalItemCount) {
                    if (preLast != lastItem && getPageTotal() > getPageCurrent()) {
                        if (mLoadListListener != null)
                            mLoadListListener.onLoadMore(getPageCurrent() + 1);
                        preLast = lastItem;
                    }
                }
            }
        });
    }

    public MyListView(Context context, T[] records) {
        this(context);
        setRecords(records);
    }

    public void setMyChoiceMode(int choiceMode) {
        mChoiceMode = choiceMode;
    }

    public int getMyChoiceMode() {
        return mChoiceMode;
    }

    public void setRecords(T[] records) {
        mRecords = new ArrayList<T>();
        mRecords.addAll(Arrays.asList(records));

        mAdapter = new MyListAdapter<>(this, recordToArray(mRecords));
        this.setAdapter(mAdapter);
    }

    public void addRecords(T[] records) {
        //mRecords.addAll(Arrays.asList(records));

        boolean is_record_found_new = false;
        for (T record_new : records) {
            boolean is_record_exist = false;

            MyListIModel recordData = record_new.getData();
            if (recordData != null) {
                for (T record_exist : mRecords) {
                    if (recordData.equals(record_exist.getData())) {
                        is_record_exist = true;
                        break;
                    }
                }
            }

            if (!is_record_exist) {
                mRecords.add(record_new);
                is_record_found_new = true;
            }
        }

        if (!is_record_found_new)
            return;

        if (mAdapter == null) {
            mAdapter = new MyListAdapter<>(this, recordToArray(mRecords));
            this.setAdapter(mAdapter);
        } else {
            mAdapter.setRecords(recordToArray(mRecords));
        }

        mAdapter.notifyDataSetChanged();
    }

    public void addRecord(T record) {
        boolean is_record_exist = false;
        for (T record_exist : mRecords) {
            if (record.getData().equals(record_exist.getData())) {
                is_record_exist = true;
                break;
            }
        }

        if (is_record_exist)
            return;

        mRecords.add(record);

        if (mAdapter == null) {
            mAdapter = new MyListAdapter<>(this, recordToArray(mRecords));
            this.setAdapter(mAdapter);
        } else {
            mAdapter.setRecords(recordToArray(mRecords));
        }

        mAdapter.notifyDataSetChanged();
    }

    public void removeRecord(T record) {
        if (mAdapter == null)
            return;

        mRecords.remove(record);

        if (mLoadListListener != null && this.getPageTotal() > this.getPageCurrent()) {
            mLoadListListener.onReloadAfterRemove(this.getPageCurrent());
        } else {
            mAdapter.setRecords(recordToArray(mRecords));
            mAdapter.notifyDataSetChanged();
        }
    }

    public void removeRecords(T[] records) {
        if (mAdapter == null)
            return;

        for (T record : records) {
            mRecords.remove(record);
        }

        if (mLoadListListener != null && this.getPageTotal() > this.getPageCurrent()) {
            mLoadListListener.onReloadAfterRemove(this.getPageCurrent());
        } else {
            mAdapter.setRecords(recordToArray(mRecords));
            mAdapter.notifyDataSetChanged();
        }
    }

    public T[] getRecords() {
        if (mAdapter != null)
            return mAdapter.getRecords();
        else
            return null;
    }

    public T getRecordByPosition(int position) {
        T record = null;
        T[] records = getRecords();
        if (records != null)
            record = records[position];
        return record;
    }

    public void setPageCurrent(int pageCurrent) {
        mPageCurrent = pageCurrent;
    }

    public int getPageCurrent() {
        return mPageCurrent;
    }

    public void setPageTotal(int pageTotal) {
        mPageTotal = pageTotal;
    }

    public int getPageTotal() {
        return mPageTotal;
    }

    public void setRecordTotal(int recordTotal) {
        mRecordTotal = recordTotal;
    }

    public int getRecordTotal() {
        return mRecordTotal;
    }

    public void setSelected(int position) {
        if (mSelectedPosition > -1) {
            View selectedBefore = this.getViewByPosition(mSelectedPosition);
            setUnSelectedView(selectedBefore);
        }

        mSelectedPosition = position;

        View selected = this.getViewByPosition(position);
        setSelectedView(selected);

        if (mAdapter != null)
            mAdapter.setSelected(mSelectedPosition);
    }

    public int getSelected() {
        return mSelectedPosition;
    }

    public void setUnSelected() {
        View selected = this.getChildAt(mSelectedPosition);
        setUnSelectedView(selected);

        mSelectedPosition = -1;

        if (mAdapter != null)
            mAdapter.setSelected(mSelectedPosition);
    }

    public void setSelectedView(View selected) {
        if (selected == null)
            return;

        int padding_left = selected.getPaddingLeft();
        int padding_top = selected.getPaddingTop();
        int padding_right = selected.getPaddingRight();
        int padding_bottom = selected.getPaddingBottom();

        Drawable drawableSelected = getResources().getDrawable(R.drawable.abs__list_pressed_holo_light);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            selected.setBackgroundDrawable(drawableSelected);
        } else {
            selected.setBackground(drawableSelected);
        }

        selected.setPadding(padding_left, padding_top, padding_right, padding_bottom);
    }

    public void setUnSelectedView(View selected) {
        if (selected == null)
            return;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            selected.setBackgroundDrawable(null);
        } else {
            selected.setBackground(null);
        }
    }

    public View getViewByPosition(int position) {
        final int firstListItemPosition = this.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + this.getChildCount() - 1;

        if (position < firstListItemPosition || position > lastListItemPosition ) {
            return this.getAdapter().getView(position, null, this);
        } else {
            final int childIndex = position - firstListItemPosition;
            return this.getChildAt(childIndex);
        }
    }

    public void setOnLoadList(LoadListListener loadListListener) {
        mLoadListListener = loadListListener;
    }

    public interface LoadListListener {
        void onReloadAfterRemove(int page);
        void onLoadMore(int page);
    }

    private T[] recordToArray(final List<T> obj) {
        if (obj == null || obj.isEmpty())
            return null;

        final T t = obj.get(0);

        @SuppressWarnings("unchecked")
        final T[] res = (T[]) Array.newInstance(t.getClass(), obj.size());

        for (int i = 0; i < obj.size(); i++) {
            res[i] = obj.get(i);
        }

        return res;
    }
}
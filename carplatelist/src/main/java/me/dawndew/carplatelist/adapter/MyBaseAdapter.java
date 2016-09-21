package me.dawndew.carplatelist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 2016/9/21.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData = new ArrayList();
    protected LayoutInflater mInflater;

    public MyBaseAdapter(Context paramContext) {
        this.mInflater = ((LayoutInflater) paramContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        this.mContext = paramContext;
    }
    public void addAllData(List<T> paramList) {
        if (paramList!=null)
            this.mData.addAll(paramList);
    }
    public void addAllDataAndNorify(List<T> paramList) {
        addAllData(paramList);
        notifyDataSetChanged();
    }

    public void clearData() {
        this.mData.clear();
    }

    public int getCount() {
        return this.mData.size();
    }

    public List<T> getData() {
        return this.mData;
    }

    public T getItem(int paramInt) {
        return this.mData.get(paramInt);
    }

    public long getItemId(int paramInt) {
        return 0L;
    }

}

package me.dawndew.carplatelist.bean;

import android.util.SparseArray;

import java.util.List;

/**
 * Created by dawndew on 2016/9/21.
 */
public class CarDataBean {
    public String name;
    public String brand_id;
    public String url;
    public boolean isIndex=false;
    public List<CarModelBean> models;

    @Override
    public String toString() {
        return "data: {name:" + name + ", brand_id:"
                + brand_id + ", models:" + models + "}";
    }
}

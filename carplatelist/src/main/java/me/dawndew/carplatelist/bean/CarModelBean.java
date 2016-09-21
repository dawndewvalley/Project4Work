package me.dawndew.carplatelist.bean;

import android.util.SparseArray;

import java.util.List;

/**
 * Created by dawndew on 2016/9/21.
 */
public class CarModelBean {
    public String name;
    public String model_id;

    public List<CarStyleBean> styles;

    @Override
    public String toString() {
        return "models : {name:" + name + ", model_id:" + model_id
                + ", styles: " + styles + "}";
    }
}

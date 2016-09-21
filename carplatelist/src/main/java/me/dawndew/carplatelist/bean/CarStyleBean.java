package me.dawndew.carplatelist.bean;

/**
 * Created by dawndew on 2016/9/21.
 */
public class CarStyleBean {
    public String name;
    public String style_id;

    @Override
    public String toString() {
        return "styles : {name:" + name + ", style_id:" + style_id + "}";
    }
}

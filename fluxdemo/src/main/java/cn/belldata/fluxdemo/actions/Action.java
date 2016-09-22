package cn.belldata.fluxdemo.actions;

/**
 * Created by android on 2016/9/22.
 */
public class Action<T> {



    private final String type;
    private final T data;



    Action(String type,T data){
        this.data=data;
        this.type=type;
    }


    public String getType() {
        return type;
    }

    public T getData() {
        return data;
    }
}

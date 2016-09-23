package cn.belldata.fluxdemo.flux.actions;

/**
 * Created by android on 2016/9/22.
 */
public class Action<T> {

    private final int type;
    private final T data;

    Action(int type,T data){
        this.data=data;
        this.type=type;
    }

    public int getType() {
        return type;
    }

    public T getData() {
        return data;
    }
}

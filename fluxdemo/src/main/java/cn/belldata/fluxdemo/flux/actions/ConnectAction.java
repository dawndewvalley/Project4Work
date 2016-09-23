package cn.belldata.fluxdemo.flux.actions;

/**
 * Created by android on 2016/9/22.
 */
public class ConnectAction<T> extends Action<T> {


    public ConnectAction(int type, T data) {
        super(type, data);
    }
}

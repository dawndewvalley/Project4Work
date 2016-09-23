package cn.belldata.fluxdemo.flux.actions;

/**
 * Created by android on 2016/9/22.
 */
public class LoginAction<T> extends ConnectAction<T> {


    public LoginAction(int type, T data) {
        super(type, data);
    }

}

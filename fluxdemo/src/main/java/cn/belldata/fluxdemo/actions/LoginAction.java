package cn.belldata.fluxdemo.actions;

import cn.belldata.fluxdemo.model.User;

/**
 * Created by android on 2016/9/22.
 */
public class LoginAction<T> extends ConnectAction<T> {
    public static final String ACTION_LOGIN_ACCOUNT_INVALID = "ACCOUNT INVALID";
    public static final String ACTION_LOGIN_PWD_INVALID = "PWD INVALID";
    public static final String ACTION_LOGIN_PROTOCOL_UNCHECK = "PROTOCOL UNCHECK";

    public LoginAction(String type, T data) {
        super(type, data);
    }

}

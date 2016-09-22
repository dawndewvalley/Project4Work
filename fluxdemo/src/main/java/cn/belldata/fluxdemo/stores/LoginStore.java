package cn.belldata.fluxdemo.stores;

import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import cn.belldata.fluxdemo.actions.Action;
import cn.belldata.fluxdemo.actions.LoginAction;
import cn.belldata.fluxdemo.model.User;

/**
 * Created by android on 2016/9/22.
 */
public class LoginStore extends Store {


    //发送登录事件
    void emitAccountError(){getBus().post(new AccountErrorEvent());}
    void emitPwdError(){getBus().post(new PwdErrorEvent());}
    void emitUnCheck(){getBus().post(new UnCheckEvent());}



    @Override
    @Subscribe
    public void onAction(Action action) {
        Log.e("login action", "onAction: "+action.getType());
    String type=action.getType();
        switch (type){
            case LoginAction.ACTION_LOGIN_ACCOUNT_INVALID:
                emitAccountError();
                break;
            case LoginAction.ACTION_LOGIN_PWD_INVALID:
                emitPwdError();
                break;
            case LoginAction.ACTION_LOGIN_PROTOCOL_UNCHECK:
                emitUnCheck();
                break;
            default:
                break;
        }

    }

    //登录事件
     public class AccountErrorEvent{}
    public  class PwdErrorEvent{}
    public class UnCheckEvent{}
}

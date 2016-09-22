package cn.belldata.fluxdemo.dispatcher;

import android.util.Log;

import cn.belldata.fluxdemo.actions.ConnectAction;
import cn.belldata.fluxdemo.actions.LoginAction;
import cn.belldata.fluxdemo.actions.MessageAction;
import cn.belldata.fluxdemo.model.User;
import cn.belldata.fluxdemo.utils.EditTextUtils;

/**
 * Created by android on 2016/9/22.
 */
public class ActionsCreator {
    private static ActionsCreator instance;
    final Dispatcher dispatcher;
    ActionsCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionsCreator get(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new ActionsCreator(dispatcher);
        }
        return instance;
    }

    public void sendMessage(String message) {
        dispatcher.dispatch(new MessageAction(MessageAction.ACTION_NEW_MESSAGE, message));
    }

    //联网事件
    public void sendConnectStart(){
        dispatcher.dispatch(new ConnectAction(ConnectAction.ATION_CONNECT_START,""));
    }
    public void sendConnectFail(String message){
        dispatcher.dispatch(new ConnectAction(ConnectAction.ATION_CONNECT_FAIL,message));
    }
    public void sendConnectInvalid(){
        dispatcher.dispatch(new ConnectAction(ConnectAction.ATION_CONNECT_INVALID,""));
    }
    public void sendConnectEorror(){
        dispatcher.dispatch(new ConnectAction(ConnectAction.ATION_CONNECT_ERROR,""));
    }

    //登录判断
    public void sendUsertoCheck(User user){
      //  sendConnectStart();
        if(!EditTextUtils.isMobileNum(user.account)){
            dispatcher.dispatch(new LoginAction(LoginAction.ACTION_LOGIN_ACCOUNT_INVALID,""));
            return;
        }else if(EditTextUtils.isContainSymbol(user.pwd)||user.pwd.length()<6||user.pwd.length()>18){
            dispatcher.dispatch(new LoginAction(LoginAction.ACTION_LOGIN_PWD_INVALID,""));
            return;
        }else if(!user.isCheck){
            dispatcher.dispatch(new LoginAction(LoginAction.ACTION_LOGIN_PROTOCOL_UNCHECK,""));
            return;
        }else {
            login(user);
        }
    }

    private void login(User user) {
        //登录
        Log.i("login","login");
    }
}

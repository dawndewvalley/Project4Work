package cn.belldata.fluxdemo.flux.stores;

import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import cn.belldata.fluxdemo.flux.actions.Action;
import cn.belldata.fluxdemo.flux.actions.ActionType;
import cn.belldata.fluxdemo.flux.actions.LoginAction;
import cn.belldata.fluxdemo.model.User;

/**
 * Created by android on 2016/9/22.
 */
public class LoginStore extends Store {

    private User usr;
    private StoreChangeEvent event;

    @Override
    public StoreChangeEvent changeEvent() {
        return event;
    }

    public User getUsr(){return usr;}

    @Override
    @Subscribe
    public void onAction(Action action) {

        int type=action.getType();
        event=new StoreChangeEvent();
        event.type=type;
        if(action.getType()==ActionType.ACTION_CONNECT_SUCCESS){
            usr= (User) action.getData();
        }
        emitStoreChange();
    }
}

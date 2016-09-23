package cn.belldata.fluxdemo.flux.stores;

import org.greenrobot.eventbus.Subscribe;

import cn.belldata.fluxdemo.flux.actions.Action;
import cn.belldata.fluxdemo.flux.actions.ActionType;
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
        switch (type){
            case ActionType.ACTION_CONNECT_SUCCESS:
                usr= (User) action.getData();
                break;
            case ActionType.ACTION_CONNECT_FAIL:
                tip_fail=(String)action.getData();
                break;
        }
        emitStoreChange();
    }
}

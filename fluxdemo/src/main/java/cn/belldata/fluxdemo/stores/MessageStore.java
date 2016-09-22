package cn.belldata.fluxdemo.stores;

import org.greenrobot.eventbus.Subscribe;

import cn.belldata.fluxdemo.actions.Action;
import cn.belldata.fluxdemo.actions.MessageAction;
import cn.belldata.fluxdemo.model.Message;

/**
 * Created by android on 2016/9/22.
 */
public class MessageStore extends Store {


    private Message mMessage = new Message();

    public MessageStore() {
        super();
    }

    public String getMessage() {
        return mMessage.getMessage();
    }

    void emitStoreChange() {
        getBus().post(changeEvent());
    }

    public StoreChangeEvent changeEvent() {
        return new StoreChangeEvent();
    }

    @Override
    @Subscribe
    public void onAction(Action action) {
        switch (action.getType()) {
            case MessageAction.ACTION_NEW_MESSAGE:
                mMessage.setMessage((String) action.getData());
                break;
            default:
        }
        emitStoreChange();
    }

    public class StoreChangeEvent {
    }
}

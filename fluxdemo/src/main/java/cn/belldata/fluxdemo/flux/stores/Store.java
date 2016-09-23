package cn.belldata.fluxdemo.flux.stores;

import org.greenrobot.eventbus.EventBus;

import cn.belldata.fluxdemo.flux.actions.Action;

/**
 * Created by android on 2016/9/22.
 */
public  abstract class Store {
    private EventBus bus=EventBus.getDefault();

    protected Store(){}
    public void register(final Object view){
        this.bus.register(view);
    }
    public void unregister(final Object view){
        this.bus.unregister(view);
    }
    void emitStoreChange() {
        this.bus.post(changeEvent());
    }
    public abstract StoreChangeEvent changeEvent();

    public abstract void onAction(Action action);
    public class   StoreChangeEvent{
        public int type;
    }

}

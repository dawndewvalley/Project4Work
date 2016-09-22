package cn.belldata.fluxdemo.stores;

import org.greenrobot.eventbus.EventBus;

import cn.belldata.fluxdemo.actions.Action;

/**
 * Created by android on 2016/9/22.
 */
public  abstract class Store {
    private EventBus bus=EventBus.getDefault();
    protected Store(){}
    public EventBus getBus(){return bus;}
    public void register(final Object view){
        this.bus.register(view);
    }
    public void unregister(final Object view){
        this.bus.unregister(view);
    }

    public abstract void onAction(Action action);





}

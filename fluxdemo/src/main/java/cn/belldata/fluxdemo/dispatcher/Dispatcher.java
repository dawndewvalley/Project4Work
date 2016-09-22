package cn.belldata.fluxdemo.dispatcher;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.belldata.fluxdemo.actions.Action;
import cn.belldata.fluxdemo.stores.Store;

/**
 * Created by android on 2016/9/22.
 */
public class Dispatcher {
    private static Dispatcher instance;
    private final List<Store> stores=new ArrayList<>();

    public static Dispatcher get(){
        if(instance==null){
            instance=new Dispatcher();
        }
        return  instance;
    }
    Dispatcher(){}

    public void register(final Store store){
        stores.add(store);
    }
    public void unregister(final Store store){
        stores.remove(store);
    }
    public void dispatch(Action action){
        post(action);
    }
    private  void post(final Action action){
        Log.i("action",action.getType());
        for(Store store:stores){
            store.onAction(action);
        }
    }
}

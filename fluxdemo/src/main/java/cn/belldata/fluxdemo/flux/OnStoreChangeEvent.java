package cn.belldata.fluxdemo.flux;

import cn.belldata.fluxdemo.flux.actions.Action;
import cn.belldata.fluxdemo.flux.stores.Store;

/**
 * Created by android on 2016/9/23.
 */
public interface OnStoreChangeEvent {
   void  onEvent(Store.StoreChangeEvent event);
}

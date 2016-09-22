package cn.belldata.fluxdemo.stores;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import cn.belldata.fluxdemo.actions.Action;
import cn.belldata.fluxdemo.actions.ConnectAction;

/**
 * Created by android on 2016/9/22.
 */
public abstract class ConnectStore<T> extends Store {
    private T obj;

    public ConnectStore(){
        super();
    }

    public T getObject(){
        return obj;
    }

    @Override
    @Subscribe
    public void onAction(Action action) {

      String type=action.getType();
      switch (type){
          case ConnectAction.ATION_CONNECT_START:
              emitConnectStart();
              break;
          case ConnectAction.ATION_CONNECT_INVALID:
              emitConnectInavlid();
              break;
          case ConnectAction.ATION_CONNECT_FAIL:
              emitCoonectFail((String) action.getData());
              break;
          case ConnectAction.ATION_CONNECT_ERROR:
              emitConnectError();
              break;
          default:
              Log.e("connect action", "onAction: "+action.getType());
              initData(action);
              break;
      }
        
    }

    protected abstract void initData(Action action);

    void emitConnectStart(){getBus().post(new ConnectStartEvent());}
    void emitConnectInavlid(){getBus().post(new ConnectInvalidEvnet());}
    void emitCoonectFail(String msg){
        ConnectFailEvent event= new ConnectFailEvent();
        event.msg=msg;
        getBus().post(event);
    }
    void emitConnectError(){getBus().post(new ConnectErrorEvent());}

    public class ConnectStartEvent{}
    public class ConnectFailEvent{
        public   String msg;

    }
    public class ConnectInvalidEvnet{}
    public class ConnectErrorEvent{}
}

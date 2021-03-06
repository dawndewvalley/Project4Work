package cn.belldata.fluxdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import cn.belldata.fluxdemo.flux.OnStoreChangeEvent;
import cn.belldata.fluxdemo.flux.actions.ActionType;
import cn.belldata.fluxdemo.flux.dispatcher.ActionsCreator;
import cn.belldata.fluxdemo.flux.dispatcher.Dispatcher;
import cn.belldata.fluxdemo.flux.stores.Store;
import cn.belldata.fluxdemo.utils.ToastUtils;

/**
 * Created by android on 2016/9/22.
 */
public abstract class  BaseActivity extends AppCompatActivity implements OnStoreChangeEvent {
    private ProgressDialog loadingdialog;
    protected Dispatcher dispatcher;
    protected ActionsCreator actionsCreator;
    protected Store store;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
        initDependencies();
    }


    private void initDependencies() {
        dispatcher=Dispatcher.get();
        actionsCreator=ActionsCreator.get(dispatcher);
        store=initStore();
        if(store!=null) {
            dispatcher.register(store);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        store.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        store.unregister(this);
    }

    protected abstract Store initStore();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(store!=null) {
            dispatcher.unregister(store);
        }
    }

    @Subscribe
    public void onEventChange(Store.StoreChangeEvent event){

        int type=event.type;
        switch (type){
            case ActionType.ACTION_CONNECT_START:
                //显示加载框
                onLoading();
                break;
            case ActionType.ACTION_CONNECT_FAIL:
                //提示网络请求失败
                String tip_fail=store.getTip_fail();
                ToastUtils.show(this,tip_fail);
                break;
            case ActionType.ACTION_CONNECT_INVALID:
                //TODO:用户已在其他地方上线，让用户强退
                break;
            case ActionType.ACTION_CONNECT_ERROR:
                //TODO:网络不可用
            default:
                onEvent(event);// 其他具体的事件处理
                break;
        }
    }

    protected void onLoading() {
        if (loadingdialog == null) {
            loadingdialog = new ProgressDialog(this);
        }
        if (!this.isFinishing()) {
            loadingdialog.show();
        }
    }

    protected void disLoading(){
        if (loadingdialog!=null&&loadingdialog.isShowing()){
            loadingdialog.dismiss();
            loadingdialog=null;
        }
    }


}

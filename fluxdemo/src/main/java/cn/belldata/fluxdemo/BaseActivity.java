package cn.belldata.fluxdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.Subscribe;

import cn.belldata.fluxdemo.dispatcher.ActionsCreator;
import cn.belldata.fluxdemo.dispatcher.Dispatcher;
import cn.belldata.fluxdemo.stores.ConnectStore;
import cn.belldata.fluxdemo.stores.Store;
import cn.belldata.fluxdemo.utils.ToastUtils;

/**
 * Created by android on 2016/9/22.
 */
public class BaseActivity extends AppCompatActivity {
    private ProgressDialog loadingdialog;




    @Override
    protected void onStop() {
        super.onStop();
        disLoading();
    }


    @Subscribe
    public void onConnectStartEvent(ConnectStore.ConnectStartEvent event){
        onLoading();
    }

    @Subscribe
    public void onConnectFailEvent(ConnectStore.ConnectFailEvent event){
        disLoading();
        ToastUtils.show(this,event.msg);
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
        }
    }
}

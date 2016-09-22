package cn.belldata.fluxdemo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.belldata.fluxdemo.dispatcher.ActionsCreator;
import cn.belldata.fluxdemo.dispatcher.Dispatcher;
import cn.belldata.fluxdemo.model.User;
import cn.belldata.fluxdemo.stores.LoginStore;
import cn.belldata.fluxdemo.stores.Store;
import cn.belldata.fluxdemo.utils.ToastUtils;

/**
 * Created by android on 2016/9/22.
 */
public class LoginActivity extends BaseActivity {
    @InjectView(R.id.et_account)
    EditText etAccount;
    @InjectView(R.id.et_pwd)
    EditText etPwd;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.cb_login)
    CheckBox cbLogin;

    private Dispatcher dispatcher;
    private ActionsCreator actionsCreator;
    private LoginStore store;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);
        initDependencies();
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

    private void initDependencies() {
        dispatcher = Dispatcher.get();
        actionsCreator = ActionsCreator.get(dispatcher);
        store = new LoginStore();
        dispatcher.register(store);
    }

    @OnClick({R.id.btn_login})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_login:
                toLogin();
                break;
            default:
                break;
        }
    }

    private void toLogin() {
        User usr = new User();
        usr.account = etAccount.getText().toString().trim();
        usr.pwd = etPwd.getText().toString().trim();
        usr.isCheck=cbLogin.isChecked();
        actionsCreator.sendUsertoCheck(usr);
    }

    @Subscribe
    public void onAccountError(LoginStore.AccountErrorEvent event){
        etAccount.setError(getString(R.string.tip_account_error));
    }

    @Subscribe
    public void onPwdError(LoginStore.PwdErrorEvent event){
    etPwd.setError(getString(R.string.tip_pwd_error));
    }

    @Subscribe
    public void onUnCheck(LoginStore.UnCheckEvent event){
        ToastUtils.show(this,getString(R.string.tip_protocol_error));
    }
}

package cn.belldata.fluxdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.belldata.fluxdemo.flux.actions.ActionType;
import cn.belldata.fluxdemo.flux.dispatcher.ActionsCreator;
import cn.belldata.fluxdemo.flux.dispatcher.Dispatcher;
import cn.belldata.fluxdemo.flux.stores.Store;
import cn.belldata.fluxdemo.model.User;
import cn.belldata.fluxdemo.flux.stores.LoginStore;
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



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    protected Store initStore() {
        return new LoginStore();
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


    @Override
    public void onEvent(Store.StoreChangeEvent event) {

        int type=event.type;
        switch (type){
            case ActionType.ACTION_LOGIN_ACCOUNT_INVALID:
                etAccount.setError(getString(R.string.tip_account_error));
                break;
            case ActionType.ACTION_LOGIN_PROTOCOL_UNCHECK:
                ToastUtils.show(this,getString(R.string.tip_protocol_error));
                break;
            case ActionType.ACTION_LOGIN_PWD_INVALID:
                etPwd.setError(getString(R.string.tip_pwd_error));
                break;
            default:
        }
    }
}

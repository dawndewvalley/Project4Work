package cn.belldata.fluxdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.belldata.fluxdemo.dispatcher.ActionsCreator;
import cn.belldata.fluxdemo.dispatcher.Dispatcher;
import cn.belldata.fluxdemo.stores.MessageStore;
import cn.belldata.fluxdemo.stores.Store;

public class MainActivity extends BaseActivity {


    @InjectView(R.id.tv_mesg)
    TextView tvMesg;
    @InjectView(R.id.et_msg)
    EditText etMsg;
    @InjectView(R.id.btn_msg)
    Button btnMsg;


    private Dispatcher dispatcher;
    private ActionsCreator actionsCreator;
    private MessageStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initDependencies();
        setupView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dispatcher.unregister(store);
    }
    private void initDependencies() {
        dispatcher = Dispatcher.get();
        actionsCreator = ActionsCreator.get(dispatcher);
        store = new MessageStore();
        dispatcher.register(store);
    }
    private void setupView() {
        btnMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if(etMsg.getText()!=null){
                    actionsCreator.sendMessage(etMsg.getText().toString());
                    etMsg.setText("");
                }*/
                Intent in=new Intent(MainActivity.this,LoginActivity.class);
                MainActivity.this.startActivity(in);

            }
        });
    }
    private void render(MessageStore store){
        tvMesg.setText(store.getMessage());
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
    @Subscribe
    public void onStoreChange(MessageStore.StoreChangeEvent event){
        render(store);
    }
}

package me.dawndew.carplatelist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import me.dawndew.carplatelist.Util.Loger;

public class MainActivity extends AppCompatActivity {
    public static final String TAG=MainActivity.class.getName();
    FragmentManager fm;

    public String[] car_info_name=new String[3];
    public String[] car_info_id=new String[3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       fm=getSupportFragmentManager();
        CarPlateEditFragment fragment=new CarPlateEditFragment();
        fm.beginTransaction().add(R.id.layout_fragment,fragment).commitAllowingStateLoss();
    }

    public void switchView(Fragment fragment){
        Loger.i( TAG,"switchView: ");
        FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.layout_fragment, fragment).addToBackStack(null)
                .commitAllowingStateLoss();
    }
}

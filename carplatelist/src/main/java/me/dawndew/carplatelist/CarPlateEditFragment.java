package me.dawndew.carplatelist;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.dawndew.carplatelist.Util.Loger;

/**
 * Created by android on 2016/9/21.
 */
public class CarPlateEditFragment extends Fragment {
public static final String TAG=CarPlateEditFragment.class.getName();
    MainActivity parent;
    TextView tv_car_edit;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.parent= (MainActivity) activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(parent.car_info_name[2]!=null){
            tv_car_edit.setText(parent.car_info_name[0]+", "+parent.car_info_name[1]+", "+parent.car_info_name[2]);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_carplate_edit,null);
         tv_car_edit= (TextView) view.findViewById(R.id.tv_car_edit);
        tv_car_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loger.i(TAG, "onClick: click");
                parent.switchView(new CarListFragment());
            }
        });

        return view;
    }
}

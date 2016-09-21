package me.dawndew.carplatelist;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.List;

import de.halfbit.pinnedsection.PinnedSectionListView;
import me.dawndew.carplatelist.adapter.CarDataAdapter;
import me.dawndew.carplatelist.adapter.CarModelAdapter;
import me.dawndew.carplatelist.adapter.CarStyleAdapter;
import me.dawndew.carplatelist.bean.CarModelBean;
import me.dawndew.carplatelist.bean.CarStyleBean;

/**
 * Created by android on 2016/9/21.
 */
public class CarListFragment extends Fragment {
    private MainActivity parent_activity;

    private View view_root;
    private PinnedSectionListView lv;
    private CarDataAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.parent_activity= (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_carlist,null);
        view_root=view.findViewById(R.id.view_carlist_root);
        lv= (PinnedSectionListView) view.findViewById(R.id.lv_carplate);
        adapter=new CarDataAdapter(parent_activity);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CarDataAdapter.Item item=adapter.getItem(position);
                parent_activity.car_info_name[0]=item.toString();
                showPopView(item.bean.models);
            }
        });
        return view;
    }
    ViewHelper vh;
    View layout_pop;
    PopupWindow popupWindow;
    CarModelAdapter adapter_model;
    CarStyleAdapter adapter_style;
    boolean flag=true;

    private void showPopView(List<CarModelBean> data){

        if(data!=null&&data.size()>0){
            if(layout_pop==null) {
                layout_pop = LayoutInflater.from(parent_activity).inflate(R.layout.layout_lv_carmodel, null);
                vh = new ViewHelper();
                layout_pop.setTag(vh);
                popupWindow = new PopupWindow(layout_pop, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, false);
            }
                vh= (ViewHelper) layout_pop.getTag();
                vh.lv= (ListView) layout_pop.findViewById(R.id.lv_carmodel);


            if(adapter_model!=null){
                adapter_model.clearData();
            }
            adapter_model=new CarModelAdapter(parent_activity);
            adapter_model.addAllData(data);
            vh.lv.setAdapter(adapter_model);
            adapter_model.notifyDataSetChanged();

            vh.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(!flag){
                        CarStyleBean styleBean=adapter_style.getItem(position);
                        flag=true;
                        parent_activity.car_info_name[2]=styleBean.name;
                        popupWindow.dismiss();
                        parent_activity.switchView(new CarPlateEditFragment());
                    }else{
                        if(adapter_style!=null){
                            adapter_style.clearData();
                        }
                        CarModelBean model=adapter_model.getItem(position);
                        adapter_style=new CarStyleAdapter(parent_activity);
                        adapter_style.addAllData(model.styles);
                        adapter_style.notifyDataSetChanged();
                        parent_activity.car_info_name[1]=model.name;
                        vh.lv.setAdapter(adapter_style);
                        flag=false;

                    }
                }
            });

            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    flag=false;
                }
            });

            layout_pop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    layout_pop.clearAnimation();
                }
            });

            popupWindow.setContentView(layout_pop);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(false);
            popupWindow.setFocusable(true);
            popupWindow.setAnimationStyle(R.style.PopupWindowAnimRight);
            popupWindow.showAsDropDown(this.view_root,0,0);

        }
    }
    class ViewHelper{
        ListView lv;
    }
}

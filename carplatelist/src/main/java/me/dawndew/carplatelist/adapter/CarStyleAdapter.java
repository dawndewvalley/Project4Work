package me.dawndew.carplatelist.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.dawndew.carplatelist.R;
import me.dawndew.carplatelist.bean.CarModelBean;
import me.dawndew.carplatelist.bean.CarStyleBean;

/**
 * Created by android on 2016/9/21.
 */
public class CarStyleAdapter extends MyBaseAdapter<CarStyleBean> {
    public CarStyleAdapter(Context paramContext) {
        super(paramContext);
    }

    class ViewHolder{
        TextView tv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            vh=new ViewHolder();
            convertView=View.inflate(mContext, R.layout.item_lv_carplate,null);
            vh.tv= (TextView) convertView.findViewById(R.id.tv_item_lv_carplate);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolder) convertView.getTag();
        }
        vh.tv.setText(getData().get(position).name);
        return convertView;
    }
}

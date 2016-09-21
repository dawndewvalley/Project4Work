package me.dawndew.carplatelist.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.halfbit.pinnedsection.PinnedSectionListView;
import me.dawndew.carplatelist.R;
import me.dawndew.carplatelist.Util.Loger;
import me.dawndew.carplatelist.Util.Utils;
import me.dawndew.carplatelist.bean.CarDataBean;

/**
 * Created by android on 2016/9/21.
 */
public class CarDataAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter,SectionIndexer {

    public static final String TAG=CarDataAdapter.class.getName();

    private Context context;
    private List<CarDataBean> datas;
    private List<Item> items;
    private List<Item> items_section;
    private Item[] sections;


    public CarDataAdapter(Context context){
        this.context=context;
        initData();
    }

    /**读取json文件，生成datas 数据集合；
     *
     */
    private void initData(){
        String json= Utils.getStringFromAssets(context, "carlist.json");
        Gson gson=new Gson();

        datas=new ArrayList<>();
        try {
            JSONObject jb=new JSONObject(json);

            for(char i='A';i<='Z';i++){

                CarDataBean indexBean=new CarDataBean();
                indexBean.isIndex=true;
                indexBean.name=i+"";
                String jb2=jb.optString( indexBean.name);

                List<CarDataBean> bean=gson.fromJson(jb2,new TypeToken<List<CarDataBean>>(){}.getType());
                if(bean!=null && bean.size()>0){
                    datas.add(indexBean);
                    datas.addAll(bean);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        initSection();
    }

    /**
     * 将datas中的数据，转换成PinnedListView中的数据模型Item
     */
    private void initSection(){
        items=new ArrayList<>();
        items_section=new ArrayList<>();
        int sectionPosition=0, listPosition=0;
        for (int i=0;i<datas.size();i++){
            CarDataBean bean=datas.get(i);
            Item item_data;
            if(bean.isIndex){
                item_data=new Item(Item.SECTION,bean);
                item_data.sectionPosition=sectionPosition;
                item_data.listPosition=listPosition;
                items_section.add(item_data);
                sectionPosition++;
            }else{
                item_data=new Item(Item.ITEM,bean);
                item_data.sectionPosition=sectionPosition-1;
                item_data.listPosition=listPosition;
            }
            items.add(item_data);
            listPosition++;
        }

        sections=new Item[items_section.size()];
        for(int i=0;i<sections.length;i++){
            sections[i]=items_section.get(i);
        }

    }


    /**
     * PinnedListView的数据模型Item,其实就是用来区分数据是属于标题，还是内容
     */
   public class Item{
        public static  final int ITEM=0;
        public static final int SECTION=1;

        public   int type;
        public  CarDataBean bean;

        public int sectionPosition;
        public int listPosition;

        public Item(int type,CarDataBean bean){
            this.bean=bean;
            this.type=type;
        }

        @Override
        public String toString(){
            return bean.name;
        }
    }



class ViewHolder{
    TextView tv;
    ImageView iv;
}

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Item getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        return getItem(position).type;
    }

    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 2;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView: "+position);
        ViewHolder vh;
        Item item=items.get(position);
        if(convertView==null){
            vh=new ViewHolder();
            convertView=View.inflate(context, R.layout.item_lv_carplate,null);
            vh.tv= (TextView) convertView.findViewById(R.id.tv_item_lv_carplate);
            vh.iv= (ImageView) convertView.findViewById(R.id.iv_item_lv_carplate);
            convertView.setTag(vh);
        }else{
            vh= (ViewHolder) convertView.getTag();
        }
        if(item.type!=Item.SECTION){
            vh.iv.setVisibility(View.VISIBLE);
            //将车标图片设置给Item
        }else{
            vh.iv.setVisibility(View.INVISIBLE);
            convertView.setBackgroundColor(Color.parseColor("#72C8FF80"));
        }

        vh.tv.setText(item.toString());
        Loger.i(TAG, "getView: "+ vh.tv.getText());
        return convertView;
    }


    @Override
    public boolean isItemViewTypePinned(int viewType) {
        return viewType==Item.SECTION;
    }



    @Override
    public Item[] getSections() {
        return sections;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        if(sectionIndex>=sections.length){
            sectionIndex=sections.length-1;
        }
        return sections[sectionIndex].listPosition;
    }

    @Override
    public int getSectionForPosition(int position) {
        if (position >= getCount()) {
            position = getCount() - 1;
        }
        return getItem(position).sectionPosition;
    }
}

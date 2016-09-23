package cn.belldata.fluxdemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by android on 2016/9/22.
 */
public class ToastUtils {
 private  static  Toast toast;

    public static void show(Context ctx,String msg){
        if(toast==null){
            toast=Toast.makeText(ctx,msg,Toast.LENGTH_SHORT);
        }else {
            toast.setText(msg);
        }
        toast.show();
    }
}

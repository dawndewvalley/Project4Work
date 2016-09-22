package cn.belldata.fluxdemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by android on 2016/9/22.
 */
public class ToastUtils {
   static  Toast toast;

    public static void show(Context ctx,String msg){
        if(toast==null){
            toast=new Toast(ctx);
        }
        toast.setText(msg);
        toast.show();
    }
}

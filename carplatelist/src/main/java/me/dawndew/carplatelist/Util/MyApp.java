package me.dawndew.carplatelist.Util;

import android.app.Application;

/**
 * Created by android on 2016/9/21.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Loger.init(true);
    }
}

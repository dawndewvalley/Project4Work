package me.dawndew.carplatelist.Util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by android on 2016/9/21.
 */
public class Utils {

    //从asset中读取文件
    public static String getStringFromAssets(Context context, String fileName){
        String Result="";
        try {
            InputStreamReader inputReader = new InputStreamReader( context.getResources().getAssets().open(fileName) );
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line="";

            while((line = bufReader.readLine()) != null){
                Result += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }
}

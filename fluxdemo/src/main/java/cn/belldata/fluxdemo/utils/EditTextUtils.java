package cn.belldata.fluxdemo.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by android on 2016/9/22.
 */
public class EditTextUtils {
    public static final String SYMBOL_STR = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】'；：”“'。，、？]";
   public static final String telRegex = "(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}";


    /**验证手机号是否合法
     * @param mobiles
     * @return
     */
    public static boolean isMobileNum(String mobiles) {
        if (TextUtils.isEmpty(mobiles)) {
            return false;
        } else
            return mobiles.matches(telRegex);
    }

    /**验证是否包含非法字符
     * @param str
     * @return
     */
    public static boolean isContainSymbol(String str) {

        if (TextUtils.isEmpty(str)) {
            return false;
        } else {
            Pattern p = Pattern.compile(SYMBOL_STR);
            Matcher m = p.matcher(str);
            return m.find();
        }
    }

}

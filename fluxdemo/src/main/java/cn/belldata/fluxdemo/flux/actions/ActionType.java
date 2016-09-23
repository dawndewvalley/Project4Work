package cn.belldata.fluxdemo.flux.actions;

/**
 * Created by android on 2016/9/23.
 */
public class ActionType {

/*联网事件类型*/
    public static final int ACTION_CONNECT_ERROR = 0x001; //网络不可用
    public static final int ACTION_CONNECT_START = 0x002; //开始联网
    public static final int ACTION_CONNECT_SUCCESS =0x003; //获取数据成功,这个放到子类中，因为不同接口联网成功后的操作不同
    public static final int ACTION_CONNECT_FAIL = 0x004; //获取数据失败
    public static final int ACTION_CONNECT_INVALID = 0x005; //用户已在其他地方登录

/*登录事件类型*/
    public static final int ACTION_LOGIN_ACCOUNT_INVALID = 0x006;
    public static final int ACTION_LOGIN_PWD_INVALID = 0x007;
    public static final int ACTION_LOGIN_PROTOCOL_UNCHECK = 0x008;
/*message action*/
    public static final int ACTION_NEW_MESSAGE = 0x009;
}

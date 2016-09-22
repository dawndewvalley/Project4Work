package cn.belldata.fluxdemo.actions;

/**
 * Created by android on 2016/9/22.
 */
public class ConnectAction<T> extends Action<T> {

    //网络不可用
    public static final String ATION_CONNECT_ERROR="UNCONNECT";
    //开始联网
    public static final String ATION_CONNECT_START="CONNECT START";
    //获取数据成功,这个放到子类中，因为不同接口联网成功后的操作不同
 //   public static final String ATION_CONNECT_SUCCESS="CONNECT SUCCESS";
    //获取数据失败
    public static final String ATION_CONNECT_FAIL="CONNECT FAIL";
    //用户已在其他地方登录
    public static final String ATION_CONNECT_INVALID="ACCOUNT INVALID";

    public ConnectAction(String type, T data) {
        super(type, data);
    }
}

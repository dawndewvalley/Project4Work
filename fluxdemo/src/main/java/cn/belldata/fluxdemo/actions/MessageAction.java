package cn.belldata.fluxdemo.actions;

/**
 * Created by android on 2016/9/22.
 */
public class MessageAction extends Action<String> {
    public static final String ACTION_NEW_MESSAGE = "new_message";
    public MessageAction(String type, String data) {
        super(type, data);
    }
}

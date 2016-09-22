package cn.belldata.fluxdemo.model;

/**
 * Created by android on 2016/9/22.
 */
public class Message {
    private String mText;

    public Message(){}

    public Message(String text) {
        mText = text;
    }

    public String getMessage() {
        return mText;
    }

    public void setMessage(String text) {
        mText = text;
    }
}

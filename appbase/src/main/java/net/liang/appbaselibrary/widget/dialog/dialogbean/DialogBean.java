package net.liang.appbaselibrary.widget.dialog.dialogbean;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/17
 */

public class DialogBean {
    private int imageId;
    private String text;
    private String code;

    public DialogBean(String text) {
        this.text = text;
    }

    public DialogBean(int imageId, String text) {
        this.imageId = imageId;
        this.text = text;
    }

    public DialogBean(String text, String code) {
        this.text = text;
        this.code = code;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

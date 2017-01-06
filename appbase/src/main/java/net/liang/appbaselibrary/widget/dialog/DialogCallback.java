package net.liang.appbaselibrary.widget.dialog;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/17
 */

public class DialogCallback {

    // todo 列表型dialog回调方法
    OnDialogItemListener onDialogItemListener;

    public void addOnDialogItemListener(OnDialogItemListener onDialogItemListener) {
        this.onDialogItemListener = onDialogItemListener;
    }

    public interface OnDialogItemListener <T> {
        void onClick(int position, T itemBean);
    }

}

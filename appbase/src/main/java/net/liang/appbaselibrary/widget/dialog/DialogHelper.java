package net.liang.appbaselibrary.widget.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import net.liang.appbaselibrary.R;
import net.liang.appbaselibrary.widget.dialog.adapter.RecyclerViewAdapter;
import net.liang.appbaselibrary.widget.dialog.dialogbean.DialogBean;

import java.util.List;


public class DialogHelper extends DialogCallback {
    private Activity mActivity;
    private AlertDialog mAlertDialog;
    private Toast mToast;

    public DialogHelper(Activity activity) {
        mActivity = activity;
    }

    /**
     * 弹对话框
     *
     * @param title            标题
     * @param msg              消息
     * @param positive         确定
     * @param positiveListener 确定回调
     * @param negative         否定
     * @param negativeListener 否定回调
     */
    public void alert(final String title, final String msg, final String positive,
                      final DialogInterface.OnClickListener positiveListener,
                      final String negative, final DialogInterface.OnClickListener negativeListener) {
        alert(title, msg, positive, positiveListener, negative, negativeListener, false);
    }

    /**
     * 弹对话框
     *
     * @param title                    标题
     * @param msg                      消息
     * @param positive                 确定
     * @param positiveListener         确定回调
     * @param negative                 否定
     * @param negativeListener         否定回调
     * @param isCanceledOnTouchOutside 是否可以点击外围框
     */
    public void alert(final String title, final String msg, final String positive,
                      final DialogInterface.OnClickListener positiveListener,
                      final String negative,
                      final DialogInterface.OnClickListener negativeListener,
                      final Boolean isCanceledOnTouchOutside) {
        dismissProgressDialog();

        mActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mActivity == null || mActivity.isFinishing()) {
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                if (title != null) {
                    builder.setTitle(title);
                }
                if (msg != null) {
                    builder.setMessage(msg);
                }
                if (positive != null) {
                    builder.setPositiveButton(positive, positiveListener);
                }
                if (negative != null) {
                    builder.setNegativeButton(negative, negativeListener);
                }
                mAlertDialog = builder.show();
                mAlertDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
                mAlertDialog.setCancelable(false);
            }
        });
    }

    //todo 底部弹出单列选项

    /**
     * 使用方式
     * List<DialogBean> list = new ArrayList<>();
     * list.add(new DialogBean("测试1"));
     * list.add(new DialogBean("测试2"));
     * list.add(new DialogBean("测试3"));
     *
     * DialogHelper dialogHelper = new DialogHelper(TestActivity.this);
     * dialogHelper.showSingleDialogBottom(list);
     * dialogHelper.addOnDialogItemListener(new DialogCallback.OnDialogItemListener<DialogBean>() {
     *
     * @Override public void onClick(int position, DialogBean itemBean) {
     *
     * }
     * });
     */
    public void showSingleDialogBottom(final List<DialogBean> data) {
        dismissProgressDialog();

        mActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mActivity == null || mActivity.isFinishing()) {
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity, R.style.dialog);
                mAlertDialog = builder.show();

                WindowManager m = mActivity.getWindowManager();
                Window dialogWindow = mAlertDialog.getWindow();
                Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
                WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
                p.height = (int) (d.getWidth() * 0.5);//(int) (d.getHeight() * 0.3); // 高度设置为屏幕的
                p.width = RelativeLayout.LayoutParams.MATCH_PARENT; // 宽度设置为屏幕的
                dialogWindow.setAttributes(p);

                View view = LayoutInflater.from(mActivity).inflate(R.layout.dialog_recyclerview, null);
                mAlertDialog.setContentView(view);

                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(R.layout.dialog_item_text, data);
                recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);

                recyclerView.addOnItemTouchListener(new OnItemClickListener() {
                    @Override
                    public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        dismissProgressDialog();
                        if (onDialogItemListener != null) {
                            onDialogItemListener.onClick(i, data.get(i));
                        }
                    }
                });

                mAlertDialog.getWindow().setGravity(Gravity.BOTTOM);
                mAlertDialog.setCancelable(true);
                mAlertDialog.setCanceledOnTouchOutside(true);
                mAlertDialog.getWindow().setWindowAnimations(R.style.dialogWindowAnimTop);
            }
        });
    }

    /**
     * 显示对话框
     *
     * @param showProgressBar
     *            是否显示圈圈
     * @param msg
     *            对话框信息
     */
    public void showProgressDialog(boolean showProgressBar, String msg) {
        showProgressDialog(msg, true, null, showProgressBar);
    }


    /**
     * 显示进度对话框
     *
     * @param msg
     *            消息
     */
    public void showProgressDialog(final String msg) {
        showProgressDialog(msg, true, null, true);
    }

    /**
     * 显示可取消的进度对话框
     *
     * @param msg
     *            消息
     */
    public void showProgressDialog(final String msg, final boolean cancelable,
                                   final DialogInterface.OnCancelListener cancelListener,
                                   final boolean showProgressBar) {
        dismissProgressDialog();

        mActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mActivity == null || mActivity.isFinishing()) {
                    return;
                }

                mAlertDialog = new GenericProgressDialog(mActivity);
                mAlertDialog.setMessage(msg);
                ((GenericProgressDialog) mAlertDialog).setProgressVisiable(showProgressBar);
                mAlertDialog.setCancelable(cancelable);
                mAlertDialog.setOnCancelListener(cancelListener);

                mAlertDialog.show();

//                mAlertDialog.setCanceledOnTouchOutside(true);
                mAlertDialog.setCanceledOnTouchOutside(false);
            }
        });
    }


    //更新确认弹框
    public void showUpgrade(final String info, final View.OnClickListener cacelLinster,
                            final View.OnClickListener upgradeLinster) {
        dismissProgressDialog();

        mActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mActivity == null || mActivity.isFinishing()) {
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity,R.style.dialog);
                mAlertDialog = builder.show();

                WindowManager m = mActivity.getWindowManager();
                Window dialogWindow=mAlertDialog.getWindow();
                Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
                WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
//                p.height = (int) (d.getHeight() * 0.4); // 高度设置为屏幕的
                p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的
//                p.y = -(int) (d.getHeight() * 0.05);

                dialogWindow.setAttributes(p);
                dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//设置可弹出输入法
//                dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

                View view =  LayoutInflater.from(mActivity).inflate(R.layout.dialog_upgrade,null);
                mAlertDialog.setContentView(view);

                TextView cacel = (TextView)view.findViewById(R.id.cacel);
                TextView upgrade = (TextView)view.findViewById(R.id.upgrade);
                TextView updateInfo = (TextView)view.findViewById(R.id.iknow_alert_dialog_content_message);

                updateInfo.setText(Html.fromHtml(info));
                cacel.setOnClickListener(cacelLinster);
                upgrade.setOnClickListener(upgradeLinster);

                mAlertDialog.setCancelable(true);
                mAlertDialog.setCanceledOnTouchOutside(true);
                mAlertDialog.getWindow().setWindowAnimations(R.style.translateWindowAnim);
            }
        });
    }

    //更新确认弹框
    public void showUpgradeDownload(final View.OnClickListener cacelLinster) {
        dismissProgressDialog();

        mActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mActivity == null || mActivity.isFinishing()) {
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity,R.style.dialog);
                mAlertDialog = builder.show();

                WindowManager m = mActivity.getWindowManager();
                Window dialogWindow=mAlertDialog.getWindow();
                Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
                WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
                p.height = (int) (d.getHeight() * 0.4); // 高度设置为屏幕的
                p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的
//                p.y = -(int) (d.getHeight() * 0.05);

                dialogWindow.setAttributes(p);
                dialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);//设置可弹出输入法
//                dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

                View view =  LayoutInflater.from(mActivity).inflate(R.layout.dialog_downloadprogress,null);
                mAlertDialog.setContentView(view);

                TextView cancle = (TextView)view.findViewById(R.id.cancle);

                cancle.setOnClickListener(cacelLinster);

                mAlertDialog.setCancelable(true);
                mAlertDialog.setCanceledOnTouchOutside(true);
                mAlertDialog.getWindow().setWindowAnimations(R.style.translateWindowAnim);
            }
        });
    }

    public AlertDialog getDialog() {
        return mAlertDialog;
    }

    public void dismissProgressDialog() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mAlertDialog != null && mAlertDialog.isShowing() && !mActivity.isFinishing()) {
                    mAlertDialog.dismiss();
                    mAlertDialog = null;
                }
            }
        });
    }


    /**
     * TOAST
     *
     * @param msg
     *            消息
     * @param period
     *            时长
     */
    public void toast(final String msg, final int period) {
        mActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                mToast = new Toast(mActivity);
                View view = LayoutInflater.from(mActivity).inflate(
                        R.layout.dialog_transient_notification, null);
                TextView tv = (TextView) view.findViewById(android.R.id.message);
                tv.setText(msg);
                mToast.setView(view);
                mToast.setDuration(period);

                mToast.setGravity(Gravity.CENTER, 0, 0);
                mToast.show();
            }
        });
    }

}

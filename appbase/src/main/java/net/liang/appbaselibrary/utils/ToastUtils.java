package net.liang.appbaselibrary.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.socks.library.KLog;

import net.liang.appbaselibrary.R;

import es.dmoral.toasty.Toasty;

/**
 * Created by lenovo on 2017/1/4.
 */

public class ToastUtils {
    //单例实现
    private Application application;

    @SuppressLint("StaticFieldLeak")
    private static ToastUtils instances;

    private ToastUtils(Application application) {
        this.application = application;
    }

    public static void init(Application application) {
        instances = new ToastUtils(application);
    }

    public static void showSnackbar(View view, String s) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = view.getContext().getTheme();
        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);

        Snackbar sb = Snackbar.make(view, s, Snackbar.LENGTH_SHORT);
        //sb.getView().setBackgroundColor(Resources.getSystem().getColor(R.color.snackBar_back));
        ((TextView) sb.getView().findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
        sb.show();
    }

    public static void showToast(String string) {
        if (instances == null) {
            KLog.e("ToastUtils instances is null, you need call init() method.");
            return;
        }

        Toasty.normal(instances.application, string).show();
    }

    public static void showErrorToast( String string) {

        if (instances == null) {
            KLog.e("ToastUtils instances is null, you need call init() method.");
            return;
        }

        Toasty.error(instances.application, string, Toast.LENGTH_SHORT, true).show();
    }

    public static void showSuccessToast(String string) {

        if (instances == null) {
            KLog.e("ToastUtils instances is null, you need call init() method.");
            return;
        }

        Toasty.success(instances.application, string, Toast.LENGTH_SHORT, true).show();
    }
}

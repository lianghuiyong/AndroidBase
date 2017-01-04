package net.liang.appbaselibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.liang.appbaselibrary.R;

/**
 * Created by lenovo on 2017/1/4.
 */

public class ToastUtils {

    public static void showSnackbar(View view, String s) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = view.getContext().getTheme();
        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);

        Snackbar sb = Snackbar.make(view, s, Snackbar.LENGTH_SHORT);
        sb.getView().setBackgroundColor(typedValue.data);
        ((TextView) sb.getView().findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
        sb.show();
    }

    public static void showToast(Context context, String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT)
                .show();
    }
}

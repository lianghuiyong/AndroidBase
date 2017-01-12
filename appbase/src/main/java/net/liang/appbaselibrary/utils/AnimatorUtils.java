package net.liang.appbaselibrary.utils;

import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;

/**
 * Created by Dino on 10/27 0027.
 */

public class AnimatorUtils {

    /**
     * 3d 翻转
     * @param context
     * @param view
     * @param duration
     * @param listenerAdapter
     */
    public static void startRotation(Context context, View view, long duration, AnimatorListenerAdapter listenerAdapter) {
        // 改变视角距离, 贴近屏幕
        int distance = 16000;
        float scale = context.getResources().getDisplayMetrics().density * distance;
        view.setCameraDistance(scale);

        //翻转动画
        AnimatorSet animationSet = new AnimatorSet();
        ObjectAnimator rotationYStart = ObjectAnimator.ofFloat(view, "rotationY", 0f, 90f);
        ObjectAnimator rotationYEnd = ObjectAnimator.ofFloat(view, "rotationY", -90f, 0f);
        animationSet.setDuration(duration);
        animationSet.playSequentially(rotationYStart,rotationYEnd);
        animationSet.start();

        rotationYStart.addListener(listenerAdapter);
    }


}

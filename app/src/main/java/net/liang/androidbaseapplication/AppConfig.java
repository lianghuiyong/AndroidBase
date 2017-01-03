package net.liang.androidbaseapplication;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by lenovo on 2017/1/3.
 */

public class AppConfig extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init(){
        if (BuildConfig.DEBUG) {
            if (LeakCanary.isInAnalyzerProcess(this)) {
                // This process is dedicated to LeakCanary for heap analysis.
                // You should not init your app in this process.
                return;
            }
            LeakCanary.install(this);
        }
    }
}

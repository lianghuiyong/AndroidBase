package net.liang.appbaselibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import net.liang.appbaselibrary.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;

import static net.liang.appbaselibrary.NetWorkStateReceiver.NetWorkState.CONNECTED;
import static net.liang.appbaselibrary.NetWorkStateReceiver.NetWorkState.DISCONNECTED;

/**
 * Created by Liang on 2017/4/5.
 */
public class NetWorkStateReceiver extends BroadcastReceiver {

    public enum NetWorkState{
        CONNECTED,
        DISCONNECTED;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (NetworkUtils.isConnected(context)){
            EventBus.getDefault().post(CONNECTED);
        }else {
            EventBus.getDefault().post(DISCONNECTED);
        }
    }
}

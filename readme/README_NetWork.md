# 动态注册（为了兼容7.0）

## 使用

代码里动态注册该广播监听

    //动态启动网络监听广播
    private void startNetReciver(){
        IntentFilter mFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        NetWorkStateReceiver mReceiver = new NetWorkStateReceiver();
        registerReceiver(mReceiver, mFilter);
    }
    
## 效果

![](http://oeqej1j2m.bkt.clouddn.com/appbase_net1.png)

![](http://oeqej1j2m.bkt.clouddn.com/appbase_net2.png)

## 自定义ui

    <net.liang.appbaselibrary.widget.NetInfoView
        android:id="@+id/tv_netWorkInfo"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:visibility="gone"/>
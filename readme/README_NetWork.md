# 1、页面无网络提示

## 代码里动态注册该广播监听、动态注册（为了兼容7.0）

    //动态启动网络监听广播
    private void startNetReciver(){
        IntentFilter mFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        NetWorkStateReceiver mReceiver = new NetWorkStateReceiver();
        registerReceiver(mReceiver, mFilter);
    }

## 自定义ui

    <net.liang.appbaselibrary.widget.NetInfoView
        android:id="@+id/tv_netWorkInfo"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:visibility="gone"/>

# 2、列表无网络提示

1)列表Adapter需继承BaseRecyclerAdapter
2)若页面继承于BaseRecyclerViewActivity或者BaseRecyclerViewFragment(使用方式：[一个好用的列表页面基类](./README_RecyclerView.md "一个好用的列表页面基类")),

只需要网络失败的时候调用
    
    showNetworkFail();

3)若不是承该BaseRecyclerViewActivity或者BaseRecyclerViewFragment

    1、adapter.addOnRecyclerAdapterListener(() -> onRefresh());
    onRefresh()为刷新页面方法
    
    2、在请求网络失败的地方调用
    adapter.showNetWorkErrorView();
    
## 效果

![](http://oeqej1j2m.bkt.clouddn.com/appbase_net01.png)

![](http://oeqej1j2m.bkt.clouddn.com/appbase_net02.png)

![](http://oeqej1j2m.bkt.clouddn.com/appbase_net03.png)
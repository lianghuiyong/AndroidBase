
[![](https://img.shields.io/badge/moven%20center-2.1.0-brightgreen.svg?style=flat)](https://bintray.com/betterliang/Android/appbase)
![](https://img.shields.io/badge/minSdk-15-blue.svg)
[![](https://img.shields.io/github/stars/lianghuiyong/AndroidBase.svg)](https://github.com/lianghuiyong/AndroidBase/stargazers)
[![](https://img.shields.io/github/forks/lianghuiyong/AndroidBase.svg)](https://github.com/lianghuiyong/AndroidBase/network)

# 说明

<img src="/art/appbase_media_2.1.0.gif" width="49%">

## Gradle

```gradle

    allprojects {
        repositories {
            jcenter()
            maven { url "https://jitpack.io" }
        }
    }

    android {
        dataBinding {
            enabled = true
        }
    }

    dependencies {
        compile 'com.better.android:appbase:x.y.z'
    }
```

## gradle-wrapper.properties

```gradle
distributionUrl=https\://services.gradle.org/distributions/gradle-3.3-all.zip
```

## MVP 

 - [MVP使用笔记](http://lianghuiyong.online/2017/04/18/MVP%E4%BD%BF%E7%94%A8%E7%AC%94%E8%AE%B0/)

![](http://oeqej1j2m.bkt.clouddn.com/MVP-Android.png)

MVP：

V层处理UI视图操作；P层实现业务逻辑操作（有设计需要的时候，核心业务甚至可以写到一个核心的P里面去）；M层为数据仓库，后台管理数据来源与数据控制，数据是来自网络、数据库、SP、缓存，示例：你可能会需要这样一个实现，一个数据列表，不需要实时性，但是需要给网络情况有问题时的用户提升用户体验，需要使用网络数据+本地缓存数据机制，那么，都可以统一在这个数据仓库做处理，代码逻辑层次很清晰，你应该会喜欢上这个设计。

V-P之间的调用方式采取依赖倒置原则，俗话就是使用抽象接口（回调）；P与V，代码写多了，其实会发现，V可以正常的调用P，P则要尽量少调用V，或者使用DataBinding，数据的变化使用绑定方式；P与M，M只与P做沟通，数据一般来自后台延时操作，这块也就是使用RxJava情况较多的地方，网络请求可以尝试使用ReTrofit。一般情况下，一个V对应一个P，而P和M，会出现一个P对应多个M的情况（M的接口多了，或许会把M按模块创建）。

Dagger+MVP：

则是实现在MVP 结构代码中，P、M 的对象创建使用 Dagger 注入方式。

## MVP+Dagger
 - [MVP+Dagger使用笔记](http://lianghuiyong.online/2017/04/18/MVP-Dagger%E4%BD%BF%E7%94%A8%E7%AC%94%E8%AE%B0/)
### 具体使用示例：
 - [MVP+Dagger实现一个数据源数据返回](https://github.com/lianghuiyong/AndroidBase/blob/appbase-2.0/app/src/main/java/net/liang/androidbaseapplication/mvp/daggernormal/Test_DaggerNormalActivity.java)
 - [MVP+Dagger使用基类列表页面实现两个数据源数据返回](https://github.com/lianghuiyong/AndroidBase/blob/appbase-2.0/app/src/main/java/net/liang/androidbaseapplication/mvp/daggerlist/Test_DaggerListActivity.java)



## 列表页面基类

``` 
    <include layout="@layout/layout_recyclerview"/>
    
    或者使用相同的ID：
    
    	SwipeRefreshLayout使用@+id/swiperefresh，
    	RecyclerView使用@+id/recyclerView
```

``` java
public class ExampleBaseRecyclerViewActivity extends BaseRecyclerViewActivity<List<String>> {
     
    @Override
    public BaseRecyclerAdapter addListAdapter() {
     
        // 默认一页页数为10，可使用setPageSize(int pageSize)修改
        return new RecyclerAdapter(this, recyclerView, null);
    }
    
    // 请求成功时的回调方法;
    // onListSuccess返回的数据为完整的请求数据，需要自己拆解列表数据，添加到适配器里
    @Override
    public void onListSuccess(List<String> strings, int pageNo) {
        //单页使用
        //adapter.showList(strings);
        //多页使用
        adapter.showList(strings, pageNo);
    }
    
    // 获取网络数据接口，注意返回的是被观察者对象
    @Override
    public Observable<List<String>> onListGetData(int pageNo) {
        ......
    }
}
``` 



## 无网络状态页面提示
 - 普通页面
 
代码里动态注册该广播监听、动态注册（为了兼容7.0）
``` java
 //动态启动网络监听广播
 private void startNetReciver(){
     IntentFilter mFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
     NetWorkStateReceiver mReceiver = new NetWorkStateReceiver();
     registerReceiver(mReceiver, mFilter);
 }
```

自定义ui
``` xml
 <net.liang.appbaselibrary.widget.NetInfoView
     android:id="@+id/tv_netWorkInfo"
     android:layout_width="match_parent"
     android:layout_height="wrap_content"
     android:visibility="gone"/>
```
<div align="center">
  	<img src="http://oeqej1j2m.bkt.clouddn.com/appbase_net03.png" width="200">
</div>

 - 列表页面
 
1、列表Adapter需继承BaseRecyclerAdapter

2、若页面继承于BaseRecyclerViewActivity或者BaseRecyclerViewFragment(使用方式：一个好用的列表页面基类),

只需要网络失败的时候调用
```
showNetworkFail();
```
3、若不是继承BaseRecyclerViewActivity或者BaseRecyclerViewFragment

1)adapter回调
```
adapter.addOnRecyclerAdapterListener(() -> onRefresh());
onRefresh()为刷新页面方法
```
2)在请求网络失败的地方调用
```
adapter.showNetWorkErrorView(); 
```

<div align="center">
 	<img src="http://oeqej1j2m.bkt.clouddn.com/appbase_net02.png" width="200">
</div>

## proguard-rules.pro

```gradle
-keep class com.chad.library.adapter.** {
   *;
}
```

## 附

 - [lambda配置](https://github.com/lianghuiyong/AndroidBase/wiki/lambda-%E9%85%8D%E7%BD%AE)

## 致谢
 
 - [![](https://img.shields.io/badge/OsChina%20App-2.8.0-brightgreen.svg)](http://git.oschina.net/oschina/android-app)
 - [![](https://img.shields.io/badge/RxJava-2.0-brightgreen.svg)](https://github.com/ReactiveX/RxJava)   
 - [![](https://img.shields.io/badge/butterknife-8.5.1-brightgreen.svg)](https://github.com/JakeWharton/butterknife)   
 - [![](https://img.shields.io/badge/todo-MVP-brightgreen.svg)](https://github.com/googlesamples/android-architecture/tree/todo-mvp/) 
 - [![](https://img.shields.io/badge/todo-DataBinding-brightgreen.svg)](https://github.com/googlesamples/android-architecture/tree/todo-databinding/) 
 - [![](https://img.shields.io/badge/BaseRecyclerViewAdapterHelper-2.9.0-brightgreen.svg)](https://github.com/CymChad/BaseRecyclerViewAdapterHelper) 
 - [![](https://img.shields.io/badge/AndroidUtilCode-1.3.5-brightgreen.svg)](https://github.com/Blankj/AndroidUtilCode) 
 
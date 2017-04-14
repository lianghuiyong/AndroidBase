
[![](https://img.shields.io/badge/moven%20center-1.1.20-brightgreen.svg?style=flat)](https://bintray.com/betterliang/Android/appbase/1.1.20)
![](https://img.shields.io/badge/minSdk-15-blue.svg)
[![](https://img.shields.io/github/stars/lianghuiyong/AndroidBase.svg)](https://github.com/lianghuiyong/AndroidBase/stargazers)
[![](https://img.shields.io/github/forks/lianghuiyong/AndroidBase.svg)](https://github.com/lianghuiyong/AndroidBase/network)
# 项目开发基类

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


## MVP基类使用介绍

- **V**

```java
public class ExampleV extends BaseAppCompatActivity(or BaseFragment) implements ExampleContract.View{
    @Override
    protected int getLayoutId() {
        return R.layout.Example;
    }
    
    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }
    
    ......
}
```

- **P**

```java
public class ExamplePresenter extends BasePresenter implements ExampleContract.Presenter {
    
    @NonNull
    private ExampleContract.View view;
    
    //你的数据管理仓库
    @NonNull
    private ExampleRepository repository;
    
    public ExamplePresenter(@NonNull ExampleContract.View view) {
        this.view = checkNotNull(view, "view cannot be null!");
        this.repository = ExampleRepository.getInstance(RemoteExampleDataSource.getInstance(), LocalExampleDataSource.getInstance());
    }
}
```

- **Contract**

```java
public interface ExampleContract {
    /**
     * view接口层  处理界面
     */
    interface View extends MvpView{
        ......
    }
    
    /**
     * Presenter接口层 处理业务
     */
    interface Presenter extends MvpPresenter{
        ......
    }
}
```

- **M**

```java
/**
 * 数据管理仓库，控制选择使用remote数据还是local数据（SP、数据库、缓存）
 */
public class ExampleRepository implements ExampleApi {
    @Nullable
    private static ExampleRepository INSTANCE = null;
    @NonNull
    private final ExampleApi localDataSource;
    @NonNull
    private final ExampleApi remoteDataSource;
    
    public ExampleRepository(@NonNull ExampleApi localDataSource, @NonNull ExampleApi remoteDataSource) {
        this.localDataSource = checkNotNull(localDataSource);
        this.remoteDataSource = checkNotNull(remoteDataSource);
    }
    
    public static ExampleRepository getINSTANCE(@NonNull ExampleApi localDataSource, @NonNull ExampleApi remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ExampleRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }
    
    @Override
    public Observable<BaseResponseData<String>> register(@Body ExampleRegisterBean sendBean) {
        return remoteDataSource.register(sendBean);
    }
    
    @Override
    public Observable<BaseResponseData<String>> unregister(@Body ExampleUnRegisterBean sendBean) {
        return localDataSource.unregister(sendBean);
    }
}
```

```java
public interface ExampleApi {
 
    /**
     * 注册
     * 使用retrofit请求
     */
    @POST("services/device/register")
    Observable<BaseResponseData<String>> register(@Body RegisterBean sendBean);

    /**
     * 注销
     * 使用非retrofit请求
     */
    Observable<BaseResponseData<String>> unregister(UnRegisterBean sendBean);
}
```

```java
//remote数据实现
public class RemoteExampleDataSource implements ExampleApi {

    private static RemoteExampleDataSource INSTANCE;

    public static RemoteExampleDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteExampleDataSource();
        }
        return INSTANCE;
    }
    
    @Override
    public Observable<BaseResponseData<String>> register(@Body RegisterBean sendBean) {
        /**
        * public static ExampleApi getExampleApi() {
        *     if (ExampleApi == null) {
        *         Retrofit retrofit = new Retrofit.Builder()
        *                 .client(new OkHttpClient())
        *                 .baseUrl(UrlConstants.HOST)
        *                 .addConverterFactory(GsonConverterFactory.create())
        *                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        *                 .build();
        *         ExampleApi = retrofit.create(ExampleApi.class);
        *     }
        *     return ExampleApi;
        * }
        */
        return NetWork.getExampleApi().register(sendBean);
    }
    
    @Override
    public Observable<BaseResponseData<String>> unregister(UnRegisterBean sendBean) {
        return null;
    }
}
```

```java
public class LocalExampleDataSource implements ExampleApi {

    @Nullable
    private static LocalExampleDataSource INSTANCE;

    public static LocalExampleDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalExampleDataSource();
        }
        return INSTANCE;
    }
    
    @Override
    public Observable<BaseResponseData<String>> register(@Body RegisterBean sendBean) {
        return null;
    }
    
    @Override
    public Observable<BaseResponseData<String>> unregister(UnRegisterBean sendBean) {
        ......
        return Observable.just(baseResponseData);
    }
}
```

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
 

[![](https://img.shields.io/badge/moven%20center-1.1.35-brightgreen.svg?style=flat)](https://bintray.com/betterliang/Android/appbase/1.1.35)
![](https://img.shields.io/badge/minSdk-15-blue.svg)
[![](https://img.shields.io/github/stars/lianghuiyong/AndroidBase.svg)](https://github.com/lianghuiyong/AndroidBase/stargazers)
[![](https://img.shields.io/github/forks/lianghuiyong/AndroidBase.svg)](https://github.com/lianghuiyong/AndroidBase/network)
# 项目开发基类

## 项目说明

MVP基类库+Dagger使用示例

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


## MVP+Dagger使用介绍


### MVP使用
- **V**

```java
public class ExampleV extends BaseAppCompatActivity(or BaseFragment) implements ExampleContract.View{
    
    @Inject
    ExamplePresenter presenter;
    
    @Override
    protected int getLayoutId() {
        return R.layout.Example;
    }
    
    @Override
    protected MvpPresenter getPresenter() {
        return presenter;
    }
    
    @Override
    public void init() {
        super.init();

        //使用Dagger创建presenter,并不需要new对象。
        DaggerViewComponent.builder()
                .repositoryComponent(DaggerRepositoryComponent.builder().build())
                .presenterModule(new PresenterModule(this))
                .build()
                .inject(this);
    }
    ......
}
```

- **P**

```java
public class ExamplePresenter extends BasePresenter implements ExampleContract.Presenter {
    
    @Inject
    Test1Repository repository1;

    @Inject
    Test2Repository repository2;
    
    @NonNull
    private ExampleContract.View view;
    
    //你的数据管理仓库
    @NonNull
    private ExampleRepository repository;
    
    //参数使用MvpView类型，便于view的清单管理和PresenterModule的单一
    public ExamplePresenter(MvpView view) {
       this.mView = (ExampleContract.View)mView;
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
        List<String> getListData();
        ......
    }
}
```

- **M**

```java
/**
 * 数据管理仓库，控制选择使用remote数据还是local数据（SP、数据库、缓存）
 */
public class Test1Repository implements Test1Api {

    private final Test1Api mTest1RemoteDataSource;

    private final Test1Api mTest1LocalDataSource;

    @Inject
    public Test1Repository(@Local Test1LocalDataSource mTest1LocalDataSource, @Remote Test1RemoteDataSource mTest1RemoteDataSource) {
        this.mTest1RemoteDataSource = mTest1RemoteDataSource;
        this.mTest1LocalDataSource = mTest1LocalDataSource;
    }

    @Override
    public List<String> testGet() {
        return mTest1LocalDataSource.testGet();
    }
}
```

```java
public interface Test1Api {
    /**
     * 测试接口
     */
    List<String> testGet();
}
```

```java
public class Test1RemoteDataSource implements Test1Api {

    @Override
    public List<String> testGet() {
        return null;
    }
}
```

```java
/**
 *
 * 数据local实现方式
 */
public class Test1LocalDataSource implements Test1Api {

    @Override
    public List<String> testGet() {

        List<String> list = new ArrayList<>();
        list.add("数据源一：1");
        list.add("数据源一：2");
        list.add("数据源一：3");
        list.add("数据源一：4");
        list.add("数据源一：5");
        list.add("数据源一：6");
        list.add("数据源一：7");
        list.add("数据源一：8");
        return list;
    }
}
```

### Dagger使用

简单说明：

一般创建对象方式，new一个Presenter对象，找到该对象的构造方法，传递参数，presenter指向该对象空间：
```    
    Presenter presenter = new Presenter(testActivity);
```
Dagger则是把对象的构建方法、构造参数和具体的new动作，做了拆分到不同文件来处理

#### Dagger使用方式一：构造参数为当前this时

1、构造方法使用@Inject标注构造方法，表明该构造方法是Dagger注入入口
```
    @Inject
    public Presenter(MvpView mView) {
        this.mView = (Test_DaggerListContract.View)mView;
    }
```
2、使用@Module标注，创建构造方法使用时参数的Module，@Provides标注为该方法返回参数实体类型
```
    @Module
    public class PresenterModule {
    
        private final MvpView mView;
    
        public PresenterModule(MvpView view) {
            mView = view;
        }
    
        @Provides
        @ActivityScoped
        MvpView provideTasksContractView() {
            return mView;
        }
    }
```
3、创建ViewComponent，@Component标注的方法，ViewComponent会自动生成一个DaggerViewComponent的方法，modules后面为参数的Module，dependencies后面为注入的依赖。
```java
@ActivityScoped
@Component(modules = PresenterModule.class)
public interface ViewComponent {
    void inject(Test_DaggerListActivity activity);
}
```
4、通过DaggerViewComponent的方法注入，@Inject标注的presenter对象已经实例化了。
```java
public class Test_DaggerListActivity {
    @Inject
    Test_DaggerListPresenter presenter;
    
    @Override
    public void init() {
        DaggerViewComponent.builder()
            //(new PresenterModule(this))时，通过this参数也已经把view对象传递给了module里的view了
            .presenterModule(new PresenterModule(this))
            .build()
            .inject(this);
    }
}
```

#### Dagger使用方式二：构造参数非当前this

1、构造方法使用@Inject标注构造方法，表明该构造方法是Dagger注入入口
```
public class Test2Repository implements Test2Api {
    
    private final Test2Api mTest2RemoteDataSource;
    
    private final Test2Api mTest2LocalDataSource;
    
    @Inject
    public Test2Repository(@Local Test2LocalDataSource mTestLocalDataSource, @Remote Test2RemoteDataSource mTestRemoteDataSource) {
        this.mTest2RemoteDataSource = mTestRemoteDataSource;
        this.mTest2LocalDataSource = mTestLocalDataSource;
    }
    
    ......
}
```
2、使用@Module标注，创建构造方法使用时参数的Module，@Provides标注为该方法返回参数实体类型
```
@Module
public class Test1RepositoryModule {

    @Singleton
    @Provides
    @Local
    Test1LocalDataSource provideTest1LocalDataSource() {
        return new Test1LocalDataSource();
    }

    @Singleton
    @Provides
    @Remote
    Test1RemoteDataSource provideTest1RemoteDataSource() {
        return new Test1RemoteDataSource();
    }

}
```
3、创建ViewComponent，@Component标注的方法，ViewComponent会自动生成一个DaggerViewComponent的方法，modules后面为参数的Module，dependencies后面为注入的依赖。
```java
@Singleton
@Component(modules = {Test1RepositoryModule.class})
public interface RepositoryComponent {
    
    Test1Repository getTest1Repository();
}
```
4、通过DaggerRepositoryComponent的方法注入，@Inject标注的repository1、repository2对象已经实例化了。
```java
public class Test_DaggerListPresenter{
   
    
    @Inject
    Test1Repository repository1;
    
    @Inject
    Test2Repository repository2;
    
    public Test_DaggerListPresenter() {
        DaggerRepositoryComponent.builder().build();
    }
    ......
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

具体使用示例：
 - [MVP+Dagger实现一个数据源数据返回](https://github.com/lianghuiyong/AndroidBase/blob/appbase-2.0/app/src/main/java/net/liang/androidbaseapplication/mvp/daggernormal/Test_DaggerNormalActivity.java)
 - [MVP+Dagger使用基类列表页面实现两个数据源数据返回](https://github.com/lianghuiyong/AndroidBase/blob/appbase-2.0/app/src/main/java/net/liang/androidbaseapplication/mvp/daggerlist/Test_DaggerListActivity.java)



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
 
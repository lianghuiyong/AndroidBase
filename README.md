
[![](https://img.shields.io/badge/moven%20center-2.1.0-brightgreen.svg?style=flat)](https://bintray.com/betterliang/Android/appbase)
![](https://img.shields.io/badge/minSdk-15-blue.svg)
[![](https://img.shields.io/github/stars/lianghuiyong/AndroidBase.svg)](https://github.com/lianghuiyong/AndroidBase/stargazers)
[![](https://img.shields.io/github/forks/lianghuiyong/AndroidBase.svg)](https://github.com/lianghuiyong/AndroidBase/network)


<img src="/art/appbase_media_2.1.0.gif" width="49%">

# 说明

# Gradle

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

# MVP 

 - [MVP使用笔记](http://lianghuiyong.online/2017/04/18/MVP%E4%BD%BF%E7%94%A8%E7%AC%94%E8%AE%B0/)

![](http://oeqej1j2m.bkt.clouddn.com/MVP-Android.png)

MVP：

V层处理UI视图操作；P层实现业务逻辑操作（有设计需要的时候，核心业务甚至可以写到一个核心的P里面去）；M层为数据仓库，后台管理数据来源与数据控制，数据是来自网络、数据库、SP、缓存，示例：你可能会需要这样一个实现，一个数据列表，不需要实时性，但是需要给网络情况有问题时的用户提升用户体验，需要使用网络数据+本地缓存数据机制，那么，都可以统一在这个数据仓库做处理，代码逻辑层次很清晰，你应该会喜欢上这个设计。

V-P之间的调用方式采取依赖倒置原则，俗话就是使用抽象接口（回调）；P与V，代码写多了，其实会发现，V可以正常的调用P，P则要尽量少调用V，或者使用DataBinding，数据的变化使用绑定方式；P与M，M只与P做沟通，数据一般来自后台延时操作，这块也就是使用RxJava情况较多的地方，网络请求可以尝试使用ReTrofit。一般情况下，一个V对应一个P，而P和M，会出现一个P对应多个M的情况（M的接口多了，或许会把M按模块创建）。

Dagger+MVP：

则是实现在MVP 结构代码中，P、M 的对象创建使用 Dagger 注入方式。

# MVP+Dagger
 - [MVP+Dagger使用笔记](http://lianghuiyong.online/2017/04/18/MVP-Dagger%E4%BD%BF%E7%94%A8%E7%AC%94%E8%AE%B0/)
### 具体使用示例：
 - [MVP+Dagger实现一个数据源数据返回](https://github.com/lianghuiyong/AndroidBase/blob/appbase-2.0/app/src/main/java/net/liang/androidbaseapplication/mvp/daggernormal/Test_DaggerNormalActivity.java)
 - [MVP+Dagger使用基类列表页面实现两个数据源数据返回](https://github.com/lianghuiyong/AndroidBase/blob/appbase-2.0/app/src/main/java/net/liang/androidbaseapplication/mvp/daggerlist/Test_DaggerListActivity.java)



# 列表页面
 - [列表页面使用](/readme/README_RecyclerView.md)

# 网络状态
 - [网络状态提示](/readme/README_NetWork.md)

# 附

 - [lambda配置](https://github.com/lianghuiyong/AndroidBase/wiki/lambda-%E9%85%8D%E7%BD%AE)

# 致谢
 
 - [![](https://img.shields.io/badge/OsChina%20App-2.8.0-brightgreen.svg)](http://git.oschina.net/oschina/android-app)
 - [![](https://img.shields.io/badge/RxJava-2.0-brightgreen.svg)](https://github.com/ReactiveX/RxJava)   
 - [![](https://img.shields.io/badge/butterknife-8.5.1-brightgreen.svg)](https://github.com/JakeWharton/butterknife)   
 - [![](https://img.shields.io/badge/todo-MVP-brightgreen.svg)](https://github.com/googlesamples/android-architecture/tree/todo-mvp/) 
 - [![](https://img.shields.io/badge/todo-DataBinding-brightgreen.svg)](https://github.com/googlesamples/android-architecture/tree/todo-databinding/) 
 - [![](https://img.shields.io/badge/BaseRecyclerViewAdapterHelper-2.9.0-brightgreen.svg)](https://github.com/CymChad/BaseRecyclerViewAdapterHelper) 
 - [![](https://img.shields.io/badge/AndroidUtilCode-1.3.5-brightgreen.svg)](https://github.com/Blankj/AndroidUtilCode) 
 
# 一些好用的基类
**BaseAppCompatActivity**

**BaseFragment**

## 项目必须打开DataBinding支持
        dataBinding {
            enabled = true
        }

###1.初始化方法
    init()

###2.已添加的使用支持
    EventBus3.0、ButterKnife、DataBinding、
    mvp架构getPresenter获取到了p句柄，页面关闭时会释放观察者（p对象需要继承BasePresenter）

###3.获取页面View
    showToast(s)

###4.接口
**1、getLayoutId()**
```
@Override
    protected int getLayoutId() {
        return R.layout.activity_base_recycler_view;
    }
```
**1、getPresenter()**
```
    Mvp的P句柄，有则返回，无则返回null就行了
```

###5.获取databinding
    getBinding();

###6.Toast
    showToast(s)

###7.设置标题栏
    xml里引用：<include layout="@layout/toolbar_center"/>
    设置标题：setToolbarCentel(true,"测试");
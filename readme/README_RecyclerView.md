# 一个好用的列表页面基类

###1. 继承BaseRecyclerViewFragment<T>或者 BaseRecyclerViewActivity<T>, T是列表对象类型
###2. XML文件，请引用

		<include layout="@layout/layout_recyclerview"/>

或者使用相同的ID：

		SwipeRefreshLayout使用@+id/swiperefresh，
		RecyclerView使用@+id/recyclerView

###3. 实现三个接口
>
```

 // 列表适配器
 // 默认一页页数为10，可使用setPageSize(int pageSize)修改

@Override
public BaseRecyclerAdapter addListAdapter() {
    return new RecyclerAdapter(this, recyclerView, null);
}

 // 请求成功时的回调方法;
 // onSuccess返回的数据为完整的请求数据，需要自己拆解列表数据，添加到适配器里
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

    List<String> list = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    for (int i = 0; i < 10; i++) {
        String string = sdf.format(new Date());
        list.add(string);
    }
    return Observable.just(list);
}
```
***
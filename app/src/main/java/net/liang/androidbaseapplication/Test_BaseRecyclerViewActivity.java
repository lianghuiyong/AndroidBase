package net.liang.androidbaseapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.socks.library.KLog;

import net.liang.appbaselibrary.base.BasePresenter;
import net.liang.appbaselibrary.base.BaseRecyclerAdapter;
import net.liang.appbaselibrary.base.BaseRecyclerViewActivity;
import net.liang.appbaselibrary.base.BaseRecyclerViewContract;
import net.liang.appbaselibrary.base.BindingViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

public class Test_BaseRecyclerViewActivity extends BaseRecyclerViewActivity<List<String>, String> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_recycler_view;
    }

    /**
     * 列表适配器
     * 默认起始页为1，可使用setFirstPageNo(int firstPageNo)修改
     * 默认一页页数为10，可使用setPageSize(int pageSize)修改
     * */
    @Override
    protected BaseRecyclerAdapter addRecyclerAdapter() {
        return new RecyclerAdapter(this, recyclerView, null);
    }

    /**
     * 请求成功时的回调方法，不能删除super.onSuccess(strings);
     * onSuccess返回的数据为完整的请求数据，需要自己拆解列表数据，添加到适配器里
     * */
    @Override
    public void onSuccess(List<String> strings) {
        super.onSuccess(strings);
        adapter.showList(strings, getPageNo());
    }

    /**
     * 请求列表数据的Body，需要知道下一页页数时调用 getPageNo();
     * */
    @Override
    public String getSendBody() {
        return "123";
    }

    /**
     * 获取网络数据接口，注意返回的是被观察者对象
     * */
    @Override
    public Observable<List<String>> getData(String sendData) {

        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 10; i++) {
            String string = sdf.format(new Date());
            list.add(string);
        }
        return Observable.just(list);
    }

    /**
     * 带bind的适配器
     * */
    class RecyclerAdapter extends BaseRecyclerAdapter<String> {

        public RecyclerAdapter(Context context, RecyclerView recyclerView, List data) {
            super(context, recyclerView, R.layout.item_base_recyclerview_layout, data);
        }

        @Override
        protected void convert(BindingViewHolder bindingViewHolder, String s) {
            bindingViewHolder.setText(R.id.data, s);
        }
    }

}
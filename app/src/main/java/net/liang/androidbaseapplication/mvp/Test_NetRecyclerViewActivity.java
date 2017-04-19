package net.liang.androidbaseapplication.mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import net.liang.androidbaseapplication.R;
import net.liang.appbaselibrary.base.BindingViewHolder;
import net.liang.appbaselibrary.base.RecyclerView.BaseRecyclerAdapter;
import net.liang.appbaselibrary.base.RecyclerView.BaseRecyclerViewActivity;
import net.liang.appbaselibrary.base.mvp.MvpPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class Test_NetRecyclerViewActivity extends BaseRecyclerViewActivity<List<String>> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_net_recycler_view;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    public void init() {
        super.init();
        setToolbarCentel(true, "网络请求测试");

        adapter.showNetWorkErrorView();
    }

    @Override
    public Observable<List<String>> onListGetData(int pageNo) {

        List<String> list = new ArrayList<>();
        list.add("哈哈哈哈");

        return Observable.just(list);
    }

    @Override
    public void onListSuccess(List<String> strings, int pageNo) {
        showToast(strings.toString());
    }

    @Override
    public BaseRecyclerAdapter addListAdapter() {
        return new TestAdapter(this, recyclerView, null);
    }

    class TestAdapter extends BaseRecyclerAdapter<String> {
        public TestAdapter(Context context, RecyclerView recyclerView, List<String> data) {
            super(context, recyclerView, R.layout.recycler_item, data);
        }

        @Override
        protected void convert(BindingViewHolder helper, String item) {

        }
    }
}

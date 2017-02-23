package net.liang.appbaselibrary.base.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import net.liang.appbaselibrary.R;
import net.liang.appbaselibrary.base.BaseAppCompatActivity;
import net.liang.appbaselibrary.data.RecyclerDataRepository;
import net.liang.appbaselibrary.data.RecyclerDataSource;
import net.liang.appbaselibrary.data.local.LocalRecyclerDataSource;

/**
 * Created on 2016/10/23.
 * By lianghuiyong@outlook.com
 *
 * @param <T> 是获取的数据类型
 */

public abstract class BaseRecyclerViewActivity<T> extends BaseAppCompatActivity implements BaseRecyclerViewContract.View<T>, RecyclerDataSource<T>, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    protected BaseRecyclerAdapter adapter;
    protected SwipeRefreshLayout swipeRefresh;
    protected RecyclerView recyclerView;
    private BaseRecyclerViewContract.Presenter recyclerPresenter;

    @Override
    public void init() {
        recyclerPresenter = new BaseRecyclerViewPresenter(this,
                new RecyclerDataRepository(this, LocalRecyclerDataSource.getInstance()));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

        adapter = adapter == null ? addListAdapter() : adapter;

        swipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
        swipeRefresh.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        recyclerPresenter.onListRefresh();
    }

    @Override
    public void setListRefresh(boolean isShow) {
        swipeRefresh.setRefreshing(isShow);
    }

    @Override
    public void showNetworkFail(String err) {
        adapter.showNetWorkErrorView();
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        recyclerPresenter.unSubscribe();
    }

    @Override
    public void onRefresh() {
        recyclerPresenter.onListRefresh();
    }

    @Override
    public void onLoadMoreRequested() {
        recyclerPresenter.onListLoadMore();
    }
}

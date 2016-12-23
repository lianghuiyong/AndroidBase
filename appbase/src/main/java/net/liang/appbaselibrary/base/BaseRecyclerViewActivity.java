package net.liang.appbaselibrary.base;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import net.liang.appbaselibrary.R;

/**
 * Created on 2016/10/23.
 * By lianghuiyong@outlook.com
 */

public abstract class BaseRecyclerViewActivity<T, S> extends BaseAppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    protected BaseRecyclerAdapter adapter;
    protected RecyclerView recyclerView;
    private SwipeRefreshLayout swiperefresh;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recyclerview;
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        swiperefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

        adapter = adapter == null ? addRecyclerAdapter() : adapter;

        swiperefresh.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swiperefresh.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                upData();
            }
        });
    }

    @Override
    public void initData() {
        upData();
    }

    @Override
    public void onRefresh() {
        upData();
    }

    public void upData() {
    }


    protected abstract BaseRecyclerAdapter<T> addRecyclerAdapter();

}

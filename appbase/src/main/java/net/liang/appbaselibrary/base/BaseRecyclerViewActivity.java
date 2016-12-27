package net.liang.appbaselibrary.base;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import net.liang.appbaselibrary.R;
import net.liang.appbaselibrary.data.RecyclerDataRepository;
import net.liang.appbaselibrary.data.RecyclerDataSource;
import net.liang.appbaselibrary.data.local.LocalRecyclerDataSource;
import net.liang.appbaselibrary.data.remote.RemoteRecyclerDataSource;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created on 2016/10/23.
 * By lianghuiyong@outlook.com
 */

public abstract class BaseRecyclerViewActivity<T, S> extends BaseAppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, BaseRecyclerViewContract.View<T, S> {
    protected abstract BaseRecyclerAdapter<T> addRecyclerAdapter();

    protected BaseRecyclerAdapter adapter;
    protected RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private BaseRecyclerViewContract.Presenter mPresenter;
    private RecyclerDataSource<T, S> repository;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recyclerview;
    }

    @Override
    public void init() {
        mPresenter = new BaseRecyclerViewPresenter(this,
                RecyclerDataRepository.getInstance(
                        RemoteRecyclerDataSource.getInstance(),
                        LocalRecyclerDataSource.getInstance()));

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);

        adapter = adapter == null ? addRecyclerAdapter() : adapter;

        swipeRefresh.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefresh.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMore();
            }
        });
    }

    @Override
    public void setPresenter(BaseRecyclerViewContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onRefresh() {
        mPresenter.onRefresh();
    }

    @Override
    public void showList(List<T> listData) {

    }

    @Override
    public void showNetworkFail(String err) {

    }
}

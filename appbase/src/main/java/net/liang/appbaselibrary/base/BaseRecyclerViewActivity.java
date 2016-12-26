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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2016/10/23.
 * By lianghuiyong@outlook.com
 */

public abstract class BaseRecyclerViewActivity<T, S> extends BaseAppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,BaseRecyclerViewContract.View {

    protected BaseRecyclerAdapter adapter;
    protected RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerDataSource<T, S> repository;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recyclerview;
    }

    @Override
    protected BasePresenter addPresenter() {
        return new BaseRecyclerViewPresenter(this,
                RecyclerDataRepository.getInstance(RemoteRecyclerDataSource.getInstance(),
                        LocalRecyclerDataSource.getInstance()));
    }

    @Override
    public void initView() {
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
    //获取列表数据
    public void getListData(S sendBean) {
        Disposable disposable = repository
                .getData(sendBean)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        disposables.add(disposable);
    }
}

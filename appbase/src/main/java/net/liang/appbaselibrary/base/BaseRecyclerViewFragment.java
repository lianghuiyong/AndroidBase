package net.liang.appbaselibrary.base;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;

import net.liang.appbaselibrary.R;
import net.liang.appbaselibrary.data.RecyclerDataRepository;
import net.liang.appbaselibrary.data.RecyclerDataSource;
import net.liang.appbaselibrary.data.local.LocalRecyclerDataSource;

import java.lang.reflect.Type;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created on 2016/10/23.
 * By lianghuiyong@outlook.com
 * @param <T> 是获取过来的数据类型
 * @param <S> 是请求的数据类型
 */

public abstract class BaseRecyclerViewFragment<T, S> extends BaseFragment implements BaseRecyclerViewContract.View<T, S>, RecyclerDataSource<T, S> {
    protected abstract BaseRecyclerAdapter addRecyclerAdapter();

    protected BaseRecyclerAdapter adapter;
    protected SwipeRefreshLayout swipeRefresh;
    protected RecyclerView recyclerView;
    private BaseRecyclerViewContract.Presenter mPresenter;
    private int pageNo = 1;

    public int getPageNo() {
        return pageNo;
    }

    @Override
    public void init() {
        mPresenter = new BaseRecyclerViewPresenter(this,
                RecyclerDataRepository.getInstance(this, LocalRecyclerDataSource.getInstance()));

        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerView);
        swipeRefresh = (SwipeRefreshLayout) getView().findViewById(R.id.swiperefresh);

        adapter = adapter == null ? addRecyclerAdapter() : adapter;

        swipeRefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNo = 1;
                mPresenter.upData();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageNo++;
                mPresenter.upData();
            }
        });

        swipeRefresh.setRefreshing(true);
        mPresenter.upData();
    }

    @Override
    public void onSuccess(T t) {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void setPresenter(BaseRecyclerViewContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showNetworkFail(String err) {
        swipeRefresh.setRefreshing(false);
        if (pageNo == adapter.getFirstPageNo()){
            adapter.showNetWorkErrorView();
        }
    }
}

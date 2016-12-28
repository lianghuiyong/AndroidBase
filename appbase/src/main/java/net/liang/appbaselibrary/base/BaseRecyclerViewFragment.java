package net.liang.appbaselibrary.base;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.chad.library.adapter.base.BaseQuickAdapter;

import net.liang.appbaselibrary.R;

import java.lang.reflect.Type;


/**
 * Created on 2016/10/23.
 * By lianghuiyong@outlook.com
 * @param <T> 是获取过来的数据类型
 * @param <S> 是请求的数据类型
 */

public abstract class BaseRecyclerViewFragment<T, S> extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected BaseRecyclerAdapter adapter;

    RecyclerView recyclerView;
    SwipeRefreshLayout swiperefresh;

    private volatile int PAGENO = 1;
    private int PAGESIZE = 10;

    @Override
    public void init() {
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        swiperefresh = (SwipeRefreshLayout) getActivity().findViewById(R.id.swiperefresh);

        adapter = adapter == null ? getRecyclerAdapter() : adapter;

        swiperefresh.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swiperefresh.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                PAGENO++;
            }
        });
    }


    protected abstract BaseRecyclerAdapter getRecyclerAdapter();

    protected abstract S getSendBody();

    protected abstract String getAddress();

    protected abstract Type getType();
}

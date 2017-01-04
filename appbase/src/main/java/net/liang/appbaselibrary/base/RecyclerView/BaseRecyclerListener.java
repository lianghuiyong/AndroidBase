package net.liang.appbaselibrary.base.RecyclerView;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lianghuiyong on 2016/6/11.
 */
public abstract class BaseRecyclerListener extends RecyclerView.OnScrollListener {
    private static final int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean controlsVisible = true;
    LinearLayoutManager layoutManager;
    BaseRecyclerAdapter baseAdapter;

    public BaseRecyclerListener(LinearLayoutManager layoutManager, BaseRecyclerAdapter baseAdapter){
        this.layoutManager = layoutManager;
        this.baseAdapter = baseAdapter;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        if (lastVisibleItemPosition + 1 == baseAdapter.getItemCount()) {
            //上拉到底部
            onLoadMore();
        }

        if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
            onSlide();
            controlsVisible = false;
            scrolledDistance = 0;
        }
        else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
            onDescent();
            controlsVisible = true;
            scrolledDistance = 0;
        }
        if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
            scrolledDistance += dy;
        }

    }

    //上拉到底部加载更多接口
    public abstract void onLoadMore();

    //上滑
    public abstract void onSlide();

    //下滑
    public abstract void onDescent();
}

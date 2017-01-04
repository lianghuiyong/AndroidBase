package net.liang.appbaselibrary.base.RecyclerView;


import com.chad.library.adapter.base.loadmore.LoadMoreView;

import net.liang.appbaselibrary.R;

/**
 * Created by Dino on 11/29 0016.
 */

final class BaseCustomLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.recycler_base_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_end;
    }
}

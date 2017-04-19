package net.liang.appbaselibrary.base.RecyclerView;

import net.liang.appbaselibrary.base.mvp.MvpPresenter;
import net.liang.appbaselibrary.base.mvp.MvpView;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/25
 */

public class BaseRecyclerViewContract {
    /**
     * view接口层  处理界面
     */
    public interface View<T> extends MvpView {

        void setListRefresh(boolean isShow);

        void onListSuccess(T t, int pageNo);

        void onListError(int pageNo, Throwable error);

        BaseRecyclerAdapter addListAdapter();
    }

    /**
     * Presenter接口层 处理业务
     */
    public interface Presenter extends MvpPresenter {
        int getListPageNo();

        void setListPageNo(int pageNo);

        void onListRefresh();

        void onListLoadMore();

        void onListUpData(int pageNo);
    }
}

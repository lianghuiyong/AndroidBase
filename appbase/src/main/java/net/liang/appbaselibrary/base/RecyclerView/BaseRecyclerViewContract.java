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
    public interface View<T, S> extends MvpView {

        void onListSuccess(T t);

        S addListSendBody();
    }

    /**
     * Presenter接口层 处理业务
     */
    public interface Presenter extends MvpPresenter {

        void onListUpData();
    }
}

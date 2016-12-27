package net.liang.appbaselibrary.base;

import java.util.List;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/25
 */

public class BaseRecyclerViewContract {
    /**
     * view接口层  处理界面
     */
    interface View<T,S> extends BaseView<BaseRecyclerViewContract.Presenter> {
        /**
         * 显示请假列表
         */
        void showList(List<T> listData);

        /**
         * 请求数据错误
         */
        void showNetworkFail(String err);

        S getSendBody();
    }

    /**
     * Presenter接口层 处理业务
     */
    interface Presenter extends BasePresenter {
        void onRefresh();

        void loadMore();
    }
}

package net.liang.appbaselibrary.base;

import java.util.List;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/25
 */

public class BaseRecyclerViewContract<T> {
    /**
     * view接口层  处理界面
     */
    interface View<T> extends BaseView<BaseRecyclerViewContract.Presenter> {
        /**
         * 显示请假列表
         */
        void showList(List<T> signStateBeanList);

        /**
         * 请求数据错误
         */
        void showNetworkFail(String err);
    }

    /**
     * Presenter接口层 处理业务
     */
    interface Presenter extends BasePresenter {
        void onRefresh();

        void loadMore();
    }
}

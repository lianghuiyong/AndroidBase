package net.liang.appbaselibrary.base;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/25
 */

public class BaseRecyclerViewContract {
    /**
     * view接口层  处理界面
     */
    interface View<T, S> extends BaseView<BaseRecyclerViewContract.Presenter> {
        void onSuccess(T t);

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

        void upData();
    }
}

package net.liang.appbaselibrary.base.RecyclerView;

import android.support.annotation.NonNull;

import net.liang.appbaselibrary.base.mvp.BasePresenter;
import net.liang.appbaselibrary.data.RecyclerDataRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/25
 */

public class BaseRecyclerViewPresenter<T> extends BasePresenter implements BaseRecyclerViewContract.Presenter {

    private int pageNo = 1;

    @NonNull
    private BaseRecyclerViewContract.View<T> view;

    @NonNull
    private RecyclerDataRepository<T> repository;

    public BaseRecyclerViewPresenter( BaseRecyclerViewContract.View<T> view,
                                      RecyclerDataRepository<T> repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public int getListPageNo() {
        return pageNo;
    }

    @Override
    public void setListPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public void onListRefresh() {
        view.setListRefresh(true);
        setListPageNo(1);
        onListUpData(getListPageNo());
    }

    @Override
    public void onListLoadMore() {
        setListPageNo(getListPageNo()+1);
        onListUpData(getListPageNo());
    }

    @Override
    public void onListUpData(final int pageNo) {
        Disposable disposable = repository
                .onListGetData(pageNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T value) {
                        view.setListRefresh(false);
                        view.onListSuccess(value,pageNo);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setListRefresh(false);
                        if (getListPageNo() == 1){
                            view.showNetworkFail("网络错误");
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        disposables.add(disposable);
    }

}

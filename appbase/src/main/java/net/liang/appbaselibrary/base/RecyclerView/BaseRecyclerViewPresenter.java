package net.liang.appbaselibrary.base.RecyclerView;

import android.support.annotation.NonNull;

import net.liang.appbaselibrary.base.mvp.BasePresenter;
import net.liang.appbaselibrary.data.RecyclerDataRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/25
 */

public class BaseRecyclerViewPresenter<T> extends BasePresenter implements BaseRecyclerViewContract.Presenter {

    @NonNull
    private BaseRecyclerViewContract.View<T> view;

    @NonNull
    private RecyclerDataRepository<T> repository;

    public BaseRecyclerViewPresenter(@NonNull BaseRecyclerViewContract.View<T> view,
                                     @NonNull RecyclerDataRepository<T> repository) {
        this.view = checkNotNull(view, "view cannot be null!");
        this.repository = checkNotNull(repository, "repository cannot be null!");
    }

    @Override
    public void onListUpData() {
        Disposable disposable = repository
                .onListGetData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<T>() {
                    @Override
                    public void onNext(T value) {
                        view.onListSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showNetworkFail("网络错误");
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        disposables.add(disposable);
    }
}

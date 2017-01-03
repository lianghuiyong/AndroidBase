package net.liang.appbaselibrary.base;

import android.support.annotation.NonNull;

import net.liang.appbaselibrary.data.RecyclerDataRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/25
 */

public class BaseRecyclerViewPresenter<T,S> implements BaseRecyclerViewContract.Presenter{

    @NonNull
    private BaseRecyclerViewContract.View<T,S> view;

    @NonNull
    private RecyclerDataRepository<T, S> repository;

    @NonNull
    private CompositeDisposable disposables;

    public BaseRecyclerViewPresenter(@NonNull BaseRecyclerViewContract.View view,
                                     @NonNull RecyclerDataRepository repository) {
        this.view = checkNotNull(view, "view cannot be null!");
        this.repository = checkNotNull(repository, "repository cannot be null!");
        disposables = new CompositeDisposable();

    }

    @Override
    public void upData(){
        //获取列表数据
            Disposable disposable = repository
                    .getData(view.getSendBody())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<T>() {
                        @Override
                        public void onNext(T value) {
                            view.onSuccess(value);
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

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}

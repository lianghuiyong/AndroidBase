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
    private RecyclerDataRepository<T,S> repository;

    @NonNull
    private CompositeDisposable disposables;
    private int pageNo = 1;
    private int pageSize = 10;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public BaseRecyclerViewPresenter(@NonNull BaseRecyclerViewContract.View view,
                                     @NonNull RecyclerDataRepository<T,S> repository) {
        this.view = checkNotNull(view, "view cannot be null!");
        this.repository = checkNotNull(repository, "repository cannot be null!");
        disposables = new CompositeDisposable();

        view.setPresenter(this);
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        view.getSendBody();
        upData(view.getSendBody());
    }

    @Override
    public void loadMore() {
        pageNo++;
        upData(view.getSendBody());
    }

    public void upData(S sendBody ){
        //获取列表数据
            Disposable disposable = repository
                    .getData(sendBody)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableObserver<T>() {
                        @Override
                        public void onNext(T value) {

                        }

                        @Override
                        public void onError(Throwable e) {

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

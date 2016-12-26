package net.liang.appbaselibrary.base;

import android.support.annotation.NonNull;

import net.liang.appbaselibrary.data.RecyclerDataRepository;

import io.reactivex.disposables.CompositeDisposable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/25
 */

public class BaseRecyclerViewPresenter implements BaseRecyclerViewContract.Presenter{

    @NonNull
    private BaseRecyclerViewContract.View view;

    @NonNull
    private RecyclerDataRepository repository;

    @NonNull
    private CompositeDisposable disposables;

    public BaseRecyclerViewPresenter(@NonNull BaseRecyclerViewContract.View view,
                                     @NonNull RecyclerDataRepository repository) {
        this.view = checkNotNull(view, "view cannot be null!");
        this.repository = checkNotNull(repository, "repository cannot be null!");
        disposables = new CompositeDisposable();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}

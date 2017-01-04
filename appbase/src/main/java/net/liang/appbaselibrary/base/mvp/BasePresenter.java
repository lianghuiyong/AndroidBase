package net.liang.appbaselibrary.base.mvp;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by lenovo on 2017/1/4.
 */

public class BasePresenter implements MvpPresenter {

    @NonNull
    protected CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        if (disposables != null && disposables.size() > 0){
            disposables.clear();
        }
    }
}

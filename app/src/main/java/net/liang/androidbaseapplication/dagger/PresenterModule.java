package net.liang.androidbaseapplication.dagger;

import net.liang.androidbaseapplication.dagger.scope.ActivityScoped;
import net.liang.appbaselibrary.base.mvp.MvpView;

import dagger.Module;
import dagger.Provides;

/**
 */
@Module
public class PresenterModule {

    private final MvpView mView;

    //@ActivityScoped
    public PresenterModule(MvpView view) {
        mView = view;
    }

    @Provides
    @ActivityScoped
    MvpView provideTasksContractView() {
        return mView;
    }

}

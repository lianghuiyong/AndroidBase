package net.liang.androidbaseapplication.dagger;

import net.liang.androidbaseapplication.dagger.scope.ActivityScoped;
import net.liang.appbaselibrary.base.mvp.MvpView;

import dagger.Module;
import dagger.Provides;

/**
 * V注入P的Module，由于V有共同的抽象层MvpView，所以只要P的初始化参数为MvpView类型，
 * 则，继承MvpView的V只需要在ViewComponent添加注入清单即可！
 */
@Module
public class PresenterModule {

    private final MvpView mView;

    public PresenterModule(MvpView view) {
        mView = view;
    }

    @Provides
    @ActivityScoped
    MvpView provideTasksContractView() {
        return mView;
    }

}

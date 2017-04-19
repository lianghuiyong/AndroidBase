package net.liang.androidbaseapplication.dagger;


import net.liang.androidbaseapplication.dagger.scope.ActivityScoped;
import net.liang.androidbaseapplication.mvp.daggerlist.Test_DaggerListActivity;
import net.liang.androidbaseapplication.mvp.daggernormal.Test_DaggerNormalActivity;

import dagger.Component;

/**
 * View注入P的参数清单
 *
 * 如果该V是集成MvpView,则在下列新增对应的V方法
 * 比如：
 *      Test_DaggerListActivity
 * 新增：
 *      void inject(Test_DaggerListActivity activity);
 *
 * 否则：另建与之相对应的Component
 */
@ActivityScoped
@Component(dependencies = RepositoryComponent.class, modules = PresenterModule.class)
public interface ViewComponent {

    void inject(Test_DaggerListActivity activity);
    void inject(Test_DaggerNormalActivity activity);
}

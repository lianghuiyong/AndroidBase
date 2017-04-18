package net.liang.androidbaseapplication.dagger;


import net.liang.androidbaseapplication.dagger.scope.ActivityScoped;
import net.liang.androidbaseapplication.mvp.daggerlist.Test_DaggerListActivity;
import net.liang.androidbaseapplication.mvp.daggernormal.Test_DaggerNormalActivity;

import dagger.Component;

/**
 * This is a Dagger component. Refer to {@link ToDoApplication} for the list of Dagger components
 * used in this application.
 * <P>
 * Because this component depends on the {@link TasksRepositoryComponent}, which is a singleton, a
 * scope must be specified. All fragment components use a custom scope for this purpose.
 */
@ActivityScoped
@Component(dependencies = RepositoryComponent.class, modules = PresenterModule.class)
public interface ViewComponent {

    void inject(Test_DaggerListActivity activity);
    void inject(Test_DaggerNormalActivity activity);
}

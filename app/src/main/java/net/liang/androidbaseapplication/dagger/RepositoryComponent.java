package net.liang.androidbaseapplication.dagger;


import net.liang.androidbaseapplication.data.Test1RepositoryModule;
import net.liang.androidbaseapplication.data.Test2RepositoryModule;
import net.liang.androidbaseapplication.data.repository.Test1Repository;
import net.liang.androidbaseapplication.data.repository.Test2Repository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * This is a Dagger component. Refer to {@link com.tyky.fda.AppConfig} for the list of Dagger components
 * used in this application.
 * <P>
 * Even though Dagger allows annotating a {@link Component @Component} as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in {@link
 * com.tyky.fda.AppConfig}.
 */
@Singleton
@Component(modules = {Test1RepositoryModule.class,
        Test2RepositoryModule.class})
public interface RepositoryComponent {

    Test1Repository getTest1Repository();
    Test2Repository getTest2Repository();
}

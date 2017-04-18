package net.liang.androidbaseapplication.data;



import net.liang.androidbaseapplication.dagger.scope.Local;
import net.liang.androidbaseapplication.dagger.scope.Remote;
import net.liang.androidbaseapplication.data.local.Test2LocalDataSource;
import net.liang.androidbaseapplication.data.remote.Test2RemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This is used by Dagger to inject the required arguments into the {@link TestRepository}.
 */
@Module
public class Test2RepositoryModule {

    @Singleton
    @Provides
    @Local
    Test2LocalDataSource provideTest2LocalDataSource() {
        return new Test2LocalDataSource();
    }

    @Singleton
    @Provides
    @Remote
    Test2RemoteDataSource provideTest2RemoteDataSource() {
        return new Test2RemoteDataSource();
    }

}

package net.liang.androidbaseapplication.data;

import net.liang.androidbaseapplication.dagger.scope.Local;
import net.liang.androidbaseapplication.dagger.scope.Remote;
import net.liang.androidbaseapplication.data.local.Test1LocalDataSource;
import net.liang.androidbaseapplication.data.remote.Test1RemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Test1Repository的module
 */
@Module
public class Test1RepositoryModule {

    @Singleton
    @Provides
    @Local
    Test1LocalDataSource provideTest1LocalDataSource() {
        return new Test1LocalDataSource();
    }

    @Singleton
    @Provides
    @Remote
    Test1RemoteDataSource provideTest1RemoteDataSource() {
        return new Test1RemoteDataSource();
    }

}

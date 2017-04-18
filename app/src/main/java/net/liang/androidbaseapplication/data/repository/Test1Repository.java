package net.liang.androidbaseapplication.data.repository;


import net.liang.androidbaseapplication.dagger.scope.Local;
import net.liang.androidbaseapplication.dagger.scope.Remote;
import net.liang.androidbaseapplication.data.local.Test1LocalDataSource;
import net.liang.androidbaseapplication.data.remote.Test1RemoteDataSource;
import net.liang.androidbaseapplication.network.Test1Api;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Liang on 2017/4/15.
 */

public class Test1Repository implements Test1Api {

    private final Test1Api mTest1RemoteDataSource;

    private final Test1Api mTest1LocalDataSource;

    @Inject
    public Test1Repository(@Local Test1LocalDataSource mTest1LocalDataSource, @Remote Test1RemoteDataSource mTest1RemoteDataSource) {
        this.mTest1RemoteDataSource = mTest1RemoteDataSource;
        this.mTest1LocalDataSource = mTest1LocalDataSource;
    }

    @Override
    public List<String> testGet() {
        return mTest1LocalDataSource.testGet();
    }
}

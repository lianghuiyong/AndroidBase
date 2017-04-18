package net.liang.androidbaseapplication.data.repository;

import net.liang.androidbaseapplication.dagger.scope.Local;
import net.liang.androidbaseapplication.dagger.scope.Remote;
import net.liang.androidbaseapplication.data.local.Test2LocalDataSource;
import net.liang.androidbaseapplication.data.remote.Test2RemoteDataSource;
import net.liang.androidbaseapplication.network.Test2Api;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Liang on 2017/4/15.
 */

public class Test2Repository implements Test2Api {

    private final Test2Api mTest2RemoteDataSource;

    private final Test2Api mTest2LocalDataSource;

    @Inject
    public Test2Repository(@Local Test2LocalDataSource mTestLocalDataSource, @Remote Test2RemoteDataSource mTestRemoteDataSource) {
        this.mTest2RemoteDataSource = mTestRemoteDataSource;
        this.mTest2LocalDataSource = mTestLocalDataSource;
    }

    @Override
    public List<String> testGet() {
        return mTest2LocalDataSource.testGet();
    }
}

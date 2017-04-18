package net.liang.androidbaseapplication.data.remote;


import net.liang.androidbaseapplication.network.Test2Api;

import java.util.List;

import javax.inject.Singleton;

/**
 * Created by Liang on 2017/4/15.
 */

@Singleton
public class Test2RemoteDataSource implements Test2Api {

    @Override
    public List<String> testGet() {
        return null;
    }
}

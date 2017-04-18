package net.liang.androidbaseapplication.data.remote;

import net.liang.androidbaseapplication.network.Test1Api;

import java.util.List;

import javax.inject.Singleton;

/**
 * Created by Liang on 2017/4/15.
 */

@Singleton
public class Test1RemoteDataSource implements Test1Api {

    @Override
    public List<String> testGet() {
        return null;
    }
}

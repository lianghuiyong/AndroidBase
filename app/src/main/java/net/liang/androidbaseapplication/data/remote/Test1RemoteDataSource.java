package net.liang.androidbaseapplication.data.remote;

import net.liang.androidbaseapplication.network.Test1Api;

import java.util.List;

import javax.inject.Singleton;

/**
 *
 * 数据remote实现方式
 */

public class Test1RemoteDataSource implements Test1Api {

    @Override
    public List<String> testGet() {
        return null;
    }
}

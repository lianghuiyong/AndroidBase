package net.liang.androidbaseapplication.data.local;

import net.liang.androidbaseapplication.network.Test1Api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

/**
 *
 * 数据local实现方式
 */
public class Test1LocalDataSource implements Test1Api {

    @Override
    public List<String> testGet() {

        List<String> list = new ArrayList<>();
        list.add("数据源一：1");
        list.add("数据源一：2");
        list.add("数据源一：3");
        list.add("数据源一：4");
        list.add("数据源一：5");
        list.add("数据源一：6");
        list.add("数据源一：7");
        list.add("数据源一：8");
        return list;
    }
}

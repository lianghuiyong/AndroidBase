package net.liang.androidbaseapplication.data.local;


import net.liang.androidbaseapplication.network.Test2Api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

/**
 *
 * 数据local实现方式
 */
public class Test2LocalDataSource implements Test2Api {

    @Override
    public List<String> testGet() {

        List<String> list = new ArrayList<>();
        list.add("数据源二：1");
        list.add("数据源二：2");
        list.add("数据源二：3");
        list.add("数据源二：4");
        list.add("数据源二：5");
        list.add("数据源二：6");
        list.add("数据源二：7");
        list.add("数据源二：8");
        return list;
    }
}

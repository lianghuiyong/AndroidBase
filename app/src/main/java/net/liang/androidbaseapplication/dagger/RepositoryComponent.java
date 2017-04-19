package net.liang.androidbaseapplication.dagger;


import net.liang.androidbaseapplication.data.Test1RepositoryModule;
import net.liang.androidbaseapplication.data.Test2RepositoryModule;
import net.liang.androidbaseapplication.data.repository.Test1Repository;
import net.liang.androidbaseapplication.data.repository.Test2Repository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 数据源注入清单
 * 每个数据源仓库，要有与之对应的数据源Module
 * 比如：Test1Repository 对应 Test1RepositoryModule
 */
@Singleton
@Component(modules = {Test1RepositoryModule.class,
        Test2RepositoryModule.class})
public interface RepositoryComponent {

    Test1Repository getTest1Repository();
    Test2Repository getTest2Repository();
}

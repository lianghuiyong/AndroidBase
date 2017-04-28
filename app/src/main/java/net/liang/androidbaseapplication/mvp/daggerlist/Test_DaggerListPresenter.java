package net.liang.androidbaseapplication.mvp.daggerlist;

import net.liang.androidbaseapplication.data.repository.Test1Repository;
import net.liang.androidbaseapplication.data.repository.Test2Repository;
import net.liang.appbaselibrary.base.mvp.BasePresenter;
import net.liang.appbaselibrary.base.mvp.MvpView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Liang on 2017/4/18.
 */

public class Test_DaggerListPresenter extends BasePresenter implements Test_DaggerListContract.Presenter {
    private final Test_DaggerListContract.View mView;

    @Inject
    Test1Repository repository1;

    @Inject
    Test2Repository repository2;

    @Inject
    public Test_DaggerListPresenter(MvpView mView) {
        this.mView = (Test_DaggerListContract.View)mView;
    }

    @Override
    public List<String> getListData() {
        List<String> listFinal = new ArrayList<>();
        listFinal.addAll(repository1.testGet());
        listFinal.addAll(repository2.testGet());

        return listFinal;
    }
}

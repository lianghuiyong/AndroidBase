package net.liang.androidbaseapplication.mvp.daggernormal;

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

public class Test_DaggerNormalPresenter extends BasePresenter implements Test_DaggerNormalContract.Presenter {
    private final Test_DaggerNormalContract.View mView;

    @Inject
    Test1Repository repository;


    @Inject
    public Test_DaggerNormalPresenter(MvpView mView) {
        this.mView = (Test_DaggerNormalContract.View)mView;
    }

    @Override
    public void getListData() {
        mView.showList(repository.testGet());
    }
}

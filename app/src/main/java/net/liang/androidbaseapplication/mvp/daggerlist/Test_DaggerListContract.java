package net.liang.androidbaseapplication.mvp.daggerlist;

import net.liang.appbaselibrary.base.mvp.MvpPresenter;
import net.liang.appbaselibrary.base.mvp.MvpView;

import java.util.List;

/**
 * Created by Liang on 2017/4/18.
 */

public class Test_DaggerListContract {
    public interface View extends MvpView{
    }

    public interface Presenter extends MvpPresenter{
        List<String> getListData();
    }
}

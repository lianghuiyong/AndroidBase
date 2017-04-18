package net.liang.androidbaseapplication.mvp.daggernormal;

import net.liang.appbaselibrary.base.mvp.MvpPresenter;
import net.liang.appbaselibrary.base.mvp.MvpView;

import java.util.List;

/**
 * Created by Liang on 2017/4/18.
 */

public class Test_DaggerNormalContract{
    public interface View extends MvpView{
        void showList(List<String> list);
    }

    public interface Presenter extends MvpPresenter{
        void getListData();
    }
}

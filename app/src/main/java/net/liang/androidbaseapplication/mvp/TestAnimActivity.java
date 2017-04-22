package net.liang.androidbaseapplication.mvp;

import net.liang.androidbaseapplication.R;
import net.liang.appbaselibrary.base.BaseAppCompatActivity;
import net.liang.appbaselibrary.base.mvp.MvpPresenter;

/**
 * Created by Dino on 4/22 0022.
 */

public class TestAnimActivity extends BaseAppCompatActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_anim;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    public void init() {
        super.init();
        setToolbarCentel(true,"共享元素");
        setRevealAnim(findViewById(R.id.rootView));
    }
}

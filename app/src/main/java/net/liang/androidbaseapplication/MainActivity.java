package net.liang.androidbaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import net.liang.appbaselibrary.base.BaseAppCompatActivity;
import net.liang.appbaselibrary.base.mvp.MvpPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseAppCompatActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    public void init() {
        setToolbarCentel_tv(true,"测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试","哈哈");
    }

    @OnClick({R.id.baseRecyclerViewActivity, R.id.baseRecyclerViewFragment})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.baseRecyclerViewActivity:
                intent.setClass(this,Test_BaseRecyclerViewActivity.class);
                startActivity(intent);
                break;

            case R.id.baseRecyclerViewFragment:
                intent.setClass(this,Test_BaseRecyclerViewFragment.class);
                startActivity(intent);
                break;
        }

    }
}

package net.liang.androidbaseapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.socks.library.KLog;

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
        setToolbarCentel_tv(true, "测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试", "哈哈");
    }

    @OnClick({R.id.baseRecyclerViewActivity, R.id.baseRecyclerViewFragment})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.baseRecyclerViewActivity:
                intent.setClass(this, Test_BaseRecyclerViewActivity.class);
                startActivity(intent);
                break;

            case R.id.baseRecyclerViewFragment:
                intent.setClass(this, Test_BaseRecyclerViewFragment.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.right_tv)
    public void onClick() {
        KLog.e("55555555555");
    }
}

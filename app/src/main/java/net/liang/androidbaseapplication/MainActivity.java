package net.liang.androidbaseapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import net.liang.androidbaseapplication.mvp.Test_AnimActivity;
import net.liang.androidbaseapplication.mvp.Test_BaseRecyclerViewActivity;
import net.liang.androidbaseapplication.mvp.Test_BaseRecyclerViewFragment;
import net.liang.androidbaseapplication.mvp.Test_MultiActivity;
import net.liang.androidbaseapplication.mvp.Test_NetRecyclerViewActivity;
import net.liang.androidbaseapplication.mvp.daggerlist.Test_DaggerListActivity;
import net.liang.androidbaseapplication.mvp.daggernormal.Test_DaggerNormalActivity;
import net.liang.appbaselibrary.NetWorkStateReceiver;
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
        setToolbarCentel(false, "测试");
    }

    @OnClick({R.id.baseRecyclerViewActivity,
            R.id.baseRecyclerViewFragment,
            R.id.net_RecyclerViewActivity,
            R.id.dagger_normal_use,
            R.id.dagger_list_use,
            R.id.test_anim,
            R.id.multi_list})
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

            case R.id.net_RecyclerViewActivity:
                intent.setClass(this, Test_NetRecyclerViewActivity.class);
                startActivity(intent);
                break;

            case R.id.dagger_normal_use:
                intent.setClass(this, Test_DaggerNormalActivity.class);
                startActivity(intent);
                break;

            case R.id.dagger_list_use:
                intent.setClass(this, Test_DaggerListActivity.class);
                startActivity(intent);
                break;
            case R.id.test_anim:
                nextActivityWithAnim(Test_AnimActivity.class,null,ANIM_FADE,
                        Pair.create(findViewById(R.id.test_anim_back),"toolbar_back"),
                        Pair.create(findViewById(R.id.ic_launcher),"ic_launcher"));
                break;

            case R.id.multi_list:
                intent.setClass(this, Test_MultiActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        startNetReciver();
    }

    //动态启动网络监听广播
    private void startNetReciver(){
        IntentFilter mFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        NetWorkStateReceiver mReceiver = new NetWorkStateReceiver();
        registerReceiver(mReceiver, mFilter);
    }
}

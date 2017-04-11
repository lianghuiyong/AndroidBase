package net.liang.androidbaseapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import net.liang.appbaselibrary.NetWorkStateReceiver;
import net.liang.appbaselibrary.base.BaseAppCompatActivity;
import net.liang.appbaselibrary.base.mvp.MvpPresenter;
import net.liang.appbaselibrary.utils.ToastUtils;

import butterknife.BindView;
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
        setToolbarCentel_tv(true, "测试测", "哈哈");

        ToastUtils.showSuccessToast("Success");
        ToastUtils.showErrorToast("Error");
        ToastUtils.showToast("Toast");
    }

    @OnClick({R.id.baseRecyclerViewActivity, R.id.baseRecyclerViewFragment, R.id.net_RecyclerViewActivity})
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
                intent.setClass(this, NetRecyclerViewActivity.class);
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

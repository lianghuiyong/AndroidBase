package net.liang.appbaselibrary.base;

import android.content.ComponentName;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.dino.changeskin.SkinManager;
import com.socks.library.KLog;

import net.liang.appbaselibrary.AppManager;
import net.liang.appbaselibrary.NetWorkStateReceiver;
import net.liang.appbaselibrary.R;
import net.liang.appbaselibrary.base.mvp.MvpPresenter;
import net.liang.appbaselibrary.base.mvp.MvpView;
import net.liang.appbaselibrary.utils.NetworkUtils;
import net.liang.appbaselibrary.utils.StringUtils;
import net.liang.appbaselibrary.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;

/**
 * Created by lianghuiyong@outlook.com on 2016/5/25.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity implements BaseViewInterface, MvpView {

    protected abstract int getLayoutId();

    protected abstract MvpPresenter getPresenter();

    private ViewDataBinding binding;
    protected Toolbar toolbar;
    private View view_netInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);

        //换肤注册
        SkinManager.getInstance().register(this);

        binding = DataBindingUtil.setContentView(this, getLayoutId());

        if (isUseButterKnife()){
            ButterKnife.bind(this, getView());
        }

        EventBus.getDefault().register(this);

        view_netInfo = findViewById(R.id.tv_netWorkInfo);

        initRecyclerView();
        init();
        initTabs();

        //初始化网络状态
        initNetWorkInfo(NetworkUtils.isConnected(this));
    }

    @Override
    public boolean isUseButterKnife() {
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getPresenter() != null) {
            getPresenter().subscribe();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (getPresenter() != null) {
            getPresenter().unSubscribe();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        AppManager.getAppManager().finishActivity(this);
        //换肤注册
        SkinManager.getInstance().unregister(this);
    }

    protected ViewDataBinding getBinding() {
        return binding;
    }

    public View getView() {
        return binding.getRoot();
    }

    @Override
    public void init() {

    }

    @Override
    public void initRecyclerView() {

    }

    @Override
    public void initTabs() {

    }

    @Override
    public void showNetworkFail() {
        if (NetworkUtils.isConnected(getBaseContext())){
            showToast("加载失败!");
        }else {
            showToast("网咯不给力，请检查网络设置!");
        }
    }

    @Override
    public void showNetworkFail(String err) {
        showToast(err);
    }

    @Override
    public void showToast(String toast) {
        ToastUtils.showToast(toast);
    }

    public void setToolbar(Boolean hasBackHome) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);    //该设置要放setTitle之后，否则setTitle会无效
        getSupportActionBar().setDisplayShowTitleEnabled(false); //取消显示默认标题

        if (hasBackHome) {
            ImageView back = (ImageView) findViewById(R.id.toolbar_back);
            back.setVisibility(View.VISIBLE);

            back.setOnClickListener(view -> onBackPressed());
        }
    }

    public void setToolbar(Boolean hasBackHome, String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (StringUtils.isEmpty(title)) {
            title = getString(R.string.app_name);
        }

        toolbar.setTitle(title);

        setSupportActionBar(toolbar);   //该设置要放setTitle之后，否则setTitle会无效

        if (hasBackHome) {
            ImageView back = (ImageView) findViewById(R.id.toolbar_back);
            back.setVisibility(View.VISIBLE);

            back.setOnClickListener(view -> onBackPressed());
        }
    }

    public void setToolbarCentel(Boolean hasBackHome, String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (StringUtils.isEmpty(title)) {
            title = getString(R.string.app_name);
        }

        TextView mTitle = (TextView) findViewById(R.id.title);
        mTitle.setText(title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (hasBackHome) {
            ImageView back = (ImageView) findViewById(R.id.toolbar_back);
            back.setVisibility(View.VISIBLE);

            back.setOnClickListener(view -> onBackPressed());
        }
    }

    public void setToolbarCentel_Img(Boolean hasBackHome, String title, int imageID) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (StringUtils.isEmpty(title)) {
            title = getString(R.string.app_name);
        }

        TextView mTitle = (TextView) findViewById(R.id.title);
        mTitle.setText(title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (hasBackHome) {
            ImageView back = (ImageView) findViewById(R.id.toolbar_back);
            back.setVisibility(View.VISIBLE);

            back.setOnClickListener(view -> onBackPressed());
        }

        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(imageID);
    }

    public void setToolbarCentel_tv(Boolean hasBackHome, String title, String right_title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (StringUtils.isEmpty(title)) {
            title = getString(R.string.app_name);
        }

        TextView mTitle = (TextView) findViewById(R.id.title);
        mTitle.setText(title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (hasBackHome) {
            ImageView back = (ImageView) findViewById(R.id.toolbar_back);
            back.setVisibility(View.VISIBLE);

            back.setOnClickListener(view -> onBackPressed());
        }

        TextView right_tv = (TextView) findViewById(R.id.right_tv);
        right_tv.setText(right_title);
        right_tv.setVisibility(TextUtils.isEmpty(right_title) ? View.GONE : View.VISIBLE);
    }

    public void nextActivity(Class<?> firstCls, Class<?> SecondCls) {
        Intent[] intents = new Intent[2];
        intents[0] = Intent.makeRestartActivityTask(new ComponentName(this, SecondCls));
        intents[1] = new Intent(this, firstCls);
        startActivities(intents);
    }

    public void nextActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void nextActivityForResult(Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    public void nextActivityForResult(Class<?> cls, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    public void nextActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    //网络状态监听
    @Subscribe
    public void onEvent(NetWorkStateReceiver.NetWorkState state) {

            switch (state){
                case CONNECTED:
                    initNetWorkInfo(true);
                    break;

                case DISCONNECTED:
                    initNetWorkInfo(false);
                    break;
            }
    }

    private void initNetWorkInfo(boolean isConnect){
        if (view_netInfo != null){
            view_netInfo.setVisibility(isConnect? View.GONE:View.VISIBLE);
        }
    }

}

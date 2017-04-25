package net.liang.appbaselibrary.base;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Pair;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;


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


/**
 * Created by lianghuiyong@outlook.com on 2016/5/25.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity implements BaseViewInterface, MvpView {
    public static final String TAG_ANIM = "TAG_ANIM";
    public static final int ANIM_NORMAL = 0;
    public static final int ANIM_EXPLODE = 1;
    public static final int ANIM_SLIDE = 2;
    public static final int ANIM_FADE = 3;
    public static final int ANIM_CHANGEBOUNDS = 4;
    public static final int ANIM_CHANGECLIPBOUNDS = 5;
    public static final int ANIM_CHANGETRANSFORM = 6;
    public static final int ANIM_CHANGEIMAGETRANSFORM = 7;

    protected abstract int getLayoutId();

    protected abstract MvpPresenter getPresenter();

    private ViewDataBinding binding;
    protected Toolbar toolbar;
    private View view_netInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setSharedElementsUseOverlay(true);
            setTransition(getIntent().getIntExtra(TAG_ANIM, 0));
        }

        binding = DataBindingUtil.setContentView(this, getLayoutId());

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //初始化网络状态
        view_netInfo = findViewById(R.id.tv_netWorkInfo);
        initNetWorkInfo(NetworkUtils.isConnected(this));

        initRecyclerView();
        init();
        initTabs();
    }

    /**
     * 设置动画过渡模式
     *
     * @param animType
     */
    @TargetApi(21)
    private void setTransition(int animType) {

        switch (animType) {
            case ANIM_NORMAL:
                break;
            case ANIM_EXPLODE:
                getWindow().setEnterTransition(new Explode());
                getWindow().setExitTransition(new Explode());
                getWindow().setReenterTransition(new Explode());
                getWindow().setReturnTransition(new Explode());
                break;
            case ANIM_SLIDE:
                getWindow().setEnterTransition(new Slide());
                getWindow().setExitTransition(new Slide());
                getWindow().setReenterTransition(new Slide());
                getWindow().setReturnTransition(new Slide());
                break;
            case ANIM_FADE:
                getWindow().setEnterTransition(new Fade());
                getWindow().setExitTransition(new Fade());
                getWindow().setReenterTransition(new Fade());
                getWindow().setReturnTransition(new Fade());

                break;

            case ANIM_CHANGEBOUNDS:
                getWindow().setEnterTransition(new Fade());
                getWindow().setExitTransition(new Fade());
                getWindow().setReenterTransition(new Fade());
                getWindow().setReturnTransition(new Fade());

                getWindow().setSharedElementExitTransition(new ChangeBounds());
                getWindow().setSharedElementEnterTransition(new ChangeBounds());
                getWindow().setSharedElementReenterTransition(new ChangeBounds());
                getWindow().setSharedElementReturnTransition(new ChangeBounds());

                break;
            case ANIM_CHANGECLIPBOUNDS:
                getWindow().setEnterTransition(new Fade());
                getWindow().setExitTransition(new Fade());
                getWindow().setReenterTransition(new Fade());
                getWindow().setReturnTransition(new Fade());

                getWindow().setSharedElementExitTransition(new ChangeClipBounds());
                getWindow().setSharedElementEnterTransition(new ChangeClipBounds());
                getWindow().setSharedElementReenterTransition(new ChangeClipBounds());
                getWindow().setSharedElementReturnTransition(new ChangeClipBounds());

                break;
            case ANIM_CHANGETRANSFORM:
                getWindow().setEnterTransition(new Fade());
                getWindow().setExitTransition(new Fade());
                getWindow().setReenterTransition(new Fade());
                getWindow().setReturnTransition(new Fade());

                getWindow().setSharedElementExitTransition(new ChangeTransform());
                getWindow().setSharedElementEnterTransition(new ChangeTransform());
                getWindow().setSharedElementReenterTransition(new ChangeTransform());
                getWindow().setSharedElementReturnTransition(new ChangeTransform());
                break;
            case ANIM_CHANGEIMAGETRANSFORM:
                getWindow().setEnterTransition(new Fade());
                getWindow().setExitTransition(new Fade());
                getWindow().setReenterTransition(new Fade());
                getWindow().setReturnTransition(new Fade());

                getWindow().setSharedElementExitTransition(new ChangeImageTransform());
                getWindow().setSharedElementEnterTransition(new ChangeImageTransform());
                getWindow().setSharedElementReenterTransition(new ChangeImageTransform());
                getWindow().setSharedElementReturnTransition(new ChangeImageTransform());
                break;
        }
    }

    /**
     * 设置android 5.0页面圆形扩散动画
     * @param view
     */
    @TargetApi(21)
    public void setRevealAnim(@NonNull View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override
                public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                    v.removeOnLayoutChangeListener(this);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                                view,
                                view.getWidth() / 2,
                                view.getHeight() / 2,
                                50,
                                1000);
                        circularReveal.setInterpolator(new AccelerateDecelerateInterpolator());
                        circularReveal.setDuration(1000);
                        circularReveal.start();
                    }
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getPresenter() != null) {
            getPresenter().subscribe();
        }
    }

    @Override
    protected void onPause() {
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

    @TargetApi(21)
    public void nextActivityWithAnim(Class<?> cls, Bundle bundle, int animType, Pair<View, String>... pairs) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.putExtra(TAG_ANIM, animType);
            if (animType == ANIM_NORMAL) {
                startActivity(intent);
            } else {
                startActivity(intent,
                        ActivityOptions.makeSceneTransitionAnimation(this,
                                pairs)
                                .toBundle());
            }
        } else {
            startActivity(intent);
        }
    }

    //网络状态监听
    @Subscribe
    public void onEvent(NetWorkStateReceiver.NetWorkState state) {

        switch (state) {
            case CONNECTED:
                initNetWorkInfo(true);
                break;

            case DISCONNECTED:
                initNetWorkInfo(false);
                break;
        }
    }

    private void initNetWorkInfo(boolean isConnect) {
        if (view_netInfo != null) {
            view_netInfo.setVisibility(isConnect ? View.GONE : View.VISIBLE);
        }
    }

}

package net.liang.appbaselibrary.base;

import android.content.ComponentName;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.liang.appbaselibrary.AppManager;
import net.liang.appbaselibrary.R;
import net.liang.appbaselibrary.utils.StringUtils;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by lianghuiyong@outlook.com on 2016/5/25.
 *
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity implements BaseViewInterface {

    protected abstract int getLayoutId();

    private ViewDataBinding binding;
    protected Toolbar toolbar;
    protected CompositeDisposable disposables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);

        binding = DataBindingUtil.setContentView(this, getLayoutId());

        disposables = new CompositeDisposable();

        /*dialogHelper = new DialogHelper(this);*/

        initView();
        initData();
        initTabs();
    }

    protected ViewDataBinding getBinding(){
        return binding;
    }

    public View getView() {
        return binding.getRoot();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initTabs(){

    }

    public void setToolbar(Boolean hasBackHome) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);    //该设置要放setTitle之后，否则setTitle会无效
        getSupportActionBar().setDisplayShowTitleEnabled(false); //取消显示默认标题

        if (hasBackHome) {
            ImageView back = (ImageView) findViewById(R.id.toolbar_back);
            back.setVisibility(View.VISIBLE);

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
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

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
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

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
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

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
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

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

        TextView right_tv = (TextView) findViewById(R.id.right_tv);
        right_tv.setVisibility(View.VISIBLE);
        right_tv.setText(right_title);
    }

    public void nextActivity(Class<?> firstCls,Class<?> SecondCls){
        Intent[] intents = new Intent[2];
        intents[0] = Intent.makeRestartActivityTask(new ComponentName(this, SecondCls));
        intents[1] = new Intent(this, firstCls);
        startActivities(intents);
    }

    public void nextActivity(Class<?> cls){
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    public void nextActivityForResult(Class<?> cls,int requestCode){
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent,requestCode);
    }

    public void nextActivityForResult(Class<?> cls,int requestCode,Bundle bundle){
        Intent intent = new Intent(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent,requestCode);
    }

    public void nextActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unsubscribe();
        AppManager.getAppManager().finishActivity(this);
    }

    public void showSnackbar(View view, String s) {
        Snackbar sb = Snackbar.make(view, s, Snackbar.LENGTH_SHORT);
        sb.getView().setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        ((TextView) sb.getView().findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);//获取Snackbar的message控件，修改字体颜色
        sb.show();
    }

    protected void unsubscribe() {
        if (disposables != null && disposables.size()>0) {
            disposables.clear();
        }
    }

}

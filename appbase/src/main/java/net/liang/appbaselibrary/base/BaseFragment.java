package net.liang.appbaselibrary.base;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.socks.library.KLog;

import net.liang.appbaselibrary.R;
import net.liang.appbaselibrary.base.mvp.MvpPresenter;

import butterknife.ButterKnife;

/**
 * Created by lianghuiyong@outlook.com on 2016/6/22.
 */
public abstract class BaseFragment extends Fragment implements BaseViewInterface {

    private ViewDataBinding binding;

    protected abstract MvpPresenter getPresenter();
    protected abstract int getLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        return getView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, getView());
        init();
        initTabs();
    }

    @Override
    public void init() {

    }

    @Override
    public void initTabs() {

    }

    protected ViewDataBinding getBinding() {
        return binding;
    }

    @Override
    public View getView() {
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (getPresenter() != null) {
            KLog.e("onResume subscribe");
            getPresenter().subscribe();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (getPresenter() != null) {
            KLog.e("onResume subscribe");
            getPresenter().subscribe();
        }
    }

    public void nextActivity(Class<?> cls) {
        Intent intent = new Intent(getContext(), cls);
        startActivity(intent);
    }

    public void nextActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getContext(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}

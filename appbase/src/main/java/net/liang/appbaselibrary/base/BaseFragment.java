package net.liang.appbaselibrary.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.liang.appbaselibrary.base.mvp.MvpPresenter;
import net.liang.appbaselibrary.base.mvp.MvpView;
import net.liang.appbaselibrary.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lianghuiyong@outlook.com on 2016/6/22.
 */
public abstract class BaseFragment extends Fragment implements BaseViewInterface, MvpView {
    private Unbinder unbinder;
    private ViewDataBinding binding;

    protected abstract MvpPresenter getPresenter();

    protected abstract int getLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, binding.getRoot());
        EventBus.getDefault().register(this);
        return getView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        init();
        initTabs();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Subscribe
    public void onEvent(String string) {

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
        ToastUtils.showToast( toast);
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
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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

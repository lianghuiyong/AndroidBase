package net.liang.appbaselibrary.base;

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

import net.liang.appbaselibrary.R;

import butterknife.ButterKnife;

/**
 * Created by lianghuiyong@outlook.com on 2016/6/22.
 */
public abstract class BaseFragment extends Fragment implements BaseViewInterface {

    private ViewDataBinding binding;
//    protected DialogHelper dialogHelper;

    protected abstract int getLayoutId();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(),container,false);
        return getView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        dialogHelper = new DialogHelper(getActivity());
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

    protected ViewDataBinding  getBinding(){
        return binding;
    }

    @Override
    public View getView() {
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

/*    public void dismissProgressDialog() {
        if(dialogHelper!=null){
            dialogHelper.dismissProgressDialog();
        }
    }*/


    public void showSnackbar(View view, String s) {
        Snackbar sb = Snackbar.make(view, s, Snackbar.LENGTH_SHORT);
        sb.getView().setBackgroundColor(getResources().getColor(R.color.colorAccent));
        ((TextView) sb.getView().findViewById(R.id.snackbar_text)).setTextColor(Color.WHITE);
        sb.show();
    }

    public void showToast(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
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

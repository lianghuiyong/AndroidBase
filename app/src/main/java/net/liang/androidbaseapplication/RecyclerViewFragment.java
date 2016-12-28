package net.liang.androidbaseapplication;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.liang.appbaselibrary.base.BaseFragment;
import net.liang.appbaselibrary.base.BaseRecyclerAdapter;
import net.liang.appbaselibrary.base.BaseRecyclerViewFragment;
import net.liang.appbaselibrary.base.BindingViewHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;


/**
 * A simple {@link BaseRecyclerViewFragment} subclass.
 */
public class RecyclerViewFragment extends BaseRecyclerViewFragment<List<String>, String> {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler_view;
    }

    @Override
    protected BaseRecyclerAdapter addRecyclerAdapter() {
        return new RecyclerAdapter(getContext(), recyclerView, null);
    }

    @Override
    public void onSuccess(List<String> strings) {
        super.onSuccess(strings);
        adapter.showList(strings, getPageNo());
    }

    @Override
    public String getSendBody() {
        return "123";
    }

    @Override
    public Observable<List<String>> getData(String sendData) {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 10; i++) {
            String string = sdf.format(new Date());
            list.add(string);
        }
        return Observable.just(list);
    }

    /**
     * 带bind的适配器
     * */
    class RecyclerAdapter extends BaseRecyclerAdapter<String> {

        public RecyclerAdapter(Context context, RecyclerView recyclerView, List data) {
            super(context, recyclerView, R.layout.item_base_recyclerview_layout, data);
        }

        @Override
        protected void convert(BindingViewHolder bindingViewHolder, String s) {
            bindingViewHolder.setText(R.id.data, s);
        }
    }
}

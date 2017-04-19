package net.liang.androidbaseapplication.mvp;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.liang.androidbaseapplication.R;
import net.liang.appbaselibrary.base.BindingViewHolder;
import net.liang.appbaselibrary.base.RecyclerView.BaseRecyclerAdapter;
import net.liang.appbaselibrary.base.RecyclerView.BaseRecyclerViewFragment;
import net.liang.appbaselibrary.base.mvp.MvpPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;


/**
 * A simple {@link BaseRecyclerViewFragment} subclass.
 */
public class RecyclerViewFragment extends BaseRecyclerViewFragment<List<String>> {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recycler_view;
    }

    @Override
    public void onListSuccess(List<String> strings, int pageNo) {
        adapter.showList(strings, pageNo);
    }

    @Override
    public BaseRecyclerAdapter addListAdapter() {
        return new RecyclerAdapter(getContext(), recyclerView, null);
    }

    @Override
    public Observable<List<String>> onListGetData(int pageNo) {
        List<String> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < 10; i++) {
            String string = sdf.format(new Date());
            list.add(string);
        }
        return Observable.just(list);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 带bind的适配器
     */
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

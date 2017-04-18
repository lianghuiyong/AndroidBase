package net.liang.androidbaseapplication.mvp;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import net.liang.androidbaseapplication.R;
import net.liang.appbaselibrary.base.RecyclerView.BaseRecyclerAdapter;
import net.liang.appbaselibrary.base.RecyclerView.BaseRecyclerViewFragment;
import net.liang.appbaselibrary.base.BindingViewHolder;
import net.liang.appbaselibrary.base.mvp.MvpPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;


/**
 * A simple {@link BaseRecyclerViewFragment} subclass.
 */
public class RecyclerViewFragment extends BaseRecyclerViewFragment<List<String>> {

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

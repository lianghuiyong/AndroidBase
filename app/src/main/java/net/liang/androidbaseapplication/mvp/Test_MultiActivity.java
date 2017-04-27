package net.liang.androidbaseapplication.mvp;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.liang.androidbaseapplication.R;
import net.liang.androidbaseapplication.bean.MultiItemBean;
import net.liang.appbaselibrary.base.BaseAppCompatActivity;
import net.liang.appbaselibrary.base.BindingViewHolder;
import net.liang.appbaselibrary.base.RecyclerView.BaseMultiRecyclerAdapter;
import net.liang.appbaselibrary.base.mvp.MvpPresenter;
import net.liang.appbaselibrary.bean.MultipleItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Test_MultiActivity extends BaseAppCompatActivity {

    @BindView(R.id.list)
    RecyclerView list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test__multi;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void init() {
        super.init();

        setToolbarCentel(true, "多类型列表示例");

        List<MultipleItem> data = new ArrayList<>();
        data.add(new MultiItemBean(1, 2, "示例"));
        data.add(new MultiItemBean(2, 2, "示例"));
        data.add(new MultiItemBean(1, 2, "示例"));
        data.add(new MultiItemBean(2, 1, "示例"));
        data.add(new MultiItemBean(2, 1, "示例"));
        data.add(new MultiItemBean(1, 2, "示例"));
        data.add(new MultiItemBean(1, 2, "示例"));
        data.add(new MultiItemBean(1, 2, "示例"));
        data.add(new MultiItemBean(1, 2, "示例"));
        data.add(new MultiItemBean(1, 2, "示例"));
        data.add(new MultiItemBean(1, 2, "示例"));

        MyAdapter adapter = new MyAdapter(data);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        list.setLayoutManager(manager);
        adapter.setSpanSizeLookup((gridLayoutManager, position) -> data.get(position).getSpanSize());
        list.setAdapter(adapter);
    }

    class MyAdapter extends BaseMultiRecyclerAdapter<MultipleItem> {

        /**
         * Same as QuickAdapter#QuickAdapter(Context,int) but with
         * some initialization data.
         *
         * @param data A new list is created out of this one to avoid mutable list
         */
        public MyAdapter(List<MultipleItem> data) {
            super(data);
            addItemType(1, R.layout.item_list_comment);
            addItemType(2, R.layout.item_list_event);
        }

        @Override
        protected void convert(BindingViewHolder helper, MultipleItem item) {

        }
    }
}

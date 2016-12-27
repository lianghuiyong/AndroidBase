package net.liang.androidbaseapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import net.liang.appbaselibrary.base.BasePresenter;
import net.liang.appbaselibrary.base.BaseRecyclerAdapter;
import net.liang.appbaselibrary.base.BaseRecyclerViewContract;
import net.liang.appbaselibrary.base.BindingViewHolder;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class BaseRecyclerViewActivity extends net.liang.appbaselibrary.base.BaseRecyclerViewActivity<String,String> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_recycler_view);
    }

    @Override
    protected BaseRecyclerAdapter<String> addRecyclerAdapter() {
        return new RecyclerAdapter(this, recyclerView, null);
    }

    @Override
    public String getSendBody() {
        return "123";
    }

    class RecyclerAdapter extends BaseRecyclerAdapter<String> {

        public RecyclerAdapter(Context context, RecyclerView recyclerView, List data) {
            super(context, recyclerView, R.layout.item_base_recyclerview_layout, data);
        }

        @Override
        protected void convert(BindingViewHolder bindingViewHolder, String s) {
        }
    }

}

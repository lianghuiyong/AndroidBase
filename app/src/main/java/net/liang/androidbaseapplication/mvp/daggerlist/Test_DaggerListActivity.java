package net.liang.androidbaseapplication.mvp.daggerlist;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.liang.androidbaseapplication.R;
import net.liang.androidbaseapplication.dagger.DaggerRepositoryComponent;
import net.liang.androidbaseapplication.dagger.DaggerViewComponent;
import net.liang.androidbaseapplication.dagger.PresenterModule;
import net.liang.appbaselibrary.base.BaseAppCompatActivity;
import net.liang.appbaselibrary.base.BindingViewHolder;
import net.liang.appbaselibrary.base.RecyclerView.BaseRecyclerAdapter;
import net.liang.appbaselibrary.base.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class Test_DaggerListActivity extends BaseAppCompatActivity implements Test_DaggerListContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

    TestAdapter adapter;

    @Inject
    Test_DaggerListPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test__dagger_list;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return null;
    }

    @Override
    public void init() {
        super.init();

        setToolbarCentel(true,"Dagger示例");

        adapter = new TestAdapter(this, recyclerView, null);

        swiperefresh.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW);
        swiperefresh.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        showList(null);

        // Create the presenter
        DaggerViewComponent.builder()
                .repositoryComponent(DaggerRepositoryComponent.builder().build())
                .presenterModule(new PresenterModule(this))
                .build()
                .inject(this);

    }

    @Override
    public void showList(List<String> list) {
        adapter.showList(list);
        swiperefresh.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        presenter.getListData();
    }

    class TestAdapter extends BaseRecyclerAdapter<String> {
        public TestAdapter(Context context, RecyclerView recyclerView, List<String> data) {
            super(context, recyclerView, R.layout.item_base_recyclerview_layout, data);
        }

        @Override
        protected void convert(BindingViewHolder helper, String item) {
            helper.setText(R.id.data, item);
        }
    }
}

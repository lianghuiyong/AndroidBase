package net.liang.androidbaseapplication.mvp.daggernormal;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.socks.library.KLog;

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
import butterknife.ButterKnife;

/**
 * 该页面使用一个数据源
 */
public class Test_DaggerNormalActivity extends BaseAppCompatActivity implements Test_DaggerNormalContract.View, SwipeRefreshLayout.OnRefreshListener {

    TestAdapter adapter;

    @Inject
    Test_DaggerNormalPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;

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
        setToolbarCentel(true, "Dagger示例");

        adapter = new TestAdapter(recyclerView, null);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class TestAdapter extends BaseRecyclerAdapter<String> {
        public TestAdapter(RecyclerView recyclerView, List<String> data) {
            super(recyclerView, R.layout.item_base_recyclerview_layout, data);
        }

        @Override
        protected void convert(BindingViewHolder helper, String item) {
            helper.setText(R.id.data, item);
        }
    }
}

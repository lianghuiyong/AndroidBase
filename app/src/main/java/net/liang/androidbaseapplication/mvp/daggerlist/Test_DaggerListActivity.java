package net.liang.androidbaseapplication.mvp.daggerlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import net.liang.androidbaseapplication.R;
import net.liang.androidbaseapplication.dagger.DaggerRepositoryComponent;
import net.liang.androidbaseapplication.dagger.DaggerViewComponent;
import net.liang.androidbaseapplication.dagger.PresenterModule;
import net.liang.appbaselibrary.base.BindingViewHolder;
import net.liang.appbaselibrary.base.RecyclerView.BaseRecyclerAdapter;
import net.liang.appbaselibrary.base.RecyclerView.BaseRecyclerViewActivity;
import net.liang.appbaselibrary.base.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


/**
 * 该页面使用BaseRecyclerViewActivity基类，
 * 并使用Dagger实现两个数据源数据返回
 */
public class Test_DaggerListActivity extends BaseRecyclerViewActivity<List<String>> implements Test_DaggerListContract.View {

    @Inject
    Test_DaggerListPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test__dagger_list;
    }

    @Override
    protected MvpPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void init() {
        setToolbarCentel(true, "列表页面使用Dagger示例");

        // Create the presenter
        DaggerViewComponent.builder()
                .repositoryComponent(DaggerRepositoryComponent.builder().build())
                .presenterModule(new PresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public Observable<List<String>> onListGetData(int pageNo) {
        return Observable.just(presenter.getListData());
    }

    @Override
    public void onListSuccess(List<String> list, int pageNo) {
        adapter.showList(list);
    }

    @Override
    public BaseRecyclerAdapter addListAdapter() {
        return new TestAdapter(recyclerView, null);
    }

    class TestAdapter extends BaseRecyclerAdapter<String> {
        TestAdapter(RecyclerView recyclerView, List<String> data) {
            super(recyclerView, R.layout.item_base_recyclerview_layout, data);
        }

        @Override
        protected void convert(BindingViewHolder helper, String item) {
            helper.setText(R.id.data, item);
        }
    }
}

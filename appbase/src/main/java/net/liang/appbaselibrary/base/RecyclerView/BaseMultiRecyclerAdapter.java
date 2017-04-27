package net.liang.appbaselibrary.base.RecyclerView;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import net.liang.appbaselibrary.BR;
import net.liang.appbaselibrary.R;
import net.liang.appbaselibrary.base.BindingViewHolder;

import java.util.List;

/**
 * Created by Liang on 2017/4/27.
 */

public abstract class BaseMultiRecyclerAdapter<T extends MultiItemEntity> extends BaseMultiItemQuickAdapter<T, BindingViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public BaseMultiRecyclerAdapter(List<T> data) {
        super(data);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    @Override
    protected BindingViewHolder createBaseViewHolder(View view) {
        return new BindingViewHolder(view);
    }
}

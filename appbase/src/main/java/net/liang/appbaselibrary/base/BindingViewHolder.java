package net.liang.appbaselibrary.base;

import android.databinding.ViewDataBinding;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

import net.liang.appbaselibrary.R;

/**
 * Created by Dino on 12/20 0020.
 */

public class BindingViewHolder extends BaseViewHolder {

    public BindingViewHolder(View view) {
        super(view);
    }

    public ViewDataBinding getBinding() {
        return (ViewDataBinding)getConvertView().getTag(R.id.BaseQuickAdapter_databinding_support);
    }

}

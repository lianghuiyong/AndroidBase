package net.liang.appbaselibrary.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Liang on 2017/4/26.
 *
 * 多类型列表处理对象，勿修改
 */

public abstract class MultipleItem implements MultiItemEntity {

    private int spanSize;
    private int itemType;

    public MultipleItem() {

    }

    public MultipleItem(int itemType,int spanSize) {
        this.spanSize = spanSize;
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }
}

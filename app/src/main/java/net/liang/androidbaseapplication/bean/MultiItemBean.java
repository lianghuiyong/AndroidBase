package net.liang.androidbaseapplication.bean;

import net.liang.appbaselibrary.bean.MultipleItem;

/**
 * Created by Liang on 2017/4/27.
 */

public class MultiItemBean extends MultipleItem {
    private String name;


    public MultiItemBean(int itemType, int spanSize, String name) {
        super(itemType, spanSize);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

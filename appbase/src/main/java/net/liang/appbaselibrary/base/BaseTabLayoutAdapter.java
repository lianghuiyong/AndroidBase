package net.liang.appbaselibrary.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lianghuiyong on 2016/5/29.
 *
 * TabLayout （String）适配器
 */
public class BaseTabLayoutAdapter extends FragmentPagerAdapter {

    private List<Fragment> tab_fragments;
    private String[] tv_Titles;

    public BaseTabLayoutAdapter(FragmentManager fm,
                                String[] tv_Titles,
                                List<Fragment> tab_fragments) {
        super(fm);
        this.tv_Titles = tv_Titles;
        this.tab_fragments = tab_fragments;
    }

    public BaseTabLayoutAdapter(FragmentManager fm,
                                List<Fragment> tab_fragments) {
        super(fm);
        this.tab_fragments = tab_fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return tab_fragments.get(position);
    }

    @Override
    public int getCount() {
        return tab_fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (tv_Titles == null){
            return null;
        }else {
            return tv_Titles[position];
        }
    }

}

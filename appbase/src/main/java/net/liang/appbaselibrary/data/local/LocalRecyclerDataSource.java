package net.liang.appbaselibrary.data.local;

import android.support.annotation.Nullable;

import net.liang.appbaselibrary.data.RecyclerDataSource;

import io.reactivex.Observable;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/23
 */

public class LocalRecyclerDataSource<T, S> implements RecyclerDataSource<T, S> {
    @Nullable
    private static LocalRecyclerDataSource INSTANCE;


    public static  LocalRecyclerDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalRecyclerDataSource<>();
        }
        return INSTANCE;
    }

    @Override
    public Observable<T> getData(S sendData) {
        return null;
    }
}

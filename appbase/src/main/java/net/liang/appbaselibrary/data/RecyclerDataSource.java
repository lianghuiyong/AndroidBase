package net.liang.appbaselibrary.data;

import io.reactivex.Observable;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/12/23
 */

public interface RecyclerDataSource<T> {
    Observable<T> onListGetData(int pageNo);
}

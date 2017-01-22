package net.liang.appbaselibrary.data;

import android.support.annotation.NonNull;

import io.reactivex.Observable;


/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/23
 */

public class RecyclerDataRepository<T> implements RecyclerDataSource<T> {

    @NonNull
    private final RecyclerDataSource<T> mRemoteAttendanceDataSource;

    @NonNull
    private final RecyclerDataSource<T> mLocalAttendanceDataSource;

    public RecyclerDataRepository(RecyclerDataSource<T> remoteFoodsDataSource,
                                  RecyclerDataSource<T> localFoodsDataSource) {
        mRemoteAttendanceDataSource = remoteFoodsDataSource;
        mLocalAttendanceDataSource = localFoodsDataSource;
    }

    @Override
    public Observable<T> onListGetData(int pageNo) {
        return mRemoteAttendanceDataSource.onListGetData(pageNo);
    }
}

package net.liang.appbaselibrary.data;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/23
 */

public class RecyclerDataRepository<T> implements RecyclerDataSource<T>{

    @NonNull
    private final RecyclerDataSource<T> mRemoteAttendanceDataSource;

    @NonNull
    private final RecyclerDataSource<T> mLocalAttendanceDataSource;

    public RecyclerDataRepository(@NonNull RecyclerDataSource<T> remoteFoodsDataSource,
                                  @NonNull RecyclerDataSource<T> localFoodsDataSource) {
        mRemoteAttendanceDataSource = checkNotNull(remoteFoodsDataSource);
        mLocalAttendanceDataSource = checkNotNull(localFoodsDataSource);
    }

    @Override
    public Observable<T> onListGetData(int pageNo) {
        return mRemoteAttendanceDataSource.onListGetData(pageNo);
    }
}

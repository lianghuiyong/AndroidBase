package net.liang.appbaselibrary.data;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/23
 */

public class RecyclerDataRepository<T, S> implements RecyclerDataSource<T, S>{

    @NonNull
    private final RecyclerDataSource<T, S> mRemoteAttendanceDataSource;

    @NonNull
    private final RecyclerDataSource<T, S> mLocalAttendanceDataSource;

    public RecyclerDataRepository(@NonNull RecyclerDataSource<T, S> remoteFoodsDataSource,
                                  @NonNull RecyclerDataSource<T, S> localFoodsDataSource) {
        mRemoteAttendanceDataSource = checkNotNull(remoteFoodsDataSource);
        mLocalAttendanceDataSource = checkNotNull(localFoodsDataSource);
    }

    @Override
    public Observable<T> getData(S sendData) {
        return mRemoteAttendanceDataSource.getData(sendData);
    }
}

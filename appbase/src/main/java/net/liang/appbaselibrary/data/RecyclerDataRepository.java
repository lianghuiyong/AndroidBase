package net.liang.appbaselibrary.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.Observable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by lianghuiyong@outlook.com
 * Date on 2016/11/23
 */

public class RecyclerDataRepository<T, S> implements RecyclerDataSource<T, S>{
    @Nullable
    private static RecyclerDataRepository INSTANCE = null;

    @NonNull
    private final RecyclerDataSource mRemoteAttendanceDataSource;

    @NonNull
    private final RecyclerDataSource mLocalAttendanceDataSource;

    private RecyclerDataRepository(@NonNull RecyclerDataSource remoteFoodsDataSource,
                                   @NonNull RecyclerDataSource localFoodsDataSource) {
        mRemoteAttendanceDataSource = checkNotNull(remoteFoodsDataSource);
        mLocalAttendanceDataSource = checkNotNull(localFoodsDataSource);
    }

    public static RecyclerDataRepository getInstance(@NonNull RecyclerDataSource remoteFoodsDataSource,
                                            @NonNull RecyclerDataSource localFoodsDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new RecyclerDataRepository(remoteFoodsDataSource, localFoodsDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<T> getData(S sendData) {
        return mRemoteAttendanceDataSource.getData(sendData);
    }
}

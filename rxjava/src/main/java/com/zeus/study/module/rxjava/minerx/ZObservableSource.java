package com.zeus.study.module.rxjava.minerx;

import io.reactivex.annotations.NonNull;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-23.
 * =======================================
 */
public interface ZObservableSource<T> {

    void subscribe(@NonNull ZObserver<? super T> observer);

}

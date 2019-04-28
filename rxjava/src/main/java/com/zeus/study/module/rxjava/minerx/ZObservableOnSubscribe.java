package com.zeus.study.module.rxjava.minerx;

import io.reactivex.annotations.NonNull;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-23.
 * =======================================
 */
public interface ZObservableOnSubscribe<T> {
    void subscribe(@NonNull ZObservableEmitter<T> emitter) throws Exception;
}

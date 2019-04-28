package com.zeus.study.module.rxjava.minerx;

import io.reactivex.annotations.NonNull;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-23.
 * =======================================
 */
public interface ZEmitter<T> {

    void onNext(@NonNull T msg);

    void onError(@NonNull Throwable throwable);

    void onComplete();
}

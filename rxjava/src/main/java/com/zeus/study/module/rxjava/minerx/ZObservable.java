package com.zeus.study.module.rxjava.minerx;


import com.zeus.study.module.rxjava.minerx.scheduler.ZScheduler;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-23.
 * =======================================
 */
public abstract class ZObservable<T> implements ZObservableSource<T> {

    public static <T> ZObservable<T> create(ZObservableOnSubscribe<T> source) {
        return new ZObservableCreate<>(source);
    }


    @Override
    public void subscribe(ZObserver<? super T> observer) {
        subscribeActual(observer);
    }

    public final ZObservable<T> subsribeOn(ZScheduler scheduler) {
        return new ZObservableSubscribeOn<>(this, scheduler);
    }

    protected abstract void subscribeActual(ZObserver<? super T> observer);
}

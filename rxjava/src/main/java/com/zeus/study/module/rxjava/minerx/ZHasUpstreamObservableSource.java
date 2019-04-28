package com.zeus.study.module.rxjava.minerx;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-24.
 * =======================================
 */
public interface ZHasUpstreamObservableSource<T> {
    ZObservableSource<T> source();
}

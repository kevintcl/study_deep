package com.zeus.study.module.rxjava.minerx;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-24.
 * =======================================
 */
public abstract class ZAbstractObservableWithUpstream<T, U> extends ZObservable<U>
        implements ZHasUpstreamObservableSource<T> {

    protected final ZObservableSource<T> source;

    public ZAbstractObservableWithUpstream(ZObservableSource<T> source) {
        this.source = source;
    }

    @Override
    public ZObservableSource<T> source() {
        return source;
    }
}

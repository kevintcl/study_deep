package com.zeus.study.module.rxjava.minerx;

import com.zeus.study.module.rxjava.minerx.scheduler.ZScheduler;

import java.util.concurrent.atomic.AtomicReference;

import sun.security.krb5.internal.PAData;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-24.
 * =======================================
 */
public final class ZObservableSubscribeOn<T> extends ZAbstractObservableWithUpstream<T, T> {
    final ZScheduler scheduler;

    public ZObservableSubscribeOn(ZObservableSource<T> source, ZScheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(ZObserver<? super T> observer) {
        ZObservableSubscribeOn.SubscribeOnObserver<T> parent = new ZObservableSubscribeOn.SubscribeOnObserver<>(observer);

        observer.onSubscribe(parent);

        parent.setDisposable(scheduler.scheduleDirect(new SubscribeTask(parent)));
    }

    final class SubscribeTask implements Runnable {

        private final SubscribeOnObserver<T> parent;

        SubscribeTask(SubscribeOnObserver<T> parent) {
            this.parent = parent;
        }

        @Override
        public void run() {
            ZObservableSubscribeOn.this.source.subscribe(parent);
        }
    }

    static final class SubscribeOnObserver<T>
            extends AtomicReference<ZDisposable>
            implements ZObserver<T>, ZDisposable {

        final ZObserver<? super T> downstream;
        final AtomicReference<ZDisposable> upstream;

        SubscribeOnObserver(ZObserver<? super T> downstream) {
            this.downstream = downstream;
            this.upstream = new AtomicReference<>();
        }

        @Override
        public void dispose() {
            ZDisposableHelper.dispose(upstream);
            ZDisposableHelper.dispose(this);

        }

        @Override
        public boolean isDisposed() {
            return ZDisposableHelper.isDisposed(get());
        }

        @Override
        public void onSubscribe(ZDisposable d) {
            ZDisposableHelper.setOnce(upstream, d);
        }

        @Override
        public void onNext(T msg) {
            downstream.onNext(msg);
        }

        @Override
        public void onError(Throwable e) {
            downstream.onError(e);
        }

        @Override
        public void onComplete() {
            downstream.onComplete();
        }

        public void setDisposable(ZDisposable d) {
            ZDisposableHelper.setOnce(this, d);
        }
    }
}

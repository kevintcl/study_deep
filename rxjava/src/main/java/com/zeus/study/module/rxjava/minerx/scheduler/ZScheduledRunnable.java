package com.zeus.study.module.rxjava.minerx.scheduler;

import com.zeus.study.module.rxjava.minerx.ZDisposable;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-25.
 * =======================================
 */
public class ZScheduledRunnable extends AtomicReferenceArray<Object>
        implements Runnable, Callable<Object>, ZDisposable {

    /**
     * Indicates that the parent tracking this task has been notified about its completion.
     */
    static final Object PARENT_DISPOSED = new Object();
    /**
     * Indicates the dispose() was called from within the run/call method.
     */
    static final Object SYNC_DISPOSED = new Object();
    /**
     * Indicates the dispose() was called from another thread.
     */
    static final Object ASYNC_DISPOSED = new Object();


    static final Object DONE = new Object();

    static final int PARENT_INDEX = 0;
    static final int FUTURE_INDEX = 1;
    static final int THREAD_INDEX = 2;


    final Runnable actual;

    public ZScheduledRunnable(Runnable actual, ZDisposableContainer parent) {
        super(3);
        this.actual = actual;
        lazySet(PARENT_INDEX, parent);
    }


    @Override
    public void dispose() {

    }

    @Override
    public boolean isDisposed() {
        return false;
    }

    @Override
    public void run() {

    }

    @Override
    public Object call() throws Exception {
        return null;
    }

    public void setFuture(Future<?> f) {
        for (; ; ) {
            Object o = get(FUTURE_INDEX);
            if (o == DONE) {
                return;
            }
            if (o == SYNC_DISPOSED) {
                f.cancel(false);
                return;
            }
            if (o == ASYNC_DISPOSED) {
                f.cancel(true);
                return;
            }
            if (compareAndSet(FUTURE_INDEX, o, f)) {
                return;
            }
        }
    }
}

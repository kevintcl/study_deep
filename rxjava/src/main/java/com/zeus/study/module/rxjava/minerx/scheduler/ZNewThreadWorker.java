package com.zeus.study.module.rxjava.minerx.scheduler;

import com.zeus.study.module.rxjava.minerx.ZDisposable;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import com.zeus.study.module.rxjava.minerx.scheduler.ZScheduler.Worker;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-24.
 * =======================================
 */
public class ZNewThreadWorker extends Worker implements ZDisposable {

    private final ScheduledExecutorService executor;
    public ZNewThreadWorker(ThreadFactory threadFactory) {
        executor = ZSchedulerPoolFactory.create(threadFactory);
    }

    @Override
    public ZDisposable schedule(Runnable run, long delay, TimeUnit unit) {
        return null;
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean isDisposed() {
        return false;
    }


    public ZScheduledRunnable scheduleActual(Runnable run, long delayTime, @NonNull TimeUnit unit, @Nullable ZDisposableContainer parent) {
        Runnable decoratedRun = run;
        ZScheduledRunnable sr = new ZScheduledRunnable(decoratedRun, parent);
        if (parent != null && !parent.add(sr)) {
            return sr;
        } else {
            try {
                Object f;
                if (delayTime <= 0L) {
                    f = executor.submit((Callable<Object>)sr);
                } else {
                    f = executor.schedule((Callable<Object>)sr, delayTime, unit);
                }

                sr.setFuture((Future)f);
            } catch (RejectedExecutionException var10) {
                if (parent != null) {
                    parent.remove(sr);
                }

            }

            return sr;
        }
    }
}

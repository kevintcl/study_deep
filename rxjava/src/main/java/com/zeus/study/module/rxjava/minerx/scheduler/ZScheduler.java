package com.zeus.study.module.rxjava.minerx.scheduler;

import com.zeus.study.module.rxjava.minerx.ZDisposable;

import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-24.
 * =======================================
 */
public abstract class ZScheduler {


    public ZDisposable scheduleDirect(Runnable run) {
        return scheduleDirect(run, 0, TimeUnit.NANOSECONDS);
    }

    public ZDisposable scheduleDirect(Runnable run, long delay, TimeUnit timeUnit) {
        Worker w = createWorker();
        Runnable decorateRun = run;
        DisposeTask task = new DisposeTask(decorateRun, w);
        w.schedule(task, delay, timeUnit);
        return task;
    }

    public abstract ZScheduler.Worker createWorker();


    static final class DisposeTask implements ZDisposable, Runnable, ZSchedulerRunnableIntrospection {

        final Runnable decorateRun;

        final Worker w;

        Thread runner;

        DisposeTask(Runnable decorateRun, Worker w) {
            this.decorateRun = decorateRun;
            this.w = w;
        }

        @Override
        public void run() {
            runner = Thread.currentThread();

            try {
                decorateRun.run();
            } finally {
                dispose();
                runner = null;
            }
        }

        @Override
        public void dispose() {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }

        @Override
        public Runnable getWrappedRunnable() {
            return null;
        }


    }

    public abstract static class Worker implements ZDisposable {

        public ZDisposable schedule(@NonNull Runnable run) {
            return schedule(run, 0, TimeUnit.NANOSECONDS);
        }

        public abstract ZDisposable schedule(@NonNull Runnable run, long delay, @NonNull TimeUnit unit);
    }
}


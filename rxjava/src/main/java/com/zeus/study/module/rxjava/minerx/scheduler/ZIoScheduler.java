package com.zeus.study.module.rxjava.minerx.scheduler;

import com.zeus.study.module.rxjava.minerx.ZDisposable;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-24.
 * =======================================
 */
public final class ZIoScheduler extends ZScheduler {

    private static final String WORKER_THREAD_NAME_PREFIX = "RxCachedThreadScheduler";
    static final ZRxThreadFactory WORKER_THREAD_FACTORY;

    private static final String EVICTOR_THREAD_NAME_PREFIX = "RxCachedWorkerPoolEvictor";
    static final ZRxThreadFactory EVICTOR_THREAD_FACTORY;

    private static final String KEY_KEEP_ALIVE_TIME = "rx2.io-keep-alive-time";
    public static final long KEEP_ALIVE_TIME_DEFAULT = 60L;

    private static final long KEEP_ALIVE_TIME;
    private static final TimeUnit KEEP_ALIVE_UNIT;


    final ThreadFactory threadFactory;

    static final ZIoScheduler.CachedWorkerPool NONE;

    final AtomicReference<CachedWorkerPool> pool;
    static {
        KEEP_ALIVE_TIME = Long.getLong(KEY_KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_DEFAULT);
        KEEP_ALIVE_UNIT = TimeUnit.SECONDS;

        EVICTOR_THREAD_FACTORY = new ZRxThreadFactory(EVICTOR_THREAD_NAME_PREFIX);

        WORKER_THREAD_FACTORY = new ZRxThreadFactory(WORKER_THREAD_NAME_PREFIX);
        NONE = new CachedWorkerPool(0, null, WORKER_THREAD_FACTORY);
        NONE.shutdown();
    }

    @Override
    public Worker createWorker() {
        //TODO
        return new EventLoopWorker(pool.get());
    }


    public ZIoScheduler(ThreadFactory factory) {
        this.threadFactory = factory;
        pool = new AtomicReference<>();
        start();
    }

    public void start() {
        CachedWorkerPool update = new CachedWorkerPool(KEEP_ALIVE_TIME, KEEP_ALIVE_UNIT, threadFactory);

        //False return indicates that the actual value was not equal to the expected value
        if (!pool.compareAndSet(NONE, update)) {
            update.shutdown();
        }
    }

    static final class EventLoopWorker extends Worker {

        private final ZCompositeDisposable tasks;

        private final ZIoScheduler.CachedWorkerPool pool;
        private final ThreadWorker threadWorker;
        EventLoopWorker(CachedWorkerPool pool) {
            this.pool = pool;
            threadWorker = pool.get();
            tasks = new ZCompositeDisposable();
        }

        @Override
        public void dispose() {
            //TODO
        }

        @Override
        public boolean isDisposed() {
            //TODO
            return false;
        }

        @Override
        public ZDisposable schedule(Runnable run, long delay, TimeUnit unit) {
            //TODO
            return threadWorker.scheduleActual(run, delay, unit, tasks);
        }
    }

    static final class CachedWorkerPool implements Runnable {
        private final long keepAliveTime;
        private final ThreadFactory threadFactory;
        private final ConcurrentLinkedQueue<ThreadWorker> expiringWorkerQueue;

        private final ScheduledExecutorService evictorService;
        private final Future<?> evictorTask;


        CachedWorkerPool(long keepAliveTime, TimeUnit unit, ThreadFactory threadFactory) {
            this.keepAliveTime = keepAliveTime;
            this.threadFactory = threadFactory;
            this.expiringWorkerQueue = new ConcurrentLinkedQueue<>();

            ScheduledExecutorService evictor = null;
            Future<?> task = null;

            if (unit != null) {
                evictor = Executors.newScheduledThreadPool(1, EVICTOR_THREAD_FACTORY);
                task = evictor.scheduleWithFixedDelay(this, keepAliveTime, keepAliveTime, TimeUnit.NANOSECONDS);
            }

            evictorService = evictor;
            evictorTask = task;
        }

        @Override
        public void run() {
            evictExpiredWorkers();
        }

        void evictExpiredWorkers() {
            //TODO
        }

        void shutdown() {
            //TODO
        }

        public ThreadWorker get() {
            //TODO
            ThreadWorker threadWorker = null;
            do {
                if (expiringWorkerQueue.isEmpty()) {
                    threadWorker = new ThreadWorker(threadFactory);
                    return threadWorker;
                }
                //获取元素并且在队列中移除，如果队列为空返回null
                threadWorker = expiringWorkerQueue.poll();
            } while (threadWorker == null);

            return threadWorker;
        }
    }

    static final class ThreadWorker extends ZNewThreadWorker {

        public ThreadWorker(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }
}

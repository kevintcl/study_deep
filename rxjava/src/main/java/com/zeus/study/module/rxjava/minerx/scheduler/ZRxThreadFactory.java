package com.zeus.study.module.rxjava.minerx.scheduler;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-24.
 * =======================================
 */
public class ZRxThreadFactory extends AtomicLong implements ThreadFactory {
    final String prefix;
    final int priority;
    final boolean nonBlocking;

    public ZRxThreadFactory(String prefix) {
        this(prefix, 5);
    }

    public ZRxThreadFactory(String prefix, int priority) {
        this(prefix, priority, false);
    }

    public ZRxThreadFactory(String prefix, int priority, boolean nonBlocking) {
        this.prefix = prefix;
        this.priority = priority;
        this.nonBlocking = nonBlocking;
    }

    @Override
    public Thread newThread(Runnable r) {
        StringBuilder builder = new StringBuilder(prefix).append("-").append(incrementAndGet());
        String name = builder.toString();
        Thread t = nonBlocking ? new RxCustomThread(r, name) : new Thread(r, name);
        t.setPriority(priority);
        t.setDaemon(true);
        return t;
    }

    static final class RxCustomThread extends Thread {
        RxCustomThread(Runnable runnable, String name) {
            super(runnable, name);
        }
    }
}

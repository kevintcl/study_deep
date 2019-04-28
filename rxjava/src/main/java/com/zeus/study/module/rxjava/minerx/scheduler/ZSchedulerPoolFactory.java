package com.zeus.study.module.rxjava.minerx.scheduler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-25.
 * =======================================
 */
public final class ZSchedulerPoolFactory {
    static final String PURGE_ENABLED_KEY = "rx2.purge-enabled";
    public static final boolean PURGE_ENABLED;
    static final String PURGE_PERIOD_SECONDS_KEY = "rx2.purge-period-seconds";
    public static final int PURGE_PERIOD_SECONDS;

    static final AtomicReference<ScheduledExecutorService> PURGE_THREAD = new AtomicReference();
    static final Map<ScheduledThreadPoolExecutor, Object> POOLS = new ConcurrentHashMap();


    static {
        Properties properties = System.getProperties();
        ZSchedulerPoolFactory.PurgeProperties pp = new ZSchedulerPoolFactory.PurgeProperties();
        pp.load(properties);
        PURGE_ENABLED = pp.purgeEnable;
        PURGE_PERIOD_SECONDS = pp.purgePeriod;
        start();
    }

    public static void start() {
        tryStart(PURGE_ENABLED);
    }

    static void tryStart(boolean purgeEnabled) {
        if (purgeEnabled) {
            while (true) {
                ScheduledExecutorService curr =  PURGE_THREAD.get();
                if (curr != null) {
                    return;
                }

                ScheduledExecutorService next = Executors.newScheduledThreadPool(1, new ZRxThreadFactory("RxSchedulerPurge"));
                if (PURGE_THREAD.compareAndSet(curr, next)) {
                    next.scheduleAtFixedRate(new ZSchedulerPoolFactory.ScheduledTask(), PURGE_PERIOD_SECONDS, PURGE_PERIOD_SECONDS, TimeUnit.SECONDS);
                    return;
                }

                next.shutdownNow();
            }
        }
    }

    public static ScheduledExecutorService create(ThreadFactory threadFactory) {

        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1, threadFactory);
        tryPutIntoPool(PURGE_ENABLED, exec);
        return null;
    }

    static void tryPutIntoPool(boolean purgeEnabled, ScheduledExecutorService exec) {
        if (purgeEnabled && exec instanceof ScheduledThreadPoolExecutor) {
            ScheduledThreadPoolExecutor e = (ScheduledThreadPoolExecutor) exec;
            POOLS.put(e, exec);
        }

    }

    static final class ScheduledTask implements Runnable {
        ScheduledTask() {
        }

        public void run() {
            Iterator iterator = (new ArrayList(POOLS.keySet())).iterator();

            while (iterator.hasNext()) {
                ScheduledThreadPoolExecutor e = (ScheduledThreadPoolExecutor) iterator.next();
                if (e.isShutdown()) {
                    POOLS.remove(e);
                } else {
                    e.purge();
                }
            }

        }
    }

    static final class PurgeProperties {
        boolean purgeEnable;
        int purgePeriod;

        PurgeProperties() {
        }

        void load(Properties properties) {
            if (properties.containsKey(PURGE_ENABLED_KEY)) {
                this.purgeEnable = Boolean.parseBoolean(properties.getProperty(PURGE_ENABLED_KEY));
            } else {
                this.purgeEnable = true;
            }

            if (this.purgeEnable && properties.containsKey(PURGE_PERIOD_SECONDS_KEY)) {
                try {
                    this.purgePeriod = Integer.parseInt(properties.getProperty(PURGE_PERIOD_SECONDS_KEY));
                } catch (NumberFormatException e) {
                    this.purgePeriod = 1;
                }
            } else {
                this.purgePeriod = 1;
            }

        }
    }
}

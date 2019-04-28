package com.zeus.study.module.rxjava.minerx.scheduler;

import com.zeus.study.module.rxjava.minerx.ZRxJavaPlugins;
import com.zeus.study.module.rxjava.minerx.scheduler.ZScheduler;

import java.util.concurrent.Callable;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-24.
 * =======================================
 */
public final class ZSchedulers {

    static final ZScheduler IO = ZRxJavaPlugins.initIoScheduler(new IOTask());

    public static ZScheduler io() {
        return IO;
    }

    static final class IOTask implements Callable<ZScheduler> {
        IOTask() {

        }

        @Override
        public ZScheduler call() throws Exception {
            return IoHolder.DEFAULT;
        }
    }

    static final class IoHolder {
        static final ZScheduler DEFAULT = new ZIoScheduler(null);

        IoHolder() {
        }
    }
}

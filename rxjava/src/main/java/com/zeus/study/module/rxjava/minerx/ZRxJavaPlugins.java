package com.zeus.study.module.rxjava.minerx;

import com.zeus.study.module.rxjava.minerx.scheduler.ZScheduler;

import java.util.concurrent.Callable;

import io.reactivex.annotations.NonNull;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-24.
 * =======================================
 */
public class ZRxJavaPlugins {

    public static ZScheduler initIoScheduler(@NonNull Callable<ZScheduler> defaultScheduler) {
        try {
            return defaultScheduler.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

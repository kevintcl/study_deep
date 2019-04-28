package com.zeus.study.module.rxjava.minerx.scheduler;

import com.zeus.study.module.rxjava.minerx.ZDisposable;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-25.
 * =======================================
 */
public interface ZDisposableContainer {

    boolean add(ZDisposable d);

    boolean remove(ZDisposable d);

    boolean delete(ZDisposable d);
}

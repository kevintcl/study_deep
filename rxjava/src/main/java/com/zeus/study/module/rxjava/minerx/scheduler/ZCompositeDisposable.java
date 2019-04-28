package com.zeus.study.module.rxjava.minerx.scheduler;

import com.zeus.study.module.rxjava.minerx.ZDisposable;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-25.
 * =======================================
 */
public class ZCompositeDisposable implements ZDisposable, ZDisposableContainer {


    @Override
    public void dispose() {

    }

    @Override
    public boolean isDisposed() {
        return false;
    }

    @Override
    public boolean add(ZDisposable d) {
        return false;
    }

    @Override
    public boolean remove(ZDisposable d) {
        return false;
    }

    @Override
    public boolean delete(ZDisposable d) {
        return false;
    }
}

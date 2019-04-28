package com.zeus.study.module.rxjava.minerx;

import java.util.concurrent.atomic.AtomicReference;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-23.
 * =======================================
 */
public enum ZDisposableHelper implements ZDisposable {
    DISPOSED;

    public static boolean isDisposed(ZDisposable d) {
        return d == DISPOSED;
    }

    public static boolean dispose(AtomicReference<ZDisposable> field) {
        ZDisposable current = field.get();
        ZDisposable d = DISPOSED;
        if (current != d) {
            current = field.getAndSet(d);
            if (current != d) {
                if (current != null) {
                    current.dispose();
                }
                return true;
            }

        }

        return false;
    }

    public static boolean setOnce(AtomicReference<ZDisposable> field, ZDisposable d) {

        if (!field.compareAndSet(null, d)) {
            //当前值不为null
            d.dispose();

            if (field.get() != DISPOSED) {
                reportDisposableSet();
            }
            return false;
        } else {

            return true;
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean isDisposed() {
        return true;
    }

    private static void reportDisposableSet() {
        System.err.println("reportDisposableSet ");
    }

}

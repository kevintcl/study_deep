package com.zeus.source.recyclerview.mine;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-29.
 * =======================================
 */
public class AdapterHelper {
    Callback mCallback;

    AdapterHelper(Callback callback) {
        mCallback = callback;
    }

    public boolean hasUpdates() {
        return false;
    }

    interface Callback {

    }
}

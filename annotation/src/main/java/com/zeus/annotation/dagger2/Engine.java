package com.zeus.annotation.dagger2;

import androidx.annotation.NonNull;

import javax.inject.Inject;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-29.
 * =======================================
 */

public class Engine {
    @Inject
    public Engine() {
    }

    @NonNull
    @Override
    public String toString() {
        return "Engine{}";
    }
    public void run() {
        System.out.println("engine run....");
    }
}

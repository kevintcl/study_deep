package com.zeus.study.module.rxjava.observerpattern;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-23.
 * =======================================
 */
public interface Zobserver<T> {

    void update(Zobserver<T> observer, T t);
}

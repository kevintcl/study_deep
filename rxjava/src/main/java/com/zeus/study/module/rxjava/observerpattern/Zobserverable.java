package com.zeus.study.module.rxjava.observerpattern;

import java.util.Vector;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-23.
 * =======================================
 */
public class Zobserverable<T> {

    private Vector<Zobserver<T>> observers = new Vector<>();

    private T message;

    public final void addObserver(Zobserver<T> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public final void removeObserver(Zobserver<T> observer) {
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    private final void notifyObservers() {
        synchronized (observers) {
            for (Zobserver<T> ob : observers) {
                ob.update(ob, message);
            }
        }
    }

    public void update(T message) {
        this.message = message;
        notifyObservers();
    }

}

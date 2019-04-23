package com.zeus.study.module.rxjava.observerpattern;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-23.
 * =======================================
 */
public class ObserverPatternClient {

    public static void main(String[] args) {
        Zobserverable<String> observerable = new Zobserverable<>();

        observerable.addObserver(new Zobserver<String>() {
            @Override
            public void update(Zobserver<String> observer, String s) {
                System.out.println("observer receive msg : " + s);
            }
        });

        observerable.update("我是通知消息！！！");
    }
}

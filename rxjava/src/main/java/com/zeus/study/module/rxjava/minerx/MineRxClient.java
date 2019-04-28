package com.zeus.study.module.rxjava.minerx;

import com.zeus.study.module.rxjava.minerx.scheduler.ZSchedulers;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-23.
 * =======================================
 */
public class MineRxClient {

    public static void main(String[] args) {

        ZObservable<String> observable = ZObservable.create(new ZObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ZObservableEmitter<String> emitter) throws Exception {
                System.out.println("send msg===========");
                emitter.onNext("hello mine rx");
            }
        });


        ZObserver<String> observer = new ZObserver<String>() {

            @Override
            public void onSubscribe(ZDisposable disposable) {
                System.out.println("onSubscribe");
//                disposable.dispose();
                System.out.println("onSubscribe dispose");
            }

            @Override
            public void onNext(String msg) {
                System.out.println("onNext receive msg : " + msg);
                int i = 0;
                i = 100 / i;
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError e = " + e.toString());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        observable.subsribeOn(ZSchedulers.io())
                .subscribe(observer);
    }
}

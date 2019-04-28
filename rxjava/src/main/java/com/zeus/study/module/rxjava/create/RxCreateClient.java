package com.zeus.study.module.rxjava.create;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-23.
 * =======================================
 */
public class RxCreateClient {

    public static void main1(String[] args) {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                System.out.println("observable=====thread name = " + Thread.currentThread().getName());
                observableEmitter.onNext("hello rxjava");

//                int i = 0;
//                i = 100 / i;

//                observableEmitter.onError(new Throwable("test error"));
                observableEmitter.onComplete();
            }
        });

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("onSubscribe thread name = " + Thread.currentThread().getName());
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext : " + s + " thread name = " + Thread.currentThread().getName());
//                int i = 0;
//                i = 100 / i;
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError : " + throwable.toString() + " thread name = " + Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete thread name = " + Thread.currentThread().getName());
            }
        };

        observable.
                subscribeOn(Schedulers.io()).
                observeOn(Schedulers.newThread()).
                subscribe(observer);
    }


    public static void main(String[] args) {
//        test();
        main1(null);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test() {
        Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {


                System.err.println("test subscribe");
                observableEmitter.onNext("11111");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        System.out.println("test onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("test onNext");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("test onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("test onComplete");
                    }
                });
    }
}

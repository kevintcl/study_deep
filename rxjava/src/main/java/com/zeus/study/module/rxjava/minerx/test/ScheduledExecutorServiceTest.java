package com.zeus.study.module.rxjava.minerx.test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-24.
 * =======================================
 */
public class ScheduledExecutorServiceTest {

    public static void main(String[] args) {
        executeFixedDelay();
    }


    /**
     * 以固定延迟时间进行执行
     * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
     */
    public static void executeFixedDelay() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(
                new Runnable() {
                    int i = 0;
                    @Override
                    public void run() {
                        System.out.println("test ------i = " + i++);
                    }
                },
                0,
                100,
                TimeUnit.MILLISECONDS);
    }

}

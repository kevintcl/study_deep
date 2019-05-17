package com.zeus.proguard.parent;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-17.
 * =======================================
 */
public class ParentTest1 {

    private String name;

    public ParentTest1() {

    }

    public ParentTest1(String name) {
        this.name = name;
    }

    private ParentTest1(int age, String name) {
        this("111");
    }

    private void printPrivate(String s) {

    }
    public void print() {
        System.out.println(getClass().getName());
    }

    protected void onCreate() {

    }
}

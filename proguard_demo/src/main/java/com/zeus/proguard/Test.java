package com.zeus.proguard;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-17.
 * =======================================
 */
public class Test {


    private void testPrivate() {
        System.out.println("testPrivate");
    }

    public void testPublic() {
        System.out.println("testPublic");
    }

    public void printPrivate() {
        testPrivate();
    }
}

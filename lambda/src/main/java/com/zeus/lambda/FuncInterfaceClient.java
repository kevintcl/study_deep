package com.zeus.lambda;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-28.
 * =======================================
 */
public class FuncInterfaceClient {

    public static void main(String[] args) {
        String up =
//                toUpperStr(new MyFunc<String>() {
//            @Override
//            public String getValue(String s) {
//                return s.toUpperCase();
//            }
//        }, "abc");
                toUpperStr(String::toUpperCase, "abcd");
        System.out.println(up);
    }

    public static String toUpperStr(MyFunc<String> mf, String str) {
        return mf.getValue(str);
    }

    @FunctionalInterface
    interface MyFunc<T> {
        T getValue(T t);
    }
}

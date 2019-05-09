package com.zeus.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-28.
 * =======================================
 */
public class StreamApi {

    public static void main(String[] args) {
//        testFilter();
        testFlatMap();
    }

    static void testFilter() {
        List<String> arrayList = Arrays.asList("a", "b", "c", "d", "e");
        arrayList.stream().filter(x->!"b".equals(x))
                .limit(2)
                .map(x->"==" + x)

                .forEach(x-> System.out.println(x));
    }

    static void testFlatMap() {
        Integer[][] ary = {{3,8,4,7,5}, {9,1,6,2}, {0,10,12,11} };

        Arrays.stream(ary)
                .flatMap(item->Arrays.stream(item))
                .sorted()
                .forEach(System.out::println);
    }
}

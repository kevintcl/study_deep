package com.zeus.lambda;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-28.
 * =======================================
 */
public class CollectorClient {

    static Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {
        System.out.println("1.  使用Collectors求ary的最大值");
        System.out.println(Arrays.stream(array).collect(Collectors.maxBy(Integer::compareTo)).get());

        System.out.println("2.  使用Collectors求ary的平均值");
        System.out.println(Arrays.stream(array).collect(Collectors.averagingInt(x->x)));

        System.out.println("3.  使用Collectors.joining输出”1:2:3:4:5:6:7:8:9:10”");
        System.out.println(Arrays.stream(array).map(x->x+":").collect(Collectors.joining()));

        System.out.println("4.  使用Collectors.reducing求ary数组的总和");

        System.out.println("5.  使用Collectors.counting求ary个数");
    }
}

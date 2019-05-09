package com.zeus.lambda;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-28.
 * =======================================
 */
public class EndOperate {
    static Integer[] ary = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    public static void main(String[] args) {

        System.out.println("1. 检查是否所有元素都小于10");
        System.out.println(Arrays.stream(ary).allMatch(x -> x < 10));

        System.out.println("2. 检查是否至少有一个元素小于2");
        System.out.println(Arrays.stream(ary).anyMatch(x -> x < 2));

        System.out.println("3. 检查是不是没一个元素大于10");
        System.out.println(Arrays.stream(ary).noneMatch(x -> x > 10));

        System.out.println("4. 返回第一个元素");
        System.out.println(Arrays.stream(ary).findFirst().get());

        System.out.println("5. ary 有多少个元素");
        System.out.println(Arrays.stream(ary).count());

        System.out.println("6. 求ary里面最大值");
        System.out.println(Arrays.stream(ary).max(Integer::compareTo).get());

        System.out.println("7. 求ary里面最小值");
        System.out.println(Arrays.stream(ary).min(Integer::compareTo).get());

        System.out.println("8. 循环遍历打出ary 里面偶数");
        Arrays.stream(ary).filter(x -> x % 2 == 0)
                .forEach(System.out::println);
    }


}

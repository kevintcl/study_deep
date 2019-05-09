package com.zeus.lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-28.
 * =======================================
 */
public class JavaInnerFuncInterfaceClient {

    public static void main(String[] args) {
        //Predicate<T>
//        predicateTest();
//        testConsumer();

//        testFunction("adfasfda");
//        testSupplier();
        testUnaryOperator();
    }

    static void predicateTest() {
        System.out.println("=============predicateTest=============");
        Predicate<String> predicate = s -> s.equals("abc");

        System.out.println(predicate.test("acd"));
        System.out.println(predicate.test("abc"));
    }

    static void testConsumer() {
        System.out.println("=============testConsumer=============");

        Consumer<String> consumer = s -> System.out.println(s.length());

        consumer.accept("abcd");
    }

    static void testFunction(String str) {
        System.out.println("=============testFunction=============");
        Function<String, Integer> function = s -> s.length();
        int length = function.apply(str);
        System.out.println(length);
    }

    static void testSupplier() {
        System.out.println("=============Supplier=============");
        Supplier<MyClass.Person> supplier =
                () -> new MyClass.Person("a", 1, 100);

        System.out.println(supplier.get());
    }

    static void testUnaryOperator() {
        System.out.println("=============UnaryOperator=============");
        UnaryOperator<String> unaryOperator = s -> s.toUpperCase();

        System.out.println(unaryOperator.apply("asdfasdf"));
    }
}

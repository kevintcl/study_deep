package com.zeus.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class MyClass {

    public static void main(String[] args) {
        MyClass m = new MyClass();
//        m.test1();

        ArrayList<Person> ps = new ArrayList<>();
        ps.add(new Person("a", 1, 3));
        ps.add(new Person("b", 3, 2));
        ps.add(new Person("c", 2, 4));

        ps.sort((p1, p2) -> Integer.compare(p1.age, p2.age));

        for (Person p : ps) {
            System.out.println(p);
        }
    }


    void test1() {
        Runnable run = () -> System.out.println("run");
        run.run();
    }

    void test2() {
        TreeSet<String> t = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return 0;
            }
        });

        TreeSet<String> t2 = new TreeSet<>(((o1, o2) -> {
            return o1.compareTo(o2);

        }));

        TreeSet<String> t3 = new TreeSet<>((String::compareTo));
    }


    interface Comp<T> {
        int compare(T o1, T o2);
    }

    static class Person implements Comp<Person> {
        String name;
        int age;
        int score;

        public Person(String name, int age, int score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        @Override
        public int compare(Person o1, Person o2) {
            return Integer.compare(o1.age, o2.age);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", score=" + score +
                    '}';
        }
    }
}

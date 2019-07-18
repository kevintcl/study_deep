package com.zeus.source.analysis.hashmap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * =======================================
 * Created by kevint on 2019-06-03.
 * =======================================
 */
public class HashTestClient {
    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>();


        hashMap.put("kevint", "tcl");

        String value = hashMap.get("kevint");

        System.out.println(value);

        testLinkedHashMap();
    }

    private static void testConcurrentHashMap() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();


    }

    private static void testLinkedHashMap() {
        System.out.println("===============LinkedHashMap==============");
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("以下是accessOrder=true的情况:");

        map = new LinkedHashMap<>(10, 0.75f, true);
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");

        map.get("2");//2移动到了内部的链表末尾

//        map.get("4");//4调整至末尾

//        map.put("3", "e");//3调整至末尾

        map.put(null, null);//插入两个新的节点 null
        map.put("5", null);//5
        iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}

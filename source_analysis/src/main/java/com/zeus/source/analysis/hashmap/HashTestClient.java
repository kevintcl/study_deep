package com.zeus.source.analysis.hashmap;

import java.util.HashMap;

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
    }
}

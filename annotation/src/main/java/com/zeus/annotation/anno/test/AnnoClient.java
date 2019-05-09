package com.zeus.annotation.anno.test;

import com.zeus.annotation.anno.Test;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-29.
 * =======================================
 */
public class AnnoClient {
    public static void main(String[] args) {
        System.out.println("annotation study=============");
        Class<TestAnnotation> tClazz = TestAnnotation.class;
        boolean hasAnno = tClazz.isAnnotationPresent(Test.class);
        System.out.println("hasAnno=" + hasAnno);

        if (hasAnno) {
            Test testAnnotation = tClazz.getAnnotation(Test.class);
            System.out.println("id=" + testAnnotation.id() + ",msg=" + testAnnotation.msg());
        }
    }
}

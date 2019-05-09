package com.zeus.annotation.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * =======================================
 * Created by tangchunlin on 2019-04-29.
 * =======================================
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {

    int id() default -1;

    String msg() default "hello";
}

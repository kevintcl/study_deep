package com.zeus.aspectj.permission;

import androidx.fragment.app.FragmentActivity;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-09.
 * =======================================
 */
//@Aspect
public class AspectjPermission {

//    @Retention(RetentionPolicy.RUNTIME)
//    @Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
//    public @interface Permission {
//        String[] value();
//    }
//
//
//    private static final String POINT_METHOD = "execution(@com.zeus.aspectj.permission.AspectjPermission.Permission * *(..))";
//
//    @Pointcut(POINT_METHOD)
//    public void callOn() {
//    }
//
//    @Around("callOn()")
//    public void aroundCall(ProceedingJoinPoint joinPoint) {
//        FragmentActivity activity = (FragmentActivity) joinPoint.getThis();
////        DescriptionWrapper
//    }
}

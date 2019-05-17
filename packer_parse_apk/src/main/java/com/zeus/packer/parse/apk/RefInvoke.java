package com.zeus.packer.parse.apk;

import android.app.Instrumentation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-10.
 * =======================================
 */
public class RefInvoke {

    /**
     * @param className   包名.类名
     * @param methodName
     * @param paramTypes
     * @param paramValues
     * @return
     */
    public static Object invokeStaticMethod(String className, String methodName, Class[] paramTypes, Object[] paramValues) {

        try {
            Class<?> clz = Class.forName(className);
            Method method = clz.getMethod(methodName, paramTypes);

            Object object = method.invoke(null, paramValues);

            return object;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object getFieldObject(String className, Object obj, String filedName) {
        try {
            Class<?> clz = Class.forName(className);
            Field field = clz.getField(filedName);

            field.setAccessible(true);
            return field.get(obj);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldObject(String className, String filedName, Object obj, Object value) {
        try {
            Class<?> clz = Class.forName(className);
            Field field = clz.getField(filedName);

            field.setAccessible(true);
            field.set(obj, value);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param className
     * @param methodName
     * @param obj
     * @param paramTypes
     * @param paramValues
     * @return
     */
    public static Object invokeMethod(String className, String methodName,
                                      Object obj, Class[] paramTypes, Object[] paramValues) {
        try {
            Class<?> clz = Class.forName(className);
            Method method = clz.getMethod(methodName, paramTypes);

            Object object = method.invoke(obj, paramValues);

            return object;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

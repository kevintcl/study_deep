package com.zeus.bugfix.fullscreenorientation;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ActivityHook {


    /**
     * java.lang.IllegalStateException: Only fullscreen opaque activities can request orientation
     * <p>
     * 修复android 8.0存在的问题
     * <p>
     * 在Activity中onCreate()中super之前调用
     *
     * @param activity
     */
    public static void hookOrientation(Activity activity) {
        //目标版本8.0及其以上时，如果系统版本为 26(8.0) 时进行修复。 SDK 27(8.1) 以后这个问题修复了
        Log.e("kevint", "hookOrientation : targetSdkVersion=" + activity.getApplicationInfo().targetSdkVersion
                +",Build.VERSION_CODES.O=" + Build.VERSION_CODES.O +",Build.VERSION.SDK_INT=" + Build.VERSION.SDK_INT);

        if (activity.getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.O
                && Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            if (isTranslucentOrFloating(activity)) {
                fixOrientation(activity);
            }
        }
    }

    /**
     * 设置屏幕不固定，绕过检查
     *
     * @param activity
     */
    private static void fixOrientation(Activity activity) {
        try {
            Class<Activity> activityClass = Activity.class;
            Field mActivityInfoField = activityClass.getDeclaredField("mActivityInfo");
            mActivityInfoField.setAccessible(true);
            ActivityInfo activityInfo = (ActivityInfo) mActivityInfoField.get(activity);
            //设置屏幕不固定
            activityInfo.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查屏幕 横竖屏或者锁定就是固定
     *
     * @param activity
     * @return
     */
    private static boolean isTranslucentOrFloating(Activity activity) {
        boolean isTranslucentOrFloating = false;
        try {

            /**
             * Activity:
             *   final TypedArray ta = obtainStyledAttributes(com.android.internal.R.styleable.Window);
             *
             * ActivityInfo:
             *  public static boolean isTranslucentOrFloating(TypedArray attributes)
             *
             *
             *   public final class R {
             *       public static final class styleable {
             *          }
             *       }
             *
             */


            Class<?> styleableClass = Class.forName("com.android.internal.R$styleable");
            Field WindowField = styleableClass.getDeclaredField("Window");
            WindowField.setAccessible(true);
            int[] styleableRes = (int[]) WindowField.get(null);

            //先获取到TypedArray
            final TypedArray typedArray = activity.obtainStyledAttributes(styleableRes);
            Class<?> ActivityInfoClass = ActivityInfo.class;


            //调用检查是否屏幕旋转
            //ActivityInfo
            // public static boolean isTranslucentOrFloating(TypedArray attributes)
            Method isTranslucentOrFloatingMethod = ActivityInfoClass
                    .getDeclaredMethod("isTranslucentOrFloating", TypedArray.class);

            isTranslucentOrFloatingMethod.setAccessible(true);
            isTranslucentOrFloating = (boolean) isTranslucentOrFloatingMethod.invoke(null, typedArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }


}


/**
 * 出现问题的原因
 *
 * onCreate:
 *
 * if (getApplicationInfo().targetSdkVersion > O && mActivityInfo.isFixedOrientation()) {
 *             final TypedArray ta = obtainStyledAttributes(com.android.internal.R.styleable.Window);
 *             final boolean isTranslucentOrFloating = ActivityInfo.isTranslucentOrFloating(ta);
 *             ta.recycle();
 *             if (isTranslucentOrFloating) {
 *                 throw new IllegalStateException(
 *                         "Only fullscreen opaque activities can request orientation");
 *             }
 *         }
 *
 *
 *
 *  ActivityInfo.isFixedOrientation() 检查屏幕方向，是否需要修复
 *
 *
 *      * Returns true if the activity's orientation is fixed.
 *      * @hide
 * public boolean isFixedOrientation() {
 *         return isFixedOrientationLandscape() || isFixedOrientationPortrait()
 *                 || screenOrientation == SCREEN_ORIENTATION_LOCKED;
 *     }
 *
 * public static boolean isTranslucentOrFloating(TypedArray attributes) {
 *         final boolean isTranslucent =
 *                 attributes.getBoolean(com.android.internal.R.styleable.Window_windowIsTranslucent,
 *                         false);
 *         final boolean isSwipeToDismiss = !attributes.hasValue(
 *                 com.android.internal.R.styleable.Window_windowIsTranslucent)
 *                 && attributes.getBoolean(
 *                         com.android.internal.R.styleable.Window_windowSwipeToDismiss, false);
 *         final boolean isFloating =
 *                 attributes.getBoolean(com.android.internal.R.styleable.Window_windowIsFloating,
 *                         false);
 *
 *         return isFloating || isTranslucent || isSwipeToDismiss;
 *     }
 */
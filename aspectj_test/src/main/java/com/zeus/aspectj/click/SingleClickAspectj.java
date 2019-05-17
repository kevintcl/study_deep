package com.zeus.aspectj.click;

import android.util.Log;
import android.view.View;

import com.zeus.aspectj.R;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-09.
 * =======================================
 *
 * @Aspect 指定切面类；
 *
 * @Pointcut 切入点；
 *
 * @Around 是切入方式Advice的一种，表示在切入点前后插入代码，
 * 还有@Before、@After；
 *
 * Pointcut语法，
 * execution，表示根据Advice在执行方法内部代码前后插入代码,
 * call，表示根据Advice在调用方法前后插入代码......
 *
 */
@Aspect
public class SingleClickAspectj {

    private static final String TAG = "kevint";
    public static final int MIN_CLICK_DELAY_TIME = 500;


    static int TIME_TAG = R.id.aspectj_double_click;

    //方法切入点
    @Pointcut("execution(@com.zeus.aspectj.click.SingleClick * *(..))")
    public void methodAnnotated() {

    }

    @Around("methodAnnotated()")//在连接点进行方法替换
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        View view = null;
        for (Object arg : joinPoint.getArgs()) {
            Log.e(TAG, "aroundJoinPoint arg = " + arg);

            if (arg instanceof View) {
                view = (View) arg;
            }
        }

        if (view != null) {
            Object tag = view.getTag(TIME_TAG);
            long lastClickTime = tag != null ? (long) tag : 0L;
            long currentTime = System.currentTimeMillis();

            Log.e(TAG, "time =" + (currentTime - lastClickTime));
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                view.setTag(currentTime);
                //execute original method

                joinPoint.proceed();
            }
        }
    }

//    @Pointcut("execution(* com.zeus.aspectj.MainActivity.on**(..))")
    @Pointcut("execution(* android.app.Activity.on**(..))")
    public void myPointCut() {
    }

    @Before("myPointCut()")
    public void onActivityMethodBefore(JoinPoint joinPoint) throws Throwable {
        Log.e(TAG, "onActivityMethodBefore=" + joinPoint.toLongString());
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 类名
        String className = methodSignature.getDeclaringType().getSimpleName();
        // 方法名
        String methodName = methodSignature.getName();

        Log.i(TAG, "className=" + className + ", methodName=" + methodName);
    }

}

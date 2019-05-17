package com.zeus.hook.ams;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.zeus.hook.ams.hook1.AMSHookUtil;

public class MyApplication extends Application {
    private static final String TAG = "kevint";

    @Override
    public void onCreate() {
        Log.e(TAG, "MyApplication onCreate");
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        Log.e(TAG, "MyApplication attachBaseContext");
        super.attachBaseContext(base);

//        HookUtil hookUtil = new HookUtil(getClass(), this);
//        hookUtil.hookAms();

        AMSHookUtil.hookActivity(base, true);
    }
}
package com.zeus.packer.source.apk;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-10.
 * =======================================
 */
public class SourceApplication extends Application {

    private static final String TAG = "kevint";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "SourceApplication onCreate");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.e(TAG, "SourceApplication attachBaseContext");
    }
}

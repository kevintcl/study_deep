package com.zeus.bugfix.o;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-22.
 * =======================================
 */
public class Background2Service extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("kevint", "Background2Service onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("kevint", "Background2Service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

}

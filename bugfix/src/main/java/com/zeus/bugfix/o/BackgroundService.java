package com.zeus.bugfix.o;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-22.
 * =======================================
 */
public class BackgroundService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i("kevint", "BackgroundService onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("kevint", "BackgroundService onStartCommand");

        new Thread(()->{
            Log.e("kevint", "onStartCommand start sleep=======");
            SystemClock.sleep(5000);
            Log.e("kevint", "onStartCommand end sleep=======");
            Intent intent1 = new Intent(getApplicationContext(), Background2Service.class);
            intent1.setAction("com.zeus.o.bg.service");
            startService(intent1);
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

}

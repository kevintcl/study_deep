package com.zeus.binder.study;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-21.
 * =======================================
 */
public class LeoAidlService extends Service {

    private String mName = "remote service name: " + getClass().getSimpleName();


    // new 一个IBinder 对象
    private IBinder iBinder = new LeoBinder();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("kevint", "LeoAidlService success onBind = " + getPidTid());
                // 返回IBinder 对象
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("kevint", "LeoAidlService success onUnbind = " + getPidTid());
        return super.onUnbind(intent);
    }

    // 创建类继承ILeoAidl.Stub 抽象类，用来实现aidl中定义的方法
    private class LeoBinder extends ILeoAidl.Stub {

        @Override
        public void setName(String name) throws RemoteException {
            Log.e("kevint", "LeoAidlService sleep begin setName = " + name + getPidTid());
            SystemClock.sleep(4000);
            Log.e("kevint", "LeoAidlService sleep end setName =" + name + getPidTid());
            mName = name;
        }

        @Override
        public String getName() throws RemoteException {
            Log.e("kevint", "LeoAidlService sleep begin  getName = " + mName + getPidTid());
            SystemClock.sleep(4000);
            Log.e("kevint", "LeoAidlService sleep end  getName = " + mName + getPidTid());
            return mName;
        }
    }

    public static String getPidTid() {
        return ",Pid=" + Process.myPid() + ",tid=" + Thread.currentThread().getId() + ",tName=" + Thread.currentThread().getName();
    }
}

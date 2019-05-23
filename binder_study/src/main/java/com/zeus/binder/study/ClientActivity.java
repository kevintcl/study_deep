package com.zeus.binder.study;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

public class ClientActivity extends AppCompatActivity {
    private static final String TAG = "kevint";
    private ILeoAidl iLeoAidl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //(2)直接使用
                    Log.i(TAG, "ClientActivity before  getName " + getPidTid());
                    String name1 = iLeoAidl.getName();
                    Log.i(TAG, "ClientActivity after  getName " + getPidTid());
                    iLeoAidl.setName("name set to hahaha");
                    Log.i(TAG, "ClientActivity after  setName " + getPidTid());
                    String name2 = iLeoAidl.getName();
                    Log.i(TAG, "ClientActivity name1 = " + name1 + ",name2 = " + name2);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        Intent intent = new Intent();
        // 参数传对应服务的包名和全类名
        intent.setComponent(new ComponentName("com.zeus.binder.study", "com.zeus.binder.study.LeoAidlService"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "ClientActivity onServiceConnected: success name = " + name);
            // (1)通过参数 IBinder service 获取对应的代理对象
            iLeoAidl = ILeoAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "ClientActivity onServiceDisconnected: success name = " + name);
            iLeoAidl = null;
        }
    };

    @Override
    protected void onDestroy() {
        Log.i(TAG, "ClientActivity onDestroy");
        super.onDestroy();
        unbindService(connection);
    }

    public static String getPidTid() {
        return ",Pid=" + Process.myPid() + ",tid" + Thread.currentThread().getId() + ",tName=" + Thread.currentThread().getName();
    }
}

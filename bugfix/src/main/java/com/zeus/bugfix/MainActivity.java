package com.zeus.bugfix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.zeus.bugfix.o.BackgroundService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.o_backgroundservice)
                .setOnClickListener(v -> {
                            Log.e("kevint", "click=====");
                            startBackgroundService();
                        }
                );

    }

    private void startBackgroundService() {

        new Thread(() -> {
            Log.e("kevint", "start sleep=======");
            SystemClock.sleep(10000);
            Log.e("kevint", "end sleep=======");
            Intent intent = new Intent(getApplicationContext(), BackgroundService.class);
            intent.setAction("com.zeus.o.bg.service");
            startService(intent);
        }).start();

        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("kevint", "MainActivity====onDestroy=");
    }
}

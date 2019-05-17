package com.zeus.aspectj;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zeus.aspectj.click.SingleClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "kevint";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.aspectj_double_click).setOnClickListener(new View.OnClickListener() {
            @SingleClick
            @Override
            public void onClick(View v) {
                Log.e(TAG, "click");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

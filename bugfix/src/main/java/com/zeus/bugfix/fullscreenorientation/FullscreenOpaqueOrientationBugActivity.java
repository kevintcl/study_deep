package com.zeus.bugfix.fullscreenorientation;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-20.
 * =======================================
 */
public class FullscreenOpaqueOrientationBugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

//        ActivityHook.hookOrientation(this);
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);

        tv.setGravity(Gravity.CENTER);

        ViewGroup.LayoutParams params =
                new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        tv.setText("Only fullscreen opaque activities can request orientation On 8.0 的设备");
        setContentView(tv, params);
    }
}

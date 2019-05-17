package com.zeus.hook.ams;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TestSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
       TextView tv =  findViewById(R.id.tv);
        tv.setBackgroundColor(Color.RED);
        tv.setText("TestSecondActivity");

    }
}

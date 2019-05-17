package com.zeus.packer.source.apk;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PackerSecondSourceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packer_source);
        TextView tv = findViewById(R.id.tv);
        tv.setText("I am " + getClass().getSimpleName());
    }
}

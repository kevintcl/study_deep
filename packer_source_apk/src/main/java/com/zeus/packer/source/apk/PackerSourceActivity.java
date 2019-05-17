package com.zeus.packer.source.apk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PackerSourceActivity extends AppCompatActivity {

    private static final String TAG = "kevint";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packer_source);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "PackerSourceActivity onclick");

                startActivity(new Intent(PackerSourceActivity.this, PackerSecondSourceActivity.class));
            }
        });
    }
}

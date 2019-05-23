package com.zeus.proguard.fragmenterror;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.zeus.proguard.R;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-22.
 * =======================================
 */
public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("kevint", "ErrorActivity onCreate");
        setContentView(R.layout.error_fragment_activity);
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new ErrorFragment("KEVINT")
                        )
                .commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("kevint", "ErrorActivity onResume");
    }

}

package com.zeus.proguard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.zeus.proguard.parent.ParentTest;
import com.zeus.proguard.parent.ParentTest1;
import com.zeus.proguard.parent.child.ChildTest;

public class ProguardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proguard);

        Test test = new Test();
        test.printPrivate();
        test.testPublic();

        new ParentTest().print();
        new ChildTest().print();

        new ParentTest1().print();

        println();
    }

    private void println() {
        System.out.println(getClass().getName());
    }
}

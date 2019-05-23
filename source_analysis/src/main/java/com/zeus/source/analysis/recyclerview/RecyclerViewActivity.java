package com.zeus.source.analysis.recyclerview;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zeus.source.analysis.R;

import java.util.ArrayList;
import java.util.List;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-23.
 * =======================================
 */
public class RecyclerViewActivity extends AppCompatActivity {
    private static final String TAG = "kevint";
    RecyclerView rv;
    RecyclerView.LayoutManager layoutManager;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recyclerview);

        rv = findViewById(R.id.rv);

        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        myAdapter = new MyAdapter(fetchData(1));

        rv.setLayoutManager(layoutManager);
        rv.setAdapter(myAdapter);

        findViewById(R.id.fetch_dat).setOnClickListener(v -> {
            Log.e(TAG, "FETCH DATA");
            List<ItemData> data = fetchData(2);
            myAdapter.addData(data);
        });
    }

    int i;

    List<ItemData> fetchData(int size) {
        List<ItemData> data = new ArrayList<>();
        int to = i + size;
        for (; i < to; i++) {
            data.add(new ItemData("我是长江 " + i + " 号"));
        }

        return data;


    }
}

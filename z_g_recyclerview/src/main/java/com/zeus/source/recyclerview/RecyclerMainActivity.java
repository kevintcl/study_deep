package com.zeus.source.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeus.source.recyclerview.R;
import com.zeus.source.recyclerview.origin.LinearLayoutManager;
import com.zeus.source.recyclerview.origin.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerMainActivity extends AppCompatActivity {

    RecyclerView rv;
    LinearLayoutManager linearLayoutManager;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_main);

        rv = findViewById(R.id.rv);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rv.setLayoutManager(linearLayoutManager);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            data.add("张三 " + i);
        }
        adapter = new MyAdapter(data);

        rv.setAdapter(adapter);

    }

    static class MyAdapter extends RecyclerView.Adapter<VH> {

        List<String> data;

        MyAdapter(List<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            TextView tv = new TextView(parent.getContext());

            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
            tv.setTextColor(Color.WHITE);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.BLUE);

            return new VH(tv);
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            holder.tv.setText(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data == null ? 0 : data.size();
        }
    }

    static class VH extends RecyclerView.ViewHolder {

        TextView tv;

        public VH(@NonNull View itemView) {
            super(itemView);
            tv = (TextView) itemView;
        }
    }
}

package com.zeus.bugfix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeus.bugfix.compact.notification.ONotificationCompatTestActivity;
import com.zeus.bugfix.o.BackgroundService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initItems();
        rv = findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rv.setAdapter(adapter);
    }

    private void initItems() {
        items.add(new Item("Notification Compact", ONotificationCompatTestActivity.class));
    }

    private RecyclerView.Adapter<Holder> adapter = new RecyclerView.Adapter<Holder>() {

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.main_item_layout, parent, false);
            Holder holder = new Holder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.tv.setText(items.get(position).title);
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, items.get(position).clz);
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    };

    private static class Item {
        String title;
        Class<?> clz;

        public Item(String title, Class<?> clz) {
            this.title = title;
            this.clz = clz;
        }


    }

    private static class Holder extends RecyclerView.ViewHolder {
        TextView tv;

        public Holder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.title);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

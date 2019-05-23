package com.zeus.source.analysis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeus.source.analysis.recyclerview.RecyclerViewActivity;

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
        items.add(new Item("RecyclerView Study", RecyclerViewActivity.class));
    }


    private RecyclerView.Adapter<Holder> adapter = new RecyclerView.Adapter<Holder>() {

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            /** 302

             In your adapter where you are inflating the item in onCreateViewHolder, is the second parameter of the inflate call null?.

             If so change it to parent which is the first parameter in the onCreateViewHolder function signature.

             View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, parent, false);
             If you need the second parameter to be null then when you get the view reference on inflating, do the following

             View rootView = LayoutInflater.from(context).inflate(R.layout.itemLayout, null, false);
             RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
             rootView.setLayoutParams(lp);
             return new RecyclerViewHolder(rootView);
             */

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
}

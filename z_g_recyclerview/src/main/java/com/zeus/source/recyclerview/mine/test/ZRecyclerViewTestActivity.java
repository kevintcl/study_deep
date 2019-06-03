package com.zeus.source.recyclerview.mine.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.zeus.source.recyclerview.R;
import com.zeus.source.recyclerview.mine.Adapter;
import com.zeus.source.recyclerview.mine.ViewHolder;
import com.zeus.source.recyclerview.mine.ZLinearLayoutManager;
import com.zeus.source.recyclerview.mine.ZRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ZRecyclerViewTestActivity extends AppCompatActivity {

    ZRecyclerView rv;
    ZLinearLayoutManager linearLayoutManager;
    MyAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_main);

        rv = findViewById(R.id.rv);

        linearLayoutManager = new ZLinearLayoutManager(this);


        rv.setLayoutManager(linearLayoutManager);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            data.add("张三 " + i);
        }
        adapter = new MyAdapter(data);

        rv.setAdapter(adapter);

    }

    static class MyAdapter extends Adapter<VH> {

        List<String> data;

        MyAdapter(List<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            MyLayout layout = new MyLayout(parent.getContext());
            layout.setLayoutParams(new  ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 120));
            layout.setGravity(Gravity.CENTER);
            layout.setBackgroundColor(Color.parseColor("#123456"));
            layout.setPadding(10, 10, 10, 10);

            TextView tv = new TextView(parent.getContext());

            tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
            tv.setTextColor(Color.WHITE);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.BLUE);

            layout.addView(tv);
            return new VH(layout, tv);
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

    static class VH extends ViewHolder {

        TextView tv;


        public VH(@NonNull View itemView, TextView tv) {
            super(itemView);
            this.tv = tv;
        }
    }

    static class MyLayout extends LinearLayout {

        public MyLayout(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            Log.e("RecyclerView", "MyLayout onMeasure=" + Log.getStackTraceString(new Throwable("onMeasure")));
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
            Log.e("RecyclerView", "MyLayout onLayout=" + Log.getStackTraceString(new Throwable("onMeasure")));
        }
    }
}

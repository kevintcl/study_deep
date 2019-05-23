package com.zeus.source.analysis.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zeus.source.analysis.R;

import java.util.ArrayList;
import java.util.List;

/**
 * =======================================
 * Created by tangchunlin on 2019-05-23.
 * =======================================
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {

    private static final String TAG = "kevint";
    List<ItemData> list;

    public MyAdapter() {

    }

    public MyAdapter(List<ItemData> list) {
        this.list = list;
    }

    public void addData(List<ItemData> data) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_layout, parent, false);
        VH vh = new VH(view);
        Log.i(TAG, "onCreateViewHolder vh=0X" + Integer.toHexString(vh.hashCode()) + "\npath=" + Log.getStackTraceString(new Throwable()));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Log.e(TAG, "onBindViewHolder position = " + position + ",holder=0X" +  Integer.toHexString(holder.hashCode())
                + "\npath=" + Log.getStackTraceString(new Throwable()));
        holder.bindData(list.get(position));
    }

    @Override
    public int getItemCount() {

        int size = list == null ? 0 : list.size();
//        Log.e(TAG, "getItemCount = size=" +  size);//Log.getStackTraceString(new Throwable("getItemCount")));
        return size;
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tv;

        VH(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.title);
        }

        void bindData(ItemData data) {
            tv.setText(data.title);
        }

    }
}

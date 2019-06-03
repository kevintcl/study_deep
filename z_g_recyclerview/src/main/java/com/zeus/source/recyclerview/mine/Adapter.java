package com.zeus.source.recyclerview.mine;

import android.view.ViewGroup;

import androidx.annotation.NonNull;

public abstract class Adapter<VH extends ViewHolder> {

    public abstract VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public abstract void onBindViewHolder(@NonNull VH holder, int position);

    public abstract int getItemCount();
}
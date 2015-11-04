package com.example.linhnguyen.myapplication.adapter;

import android.support.v7.widget.RecyclerView;

import com.example.linhnguyen.myapplication.callback.OnRecyclerViewItemClick;

/**
 * Created by Envy 15T on 9/21/2015.
 */
public abstract class AdapterWithItemClick<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    OnRecyclerViewItemClick onRecyclerViewItemClick;

    public OnRecyclerViewItemClick getOnRecyclerViewItemClick() {
        return onRecyclerViewItemClick;
    }

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }
}

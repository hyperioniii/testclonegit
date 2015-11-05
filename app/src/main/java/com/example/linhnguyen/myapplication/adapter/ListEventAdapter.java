package com.example.linhnguyen.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linhnguyen.myapplication.R;
import com.example.linhnguyen.myapplication.adapter.ViewHolder.ListEventViewHolder;
import com.example.linhnguyen.myapplication.callback.OnRecyclerViewItemClick;
import com.example.linhnguyen.myapplication.model.CalenderEvent;

import java.util.List;

/**
 * Created by LinhNguyen on 11/4/2015.
 */
public abstract class ListEventAdapter extends AdapterWithItemClick<ListEventViewHolder> {

    public abstract void dataSender(int truyencaikhigithitruyen);

    List<CalenderEvent> data;
    int offsetx;  // new
    int x;

    public ListEventAdapter(List<CalenderEvent> data) {
        this.data = data;
    }

    @Override
    public ListEventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_event, viewGroup, false);
        return new ListEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListEventViewHolder listEventViewHolder, int i) {
        listEventViewHolder.setup(data.get(i));
        listEventViewHolder.swipeLayout(x, offsetx);

        dataSender(i);


    }

    public void swipeView(int x, int offsetx) {
        this.x = x;
        this.offsetx = offsetx;
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

}

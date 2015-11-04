package com.example.linhnguyen.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.linhnguyen.myapplication.R;
import com.example.linhnguyen.myapplication.adapter.DividerItemDecoration;
import com.example.linhnguyen.myapplication.adapter.ListEventAdapter;
import com.example.linhnguyen.myapplication.model.CalenderEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by LinhNguyen on 11/4/2015.
 */
public class FragmentListEvent extends BaseFragment {

    @InjectView(R.id.list_event)
    RecyclerView   mRecyclerView;

    List<CalenderEvent> data = new ArrayList<>();

    @InjectView(R.id.swipe_refresh_layout_list_event)
    SwipeRefreshLayout mSwipeRefreshLayout;

    ListEventAdapter adapter;

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_listeven;
    }

    @Override
    protected void initView(View root) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));
        mSwipeRefreshLayout.setRefreshing(false);

    }

    public static FragmentListEvent  intantce(){
        FragmentListEvent fm = new FragmentListEvent();
        return fm;
    }

    @Override
    protected void getArgument(Bundle bundle) {

    }

    @Override
    protected void initData() {
        setUi();
    }

    public void setUi(){
        getdata();
        adapter = new ListEventAdapter(data);
        mRecyclerView.setAdapter(adapter);

        final Runnable r = new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        };
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(r, 2000);
            }
        });
    }

    public void getdata() {
        data.add( new CalenderEvent("","http://res.vtc.vn/media/vtcnews/2014/epi/viettu/2014_03_11/Tu%20Anh/duong-tu-anh-13_MHSK.jpg?width=200abcdefffffffffff"));
        data.add( new CalenderEvent("","http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpgabcdefffffffffff"));
        data.add( new CalenderEvent("","http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpgabcdefffffffffff"));
        data.add( new CalenderEvent("","http://res.vtc.vn/media/vtcnews/2014/epi/viettu/2014_03_11/Tu%20Anh/duong-tu-anh-13_MHSK.jpg?width=200abcdefffffffffff"));
        data.add( new CalenderEvent("","http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpgabcdefffffffffff"));
        data.add( new CalenderEvent("","http://inthecheesefactory.com/uploads/source/glidepicasso/cover.jpg abcdefffffffffff"));

    }
}

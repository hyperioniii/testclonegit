package com.example.linhnguyen.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.linhnguyen.myapplication.R;
import com.example.linhnguyen.myapplication.adapter.DividerItemDecoration;
import com.example.linhnguyen.myapplication.adapter.ListEventAdapter;
import com.example.linhnguyen.myapplication.model.CalenderEvent;
import com.example.linhnguyen.myapplication.util.CustomsRecycleViewHoriziontal;
import com.example.linhnguyen.myapplication.util.DebugLog;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by LinhNguyen on 11/4/2015.
 */
public class FragmentListEvent extends BaseFragment  {

    @InjectView(R.id.list_event)
    CustomsRecycleViewHoriziontal mRecyclerView;

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

    public static FragmentListEvent intantce() {
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

    public void setUi() {
        getdata();
        adapter = new ListEventAdapter(data) {
            @Override
            public void dataSender(int truyencaikhigithitruyen) {
                // day la du lieu trong adapter truyen ra, cu khi nao trong ham kia co thay doi thi ngoai nay cung thay doi theo dong bo vs nhau
                // don gian the thoi

            }
        };
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setOnScrollListener(new CustomsRecycleViewHoriziontal.OnScrollListener() {
            @Override
            public void onScrollChanged(CustomsRecycleViewHoriziontal scrollView, int x, int y, int oldX, int oldY) {
                DebugLog.d("============4============" + x + "++" + y);
                DebugLog.d("============5============" + oldX + "++" + oldY);

                // thằng này bắt scroll list view
                adapter.swipeView(x,oldX);
            }

            @Override
            public void onEndScroll(CustomsRecycleViewHoriziontal scrollView) {

            }
        });

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int heightSc = displaymetrics.heightPixels;
        int widthSc = displaymetrics.widthPixels;
    }

    public void getdata() {
        for (int i = 0; i < 10; i++) {
            data.add(new CalenderEvent("", "http://res.vtc.vn/media/vtcnews/2014/epi/viettu/2014_03_11/Tu%20Anh/duong-tu-anh-13_MHSK.jpg?width=200abcdefffffffffff"));
        }
    }


}

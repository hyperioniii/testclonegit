package com.example.linhnguyen.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.linhnguyen.myapplication.R;
import com.example.linhnguyen.myapplication.eventbus.MainScreenSettingEvent;
import com.example.linhnguyen.myapplication.fragment.FragmentListEvent;
import com.example.linhnguyen.myapplication.util.FragmentUtil;

public class MainActivity extends BaseActivity {


    @Override
    public int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        FragmentUtil.showFragment(MainActivity.this, FragmentListEvent.intantce(),false,null,"",false);
    }

    @SuppressWarnings("unused")
    public void onEventMainThread(MainScreenSettingEvent event) {

    }
}

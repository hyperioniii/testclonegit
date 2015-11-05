package com.example.linhnguyen.myapplication.adapter.ViewHolder;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.linhnguyen.myapplication.BaseApplication;
import com.example.linhnguyen.myapplication.R;
import com.example.linhnguyen.myapplication.model.CalenderEvent;
import com.example.linhnguyen.myapplication.util.DebugLog;
import com.example.linhnguyen.myapplication.vew.SwipeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnTouch;

/**
 * Created by LinhNguyen on 11/4/2015.
 */
public class ListEventViewHolder extends OnClickViewHolder {

    @InjectView(R.id.img_item_img)
    ImageView imgEvent;
    @InjectView(R.id.tv_item_content)
    TextView tvContetnt;

    public ListEventViewHolder(View itemView) {
        super(itemView);
    }

    public void setup(CalenderEvent data) {
        DebugLog.d("=========" + data.uri);
//        Glide.with(BaseApplication.getInstance()).load(data.uri).override(350,500).into(imgEvent);
        tvContetnt.setText(data.content);
    }

    public void swipeLayout(int x, int offsetxx) {
        int height = imgEvent.getLayoutParams().height;
        int heighttv = tvContetnt.getLayoutParams().height;
        int total = height - heighttv;

        // Textview Up - scroll up
        if (offsetxx - x > 0) {
            delta = offsetxx - x;
        } else {
            delta = offsetxx - x;
        }
        tvContetnt.setY(tvContetnt.getY() + delta);
        DebugLog.d("============12============" + tvContetnt.getY() + "++" + delta);
    }

    int delta;

}

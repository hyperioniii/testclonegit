package com.example.linhnguyen.myapplication.adapter.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.linhnguyen.myapplication.BaseApplication;
import com.example.linhnguyen.myapplication.R;
import com.example.linhnguyen.myapplication.model.CalenderEvent;
import com.example.linhnguyen.myapplication.util.DebugLog;

import butterknife.ButterKnife;
import butterknife.InjectView;

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

    public void setup(CalenderEvent data){
        DebugLog.d("========="+data.uri);
//        Glide.with(BaseApplication.getInstance()).load(data.uri).override(350,500).into(imgEvent);
        tvContetnt.setText(data.content);
    }
}

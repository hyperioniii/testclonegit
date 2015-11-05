package com.example.linhnguyen.myapplication.vew;

/**
 * Created by tgioihan on 12/29/2014.
 */
public interface ISlide {
    public void onStartSlide();
    public void onSlide(int offset, int maxDistance);
    public void onSlideFinish(boolean isIn);
}

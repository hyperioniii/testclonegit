package com.example.linhnguyen.myapplication.vew;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.linhnguyen.myapplication.R;
import com.example.linhnguyen.myapplication.util.DebugLog;


public class SwipeView extends RelativeLayout {
    public static final int STATE_SHOW = 1;
    public static final int STATE_HIDE = 1;

    private static final int MAXDURATION = 400;
    private int mTouchSlop;
    private TextView actionView;
    private View contentView;
    private boolean mIsScrolling;
    private float lastTouchX;
    private IAction actionListener;
    private boolean waitAction;

    public int getOffsetActionButton() {
        return offsetX;
    }

    public void setOffsetActionButton(int offsetX) {
        this.offsetX = offsetX;
    }

    public interface IAction{
        public void onAction(SwipeView view);
    }


    private int offsetX;
    private float initialTouchX;
    private Fillinger mFillinger;
    private int state;
    private boolean enableSwipe;

    public SwipeView(Context context) {
        super(context);
    }

    public SwipeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mFillinger = new Fillinger(context);
        mTouchSlop = (int) context.getResources().getDimension(R.dimen.touch_slop_swipview);
        enableSwipe = true;
    }

    public void setActionListener(IAction listener){
        this.actionListener = listener;
        mFillinger.setListener(actionListener);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        actionView = (TextView) findViewById(R.id.tv_item_content);
        contentView =  findViewById(R.id.img_item_img);
        actionView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mIsScrolling){
                    hide(true);
                    waitAction = true;
                }
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int width = r-l;
        final  int height = b-t;
        DebugLog.e("left " + l + " top " + t + " right " + r + " bottom " + b);
        contentView.layout(offsetX, 0, width + offsetX, height);
        actionView.layout(width + offsetX, 0, width + offsetX + actionView.getMeasuredWidth(), height);
    }

    public int checkSate() {
        if(offsetX==-actionView.getMeasuredWidth()){
            state = STATE_SHOW;
        }else{
            state = STATE_HIDE;
        }
        return state;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action & MotionEventCompat.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                lastTouchX = ev.getX();
                initialTouchX = ev.getX();
                mFillinger.cancleAnimation();
                getParent().requestDisallowInterceptTouchEvent(false);
                mIsScrolling = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mIsScrolling){
                    if (Math.abs(ev.getX() - initialTouchX) > mTouchSlop) {

                        return true;
                    }
                }else{
                    return false;
                }
                break;

            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(waitAction){
            return false;
        }
        if(!enableSwipe){
            return false;
        }
        final int action = ev.getAction();
        switch (action & MotionEventCompat.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                lastTouchX = ev.getX();
                initialTouchX = ev.getX();
                mFillinger.cancleAnimation();
                getParent().requestDisallowInterceptTouchEvent(false);
                mIsScrolling = false;
                break;
            case MotionEvent.ACTION_MOVE:
                DebugLog.d("mIsScrolling "+mIsScrolling +" initialTouchX "+initialTouchX +" ev.getX() "+ev.getX());
                if(mIsScrolling){
                    moveViewByX(ev.getX() - lastTouchX);
                    lastTouchX = ev.getX();
                    getParent().requestDisallowInterceptTouchEvent(true);
                }else{
                    DebugLog.d("ev.getX()-initialTouchX) "+(ev.getX()-initialTouchX)+" mTouchSlop "+mTouchSlop);
                    if (Math.abs(ev.getX() - initialTouchX) > mTouchSlop) {
                        mIsScrolling = true;
                        getParent().requestDisallowInterceptTouchEvent(true);
                        lastTouchX = ev.getX();
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if(offsetX>-actionView.getMeasuredWidth()&& offsetX!=0){
                    hide();
                }
                mIsScrolling = false;
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            case MotionEvent.ACTION_CANCEL:
                mIsScrolling = false;
            default:
                break;
        }
        return true;
    }

    public void show(){
        mFillinger.startScroll(offsetX,-actionView.getMeasuredWidth(), actionView.getMeasuredWidth(),MAXDURATION);
    }

    public void hide(boolean action){
        mFillinger.startScroll(offsetX,0, actionView.getMeasuredWidth(),MAXDURATION,action);
    }

    public void hide(){
        mFillinger.startScroll(offsetX,0, actionView.getMeasuredWidth(),MAXDURATION);
    }

    private void moveViewByX(float diffX) {
        offsetX+=diffX;
        if(offsetX<-actionView.getMeasuredWidth()){
            offsetX = -actionView.getMeasuredWidth();
        }
        if (offsetX>0){
            offsetX = 0;
        }
        actionView.layout(getWidth() + offsetX, 0, getWidth() + offsetX + actionView.getMeasuredWidth(), getHeight());
        contentView.layout(offsetX,0, contentView.getWidth()+offsetX, contentView.getHeight());
    }

    public void setViewState(int stateHide) {
        if(offsetX<0){
            mFillinger.startScroll(offsetX,0, actionView.getMeasuredWidth(),MAXDURATION);
        }
    }

    public boolean isEnableSwipe() {
        return enableSwipe;
    }

    public void setEnableSwipe(boolean enableSwipe) {
        this.enableSwipe = enableSwipe;
    }

    public View getContentView() {
        return contentView;
    }

    public TextView getActionView() {
        return actionView;
    }

    public class Fillinger implements Runnable {
        private static final String tag = "Fillinger";
        private Scroller mScroller;
        private int lastX;
        private boolean more;
        private int currentY;
        private int diffX;

        private IAction listener ;
        private boolean action;

        public Fillinger(Context context) {
            mScroller = new Scroller(context);
        }

        public void startScroll(float startX, float endX, float maxDistance, int maxDurationForFling,boolean action) {
            this.action = action;
            startScroll(startX,endX,maxDistance,maxDurationForFling);
        }

        public void startScroll(float startX, float endX, float maxDistance, int maxDurationForFling) {
            int duration = (int) Math.min(Math.abs((endX - startX)) / maxDistance * maxDurationForFling, maxDurationForFling);
            lastX = (int) startX;
            mScroller.startScroll(0, (int) startX, 0, -(int) (endX - startX), duration);
            setDrawingCacheEnabled(true);
            post(this);
        }

        public void cancleAnimation(){
            removeCallbacks(this);
        }

        @Override
        public void run() {
            more = mScroller.computeScrollOffset();
            currentY = mScroller.getCurrY();
            diffX = lastX - currentY;
            moveViewByX(diffX);
            lastX = currentY;
            if (more) {
                post(this);
            }else{
                setDrawingCacheEnabled(false);
                if(action&&listener!=null){
                    listener.onAction(SwipeView.this);
                }
                action = false;
                SwipeView.this.waitAction = false;
            }
        }

        public void setListener(IAction listener) {
            this.listener = listener;
        }

        public IAction getListener() {
            return listener;
        }
    }
}

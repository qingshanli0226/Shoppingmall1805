package com.shopmall.bawei.shopmall1805.home;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public
class PullToRefreshView extends FrameLayout {
    private RecyclerView recyclerView;
    private float beGinY = 0f;
    private float lastY = 0f;

    public PullToRefreshView(@NonNull Context context) {
        super(context);
        init();
    }


    public PullToRefreshView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToRefreshView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        TextView textView = new TextView(getContext());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50);
        textView.setText("下拉刷新");
        textView.setBackgroundColor(Color.RED);
        textView.setLayoutParams(params);
        textView.setTextSize(26);
        addView(textView);

        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutParams(params1);
        recyclerView.setBackgroundColor(Color.BLUE);
        addView(recyclerView);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isTouch = true;
        if (event.getAction()==MotionEvent.ACTION_MOVE){
            float moveY = event.getY()-beGinY;
            FrameLayout.LayoutParams params = (LayoutParams) recyclerView.getLayoutParams();
            int top = params.topMargin;
            top = top+(int)moveY;
            if (top>50){
                top  = 50;
            }else if (top<0){
                top = 0;
            }
            params.topMargin = top;
            recyclerView.setLayoutParams(params);
            lastY = event.getY();
        }
        return isTouch;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isinter  = false;
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN://点击
                    beGinY = ev.getY();
                break;
                case MotionEvent.ACTION_MOVE://放开
                    float moveY = ev.getY()-beGinY;
                    if (moveY>0){
                        if (recyclerView.getScaleY()==0){
                            isinter = true;
                        }
                    }else if (moveY<0){
                        FrameLayout.LayoutParams params = (LayoutParams) recyclerView.getLayoutParams();
                        int blow = params.topMargin;
                        if (blow>0){
                            isinter = true;
                        }
                    }
                    beGinY  = ev.getY();
                    break;
        }
        return isinter;
    }
}

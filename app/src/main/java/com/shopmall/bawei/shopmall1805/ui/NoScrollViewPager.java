package com.shopmall.bawei.shopmall1805.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {
    private boolean isCanScroll = false;//默认不可以滑动
    public NoScrollViewPager(Context context) {
        super(context);
    }
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public boolean isCanScroll() {
        return isCanScroll;
    }
    public void setCanScroll(boolean canScroll) {
        isCanScroll = canScroll;
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isCanScroll) {
            return super.onTouchEvent(ev);
        } else {
            return false;
        }

    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isCanScroll) {
            return super.onInterceptTouchEvent(ev);
        } else{
            return false;
        }
    }
}

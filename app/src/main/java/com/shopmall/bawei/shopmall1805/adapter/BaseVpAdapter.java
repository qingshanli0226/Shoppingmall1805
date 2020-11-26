package com.shopmall.bawei.shopmall1805.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

public class BaseVpAdapter extends ViewPager {

    private boolean canscroll=true;
    public BaseVpAdapter(@NonNull Context context) {
        super(context);
    }
    public boolean setscrollable(boolean canscroll){
        this.canscroll=canscroll;
        return canscroll;
    }

    public BaseVpAdapter(@NonNull Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return canscroll&&super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return canscroll&&super.onTouchEvent(ev);
    }


}

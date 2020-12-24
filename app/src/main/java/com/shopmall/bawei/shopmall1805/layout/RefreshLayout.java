package com.shopmall.bawei.shopmall1805.layout;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.shopmall.bawei.shopmall1805.R;

public class RefreshLayout extends FrameLayout {
    private LinearLayout linearLayout;
    private LinearLayout loadingLinearLayout;
    public RefreshLayout(@NonNull Context context) {
        super(context);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    //定义上拉加载页面
    public void initLoadingView(){
        //下拉显示的头布局是LinerLayout
        loadingLinearLayout=new LinearLayout(getContext());
        loadingLinearLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));//设置布局的宽度和高度
        loadingLinearLayout.setOrientation(LinearLayout.VERTICAL);//设置排列方向
        addView(loadingLinearLayout);//添加进布局

        View loaderView =LayoutInflater.from(getContext()).inflate(R.layout.load_layoud,null);
        
    }
}

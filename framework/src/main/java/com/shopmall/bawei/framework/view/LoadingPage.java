package com.shopmall.bawei.framework.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shopmall.bawei.framework.R;


//在基类中定义加载页面，错误页面，空白页面
public abstract class LoadingPage extends FrameLayout {

    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;
    private TextView errorTv;

    public LoadingPage(@NonNull Context context) {
        super(context);
        init(context);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init(Context context) {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        LayoutInflater layoutInflater = LayoutInflater.from(context);


        errorView = layoutInflater.inflate(R.layout.view_error, null);
        addView(errorView, layoutParams);//添加到FrameLayout中
        errorTv = errorView.findViewById(R.id.errorTv);
        emptyView = layoutInflater.inflate(R.layout.view_empty, null);
        addView(emptyView, layoutParams);//添加到FrameLayout中

        successView = layoutInflater.inflate(getSuccessLayoutId(), null);
        addView(successView, layoutParams);//将正确页面也添加到布局中
        loadingView = layoutInflater.inflate(R.layout.view_loading, null);
        loadingView.setBackgroundColor(Color.TRANSPARENT);//背景透明
        addView(loadingView, layoutParams);//添加到FrameLayout中

        //默认显示个页面
        showSuccessView();
    }

    protected abstract int getSuccessLayoutId();

    //显示loading页面
    public void showLoadingPage() {
        errorView.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
        successView.setVisibility(VISIBLE);
        emptyView.setVisibility(GONE);
    }

    //显示错误页面
    public void showErrorPage(String errorMsg) {
        errorTv.setText(errorMsg);
        errorView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        successView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
    }

    //显示空白页面
    public void showEmptyPage() {
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        successView.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
    }

    //显示正确页面
    public void showSuccessView() {
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
        emptyView.setVisibility(GONE);
    }
}

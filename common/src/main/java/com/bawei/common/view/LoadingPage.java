package com.bawei.common.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bawei.common.R;

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
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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

    public View getSuccessView() {
        return successView;
    }

    public void showSuccessView() {
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
    }

    public void showErrorView(String errorString) {
        errorTv.setText(errorString);
        errorView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        successView.setVisibility(GONE);
    }

    public void showLoadingView() {
        errorView.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
        emptyView.setVisibility(GONE);
        successView.setVisibility(GONE);
    }

    public void showEmptyView() {
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
        successView.setVisibility(GONE);
    }

    protected abstract int getSuccessLayoutId();
}

package com.shopmall.bawei.framework.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shopmall.bawei.framework.R;

public abstract class LoadingPage extends FrameLayout {

    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;
    private TextView errorTv;


    public View getSuccessView() {
        return successView;
    }


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

        errorView = layoutInflater.inflate(R.layout.view_error,null);
        addView(errorView,layoutParams);
        errorTv = errorView.findViewById(R.id.error_tv);
        emptyView = layoutInflater.inflate(R.layout.view_empty,null);
        addView(emptyView, layoutParams);//添加到FrameLayout中



        if(getSuccessLayoutId() != 0){
            successView = layoutInflater.inflate(getSuccessLayoutId(), null);
            addView(successView, layoutParams);//将正确页面也添加到布局中
        } else if(!(getSuccessView() instanceof View)) {
            addView(successView = getSuccessLayoutView(), layoutParams);
        }
        loadingView = layoutInflater.inflate(R.layout.view_loading, null);
        loadingView.setBackgroundColor(Color.TRANSPARENT);//背景透明

        addView(loadingView, layoutParams);//添加到FrameLayout中

        showSuccessView();
    }

    //显示正确页面
    public void showSuccessView() {
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
        emptyView.setVisibility(GONE);
    }

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
        errorTv.setGravity(Gravity.CENTER);
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

    //protected  abstract int getSuccessLayoutId();


    protected int getSuccessLayoutId(){
        return 0;
    }

    protected View getSuccessLayoutView(){
        return new View(getContext());
    }

}

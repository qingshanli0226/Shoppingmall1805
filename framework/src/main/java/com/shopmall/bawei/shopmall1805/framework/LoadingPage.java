package com.shopmall.bawei.shopmall1805.framework;

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

import com.shopmall.bawei.framework.R;

import org.w3c.dom.Text;

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
        init(context);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutParams layoutParams=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        errorView = layoutInflater.inflate(R.layout.view_error,null);//错误页添加
        addView(errorView,layoutParams);

        emptyView = layoutInflater.inflate(R.layout.view_empty,null);//空白页添加
        addView(emptyView,layoutParams);

        successView = layoutInflater.inflate(getSuccessLyaout(),null);//正确页添加
        addView(successView,layoutParams);

        loadingView = layoutInflater.inflate(R.layout.view_loading,null);//加载页添加
        loadingView.setBackgroundColor(Color.TRANSPARENT);
        addView(loadingView,layoutParams);

        showSuccessView();
    }
    protected abstract int getSuccessLyaout();

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
    public void showSuccessView(){
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
        emptyView.setVisibility(GONE);
    }

}

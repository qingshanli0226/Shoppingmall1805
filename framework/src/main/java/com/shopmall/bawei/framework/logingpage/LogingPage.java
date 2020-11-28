package com.shopmall.bawei.framework.logingpage;

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
public abstract class LogingPage extends FrameLayout {
    private View loadingView;
    private View erroyView;
    private TextView erroyTv;
    private View emptyView;
    private View sucessView;
    public LogingPage(@NonNull Context context) {
        super(context);
        init(context);
    }

    public LogingPage(@NonNull  Context context, @Nullable  AttributeSet attrs) {
        super(context, attrs);
    }

    public LogingPage(@NonNull  Context context, @Nullable  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public View getSucessView() {
        return sucessView;
    }

    public void init(Context context){
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        erroyView = layoutInflater.inflate(R.layout.view_error,null);
        addView(erroyView,layoutParams);
        erroyTv = erroyView.findViewById(R.id.errorTv);

        emptyView = layoutInflater.inflate(R.layout.view_empty,null);
        addView(emptyView,layoutParams);

        sucessView = layoutInflater.inflate(getsuccessId(),null);
        addView(sucessView,layoutParams);//将正确的界面添加到布局中

        loadingView = layoutInflater.inflate(R.layout.view_loading,null);
        loadingView.setBackgroundColor(Color.TRANSPARENT);//背景透明
        addView(loadingView,layoutParams);//添加到frameLayout
        //默认显示sucessView页面
        showsucessPage();

    }

    //显示loading页面
    public void loadingPage(){
        erroyView.setVisibility(GONE);
        sucessView.setVisibility(VISIBLE);
        loadingView.setVisibility(VISIBLE);
        emptyView.setVisibility(GONE);
    }
    //显示错误页面
    public void showError(String errorshow){
        erroyTv.setText(errorshow);
        erroyView.setVisibility(VISIBLE);
        sucessView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
    }
    //显示空白界面
    public void showEnptyPage(){
        erroyView.setVisibility(GONE);
        sucessView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
    }
    //显示正确页面
    public void showsucessPage(){
        erroyView.setVisibility(GONE);
        sucessView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
    }
    protected abstract int getsuccessId();

}

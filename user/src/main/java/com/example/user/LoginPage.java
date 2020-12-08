package com.example.user;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class LoginPage extends FrameLayout {

    private View loadingView;
    private View errorView;
    private TextView errorTv;
    private View emptyView;
    private View successView;

    public LoginPage(Context context) {
        super(context);
    }

    public LoginPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //初始化
    public void init(Context context){
        LayoutParams layoutParams =new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        errorView = layoutInflater.inflate(R.layout.view_error, null);
        addView(errorView,layoutParams);
        errorTv=errorView.findViewById(R.id.errorTv);


        emptyView=layoutInflater.inflate(R.layout.view_error,null);
        addView(emptyView,layoutParams);

        successView=layoutInflater.inflate(getsuccessId(),null);
        addView(successView,layoutParams);

        loadingView=layoutInflater.inflate(R.layout.view_loading,null);
        loadingView.setBackgroundColor(Color.TRANSPARENT);
        addView(loadingView,layoutParams);
        showsuccessPage();
    }
    //显示loading页面
    public void loadingPage(){
        errorView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
        loadingView.setVisibility(VISIBLE);
        emptyView.setVisibility(GONE);
    }
    //显示错误页面
    public void showError(String errorshow){
        errorTv.setText(errorshow);
        errorView.setVisibility(VISIBLE);
        successView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
    }
    //显示空白界面
    public void showEnptyPage(){
        errorView.setVisibility(GONE);
        successView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
    }
    //显示正确页面
    private void showsuccessPage() {
        errorView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
    }

    protected abstract int getsuccessId();


}

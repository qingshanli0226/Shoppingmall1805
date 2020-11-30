package com.example.framework.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.framework.R;

public abstract class LoadingPage extends FrameLayout {
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View emptyCarView;
    private View successView;
    private TextView errorTv;
    private TextView tvEmptyCartTobuy;
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
    public void init(Context context){
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        errorView=layoutInflater.inflate(R.layout.view_error,null);
        addView(errorView,layoutParams);
        errorTv=errorView.findViewById(R.id.tv_error);
        emptyView=layoutInflater.inflate(R.layout.view_empty,null);
        addView(emptyView,layoutParams);
        emptyCarView=layoutInflater.inflate(R.layout.empty_cart,null);
        addView(emptyCarView,layoutParams);
        tvEmptyCartTobuy =emptyCarView.findViewById(R.id.tv_empty_cart_tobuy);
        successView=layoutInflater.inflate(getSuccessLayoutId(),null);
        addView(successView);
        loadingView=layoutInflater.inflate(R.layout.view_loading,null);
        loadingView.setBackgroundColor(Color.TRANSPARENT);
        addView(loadingView,layoutParams);
        showSuccessView();
    }

    public void showSuccessView(){
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        successView.setVisibility(VISIBLE);
        emptyView.setVisibility(GONE);
        emptyCarView.setVisibility(GONE);
    }
    //显示loading页面
    public void showLoadingPage() {
        errorView.setVisibility(GONE);
        loadingView.setVisibility(VISIBLE);
        successView.setVisibility(VISIBLE);
        emptyView.setVisibility(GONE);
        emptyCarView.setVisibility(GONE);
    }

    //显示错误页面
    public void showErrorPage(String errorMsg) {
        errorTv.setText(errorMsg);
        errorView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        successView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        emptyCarView.setVisibility(GONE);
    }

    //显示空白页面
    public void showEmptyPage() {
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        successView.setVisibility(GONE);
        emptyView.setVisibility(VISIBLE);
        emptyCarView.setVisibility(GONE);
    }
    public void showEmptyCarPage() {
        tvEmptyCartTobuy.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Yoyo", "onClick: ");
                ARouter.getInstance().build("/main/MainActivity").withString("position","0").navigation();
            }
        });
        errorView.setVisibility(GONE);
        loadingView.setVisibility(GONE);
        successView.setVisibility(GONE);
        emptyView.setVisibility(GONE);
        emptyCarView.setVisibility(VISIBLE);
    }

    protected abstract int getSuccessLayoutId();

}

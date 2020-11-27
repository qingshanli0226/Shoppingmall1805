package com.bawei.deom.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.shopmall.bawei.framework.R;

public abstract class LoadingPage extends FrameLayout {
     private View loadingView;
     private View errorView;
     private View emptyView;
     private View successView;
     private TextView errorTV;

    public LoadingPage( @NonNull Context context) {
        super(context);
        init(context);
    }

    public LoadingPage(@NonNull  Context context,  @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingPage( @NonNull Context context,  @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    private void init(Context context){
    FrameLayout.LayoutParams layoutParams=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        LayoutInflater layoutInflater=LayoutInflater.from(context);

        errorView=layoutInflater.inflate(R.layout.view_error,null);
        addView(errorView,layoutParams);
        errorTV=errorView.findViewById(R.id.errorTv);
        emptyView=layoutInflater.inflate(R.layout.view_empty,null);
        addView(emptyView,layoutParams);

        successView=layoutInflater.inflate(getSuccessLayoutid(),null);
        addView(successView,layoutParams);
        loadingView=layoutInflater.inflate(R.layout.view_loading,null);
        loadingView.setBackgroundColor(Color.TRANSPARENT);
        addView(loadingView,layoutParams);

    }


    protected abstract int getSuccessLayoutid();

    //显示loading
   public  void showLoadingPage(){
       errorView.setVisibility(GONE);
       loadingView.setVisibility(VISIBLE);
       successView.setVisibility(VISIBLE);
       emptyView.setVisibility(GONE);
   }
  //显示错误页面
  public void showErrorPage(String errorMsg) {
      errorTV.setText(errorMsg);
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
  //显示正确页面
  public void showSuccessView() {
      errorView.setVisibility(GONE);
      loadingView.setVisibility(GONE);
      successView.setVisibility(VISIBLE);
      emptyView.setVisibility(GONE);
  }
}

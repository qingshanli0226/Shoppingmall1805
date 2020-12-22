package com.shopmall.bawei.framework.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.shopmall.bawei.framework.R;


public class RefreshLayout extends FrameLayout {
    private LinearLayout linearLayout;
    private ProgressBar progressBar;//头布局加载的转圈
    private TextView textTv;
    private int headerViewHeight;
    private int footViewHeight;
    private int paddingTop;
    private int paddingBottom;
    private float lastY;
    private IRefreshListener iRefreshListener;
    private  ListView listView;
    private int firstVisiblePosition;

    public RefreshLayout(@NonNull Context context) {
        super(context);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


        initHeaderView();
//        initFootView();
    }

    private void initFootView() {
        //下拉显示的头布局的根布局是LinearLayout
        linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        addView(linearLayout);//index =0



        View footView = LayoutInflater.from(getContext()).inflate(R.layout.refresh_layout, null);
        progressBar = footView.findViewById(R.id.progressBar);
        textTv = footView.findViewById(R.id.textTv);
        linearLayout.addView(footView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200));
        footViewHeight = 200;

        paddingBottom = -footViewHeight;
        linearLayout.setPadding(0,0,0,paddingBottom);//将加载的UI默认隐藏到顶部，不显示出来
        //如果你想显示出来，将padding的顶部的从负数逐渐增加到0
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initHeaderView() {
        //下拉显示的头布局的根布局是LinearLayout
        linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        addView(linearLayout);//index =0



        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.refresh_layout, null);
        progressBar = headerView.findViewById(R.id.progressBar);
        textTv = headerView.findViewById(R.id.textTv);
        linearLayout.addView(headerView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,200));
        headerViewHeight = 200;

        paddingTop = -headerViewHeight;
        linearLayout.setPadding(0,paddingTop,0,0);//将加载的UI默认隐藏到顶部，不显示出来
        //如果你想显示出来，将padding的顶部的从负数逐渐增加到0
    }


    //该函数将xml布局生成了view
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        listView = (ListView) getChildAt(1);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                firstVisiblePosition = firstVisibleItem;
            }
        });
    }

    private int oldY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
         switch (ev.getAction()) {
             case MotionEvent.ACTION_DOWN:
                 oldY = (int) ev.getY();
                 break;
             case MotionEvent.ACTION_MOVE:
                 if (ev.getY()> oldY && firstVisiblePosition == 0) {
                     return true;//如果等于0就拦截事件，显示下拉刷新的headerview
                 } else {
                     return false;
                 }
         }

         return  super.onInterceptTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
         super.onTouchEvent(event);

         switch (event.getAction()) {
             case MotionEvent.ACTION_DOWN:
                 lastY = event.getY();//记录当前点击的位置在y轴的坐标
                 break;
             case MotionEvent.ACTION_MOVE:
                 //确保手指是往下滑动，并且滑动距离不超过headerview的高度，如果超过就不用处理了
                 if (event.getY() > lastY && event.getY()-lastY <= headerViewHeight) {
                     float diffY = event.getY() - lastY;
                     float pTop = paddingTop+diffY;
                     linearLayout.setPadding(0, (int) pTop,0,0);//改变头部的top的值，逐渐让它显示出来
                 }
                 break;
             case MotionEvent.ACTION_UP:
                 if (event.getY() - lastY >= headerViewHeight/2) {//当你滑动的距离超过了headerview高度的一半，就会将headerView显示出来
                     linearLayout.setPadding(0,0,0,0);
                     progressBar.setVisibility(VISIBLE);
                     textTv.setVisibility(VISIBLE);
                     if (iRefreshListener!=null) {
                         iRefreshListener.onRefresh();/*
                       */
                     }
                     new Handler().postDelayed(new Runnable() {
                         @Override
                         public void run() {
                             //两秒钟之后再次隐藏
                             linearLayout.setPadding(0,paddingTop,0,0);
                             progressBar.setVisibility(GONE);
                             textTv.setVisibility(GONE);
                             if (iRefreshListener!=null) {
                                 iRefreshListener.onRefreshComplete();
                             }
                         }
                     },2000);
                 } else {
                     linearLayout.setPadding(0,paddingTop,0,0);
                 }
                 break;
         }





         return true;//消费事件
    }

    public void addRefreshListener(IRefreshListener iRefreshListener) {
        this.iRefreshListener = iRefreshListener;
    }


    public interface IRefreshListener{
        void onRefresh();
        void onRefreshComplete();
    }
}

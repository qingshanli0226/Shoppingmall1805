package com.shopmall.bawei.shopmall1805.layout;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shopmall.bawei.shopmall1805.R;

public class RefreshLayout extends FrameLayout {
    private LinearLayout linearLayout;
    private LinearLayout loadingLinearLayout;
    private ProgressBar progressBar;
    private ProgressBar loadProgressBar;//头布局加载的转圈
    private TextView textTv;
    private TextView loadTextTv;
    private int headerViewHeight;
    private int loadingPaddingTop;
    private int height;
    private RecyclerView recyclerView;
    private int firstVisiblePosition;
    private boolean isNeedLoad;
    private float lastY;
    private final int REFRESH_MAX_TOP = 200;
    private final int LOAD_MAX_TOP = 300;
    private int paddingTop;
    public RefreshLayout(@NonNull Context context) {
        super(context);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        height=wm.getDefaultDisplay().getHeight();
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

        View loaderView =LayoutInflater.from(getContext()).inflate(R.layout.load_layoud,null);//获得加载数据的布局
        loadProgressBar=loaderView.findViewById(R.id.loadProgressBar);//获得加载数据的ProgressBar
        loadTextTv=loaderView.findViewById(R.id.loadTextTv);
        loadingLinearLayout.addView(loaderView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
         loadingPaddingTop=height;
         loadingLinearLayout.setPadding(0,loadingPaddingTop,0,0);//将加载的UI默认隐藏到顶部不显示出来
        //如果你想显示出来将padding的顶部的从负数逐渐增加到0
    }
    private void initRefreshView(){//定义下拉刷新的View
        //下拉刷线的头布局的更布局是LInerLayout
        linearLayout=new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));//设置一个线性布局的宽高
        linearLayout.setOrientation(LinearLayout.VERTICAL);//设置线性布局的排列方式
        addView(linearLayout);//添加进页面
        View headerView=LayoutInflater.from(getContext()).inflate(R.layout.redresh_layoud,null);//获得布局id
        progressBar=headerView.findViewById(R.id.progressBar);
        textTv=headerView.findViewById(R.id.textTv);
        linearLayout.addView(headerView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));//将控件设置进布局
         headerViewHeight=REFRESH_MAX_TOP;

        paddingTop=-headerViewHeight;//获得下拉的高度
        linearLayout.setPadding(0,paddingTop,0,0);
    }
    int lastItempostion;//最后一个的位置
    int firstItemPosition;//第一个的位置
    int visibleItemCount;//可见的项目数
    int itemCount;//一共的项目数
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recyclerView=(RecyclerView)getChildAt(0);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager){
                    LinearLayoutManager linearLayoutManager=(LinearLayoutManager)layoutManager;
                      lastItempostion=linearLayoutManager.findLastVisibleItemPosition();//最后一个的位置
                     firstItemPosition = linearLayoutManager.findFirstVisibleItemPosition();//第一个的位置
                    visibleItemCount=lastItempostion-firstVisiblePosition;
                    itemCount = recyclerView.getLayoutManager().getItemCount();
                    Log.e("数量","第一个"+firstVisiblePosition+"最后一个"+lastItempostion+"总共"+visibleItemCount);

                }


            }
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                  firstVisiblePosition=firstItemPosition;
                  if(firstItemPosition+visibleItemCount==itemCount){
                      Log.d("上拉加载", "需要上拉加载。。。");
                      isNeedLoad=true;
                  }else {
                      Log.d("不能上拉加载", "不能上拉加载。。。");
                      isNeedLoad = false;
                  }
            }
        });
        initRefreshView();
        initLoadingView();
    }
    private int oldy;
    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                oldy=(int)event.getY();
                lastY=event.getY();
                lastListY=recyclerView.getY();
                Log.e("Y轴","lastListY"+recyclerView.getY()+"oldy"+oldy+"lastY"+lastY);
                lastListX=recyclerView.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getY()>oldy&&firstVisiblePosition==0){
                    Log.d("下拉刷新拦截成功 ", "下拉刷新拦截成功。。。。");
                    return true;//如果等于0就拦截事件，显示下拉刷新的headerview
                }else if (isNeedLoad&&event.getY()<oldy){
                    Log.d("上拉拦截成功 ", "上拉拦截成功。。。。");
                    return true;
                }else {
                   return false;
                 }
        }
        return super.onInterceptHoverEvent(event);
    }

    private float lastListY, lastListX;
    private IRefreshListener iRefreshListener;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
         super.onTouchEvent(event);
         switch (event.getAction()){
             case MotionEvent.ACTION_DOWN:
                 break;
             case MotionEvent.ACTION_HOVER_MOVE:
                 //确保手指是往下滑动，并且滑动距离不超过headerview的高度，如果超过就不用处理了
                if (event.getY()>lastY&&event.getY()-lastY<=headerViewHeight){
                      float diffy=event.getY()-lastY;
                      float pTop=paddingTop+diffy;
                      linearLayout.setPadding(0,(int)pTop,0,0);//改变头部的top值逐渐让他显示出来
                    recyclerView .scrollTo((int)lastListX,-(int)(lastListY+diffy));
                }else if (isNeedLoad&&lastY-event.getY()<=LOAD_MAX_TOP){
                   float loadDiffy= lastY-event.getY();
                    Log.d("loadDiffY ", "loadDiffY = " + loadDiffy);
                   loadingLinearLayout.setPadding(0,(int)(loadingPaddingTop-loadDiffy),0,0);
                }else {
                    Log.d("条件不满足 ", "条件不满足:" + lastY + " " + event.getY());
                }
                 break;
                case MotionEvent.ACTION_UP:
                    if (event.getY()-lastY>=headerViewHeight/2){//当你滑动的距离超过了headerview高度的一半，就会将headerView显示出来
                            linearLayout.setPadding(0,0,0,0);
                            progressBar.setVisibility(VISIBLE);
                            textTv.setVisibility(VISIBLE);
                            if (iRefreshListener!=null){
                                iRefreshListener.onRefresh();
                            }
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {//两秒以后再次隐藏
                                    linearLayout.setPadding(0,paddingTop,0,0);
                                    progressBar.setVisibility(GONE);
                                    textTv.setVisibility(GONE);
                                    if (iRefreshListener!=null){
                                        iRefreshListener.onRefreshComplete();
                                    }
                                    recyclerView.scrollTo((int)lastListX,-(int)(lastListY));
                                }
                            },2000);
                    }else if (isNeedLoad&&event.getY()<lastY){
                        if (lastY-event.getY()>LOAD_MAX_TOP/2){//当recyleView的长度减去你滑动的长度大于总长度的一般
                            loadingLinearLayout.setPadding(0,loadingPaddingTop-LOAD_MAX_TOP,0,0);
                            loadProgressBar.setVisibility(VISIBLE);
                            loadTextTv.setVisibility(VISIBLE);
                            linearLayout.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setVisibility(GONE);
                                    textTv.setVisibility(GONE);
                                    loadingLinearLayout.setPadding(0,loadingPaddingTop,0,0);
                                    if (iRefreshListener!=null) {
                                        iRefreshListener.onLoadMore();
                                    }
                                }
                            },2000);
                        }else {
                            loadingLinearLayout.setPadding(0,loadingPaddingTop,0,0);
                        }
                    }else {
                        linearLayout.setPadding(0,paddingTop,0,0);
                        recyclerView.scrollTo((int)lastListX,-(int)(lastListY));
                    }

                    break;
         }
        return true;
    }
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }

    public void addRefreshListener(IRefreshListener iRefreshListener) {
        this.iRefreshListener = iRefreshListener;
    }
    public interface IRefreshListener{
        void onRefresh();
        void onLoadMore();
        void onRefreshComplete();
    }
}

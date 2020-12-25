package com.example.elevenmonthshoppingproject;

import android.content.Context;
import android.os.Handler;
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
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;

public class RefreshLayout extends FrameLayout {
    private LinearLayout linearLayout;
    private LinearLayout loadingLinearLayout;
    private ProgressBar progressBar;
    private ProgressBar loadProgressBar;
    private int height;
    private ListView listView;
    private int firstVisiblePosition;
    private boolean isNeedLoad;
    private IRefreshListerner iRefreshListerner;
    private float lastY;
    private float lastListY, lastListX;
    private int headerViewHeight;
    private TextView textTv;
    private TextView loadTextTv;
    private int paddingTop;
    private int loadingPaddingTop;
    private final int REFRESH_MAX_TOP = 200;
    private final int LOAD_MAX_TOP = 300;
    public RefreshLayout(Context context) {
        super(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
         height = windowManager.getDefaultDisplay().getHeight();
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //定义上拉加载的页面
    private void iniLoadingView(){
        loadingLinearLayout = new LinearLayout(getContext());
        loadingLinearLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        loadingLinearLayout.setOrientation(LinearLayout.VERTICAL);
        addView(loadingLinearLayout);//index =0

        View loaderView = LayoutInflater.from(getContext()).inflate(R.layout.load_layout, null);
        loadProgressBar = loaderView.findViewById(R.id.loadProgressBar);
        loadTextTv = loaderView.findViewById(R.id.loadTextTv);
        loadingLinearLayout.addView(loaderView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        loadingPaddingTop = height;
        loadingLinearLayout.setPadding(0,loadingPaddingTop,0,0);//将加载的UI默认隐藏到顶部，不显示出来
    }
    //定义下拉刷新的页面
    private void  iniRefreshView(){
        linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        addView(linearLayout);//index =0

        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.refresh_layout, null);
        progressBar = headerView.findViewById(R.id.progressBar);
        textTv = headerView.findViewById(R.id.textTv);
        linearLayout.addView(headerView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,REFRESH_MAX_TOP));
        headerViewHeight = REFRESH_MAX_TOP;

        paddingTop = -headerViewHeight;
        linearLayout.setPadding(0,paddingTop,0,0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        listView= (ListView)getChildAt(0);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                firstVisiblePosition=i;
                if (firstVisiblePosition+i1==listView.getCount()){
                    Log.i("---","需要上拉加载");
                    isNeedLoad=true;
                }else {
                    Log.i("---","需要下拉刷新");
                    isNeedLoad=false;
                }
            }
        });
        iniLoadingView();
        iniRefreshView();
    }

    private int oldY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                oldY= (int) ev.getY();
                lastY=ev.getY();
                lastListX=listView.getX();
                lastListY=listView.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (ev.getY()>oldY&&firstVisiblePosition==0){
                    return true;
                }else if (isNeedLoad && ev.getY()<oldY){
                    return true;
                }else {
                    return false;
                }

        }

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getY()>lastY&&event.getY()-lastY<=headerViewHeight){
                    float diffy = event.getY() - lastY;
                    float pTop = paddingTop+diffy;
                    linearLayout.setPadding(0, (int) pTop,0,0);//改变头部的top的值，逐渐让它显示出来
                    listView.scrollTo((int)lastListX,-(int)(lastListY+diffy));
                }else if (isNeedLoad && lastY - event.getY() <= LOAD_MAX_TOP ) {
                    float loadDiffY = lastY - event.getY();
                    loadingLinearLayout.setPadding(0, (int) (loadingPaddingTop-loadDiffY),0,0);
                }else {
                    Toast.makeText(getContext(), "不满足条件", Toast.LENGTH_SHORT).show();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (event.getY() - lastY >= headerViewHeight/2) {//当你滑动的距离超过了headerview高度的一半，就会将headerView显示出来
                    linearLayout.setPadding(0,0,0,0);
                    progressBar.setVisibility(VISIBLE);
                    textTv.setVisibility(VISIBLE);
                    if (iRefreshListerner!=null) {
                        iRefreshListerner.onRefresh();
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //两秒钟之后再次隐藏
                            linearLayout.setPadding(0,paddingTop,0,0);
                            progressBar.setVisibility(GONE);
                            textTv.setVisibility(GONE);
                            if (iRefreshListerner!=null) {
                                iRefreshListerner.onRefreshComplete();
                            }
                            listView.scrollTo((int)lastListX,-(int)(lastListY));
                        }
                    },2000);
                } else if (isNeedLoad && event.getY()<lastY) {
                    Log.d("LQS ", "显示上拉页面:");
                    if (lastY-event.getY()>LOAD_MAX_TOP/2) {
                        loadingLinearLayout.setPadding(0,loadingPaddingTop-LOAD_MAX_TOP,0,0);
                        loadProgressBar.setVisibility(VISIBLE);
                        loadTextTv.setVisibility(VISIBLE);
                        linearLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(GONE);
                                textTv.setVisibility(GONE);
                                loadingLinearLayout.setPadding(0,loadingPaddingTop,0,0);
                                if (iRefreshListerner!=null) {
                                    iRefreshListerner.onLoadMore();
                                }
                            }
                        },2000);
                    } else {
                        loadingLinearLayout.setPadding(0,loadingPaddingTop,0,0);
                    }
                }
                else {
                    linearLayout.setPadding(0,paddingTop,0,0);
                    listView.scrollTo((int)lastListX,-(int)(lastListY));
                }
                break;

        }
        return true;
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(target, velocityX, velocityY, consumed);
    }


    public void addIRefreshListerner(IRefreshListerner iRefreshListerner) {
        this.iRefreshListerner = iRefreshListerner;
    }

    public interface  IRefreshListerner{
        void onRefresh();
        void onLoadMore();
        void onRefreshComplete();
    }
}

package com.bawei.framework;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RefreshLayout extends FrameLayout {

    private int height;
    private LinearLayout loadingLinearLayout;
    private ProgressBar loadProgressBar;
    private ProgressBar progressBar;
    private TextView loadText;
    private int loadingPaddingTop;
    private LinearLayout linearLayout;
    private TextView textTv;
    private int headerViewHeight;
    private final int REFRESH_MAX_TOP = 200;
    private final int LOAD_MAX_TOP = 300;
    private int paddingTop;
    private RecyclerView recyclerView;
    private int firstVisiblePosition;
    private boolean isNeed;
    private float lastY;
    private IRefreshListener iRefreshListener;

    public RefreshLayout(@NonNull Context context) {
        super(context);
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        height = windowManager.getDefaultDisplay().getHeight();
    }

    public RefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initLoadingView() {
        loadingLinearLayout = new LinearLayout(getContext());
        loadingLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        loadingLinearLayout.setOrientation(LinearLayout.VERTICAL);
        addView(loadingLinearLayout);

        View loaderView = LayoutInflater.from(getContext()).inflate(R.layout.load_layout, null);
        loadProgressBar = loaderView.findViewById(R.id.loadProgress);
        loadText = loaderView.findViewById(R.id.loadText);
        loadingLinearLayout.addView(loaderView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        loadingPaddingTop = height;
        loadingLinearLayout.setPadding(0, loadingPaddingTop, 0, 0);
    }

    private void initRefreshView() {
        linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        addView(linearLayout);//index =0

        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.refresh_layout, null);
        progressBar = headerView.findViewById(R.id.progressBar);
        textTv = headerView.findViewById(R.id.textTv);
        linearLayout.addView(headerView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, REFRESH_MAX_TOP));
        headerViewHeight = REFRESH_MAX_TOP;

        paddingTop = -headerViewHeight;
        linearLayout.setPadding(0, paddingTop, 0, 0);//将加载的UI默认隐藏到顶部，不显示出来
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recyclerView = (RecyclerView) getChildAt(0);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                firstVisiblePosition = dx;
                if (dx + dy == recyclerView.getChildCount()) {
                    isNeed = true;
                } else {
                    isNeed = false;
                }
            }
        });
        initLoadingView();
        initRefreshView();
    }

    private int oldY;

    private float lastListY, lastListX;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                oldY = (int) ev.getY();
                lastY = ev.getY();//记录当前点击的位置在y轴的坐标
                lastListY = recyclerView.getY();
                lastListX = recyclerView.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                if (ev.getY() > oldY && firstVisiblePosition == 0) {
                    Log.d("LQS ", "下拉刷新拦截成功。。。。");
                    return true;//如果等于0就拦截事件，显示下拉刷新的headerview
                } else if (isNeed && ev.getY() < oldY) {
                    Log.d("LQS ", "上拉拦截成功。。。。");
                    return true;
                } else {
                    return false;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getY() > lastY && event.getY() - lastY <= headerViewHeight) {
                    float diffY = event.getY() - lastY;
                    float pTop = paddingTop + diffY;
                    linearLayout.setPadding(0, (int) pTop, 0, 0);
                    recyclerView.scrollTo((int) lastY, -(int) (lastListY + diffY));
                } else if (isNeed && lastY - event.getY() <= LOAD_MAX_TOP) {
                    float loadDiffY = lastY - event.getY();
                    loadingLinearLayout.setPadding(0, (int) (loadingPaddingTop - loadDiffY), 0, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (event.getY() - lastY >= headerViewHeight / 2) {
                    linearLayout.setPadding(0, 0, 0, 0);
                    progressBar.setVisibility(VISIBLE);
                    textTv.setVisibility(VISIBLE);
                    if (iRefreshListener != null) {
                        iRefreshListener.onRefresh();
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            linearLayout.setPadding(0, paddingTop, 0, 0);
                            progressBar.setVisibility(GONE);
                            textTv.setVisibility(GONE);
                            if (iRefreshListener != null) {
                                iRefreshListener.onRefreshComplete();
                            }
                            recyclerView.scrollTo((int) lastListX, -(int) lastListY);
                        }
                    }, 2000);
                } else if (isNeed && event.getY() < lastY) {
                    if (lastY-event.getY()>LOAD_MAX_TOP/2){
                        loadingLinearLayout.setPadding(0,loadingPaddingTop-LOAD_MAX_TOP,0,0);
                        loadProgressBar.setVisibility(VISIBLE);
                        loadText.setVisibility(VISIBLE);
                        linearLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(GONE);
                                textTv.setVisibility(GONE);
                                loadingLinearLayout.setPadding(0,loadingPaddingTop,0,0);
                                if (iRefreshListener!=null){
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

    public void addRefreshListener(IRefreshListener iRefreshListener){
        this.iRefreshListener = iRefreshListener;
    }

    public interface IRefreshListener {
        void onRefresh();

        void onLoadMore();

        void onRefreshComplete();
    }
}

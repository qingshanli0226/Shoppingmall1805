package cn.bw.textprojectone;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public
class RefreshLayout extends FrameLayout {
    private int height ;
    private ListView listView;
    private int firstVisiblePosition;
    private boolean isNeedLoad ;
    private LinearLayout linearLayout;
    private LinearLayout loadingLinnerLayout;
    private ProgressBar loadProgressBar;
    private TextView loadTextTv;
    private ProgressBar progressBar;
    private TextView textTv ;
    private final int REFRESH_MAX_TOP = 200;
    private int headerViewHeight ;
    private int paddinTop;
    private int loadingPaddingTop ;
    private float lastY;
    private float lastListY;
    private float lastListX;
    private int LOAD_MAX_TOP;
    private IRefreshListener iRefreshListener;
    public RefreshLayout(Context context) {
        super(context);
    }
    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        height = windowManager.getDefaultDisplay().getHeight();
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        listView = (ListView) getChildAt(0);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                firstVisiblePosition = firstVisibleItem;
                if (firstVisibleItem + visibleItemCount == listView.getCount()) {
                    Log.d("LQS", "需要上拉加载。。。");
                    isNeedLoad = true;
                } else {
                    Log.d("LQS", "不能上拉加载。。。");
                    isNeedLoad = false;
                }
            }
        });

        initRefreshView();
        initLoadingView();

    }

    private void initLoadingView() {
        loadingLinnerLayout = new LinearLayout(getContext());
        loadingLinnerLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        loadingLinnerLayout.setOrientation(LinearLayout.VERTICAL);
        addView(loadingLinnerLayout);

        View loadView = LayoutInflater.from(getContext()).inflate(R.layout.load_layout,null);
        loadProgressBar = loadView.findViewById(R.id.loadProgressBar);
        loadTextTv = loadView.findViewById(R.id.loadTextTv);
        loadingLinnerLayout.addView(loadView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        loadingPaddingTop = height;//???
        loadingLinnerLayout.setPadding(0,loadingPaddingTop,0,0);
        //如果你想显示出来 将padding的顶部从负数增加到0
    }

    /**
     * 下拉刷新
     */
    private void initRefreshView() {
        linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        addView(linearLayout);

        View headView = LayoutInflater.from(getContext()).inflate(R.layout.refresh_layout,null);
        progressBar = headView.findViewById(R.id.progressBar);
        textTv = headView.findViewById(R.id.textTv);
        linearLayout.addView(headView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,REFRESH_MAX_TOP));
        headerViewHeight = REFRESH_MAX_TOP;

        paddinTop = -headerViewHeight;
        linearLayout.setPadding(0,paddinTop,0,0);
    }

    private int oldY;
    //是否拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                oldY = (int)ev.getY();
                lastY = ev.getY();
                lastListY = listView.getY();
                lastListX = listView.getX();

                break;
             case MotionEvent.ACTION_MOVE:
                if (ev.getY() >oldY && firstVisiblePosition==0){
                    Log.i("====","下拉刷新,拦截成功");
                    return true;
                }else if (isNeedLoad && ev.getY()>oldY){
                    Log.i("====","上拉拦截，拦截成功");
                    buildDrawingCache();
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
            case MotionEvent.ACTION_MOVE:
                if (event.getY()>lastY && event.getY()-lastY <= headerViewHeight){
                    float diffY = event.getY()-lastY;
                    float pTop = paddinTop+diffY;
                    Log.i("====","pTop"+pTop);
                    linearLayout.setPadding(0,(int) pTop,0,0);
                    listView.scrollTo((int) lastListX,-(int)(lastListY+diffY));
                }else if (isNeedLoad && lastY-event.getY()<=LOAD_MAX_TOP){
                    float loadDiffY = lastY - event.getY();
                    Log.i("====","LoadDIff - >"+loadDiffY);
                    loadingLinnerLayout.setPadding(0,(int)(loadingPaddingTop-loadDiffY),0,0);
                }else {
                    Log.i("====","条件不满足："+lastY+""+event.getY());
                }
                break;
            case MotionEvent.ACTION_UP:
                if (event.getY()-lastY >= headerViewHeight/2){
                    linearLayout.setPadding(0,0,0,0);
                    progressBar.setVisibility(VISIBLE);
                    textTv.setVisibility(VISIBLE);
                    if (iRefreshListener !=null){
                        iRefreshListener.onRefresh();
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            linearLayout.setPadding(0,paddinTop,0,0);
                            progressBar.setVisibility(GONE);
                            textTv.setVisibility(GONE);
                            if (iRefreshListener!=null){
                                iRefreshListener.onRefreshComPlete();
                            }
                            listView.scrollTo((int)lastListX,-(int)lastListY);
                        }
                    },2000);
                }else  if (isNeedLoad && event.getY()<lastY){
                    Log.i("====","显示上拉页面");
                    if (lastY - event.getY()>LOAD_MAX_TOP/2){
                        loadingLinnerLayout.setPadding(0,loadingPaddingTop-LOAD_MAX_TOP,0,0);
                        loadProgressBar.setVisibility(VISIBLE);
                        loadTextTv.setVisibility(VISIBLE);
                        linearLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(GONE);
                                textTv.setVisibility(GONE);
                                loadingLinnerLayout.setPadding(0,loadingPaddingTop,0,0);
                                if (iRefreshListener !=null){
                                    iRefreshListener.onLoadMore();
                                }
                            }
                        },2000);
                    }else {
                        loadingLinnerLayout.setPadding(0,loadingPaddingTop,0,0);
                    }
                }else {
                    linearLayout.setPadding(0,paddinTop,0,0);
                    listView.scrollTo((int)lastListX,-(int)lastListY);
                }
                break;
        }
        return true;
    }
    private ArrayList<IRefreshListener> arrayList = new ArrayList<>();
    public void AddRefreshListener(IRefreshListener iRefreshListener){
        if (!arrayList.contains(iRefreshListener)){
            arrayList.add(iRefreshListener);
        }
    }
    public interface IRefreshListener{
        void onRefresh();
        void onLoadMore();
        void onRefreshComPlete();
    }
}

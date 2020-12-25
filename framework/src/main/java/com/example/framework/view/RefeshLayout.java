package com.example.framework.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shopmall.bawei.framework.R;

public class RefeshLayout extends FrameLayout {
    private int height;
    private LinearLayout loadingLayoutLister;
    private ProgressBar loadProgressBar;//头布局加载的进度
    private TextView loadTv;//进度下面显示的文本
    public RefeshLayout(@NonNull Context context) {
        super(context);
    }

    public RefeshLayout(@NonNull Context context, @Nullable  AttributeSet attrs) {
        super(context, attrs);
        WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //获取屏幕的高度
        height = systemService.getDefaultDisplay().getHeight();
    }
    public RefeshLayout(@NonNull Context context, @Nullable  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //定义上拉加载
    private void initLodeModeView(){
        //上拉加载的头布局是LinearLayout
        loadingLayoutLister = new LinearLayout(getContext());
        loadingLayoutLister.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
        loadingLayoutLister.setOrientation(LinearLayout.VERTICAL);
        //将你定义的布局添加到跟布局中
        addView(loadingLayoutLister);

        View loadView = LayoutInflater.from(getContext()).inflate(R.layout.load_layout, null);
        loadProgressBar = loadView.findViewById(R.id.loadProgressBar);
        loadTv = loadView.findViewById(R.id.loadTextTv);

    }
}

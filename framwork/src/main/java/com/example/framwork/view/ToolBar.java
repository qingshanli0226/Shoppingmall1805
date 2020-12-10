package com.example.framwork.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.framwork.R;

public class ToolBar extends RelativeLayout {
    private ImageView toolbarLeftimg,toolbarRightimg;
    private TextView toolbarLefttxt,toolbarRighttxt;
    private boolean isShowLeft=true;
    private boolean isShowRight=true;
    private boolean isShowTitle=true;
    private boolean isRightOnlyText=false;
    private boolean isRightOnlyImg=false;

    private int rightImgid;
    private int leftImgid;
    private String rightText;
    private String titleText;
    private int rightTextColor;
    private float rightTextSize;

    private    IToolBarClickLinsterner iToolBarClickLinsterner;

    public ToolBar(Context context) {
        super(context);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    //初始化函数
    private void init(Context context,AttributeSet atttrs,int defStyleAttr){
                initToolBarAttrs(context,atttrs);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.framwork_layout,this);

    }

    private void initToolBarAttrs(Context context, AttributeSet atttrs) {

    }


    public void setiToolBarClickLinsterner(IToolBarClickLinsterner iToolBarClickLinsterner) {
        this.iToolBarClickLinsterner = iToolBarClickLinsterner;
    }

    public interface IToolBarClickLinsterner{
        void onLeftClick();
        void onRightClick();
    }
}

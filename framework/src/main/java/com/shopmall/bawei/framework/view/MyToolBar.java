package com.shopmall.bawei.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyToolBar extends RelativeLayout {
    private ImageView toolbarLeftImg;
    private TextView toolbarTitleTv;
    private ImageView toolbarRightImg;
    private TextView toolbarRightTv;

    private boolean isShowLeft = true;
    private boolean isShowTitle = true;
    private boolean isShowRight = true;
    private boolean isRightOnlyText = false;
    private boolean isRightOnlyImg = false;

    private int rightImgId;
    private int leftImgId;
    private String rightText;
    private String titleText;
    private int rightTextColor;
    private float rightTextSize;

    private IToolBarClickListner iToolBarClickListner;


    public void setRightVisible(boolean flag) {
        if(flag){
            toolbarRightTv.setVisibility(VISIBLE);
        } else {
            toolbarRightTv.setVisibility(GONE);
        }
    }

    public void setLeftVisible(boolean flag) {
        if(flag){
            toolbarLeftImg.setVisibility(VISIBLE);
        } else {
            toolbarLeftImg.setVisibility(GONE);
        }
    }

    public MyToolBar(Context context) {
        super(context);
        init(context,null,0);
    }

    public MyToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public MyToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initToolBarAttrs(context, attrs);


        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(com.shopmall.bawei.common.R.layout.toolbar_layout,this);
        toolbarLeftImg = (ImageView) findViewById(com.shopmall.bawei.common.R.id.toolbarLeftImg);
        toolbarTitleTv = (TextView) findViewById(com.shopmall.bawei.common.R.id.toolbarTitleTv);
        toolbarRightImg = (ImageView) findViewById(com.shopmall.bawei.common.R.id.toolbarRightImg);
        toolbarRightTv = (TextView) findViewById(com.shopmall.bawei.common.R.id.toolbarRightTv);

        if(!isShowLeft) showNotLeft();
        if(!isShowTitle) showNotTitle();
        if(rightImgId != 0) setToolbarRightImg(rightImgId);
        if(rightText != null) setToolbarRightTv(rightText);
        if(rightTextColor != 0) setRightTextColor(rightTextColor);
        if (rightTextSize!=0) setRightTvTextSize((int)rightTextSize);
        if (titleText!=null) setToolBarTitle(titleText);
        if (!isShowRight) showNotRight();
        if (leftImgId!=0) setToolBarLeftImg(leftImgId);
        if (isRightOnlyText) showOnlyRightTv(rightText);
        if (isRightOnlyImg) showOnlyRightImg(rightImgId);

        initListener();

    }

    private void initToolBarAttrs(Context context, AttributeSet attrs) {
        //获取Toolbar在布局中定义的属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, com.shopmall.bawei.common.R.styleable.MyToolBar);
        isShowLeft = typedArray.getBoolean(com.shopmall.bawei.common.R.styleable.MyToolBar_left_show,true);
        isShowTitle = typedArray.getBoolean(com.shopmall.bawei.common.R.styleable.MyToolBar_title_show,true);
        rightText = typedArray.getString(com.shopmall.bawei.common.R.styleable.MyToolBar_right_text);
        rightImgId = typedArray.getResourceId(com.shopmall.bawei.common.R.styleable.MyToolBar_right_src,0);
        leftImgId = typedArray.getResourceId(com.shopmall.bawei.common.R.styleable.MyToolBar_left_src,0);
        rightTextColor = typedArray.getColor(com.shopmall.bawei.common.R.styleable.MyToolBar_right_text_color, Color.BLACK);
        rightTextSize = typedArray.getInt(com.shopmall.bawei.common.R.styleable.MyToolBar_right_text_size, 0);
        titleText = typedArray.getString(com.shopmall.bawei.common.R.styleable.MyToolBar_title_text);
        isShowRight = typedArray.getBoolean(com.shopmall.bawei.common.R.styleable.MyToolBar_right_show, true);
        isRightOnlyText = typedArray.getBoolean(com.shopmall.bawei.common.R.styleable.MyToolBar_right_show_only_text, false);
        isRightOnlyImg = typedArray.getBoolean(com.shopmall.bawei.common.R.styleable.MyToolBar_right_show_only_img, false);

    }

    public void setRightTvText(String str) {
        toolbarRightTv.setText(str);
    }

    //封装ToolBar的控件的点击事件
    public interface IToolBarClickListner{
        void onLeftClick();//ToolBar的左侧控件被点击时调用
        void onRightClick();//ToolBar的右侧控件被点击
    }

    private void initListener() {
        toolbarLeftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iToolBarClickListner != null) {
                    iToolBarClickListner.onLeftClick();
                }
            }
        });
        toolbarRightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolBarClickListner!=null) {
                    iToolBarClickListner.onRightClick();
                }
            }
        });
        toolbarRightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iToolBarClickListner != null){
                    iToolBarClickListner.onRightClick();
                }
            }
        });
    }

    public void setToolBarClickListner(IToolBarClickListner iToolBarClickListner) {
        this.iToolBarClickListner = iToolBarClickListner;
    }

    private void showOnlyRightImg(int rightImgId) {
        toolbarRightImg.setImageResource(rightImgId);
        toolbarRightImg.setVisibility(VISIBLE);
        toolbarRightTv.setVisibility(GONE);
    }

    private void showOnlyRightTv(String rightText) {
        toolbarRightImg.setVisibility(GONE);
        toolbarRightTv.setVisibility(VISIBLE);
        toolbarRightTv.setText(rightText);
    }

    private void setToolBarLeftImg(int leftImgId) {
        toolbarLeftImg.setImageResource(leftImgId);
    }

    private void showNotRight() {
        toolbarRightTv.setVisibility(GONE);
        toolbarRightImg.setVisibility(GONE);
    }

    private void setToolBarTitle(String titleText) {
        toolbarTitleTv.setText(titleText);
    }

    private void setRightTvTextSize(int rightTextSize) {
        toolbarRightTv.setTextSize(rightTextSize);
    }

    private void setRightTextColor(int rightTextColor) {
        toolbarRightTv.setTextColor(rightTextColor);
    }

    private void setToolbarRightTv(String rightText) {
        toolbarRightTv.setText(rightText);
    }

    private void setToolbarRightImg(int rightImgId) {
        toolbarRightImg.setImageResource(rightImgId);
    }

    private void showNotTitle() {
        toolbarTitleTv.setVisibility(GONE);
    }

    private void showNotLeft() {
        toolbarLeftImg.setVisibility(GONE);
    }
}

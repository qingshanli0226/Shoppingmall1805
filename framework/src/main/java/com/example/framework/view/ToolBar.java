package com.example.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shopmall.bawei.framework.R;

public class ToolBar extends RelativeLayout {
    private ImageView toolBarLeftImg,toolbarRightImg;
    private TextView toolbarTitleTv, toolbarRightTv;
    private boolean isShowLeft = true;
    private boolean isShowTitle = true;
    private boolean isShowRight = true;
    private boolean isShowBottom = true;
    private boolean isRightOnlyText = false;
    private boolean isRightOnlyImg = false;
    private int rightImgId;
    private int leftImgId;
    private String rightText;
    private String titleText;
    private int rightTextColor;
    private float rightTextSize;
    private View toolbarBottomLine;
    private IToolBarClickListner iToolBarClickListner;
    private Context context;
    public ToolBar(Context context) {
        super(context);
        this.context=context;
        NullPointerException nullPointerException = new NullPointerException(hashCode()+"");

        nullPointerException.printStackTrace();


    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init(context,attrs,0);
        NullPointerException nullPointerException = new NullPointerException(hashCode()+"");
        nullPointerException.printStackTrace();
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        init(context,attrs,0);
    }
    private void init(Context context,AttributeSet attrs,int defstyleAttr){
        initToolBarAttrs(context,attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.toolbar_layout, this);//第二个参数一定要是this
        toolbarBottomLine =findViewById(R.id.toolbar_bottom_line);
        toolBarLeftImg = findViewById(R.id.toolbarLeftImg);
        toolbarRightImg = findViewById(R.id.toolbarRightImg);
        toolbarTitleTv = findViewById(R.id.toolbarTitleTv);
        toolbarRightTv = findViewById(R.id.toolbarRightTv);
        Glide.with(context).load(R.drawable.back).into(toolBarLeftImg);
        if(!isShowLeft) showNotLeft();
        if(!isShowTitle) showNotTitle();
        if(!isShowRight) showNotRight();
        if(!isShowBottom) showNotBottom();
        if(rightImgId!=0)setToolBarRightImg(rightImgId);
        if(rightText!=null)setToolBarRightTv(rightText);
        if(rightTextColor!=0) setRightTextColor(rightTextColor);
        if(rightTextSize!=0) setRightTextSize(rightTextSize);
        if(titleText!=null) setToolBarTitle(titleText);
        if(leftImgId!=0)setToolBarLeftImg(leftImgId);
        if(isRightOnlyText)showOnlyRightTv(rightText);
        if(isRightOnlyImg)showOnlyRightImg(rightImgId);
        initListener();
    }

    private void showNotBottom() {
        toolbarBottomLine.setVisibility(GONE);
    }

    private void initToolBarAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
        isShowLeft=typedArray.getBoolean(R.styleable.ToolBar_left_show,true);
        isShowTitle=typedArray.getBoolean(R.styleable.ToolBar_title_show,true);
        isShowBottom=typedArray.getBoolean(R.styleable.ToolBar_bottom_show,true);
        rightText = typedArray.getString(R.styleable.ToolBar_right_text);
        rightImgId = typedArray.getResourceId(R.styleable.ToolBar_right_src,0);
        leftImgId = typedArray.getResourceId(R.styleable.ToolBar_left_src,0);
        rightTextColor = typedArray.getColor(R.styleable.ToolBar_right_text_color, Color.BLACK);
        rightTextSize = typedArray.getInt(R.styleable.ToolBar_right_text_size, 0);
        titleText = typedArray.getString(R.styleable.ToolBar_title_text);
        isShowRight = typedArray.getBoolean(R.styleable.ToolBar_right_show, true);
        isRightOnlyText = typedArray.getBoolean(R.styleable.ToolBar_right_show_only_text, false);
        isRightOnlyImg = typedArray.getBoolean(R.styleable.ToolBar_right_show_only_img, false);


    }

    private void initListener() {
        toolBarLeftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iToolBarClickListner!=null){
                    iToolBarClickListner.onLeftClick();
                }
            }
        });
        toolbarRightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iToolBarClickListner!=null){
                    iToolBarClickListner.onRightClick();
                }
            }
        });
        toolbarRightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LQS===================-------------------", context.getClass().getSimpleName() + " " + ToolBar.this.hashCode());

                if(iToolBarClickListner!=null){
                    iToolBarClickListner.onRightClick();
                }
            }
        });
    }

    public void showOnlyRightImg(int rightImgId) {
        Glide.with(context).load(rightImgId).into(toolbarRightImg);
        toolbarRightImg.setVisibility(VISIBLE);
        toolbarRightTv.setVisibility(GONE);
    }

    public void showOnlyRightTv(String rightText) {
        toolbarRightImg.setVisibility(GONE);
        toolbarRightTv.setVisibility(VISIBLE);
        toolbarRightTv.setText(rightText);
    }

    public void setToolBarLeftImg(int leftImgId) {
        Glide.with(context).load(leftImgId).into(toolBarLeftImg);
    }

    public void setToolBarTitle(String titleText) {
        toolbarTitleTv.setText(titleText);
    }

    public void setRightTextSize(float rightTextSize) {
        toolbarRightTv.setTextSize(rightTextSize);
    }

    public void setRightTextColor(int rightTextColor) {
        toolbarRightTv.setTextColor(rightTextColor);
    }

    public void setToolBarRightTv(String rightText) {
        toolbarRightTv.setText(rightText);
    }

    public void setToolBarRightImg(int rightImgId) {
        toolbarRightImg.setImageResource(rightImgId);
    }

    public void showNotRight() {
        toolbarRightTv.setVisibility(GONE);
        toolbarRightImg.setVisibility(GONE);
    }

    public void showNotTitle() {
        toolbarTitleTv.setVisibility(GONE);
    }

    public void showNotLeft() {
        toolBarLeftImg.setVisibility(GONE);
    }
    public void setToolBarClickListner(IToolBarClickListner toolBarClickListner){
        iToolBarClickListner=toolBarClickListner;
    }
    public interface IToolBarClickListner{
        void onLeftClick();
        void onRightClick();
    }
}

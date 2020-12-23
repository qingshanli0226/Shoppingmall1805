package com.example.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopmall.bawei.framework.R;

// TODO 自定义一个，将系统的控件管理起来，对外提供一个借口来实现功能
public class ToolBar extends RelativeLayout {
    private ImageView toolBarLeftImg,toolbarRightImg;
    private TextView toolbarTitleTv, toolbarRightTv;
    private boolean ivShowLeft = true;
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

    private IToolBarClickListenter iToolBarClickListenter;
    public ToolBar(Context context) {
        super(context);
        init(context,null,0);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }
    //初始化函数
    private void init(Context context,AttributeSet attributeSet,int defStyleAttr){
        //获取ToolBar在布局中定义的属性值
        initToolBar(context,attributeSet);
        LayoutInflater from = LayoutInflater.from(context);
        from.inflate(R.layout.toolbar_layout, this);//这里一定是this
        toolBarLeftImg = findViewById(R.id.toolbarLeftImg);
        toolbarRightImg = findViewById(R.id.toolbarRightImg);
        toolbarTitleTv = findViewById(R.id.toolbarTitleTv);
        toolbarRightTv = findViewById(R.id.toolbarRightTv);

        //使用属性值来控制toolbar中的属性是否要显示
        if (!ivShowLeft) showNotLeft();
        if (!isShowTitle) showNotTitle();
        if (rightImgId!=0) setToolBarRightImage(rightImgId);
        if (rightText!=null) setToolBarRightTv(rightText);
        if (rightTextColor!=0) setRightTextColor(rightTextColor);
        if (rightTextSize!=0) setRightTextSize(rightTextSize);
        if (titleText!=null) setTitleText(titleText);
        if (!isShowRight) showNotRight();
        if (leftImgId!=0) setToolBarLefrImg(leftImgId);
        if (isRightOnlyText) showRightOnlyText(rightText);
        if (isRightOnlyImg) showRightOnlyImg(rightImgId);
        //封装点击事件
        initListenter();
    }
    private void initToolBar(Context context, AttributeSet attributeSet) {
        //获取ToolBar在布局中定义的属性值
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ToolBar);
        ivShowLeft = typedArray.getBoolean(R.styleable.ToolBar_left_show, true);
        isShowTitle = typedArray.getBoolean(R.styleable.ToolBar_title_show,true);
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

    //控件的点击事件
    private void initListenter() {
        toolBarLeftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolBarClickListenter!=null){
                    iToolBarClickListenter.onLeftClick();
                }
            }
        });
        toolbarRightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolBarClickListenter!=null){
                    iToolBarClickListenter.onRightClick();
                }
            }
        });
        toolbarRightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolBarClickListenter!=null){
                    iToolBarClickListenter.onRightClick();
                }
            }
        });
    }

    //只显示右边的图片
    public void showRightOnlyImg(int rightImgId) {
        toolbarRightTv.setVisibility(GONE);
        toolbarRightImg.setVisibility(VISIBLE);
        toolbarRightImg.setImageResource(rightImgId);
    }

    //只显示右边的文本
    public void showRightOnlyText(String rightText) {
        toolbarRightImg.setVisibility(GONE);
        toolbarRightTv.setVisibility(VISIBLE);
        toolbarRightTv.setText(rightText);
    }

    //修改左侧显示的图片
    public void setToolBarLefrImg(int leftImgId) {
        toolBarLeftImg.setImageResource(leftImgId);
    }

    //不显示右边的内容
    public void showNotRight() {
        toolbarRightTv.setVisibility(GONE);
        toolbarRightImg.setVisibility(GONE);
    }

    //更改ToolBar显示的主题文字
    public void setTitleText(String titleText) {
        toolbarTitleTv.setText(titleText);
    }

    //修改右侧显示的文本字体大小
    public void setRightTextSize(float rightTextSize) {
        toolbarRightTv.setTextSize(rightTextSize);
    }

    //修改右侧显示的文本颜色
    public void setRightTextColor(int rightTextColor) {
        toolbarRightTv.setTextColor(rightTextColor);
    }

    //修改右侧显示的文本
    public void setToolBarRightTv(String rightText) {
        toolbarRightTv.setText(rightText);
    }

    //修改右侧显示的图片
    public void setToolBarRightImage(int rightImgId) {
        toolbarRightImg.setImageResource(rightImgId);
    }

    //不显示title
    public void showNotTitle() {
        toolbarTitleTv.setVisibility(GONE);
    }

    //左侧的图片不显示出来
    public void showNotLeft() {
        toolBarLeftImg.setVisibility(GONE);
    }
    //注册listenter
    public void setiToolBarClickListenter(IToolBarClickListenter iToolBarClickListenters){
        iToolBarClickListenter = iToolBarClickListenters;
    }
    //封装ToolBar的点击事件
    public interface IToolBarClickListenter{
        void onLeftClick();//ToolBar左侧点击时候被调用
        void onRightClick();//ToolBar右侧点击时被调用
    }

}

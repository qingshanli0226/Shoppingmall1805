package com.shopmall.bawei.framework.example.framework;

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

public class ToolBar extends RelativeLayout {
    private ImageView toolBarLeftImg,toolbarRightImg;
    private TextView toolbarTitleTv, toolbarRightTv;
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


    public ToolBar(Context context) {
        super(context);
        init(context,null,0);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int i) {

        initToolBarAttrs(context,attrs);
        View inflate = LayoutInflater.from(context).inflate(R.layout.toolbar_layout, this);

        toolBarLeftImg = inflate.findViewById(R.id.toolbarLeftImg);
        toolbarRightImg = inflate.findViewById(R.id.toolbarRightImg);
        toolbarTitleTv = inflate.findViewById(R.id.toolbarTitleTv);
        toolbarRightTv = inflate.findViewById(R.id.toolbarRightTv);

        if (!isShowLeft){
            //左侧图片不显示
            toolBarLeftImg.setVisibility(GONE);
        }

        if (isShowTitle){
            //不显示title
            toolbarTitleTv.setVisibility(GONE);
        }

        if (rightImgId != 0){
            //修改右侧显示图片
            toolbarRightImg.setImageResource(rightImgId);
        }
        if (rightText!=null){
            //修改右侧文本
            setToolbarRightTv(rightText);

        }
        if (rightTextColor!=0){
            //修改右侧字体颜色
            toolbarRightTv.setTextColor(rightTextColor);
        }
        if (rightTextSize!=0){
            //修改右侧字体大小
            toolbarRightTv.setTextSize(rightTextSize);
        }
        if (titleText!=null){
            //可以修改toolbar的显示的主题
            toolbarTitleTv.setText(titleText);
        }
        if (!isShowRight){
            //不显示右侧的内容
            toolbarRightTv.setVisibility(GONE);
            toolbarRightImg.setVisibility(GONE);
        }
        if (leftImgId!=0){
            //修改左侧显示图片
            toolBarLeftImg.setImageResource(leftImgId);
        }
        if (isRightOnlyText){
            //只显示右侧的文本
            toolbarRightImg.setVisibility(GONE);
            toolbarRightTv.setVisibility(GONE);
            toolbarRightTv.setText(rightText);
        }
        if (isRightOnlyImg){
            //只显示右侧的图片
            toolbarRightImg.setImageResource(rightImgId);
            toolbarRightImg.setVisibility(VISIBLE);
            toolbarRightTv.setVisibility(GONE);
        }
        initListener();

    }

    public void setToolbarRightTv(String rightText) {
        toolbarRightTv.setText(rightText);
    }

    private void initListener() {
        toolBarLeftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolBarClickListner != null){
                    iToolBarClickListner.onLeftClick();
                }
            }
        });

        toolbarRightImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolBarClickListner != null) {
                    iToolBarClickListner.onRightClick();
                }
            }
        });

        toolbarRightTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolBarClickListner != null){
                    iToolBarClickListner.onRightClick();
                }
            }
        });


    }

    private void initToolBarAttrs(Context context, AttributeSet attrs) {
        //获取Toolbar在布局中定义的属性值
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);

        isShowLeft = typedArray.getBoolean(R.styleable.ToolBar_left_show, true);
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


    public void setToolBarClickListner(IToolBarClickListner iToolBarClickListner) {
        this.iToolBarClickListner = iToolBarClickListner;
    }

    //封装ToolBar的控件的点击事件
    public interface IToolBarClickListner{
        void onLeftClick();//ToolBar的左侧控件被点击时调用
        void onRightClick();//ToolBar的右侧控件被点击
    }
}

package com.shopmall.bawei.framework.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shopmall.bawei.common.R;

public class BottomBar extends FrameLayout {

    private IBottomBarSelectListener iBottomBarSelectListener;
    public static final int HOME_INDEX = 0;
    public static final int TYPE_INDEX = 1;
    public static final int SHOPCAR_INDEX = 2;
    public static final int MINE_INDEX = 3;
    private RadioGroup radioGroup;

    private int selectColor;


    public BottomBar(@NonNull Context context) {
        super(context);
        init(context, null, 0);
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    //数组的长度为4
    public void setTabTitle(String[] tabTitles) {
        RadioButton homeButton = findViewById(R.id.home);
        homeButton.setText(tabTitles[0]);
        RadioButton typeButton = findViewById(R.id.type);
        typeButton.setText(tabTitles[1]);
        RadioButton shopcarButton = findViewById(R.id.shopcar);
        shopcarButton.setText(tabTitles[2]);
        RadioButton mineButton = findViewById(R.id.mine);
        mineButton.setText(tabTitles[3]);
    }

    public void setShopcarCount(String count) {
        if (TextUtils.isEmpty(count) && !count.equals("0")) {
            return;
        }
        RadioButton shopcarButton = findViewById(R.id.shopcar);
        shopcarButton.setText("购物车：" + count);
    }

    //初始化函数
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        initBottomBarAttrs(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.bottombar_layout, this);

        radioGroup = findViewById(R.id.bottomGroup);
        //设置点击事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.home) {
                   selectHome();
                } else if (checkedId == R.id.type) {
                    selectType();
                } else if (checkedId == R.id.shopcar) {
                   selectShopcar();
                } else {
                    selectMine();
                }
            }
        });
        selectHome();//默认显示home
    }

    public void selectHome() {
        findViewById(R.id.home).setBackgroundColor(selectColor);
        findViewById(R.id.type).setBackgroundColor(Color.WHITE);
        findViewById(R.id.shopcar).setBackgroundColor(Color.WHITE);
        findViewById(R.id.mine).setBackgroundColor(Color.WHITE);
        radioGroup.check(R.id.home);
        if (iBottomBarSelectListener!=null) {
            iBottomBarSelectListener.onBottomBarSelected(HOME_INDEX);
        }
    }

    public void selectType() {
        findViewById(R.id.home).setBackgroundColor(Color.WHITE);
        findViewById(R.id.type).setBackgroundColor(selectColor);
        findViewById(R.id.shopcar).setBackgroundColor(Color.WHITE);
        findViewById(R.id.mine).setBackgroundColor(Color.WHITE);
        radioGroup.check(R.id.type);
        if (iBottomBarSelectListener!=null) {
            iBottomBarSelectListener.onBottomBarSelected(TYPE_INDEX);
        }
    }

    public void selectShopcar() {
        findViewById(R.id.home).setBackgroundColor(Color.WHITE);
        findViewById(R.id.type).setBackgroundColor(Color.WHITE);
        findViewById(R.id.shopcar).setBackgroundColor(selectColor);
        findViewById(R.id.mine).setBackgroundColor(Color.WHITE);
        radioGroup.check(R.id.shopcar);
        if (iBottomBarSelectListener!=null) {
            iBottomBarSelectListener.onBottomBarSelected(SHOPCAR_INDEX);
        }
    }

    public void selectMine() {
        findViewById(R.id.home).setBackgroundColor(Color.WHITE);
        findViewById(R.id.type).setBackgroundColor(Color.WHITE);
        findViewById(R.id.shopcar).setBackgroundColor(Color.WHITE);
        findViewById(R.id.mine).setBackgroundColor(selectColor);
        radioGroup.check(R.id.mine);
        if (iBottomBarSelectListener!=null) {
            iBottomBarSelectListener.onBottomBarSelected(MINE_INDEX);
        }
    }

    private void initBottomBarAttrs(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BottomBar);
        selectColor = typedArray.getColor(R.styleable.BottomBar_select_color, Color.YELLOW);
    }


    public void setBottomBarSelectListener(IBottomBarSelectListener listener) {
        this.iBottomBarSelectListener = listener;
    }

    //定义一个接口，；这个接口，Activity或者Fragment实现这个接口，通过这个接口达到自定义view和Activity或者Fragment之间的通信
    public interface IBottomBarSelectListener {
        void onBottomBarSelected(int selectIndex);
    }
}

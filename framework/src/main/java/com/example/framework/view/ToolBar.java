package com.example.framework.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class ToolBar extends RelativeLayout {
    public ToolBar(Context context) {
        super(context);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void init(Context context,AttributeSet attrs,int defstyleAttr){
        initToolBarAttrs(context,attrs);
    }

    private void initToolBarAttrs(Context context, AttributeSet attrs) {

    }
}

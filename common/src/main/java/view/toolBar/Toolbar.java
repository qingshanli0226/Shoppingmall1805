package view.toolBar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shopmall.bawei.common.R;

public
class Toolbar extends RelativeLayout {
    private ImageView ToobarLeftImag,toobarRightIMage;
    private TextView toobarTliteTv,toobarRightTv;

    private boolean isshowleft = true;
    private boolean IsshowTlite = true;
    private boolean isShowRight = true;

    private boolean isRightOnlyText = false;
    private boolean isRightOnlyImage =false;

    private int rightImgId;
    private int leftImgId;

    private String rightText;
    private String tliteText;

    private int rightTextColor;
    private float rightTextSize;
    public Toolbar(Context context) {
        super(context);
        init(context,null,0);
    }


    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initToolBarAttrs(context,attrs);

        LayoutInflater from = LayoutInflater.from(context);
        from.inflate(R.layout.toolbar_layout,this);


        ToobarLeftImag = findViewById(R.id.toolbarLeftImg);
        toobarRightIMage = findViewById(R.id.toolbarRightImg);
        toobarTliteTv = findViewById(R.id.toolbarTitleTv);
        toobarRightTv = findViewById(R.id.toolbarRightTv);
    }

    private void initToolBarAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
        isshowleft = typedArray.getBoolean(R.styleable.ToolBar_left_show,true);
        IsshowTlite = typedArray.getBoolean(R.styleable.ToolBar_title_show,true);
        rightText = typedArray.getString(R.styleable.ToolBar_right_text);
        rightImgId = typedArray.getResourceId(R.styleable.ToolBar_right_src,0);
        leftImgId = typedArray.getResourceId(R.styleable.ToolBar_left_src,0);
        rightTextColor =typedArray.getColor(R.styleable.ToolBar_right_text_color, Color.BLACK);
        rightTextSize = typedArray.getInt(R.styleable.ToolBar_right_text_size,0);

        tliteText = typedArray.getString(R.styleable.ToolBar_right_text);
        isShowRight = typedArray.getBoolean(R.styleable.ToolBar_right_show,true);
        isRightOnlyText = typedArray.getBoolean(R.styleable.ToolBar_right_show_only_text,false);
        isRightOnlyImage = typedArray.getBoolean(R.styleable.ToolBar_right_show_only_img,false);
    }


}

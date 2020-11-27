package mvp;

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
    private ImageView toolBarLeftImg, toolbarRightImg;
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
        init(context, null, 0);
    }

    public ToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        initToolBarAttrs(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.toolbar_layout, this);//第二个参数一定要是this
        toolBarLeftImg = findViewById(R.id.toolbarLeftImg);
        toolbarRightImg = findViewById(R.id.toolbarRightImg);
        toolbarTitleTv = findViewById(R.id.toolbarTitleTv);
        toolbarRightTv = findViewById(R.id.toolbarRightTv);


        if (!isShowLeft) showNotLeft();
        if (!isShowTitle) showNotTitle();
        if (rightImgId != 0) setToolBarRightImg(rightImgId);
        if (rightText != null) setToolbarRightTv(rightText);
        if (rightTextColor != 0) setRightTextColor(rightTextColor);
        if (rightTextSize != 0) setRightTvTextSize((int) rightTextSize);
        if (titleText != null) setToolBarTitle(titleText);
        if (!isShowRight) showNotRight();
        if (leftImgId != 0) setToolBarLeftImg(leftImgId);
        if (isRightOnlyText) showOnlyRightTv(rightText);
        if (isRightOnlyImg) showOnlyRightImg(rightImgId);

        initListener();
    }

    private void initListener() {
        toolBarLeftImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iToolBarClickListner != null) {
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
                if (iToolBarClickListner != null) {
                    iToolBarClickListner.onRightClick();
                }
            }
        });

    }

    private void initToolBarAttrs(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ToolBar);
        isShowLeft = typedArray.getBoolean(R.styleable.ToolBar_left_show, true);
        isShowTitle = typedArray.getBoolean(R.styleable.ToolBar_title_show, true);
        rightText = typedArray.getString(R.styleable.ToolBar_right_text);
        rightImgId = typedArray.getResourceId(R.styleable.ToolBar_right_src, 0);
        leftImgId = typedArray.getResourceId(R.styleable.ToolBar_left_src, 0);
        rightTextColor = typedArray.getColor(R.styleable.ToolBar_right_text_color, Color.BLACK);
        rightTextSize = typedArray.getInt(R.styleable.ToolBar_right_text_size, 0);
        titleText = typedArray.getString(R.styleable.ToolBar_title_text);
        isShowRight = typedArray.getBoolean(R.styleable.ToolBar_right_show, true);
        isRightOnlyText = typedArray.getBoolean(R.styleable.ToolBar_right_show_only_text, false);
        isRightOnlyImg = typedArray.getBoolean(R.styleable.ToolBar_right_show_only_img, false);
    }

    public void setToolBarTitle(String title) {
        toolbarTitleTv.setText(title);
    }

    public void setToolBarLeftImg(int imgId) {
        toolBarLeftImg.setImageResource(imgId);
    }

    public void setToolBarRightImg(int imgId) {
        toolbarRightImg.setImageResource(imgId);
    }

    public void setToolbarRightTv(String rightText) {
        toolbarRightTv.setText(rightText);
    }

    public void showNotRight() {
        toolbarRightTv.setVisibility(GONE);
        toolbarRightImg.setVisibility(GONE);
    }

    public void showOnlyRightImg(int imgId) {
        toolbarRightImg.setImageResource(imgId);
        toolbarRightImg.setVisibility(VISIBLE);
        toolbarRightTv.setVisibility(GONE);
    }

    public void showOnlyRightTv(String rightText) {
        toolbarRightImg.setVisibility(GONE);
        toolbarRightTv.setVisibility(VISIBLE);
        toolbarRightTv.setText(rightText);
    }

    public void showNotLeft() {
        toolBarLeftImg.setVisibility(GONE);
    }

    public void showNotTitle() {
        toolbarTitleTv.setVisibility(GONE);
    }

    public void setRightTextColor(int color) {
        toolbarRightTv.setTextColor(color);
    }


    public void setRightTvTextSize(int size) {
        toolbarRightTv.setTextSize(size);
    }

    public void setToolBarClickListner(IToolBarClickListner toolBarClickListner) {
        iToolBarClickListner = toolBarClickListner;
    }


    public interface IToolBarClickListner {
        void onLeftClick();

        void onRightClick();
    }
}

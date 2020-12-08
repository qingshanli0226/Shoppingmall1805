package mvp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
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
        init(context,attrs,0);
    }

    public ToolBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }
    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
      LayoutInflater inflater =  LayoutInflater.from(context);
      inflater.inflate(R.layout.layout,this);
        toolBarLeftImg = findViewById(R.id.toolbarLeftImg);
        toolbarRightImg = findViewById(R.id.toolbarRightImg);
        toolbarTitleTv = findViewById(R.id.toolbarTitleTv);
        toolbarRightTv = findViewById(R.id.toolbarRightTv);

        if (!isShowLeft) showNotLeft();
        if (!isShowTitle) showNotTitle();
        if (rightImgId != 0) setToolBarRightImg(rightImgId);
        if (rightText!=null) setToolbarRightTv(rightText);
        if (rightTextColor!=0) setRightTextColor(rightTextColor);
        if (rightTextSize!=0) setRightTvTextSize((int)rightTextSize);
        if (titleText!=null) setToolBarTitle(titleText);
        if (!isShowRight) showNotRight();
        if (leftImgId!=0) setToolBarLeftImg(leftImgId);
        if (isRightOnlyText) showOnlyRightTv(rightText);
        if (isRightOnlyImg) showOnlyRightImg(rightImgId);
    }

    //可以修改toolbar的显示的主题
    public void setToolBarTitle(String title) {
        toolbarTitleTv.setText(title);
    }

    //修改左侧显示图片
    public void setToolBarLeftImg(int imgId) {
        toolBarLeftImg.setImageResource(imgId);
    }

    //修改右侧显示图片
    public void setToolBarRightImg(int imgId) {
        toolbarRightImg.setImageResource(imgId);
    }

    //修改右侧文本
    public void setToolbarRightTv(String rightText) {
        toolbarRightTv.setText(rightText);
    }

    //不显示右侧的内容
    public void showNotRight() {
        toolbarRightTv.setVisibility(GONE);
        toolbarRightImg.setVisibility(GONE);
    }

    //只显示右侧的图片
    public void showOnlyRightImg(int imgId) {
        toolbarRightImg.setImageResource(imgId);
        toolbarRightImg.setVisibility(VISIBLE);
        toolbarRightTv.setVisibility(GONE);
    }

    //只显示右侧的文本
    public void showOnlyRightTv(String rightText) {
        toolbarRightImg.setVisibility(GONE);
        toolbarRightTv.setVisibility(VISIBLE);
        toolbarRightTv.setText(rightText);
    }

    //左侧图片也不显示
    public void showNotLeft() {
        toolBarLeftImg.setVisibility(GONE);
    }

    //不显示title
    public void showNotTitle() {
        toolbarTitleTv.setVisibility(GONE);
    }

    //修改右侧字体颜色
    public void setRightTextColor(int color) {
        toolbarRightTv.setTextColor(color);
    }

    //修改右侧字体大小
    public void setRightTvTextSize(int size) {
        toolbarRightTv.setTextSize(size);
    }

    public void setToolBarClickListner(IToolBarClickListner toolBarClickListner) {
        iToolBarClickListner = toolBarClickListner;
    }
    //封装ToolBar的控件的点击事件
    public interface IToolBarClickListner{

    }
}

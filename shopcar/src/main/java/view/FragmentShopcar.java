package view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shopmall.bawei.shopcar.R;

import framework.BaseFragment;
import view.loadinPage.ErrorBean;

public
class FragmentShopcar extends BaseFragment implements ToolBar.IToolBarClickListner, View.OnClickListener {
    private RecyclerView shopCarRv;
    private RelativeLayout normalLayout;
    private CheckBox allSelect;
    private TextView sumNote;
    private TextView sumValue;
    private Button payBtn;
    private RelativeLayout editLayout;
    private CheckBox allEditSelect;
    private Button saveBtn;
    private Button deleteBtn;
    private boolean clickChange = true;
    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void InitData() {

        tooBar = (ToolBar) findViewById(R.id.tooBar);
        shopCarRv = (RecyclerView) findViewById(R.id.shopCarRv);//适配器
        normalLayout = (RelativeLayout) findViewById(R.id.normalLayout);//全选框 RelativeLayout
        allSelect = (CheckBox) findViewById(R.id.allSelect);//全选
        sumNote = (TextView) findViewById(R.id.sumNote);//总计
        sumValue = (TextView) findViewById(R.id.sumValue);//总计金额
        payBtn = (Button) findViewById(R.id.payBtn);//支付按钮
        editLayout = (RelativeLayout) findViewById(R.id.editLayout);//编辑 RelativeLayout
        allEditSelect = (CheckBox) findViewById(R.id.allEditSelect);//全选
        saveBtn = (Button) findViewById(R.id.saveBtn);//保存
        deleteBtn = (Button) findViewById(R.id.deleteBtn);//删除

    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_shopcar;
    }


    @Override
    public void onClick(View v) {

    }


    //页面完善
    @Override
    public void showLoaDing() {
        showLoaDing();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
        showEmpty();
    }


    //Toobar 的左边点击方法右边点击方法
    @Override
    public void onLeftClick() {
    }

    @Override
    public void onRightClick() {

    }
}

package com.shopmall.bawei.order.mview;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.order.IPassBack;
import com.shopmall.bawei.order.R;
import com.shopmall.bawei.order.presenter.OrderPresenter;

import framework.BaseActivity;
import framework.Contact;
import mode.BaseBean;
import view.SkipFinalUlis;
import view.ToolBar;
import view.loadinPage.ErrorBean;
import view.loadinPage.UnpaidBean;

@Route(path = "/order/BindingActivity")
public class BindingActivity extends BaseActivity<OrderPresenter> implements View.OnClickListener, ToolBar.IToolBarClickListner, Contact.ICenterOrderIview {
    private EditText editPhone;
    private Button buttonPhone;
    private EditText editSite;
    private Button buttonSite;
    private Button resultButton;
    //接口返回的数据
    private IPassBack.IDataBack iDataBack = new IPassBack.IDataBack() {
        @Override
        public void onJsonData(BaseBean baseBean) {
            Log.i("====","返回"+baseBean);
        }
    };
    @Override
    protected void createPresenter() {
        jsonPresenter = new OrderPresenter(this);
    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void initData() {
        editPhone = (EditText) findViewById(R.id.editPhone);
        buttonPhone = (Button) findViewById(R.id.buttonPhone);
        editSite = (EditText) findViewById(R.id.editSite);
        buttonSite = (Button) findViewById(R.id.buttonSite);
        tooBar = findViewById(R.id.tooBar);
        resultButton = (Button) findViewById(R.id.resultButton);

        buttonPhone.setOnClickListener(this);
        buttonSite.setOnClickListener(this);
        resultButton.setOnClickListener(this);
        IPassBack.getInstance().setiDataBacks(iDataBack);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonPhone) {
            Toast.makeText(this, "绑定手机号", Toast.LENGTH_SHORT).show();
            jsonPresenter.goBindingPhone(editPhone.getText().toString().trim());
        }else if (v.getId()==R.id.buttonSite){
            Toast.makeText(this, "绑定地址", Toast.LENGTH_SHORT).show();
            jsonPresenter.goBindingPoint(editSite.getText().toString().trim());
        }else if (v.getId()==R.id.resultButton){
            Toast.makeText(this, "跳转回归支付界面", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(SkipFinalUlis.PAY_ACTIVITY).navigation();
        }
    }
    @Override
    protected int getlayoutId() {
        return R.layout.activity_binding;
    }

    @Override
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onUnpaidSuccess(UnpaidBean unpaidBean) {

    }

    @Override
    public void onError(String Error) {

    }
}
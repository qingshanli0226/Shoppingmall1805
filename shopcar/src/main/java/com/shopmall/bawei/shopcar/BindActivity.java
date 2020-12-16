package com.shopmall.bawei.shopcar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.ARouterHelper;
import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.UserManager;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.shopcar.contract.BindContract;
import com.shopmall.bawei.shopcar.contract.BindImpl;

public class BindActivity extends BaseActivity<BindImpl, BindContract.IBindView> implements BindContract.IBindView, View.OnClickListener {
    private EditText etLoginPhone;
    private Button bindPhone;
    private EditText etLoginAdd;
    private Button bindAddress;


    @Override
    protected int layoutId() {
        return R.layout.activity_bind;
    }

    @Override
    protected void initView() {
        etLoginPhone = (EditText) findViewById(R.id.et_login_phone);
        bindPhone = (Button) findViewById(R.id.bind_phone);
        etLoginAdd = (EditText) findViewById(R.id.et_login_add);
        bindAddress = (Button) findViewById(R.id.bind_address);
    }

    @Override
    protected void initListener() {
        bindPhone.setOnClickListener(this);
        bindAddress.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new BindImpl();
    }

    @Override
    public void onBindPhone(String phone) {
        Toast.makeText(this, phone, Toast.LENGTH_SHORT).show();
        LoginBean loginBean = new LoginBean();
        loginBean.setName(UserManager.getInstance().getName());
        loginBean.setToken(UserManager.getInstance().getToken());
        if(UserManager.getInstance().isBindAddress()){
            loginBean.setAddress(UserManager.getInstance().getAddress());
        }
        loginBean.setPhone(phone);
        UserManager.getInstance().saveLoginBean(loginBean);
    }

    @Override
    public void onBindAddress(String address) {
        Toast.makeText(this, address, Toast.LENGTH_SHORT).show();
        LoginBean loginBean = new LoginBean();
        loginBean.setName(UserManager.getInstance().getName());
        loginBean.setToken(UserManager.getInstance().getToken());
        if(UserManager.getInstance().isBindPhone()){
            loginBean.setPhone(UserManager.getInstance().getPhone());
        }
        loginBean.setAddress(address);
        UserManager.getInstance().saveLoginBean(loginBean);
        ARouter.getInstance().build(ARouterHelper.APP_MAIN).navigation();
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoaDing() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bind_phone){
            httpPresenter.bindPhone(etLoginPhone.getText().toString());
        } else{
            httpPresenter.bindAddress(etLoginAdd.getText().toString());
        }
    }
}

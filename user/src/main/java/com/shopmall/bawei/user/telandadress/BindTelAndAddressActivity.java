package com.shopmall.bawei.user.telandadress;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.base.BaseActivity;
import com.example.framework.manager.UserManager;
import com.example.net.bean.UpDateAddressBean;
import com.example.net.bean.UpdatePhoneBean;
import com.shopmall.bawei.user.R;
import com.shoppmall.common.adapter.error.ErrorBean;

@Route(path = "/user/BindTelAndAddressActivity")
public class BindTelAndAddressActivity extends BaseActivity<BindTelAndAddressPresenterImpl, BindTelAndAddressContract.IBindTelAndAddressView> implements BindTelAndAddressContract.IBindTelAndAddressView {
    private EditText etBindTelandadressPhone;
    private Button btnBindTel;
    private EditText etBindTelandadressAdress;
    private Button btnBindAdress;
    private ProgressBar pbBindTelandadress;
    private String key;

    @Override
    protected void initListener() {
        super.initListener();
        btnBindTel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = etBindTelandadressPhone.getText().toString();
                presenter.bindTel(tel);
            }
        });
        btnBindAdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = etBindTelandadressAdress.getText().toString();
                presenter.bindAddress(address);
            }
        });
    }

    @Override
    protected void initPresenter() {
        presenter=new BindTelAndAddressPresenterImpl();

    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        if(key.equals("main")){
            ARouter.getInstance().build("/main/MainActivity").navigation();
        }else if(key.equals("shopCar")){
            ARouter.getInstance().build("/shopCar/ShopCarActivity").navigation();
        }
        finish();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_bind_tel_and_address;
    }

    @Override
    protected void initView() {
        etBindTelandadressPhone = (EditText) findViewById(R.id.et_bind_telandadress_phone);
        btnBindTel = (Button) findViewById(R.id.btn_bind_tel);
        etBindTelandadressAdress = (EditText) findViewById(R.id.et_bind_telandadress_adress);
        btnBindAdress = (Button) findViewById(R.id.btn_bind_adress);
        pbBindTelandadress = (ProgressBar) findViewById(R.id.pb_bind_telandadress);

    }

    @Override
    public void onBindTelOk(UpdatePhoneBean bean) {
        if(bean.getCode().equals("200")){
            String result = bean.getResult();
            if(UserManager.getInstance().isLogin()){
                UserManager.getInstance().saveTel(result);
                Toast.makeText(this, "电话绑定成功", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBindAddressOk(UpDateAddressBean bean) {
        if(bean.getCode().equals("200")){
            String result = bean.getResult();
            if(UserManager.getInstance().isLogin()){
                UserManager.getInstance().saveAdress(result);
                Toast.makeText(this, "地址绑定成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showloading() {
        pbBindTelandadress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        pbBindTelandadress.setVisibility(View.GONE);
        if(!isSuccess){
            Toast.makeText(this, ""+errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showEmpty() {

    }
}
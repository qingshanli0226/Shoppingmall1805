package com.shopmall.user;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.user.R;
import com.shopmall.common.Constants;
import com.shopmall.framework.base.BaseMVPActivity;
import com.shopmall.framework.contract.Contract;
import com.shopmall.framework.manager.ShopUserManager;
import com.shopmall.framework.mvptest.presenter.ShopCarPresenter;

@Route(path = "/user/InfoMainActivity")
public class InfoMainActivity extends BaseMVPActivity<ShopCarPresenter> implements Contract.ShopCarContractView {
    private EditText infoName;
    private EditText infoPhone;
    private Button infoPhoneTj;
    private EditText infoAddress;
    private Button infoAddressTj;
    private Button infoSubmit;

    @Override
    protected void initEvent() {
        mPresenter = new ShopCarPresenter(this);
    }

    @Override
    protected void initData() {
        String userName = ShopUserManager.getInstance().getUserName();
        if (userName!=null){
            infoName.setText(userName+"");
        }

        Log.i("---", "initData: 略略略略略略");

        infoPhoneTj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = infoPhone.getText().toString().trim();
                mPresenter.updatePhone(Constants.UPDATE_PHONE,phone);
            }
        });

        infoAddressTj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = infoAddress.getText().toString().trim();
                mPresenter.updateAddress(Constants.UPDATE_ADDRESS,address);
            }
        });

        infoSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone1 = ShopUserManager.getInstance().getPhone();
                String address1 = ShopUserManager.getInstance().getAddress();
                if (phone1==null||address1==null){
                    Toast.makeText(InfoMainActivity.this, "电话号码或地址未提交成功", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(InfoMainActivity.this, "购买成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        infoName = (EditText) findViewById(R.id.info_name);
        infoPhone = (EditText) findViewById(R.id.info_phone);
        infoPhoneTj = (Button) findViewById(R.id.info_phone_tj);
        infoAddress = (EditText) findViewById(R.id.info_address);
        infoAddressTj = (Button) findViewById(R.id.info_address_tj);
        infoSubmit = (Button) findViewById(R.id.info_submit);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_info_main;
    }

    @Override
    public void Success(Object... objects) {
        Toast.makeText(this, ""+(String) objects[0], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Error(String s) {
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }
}
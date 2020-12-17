package com.shopmall.bawei.user;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseActivity;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.manager.ShopUserManager;
import com.shopmall.bawei.framework.mvptest.presenter.ShopcarPresenter;
@Route(path = "/user/InfoMainActivity")
public class InfoMainActivity extends BaseActivity<ShopcarPresenter> implements Constant.ShopcarConstartView {
    private EditText infoName;
    private EditText infoPhone;
    private Button infoPhoneTijiao;
    private EditText infoAdress;
    private Button infoAdressTijiao;
    private Button infoSubmit;
    @Override
    protected void oncreatePresenter() {
        mPresenter=new ShopcarPresenter(this);
    }

    @Override
    protected void initEnvent() {

    }

    @Override
    protected void initview() {
        infoName = findViewById(R.id.info_name);
        infoPhone = findViewById(R.id.info_phone);
        infoPhoneTijiao = findViewById(R.id.info_phone_tijiao);
        infoAdress = findViewById(R.id.info_adress);
        infoAdressTijiao = findViewById(R.id.info_adress_tijiao);
        infoSubmit = findViewById(R.id.info_submit);

    }

    @Override
    protected void initData() {
        String userName = ShopUserManager.getInstance().getUserName();
        if (userName!=null){
            infoName.setText(userName+"");
        }

        infoPhoneTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = infoPhone.getText().toString();
                mPresenter.updatePhone(Constants.UPDATEPHONE,phone);
            }
        });


        infoAdressTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adress = infoAdress.getText().toString();
                mPresenter.updateAddress(Constants.UPDATEADDRESS,adress);
            }
        });

        infoSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getphone = ShopUserManager.getInstance().getphone();
                String getaddress = ShopUserManager.getInstance().getaddress();
                if (getphone==null||getaddress==null){
                    Toast.makeText(InfoMainActivity.this, "电话或地址暂未提交成功", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    Toast.makeText(InfoMainActivity.this, "购买成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_info_user;
    }

    @Override
    public void Success(Object... objects) {
        Toast.makeText(this, ""+(String)objects[0], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Error(String s) {
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }
}

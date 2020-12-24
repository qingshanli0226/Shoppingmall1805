package com.shopmall.bawei.shopmall1805.myfragment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.common2.UpdaptePhoneBean;
import com.example.common2.UpdataAddressBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.myfragment.contract.AddressContract;
import com.shopmall.bawei.shopmall1805.myfragment.presenter.AddressPresenter;

import mvp.CacheManager;
import mvp.ShopUserManager;
import mvp.view.BaseMVPActivity;

public class ReceivingAddressMainActivity extends BaseMVPActivity<AddressPresenter, AddressContract.IAddress> implements AddressContract.IAddress {

    private ImageView bank;
    private EditText updataAddress;
    private EditText updataPhone;
    private Button updateOk;



    @Override
    public void onAddess(UpdataAddressBean updataAddressBean) {
        if (updataAddressBean.getCode().equals("200")){
            Toast.makeText(this, "地址修改成功 ", Toast.LENGTH_SHORT).show();
            ShopUserManager.getInstance().saveaddrees(updataAddressBean);
           finish();
        }else {
            Toast.makeText(this, "地址修改失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPhone(UpdaptePhoneBean updaptePhoneBean) {
        if (updaptePhoneBean.getCode().equals("200")){
            Toast.makeText(this, "手机号修改成功 ", Toast.LENGTH_SHORT).show();
            ShopUserManager.getInstance().savephone(updaptePhoneBean);
        }else {
            Toast.makeText(this, "手机号修改失败", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void initPresenter() {
        ihttpPresenter = new AddressPresenter();
        ihttpPresenter.attachView(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        bank = (ImageView) findViewById(R.id.bank);
        updataAddress = (EditText) findViewById(R.id.updata_address);
        updataPhone = (EditText) findViewById(R.id.updata_phone);
        updateOk = (Button) findViewById(R.id.update_ok);
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updateOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Editable address = updataAddress.getText();
                Editable phone = updataPhone.getText();
                if (address!=null||phone!=null){
                    ihttpPresenter.addAddress(String.valueOf(address));
                    ihttpPresenter.addPhone(String.valueOf(phone));
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_receiving_address_main;
    }

    @Override
    public void showError(String code, String message) {

    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }
}
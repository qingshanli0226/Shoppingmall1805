package com.shopmall.bawei.shopmall1805.shopcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopmall1805.common.ARouterUtils;
import com.shopmall.bawei.shopmall1805.common.ErrorBean;
import com.shopmall.bawei.shopmall1805.framework.BaseActivity;
import com.shopmall.bawei.shopmall1805.framework.BaseMVPActivity;
import com.shopmall.bawei.shopmall1805.framework.service.CacheManager;
import com.shopmall.bawei.shopmall1805.net.entity.LoginBean;
import com.shopmall.bawei.shopmall1805.shopcar.contract.InformationContract;
import com.shopmall.bawei.shopmall1805.shopcar.presenter.InformationPresenterImpl;
import com.shopmall.bawei.shopmall1805.shopcar.presenter.ShopcarPresenterImpl;

import java.util.HashMap;


@Route(path = ARouterUtils.ADD_ADDRESS)
public class AddAddressActivity extends BaseMVPActivity<InformationPresenterImpl, InformationContract.InformationView> implements InformationContract.InformationView {
    private Button btYes;
    private EditText addressPhoneEdit;
    private EditText addressLocationEdit;
    private EditText addressPeopleEdit;

    @Override
    protected void initData() {
        btYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = addressPeopleEdit.getText().toString().trim();
                if(name!=null){
                    CacheManager.getInstance().addName(name);
                }
                String phone = addressPhoneEdit.getText().toString().trim();
                String location = addressLocationEdit.getText().toString().trim();
                httpPresenter.UpDataPhone(phone);
                httpPresenter.UpDataAddress(location);

                SharedPreferences address = getSharedPreferences("address", MODE_PRIVATE);
                SharedPreferences.Editor edit = address.edit();
                edit.putBoolean("loca",true);
                edit.commit();
                finish();
            }
        });
    }
    @Override
    protected void initPresenter() {
        httpPresenter = new InformationPresenterImpl();
    }
    @Override
    protected void initView() {
        btYes = findViewById(R.id.bt_yes);
        addressPhoneEdit = findViewById(R.id.address_phone_edit);
        addressLocationEdit = findViewById(R.id.address_location_edit);
        addressPeopleEdit = findViewById(R.id.address_people_edit);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }
    @Override
    public void onUpDataPhone(String phone) {
        if(phone!=null){
            CacheManager.getInstance().addPhone(phone);
        }
    }
    @Override
    public void onAddressUpData(String address) {
        if(address!=null){
            CacheManager.getInstance().addAddress(address);
        }
    }
    @Override
    public void showLoaing() {
    }
    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }
    @Override
    public void showEmpty() {

    }
}
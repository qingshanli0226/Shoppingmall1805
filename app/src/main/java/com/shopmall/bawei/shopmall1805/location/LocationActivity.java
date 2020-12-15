package com.shopmall.bawei.shopmall1805.location;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.deom.BaseActivity;
import com.bawei.deom.CacheManager;
import com.bawei.deom.Login;
import com.bawei.deom.ShopUserManager;
import com.bawei.deom.personalinformation.InformationCountroller;
import com.bawei.deom.personalinformation.InformationImpl;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.MainActivity;

import bean.LoginBean;
import bean.PhoneBean;

public class LocationActivity extends BaseActivity<InformationImpl, InformationCountroller.InformationView> implements InformationCountroller.InformationView {
    private EditText textLocation;
    private EditText textPhone;
    private Button bthDetermine;
    private Button bthlocation;
    private Button bthphone;






    @Override
    protected int getLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    protected void inPresone() {
        prine=new InformationImpl();
    }

    @Override
    protected void inData() {
         bthDetermine.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(LocationActivity.this, MainActivity.class);
                 startActivity(intent);
                 LocationActivity.this.finish();
             }
         });
        bthlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prine.UpdateAddressShow(textLocation.getText().toString());
            }
        });
        bthphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prine.UpdatePhoneShow(textPhone.getText().toString());
            }
        });
    }

    @Override
    protected void intView() {
        textLocation = (EditText) findViewById(R.id.text_location);
        textPhone = (EditText) findViewById(R.id.text_phone);
        bthDetermine = (Button) findViewById(R.id.bth_determine);
        bthlocation = (Button) findViewById(R.id.bthlocation);
        bthphone = (Button) findViewById(R.id.bthphone);
    }

    @Override
    public void onupdateAddressView(PhoneBean phoneBean) {
        Toast.makeText(this, "添加地址"+phoneBean.getResult(), Toast.LENGTH_SHORT).show();
        LoginBean.ResultBean resultBean=new LoginBean.ResultBean();
        resultBean.setAddress(textLocation.getText().toString());
        CacheManager.getInstance().setLoging(resultBean);

    }

    @Override
    public void onupdatePhoneView(PhoneBean phoneBean) {
        Toast.makeText(this, "添加电话"+phoneBean.getResult(), Toast.LENGTH_SHORT).show();
        LoginBean.ResultBean resultBean=new LoginBean.ResultBean();
        resultBean.setPhone(textPhone.getText().toString());
        CacheManager.getInstance().setLoging(resultBean);

    }

    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }
}

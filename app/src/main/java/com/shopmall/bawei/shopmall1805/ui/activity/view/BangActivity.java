package com.shopmall.bawei.shopmall1805.ui.activity.view;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.ShopUsermange;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ui.activity.contract.BindContract;
import com.shopmall.bawei.shopmall1805.ui.activity.presenter.BindPresenter;

public class BangActivity extends BaseActivity<BindPresenter, BindContract.IBindView> implements BindContract.IBindView, View.OnClickListener {
    private TextView tvPhone;
    private Button btBindPhone;
    private TextView tvAdress;
    private Button btBindAdress;
    private Button btTi;



    @Override
    protected void initpreseter() {
        httpresenter = new BindPresenter();
    }

    @Override
    protected void initdate() {
        btBindAdress.setOnClickListener(this);
        btBindPhone.setOnClickListener(this);
        btTi.setOnClickListener(this);
    }

    @Override
    protected void initview() {
        //初始化控件
        tvPhone = findViewById(R.id.tv_phone);
        btBindPhone = findViewById(R.id.bt_bind_phone);
        tvAdress = findViewById(R.id.tv_adress);
        btBindAdress = findViewById(R.id.bt_bind_adress);
        btTi = findViewById(R.id.bt_ti);
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_bind;
    }

    @Override
    public void onClick(View v) {
        String phone = tvPhone.getText().toString();
        String address = tvAdress.getText().toString();
        if (v==btBindAdress){
            httpresenter.BindAdress(address);
       }else if (v==btBindPhone){
            httpresenter.BindPhone(phone);
       }else if (v == btTi){
           Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
            finish();
       }
    }

    @Override
    public void onPhoneResult(String result) {
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        ShopUsermange.getInstance().setPhone(result);
    }

    @Override
    public void onAdressResult(String result) {
        Toast.makeText(this, ""+result, Toast.LENGTH_SHORT).show();
        ShopUsermange.getInstance().setAddress(result);
    }

    @Override
    public void onErroy(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showsloading() {
        showloading();
    }

    @Override
    public void hideloading() {
        hideLoading();
    }

    @Override
    public void showErroy(String message) {
      showerror(message);
    }

    @Override
    public void showEmpty() {
        showEnpty();
    }


}

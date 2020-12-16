package com.shopmall.bawei.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.shopmall.bawei.order.contract.OrderContract;
import com.shopmall.bawei.order.presenter.OrderPresenterImpl;
@Route(path = "/order/address")
public class TakeActivity extends BaseActivity<OrderPresenterImpl, OrderContract.IOrderView>implements OrderContract.IOrderView {

    private EditText etPhone;
    private EditText etAddress;
    private EditText etDz;
    private Button btnSub;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_take;
    }

    protected void initView() {
        etPhone = (EditText) findViewById(R.id.et_phone);
        etAddress = (EditText) findViewById(R.id.et_address);
        etDz = (EditText) findViewById(R.id.et_dz);
        btnSub = (Button) findViewById(R.id.btn_sub);
        httpPresenter = new OrderPresenterImpl();
    }

    @Override
    protected void initData() {
        initView();
        ARouter.getInstance().inject(this);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etPhone.getText().toString();
                String address = etAddress.getText().toString();
                httpPresenter.upDataPhone(phone);
                httpPresenter.upDataAddress(address);
                Toast.makeText(TakeActivity.this, "提交完成", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TakeActivity.this,OrderActivity.class));
            }
        });
    }

    @Override
    public void onUpDataPhone(String phone) {
        Log.i("TAG", "onUpDataPhone: "+phone);
    }

    @Override
    public void onUpDataAddress(String address) {
        Log.i("TAG", "onUpDataPhone: "+address);
    }

    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void showErroy(String message) {

    }

    @Override
    public void showEmpty() {

    }
}
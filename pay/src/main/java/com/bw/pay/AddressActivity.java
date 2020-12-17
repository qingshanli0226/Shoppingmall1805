package com.bw.pay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bw.framework.BaseActivity;
import com.bw.framework.ShopUserManager;
import com.bw.net.InventoryBean;
import com.bw.net.OrderInfoBean;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.ShopCarBean;
import com.bw.pay.contract.AddressContract;
import com.bw.pay.presenter.AddressPresenter;
import com.shopmall.bawei.pay.R;

import java.util.List;

public class AddressActivity extends BaseActivity<AddressPresenter, AddressContract.AddressView>implements AddressContract.AddressView{

    private EditText editNumber;
    private EditText editAddress;
    private Button submitBtn;
    private Button btnUpdateNumber;
    private Button btnUpdateAddress;

    @Override
    protected void initView() {
        super.initView();
        editNumber = (EditText) findViewById(R.id.editNumber);
        editAddress = (EditText) findViewById(R.id.editAddress);
        submitBtn = (Button) findViewById(R.id.submitBtn);



        btnUpdateNumber = (Button) findViewById(R.id.btnUpdateNumber);
        btnUpdateAddress = (Button) findViewById(R.id.btnUpdateAddress);

        btnUpdateAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpPresenter.updateAddress(editAddress.getText().toString());
            }
        });

        btnUpdateNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpPresenter.updateNumber(editNumber.getText().toString());

            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this, OrderActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
        httpPresenter = new AddressPresenter();
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    public void onUpdateNumberOk(String result) {
        Log.i("---", "onUpdateNumberOk: "+result);
        ShopUserManager.getInstance().setPhone(result);
    }

    @Override
    public void onUpdateAddressOk(String result) {
        Log.i("---", "onUpdateAddressOk: "+result);
        ShopUserManager.getInstance().setAddress(result);
    }

    @Override
    public void onInventory(List<InventoryBean> inventoryBean) {

    }

    @Override
    public void onOrderInfo(OrderInfoBean orderInfoBean) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showsLoaing() {
        showLoading();
    }

    @Override
    public void hidesLoading(boolean isSuccess) {
        hideLoadingPage(isSuccess);
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
}

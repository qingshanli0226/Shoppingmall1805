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

public class AddressActivity extends BaseActivity<AddressPresenter, AddressContract.AddressView>implements AddressContract.AddressView ,ShopUserManager.IUserLoginChangedListener{

    private EditText editNumber;
    private EditText editAddress;
    private Button submitBtn;
    private LoginBean newLoginBean =new LoginBean();


    @Override
    protected void initView() {
        super.initView();
        editNumber = (EditText) findViewById(R.id.editNumber);
        editAddress = (EditText) findViewById(R.id.editAddress);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        ShopUserManager.getInstance().registerUserLoginChangedListener(this);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpPresenter.updateAddress(editAddress.getText().toString());
                httpPresenter.updateNumber(editNumber.getText().toString());
//                ShopUserManager.getInstance().saveLoginBean(newLoginBean);
                Intent intent = new Intent(AddressActivity.this, OrderActivity.class);
                startActivity(intent);
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
        newLoginBean.setPhone(editNumber.getText().toString());
    }

    @Override
    public void onUpdateAddressOk(String result) {
        Log.i("---", "onUpdateAddressOk: "+result);
        newLoginBean.setAddress(editAddress.getText().toString());
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

    }

    @Override
    public void hidesLoading(boolean isSuccess) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onUserLogin(LoginBean loginBean) {
        newLoginBean.setId(loginBean.getId());
        newLoginBean.setName(loginBean.getName());
        newLoginBean.setToken(loginBean.getToken());
        newLoginBean.setPassword(loginBean.getPassword());

        Log.i("---", "onUserLogin: addressActivity："+loginBean.getName());
    }

    @Override
    public void onUserLoginOut() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}

package com.example.elevenmonthshoppingproject.address.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.address.contract.AddressContract;
import com.example.elevenmonthshoppingproject.address.presenter.AddressPresenterImpl;
import com.example.framwork.BaseMVPActivity;

public class AddressMessageActivity extends BaseMVPActivity<AddressPresenterImpl, AddressContract.AddressIView> implements AddressContract.AddressIView, View.OnClickListener {

    private EditText editMessage;
    private Button btnSmart;
    private TextView txtPhone;
    private EditText editPhoneCard;
    private Button btnPhone;
    private Button btnSure;
    private Intent intent;

    private AddressPresenterImpl addressPresenter;
    @Override
    public void onAddress(String messageBean) {
        Log.i("---",""+messageBean);
        Toast.makeText(this, "成功获取到地址", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPhone(String phoneMessage) {
        Toast.makeText(this, "成功获取到手机号", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void iniHttpView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_message;
    }

    @Override
    protected void iniView() {
        editMessage = findViewById(R.id.edit_message);
        btnSmart = findViewById(R.id.btn_smart);
        txtPhone = findViewById(R.id.txt_phone);


        editPhoneCard = findViewById(R.id.edit_phoneCard);

        txtPhone = findViewById(R.id.txt_phone);
        editPhoneCard = findViewById(R.id.edit_phoneCard);
        btnPhone = findViewById(R.id.btn_phone);
        btnSure = findViewById(R.id.btn_sure);

        btnSmart.setOnClickListener(this);
        btnPhone.setOnClickListener(this);
        btnSure.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {
        addressPresenter=new AddressPresenterImpl();
        addressPresenter.attatch(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onError(String code, String message) {
        Toast.makeText(this, "没有获取到地址信息"+code+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading(boolean isSuccess, String message) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        addressPresenter.detachview();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_smart:
                String message = editMessage.getText().toString().trim();
                addressPresenter.getAddress(message);
                break;
            case R.id.btn_phone:
                String phone = editPhoneCard.getText().toString().trim();
                addressPresenter.getPhone(phone);
                break;
            case R.id.btn_sure:
                if (editMessage.getText()==null||editPhoneCard.getText()==null){
                    Toast.makeText(this, "不可为空", Toast.LENGTH_SHORT).show();
                }
                ARouter.getInstance().build("/Main/MainActivity").navigation();
                break;

        }
    }
}

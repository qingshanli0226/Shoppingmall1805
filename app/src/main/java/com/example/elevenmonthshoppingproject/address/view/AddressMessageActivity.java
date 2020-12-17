package com.example.elevenmonthshoppingproject.address.view;

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
import com.example.net.bean.MessageBean;

public class AddressMessageActivity extends BaseMVPActivity<AddressPresenterImpl, AddressContract.AddressIView> implements AddressContract.AddressIView, View.OnClickListener {

    private EditText editMessage;
    private Button btnSmart;

    private AddressPresenterImpl addressPresenter;
    @Override
    public void onAddress(String messageBean) {
        Log.i("---",""+messageBean);
        Toast.makeText(this, "成功获取到信息", Toast.LENGTH_SHORT).show();
        ARouter.getInstance().build("/Main/MainActivity").navigation();
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

        btnSmart.setOnClickListener(this);
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
        }
    }
}

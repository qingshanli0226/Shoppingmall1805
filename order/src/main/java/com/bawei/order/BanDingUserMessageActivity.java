package com.bawei.order;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.ErrorBean;
import com.bawei.framework.BaseActivity;
import com.bawei.framework.ShopUserManager;
import com.bawei.order.contact.BanDingImpl;
import com.bawei.order.contact.BanDingUserContact;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Route(path = "/order/BanDing")
public class BanDingUserMessageActivity extends BaseActivity<BanDingImpl, BanDingUserContact.IBanDIngView> implements BanDingUserContact.IBanDIngView, View.OnClickListener {

    private EditText etBandingPhone;
    private EditText etBandingAddress;
    private Button btnBanding;

    private String phone;
    private String address;

    private boolean isPhone = false;
    private boolean isAddress = false;

    @Override
    protected int layoutId() {
        return R.layout.activity_ban_ding_user_message;
    }

    @Override
    protected void initView() {
        etBandingPhone = findViewById(R.id.et_banding_phone);
        etBandingAddress = findViewById(R.id.et_banding_address);
        btnBanding = findViewById(R.id.btn_banding);

        btnBanding.setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new BanDingImpl();
    }

    @Override
    public void onBanDingPhone(String message) {
        ShopUserManager.getInstance().banDingPhone(message);
        isPhone = true;
    }

    @Override
    public void onBanDingAddress(String message) {
        ShopUserManager.getInstance().banDingAddress(message);
        isAddress = true;
    }

    @Override
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_banding) {
            phone = etBandingPhone.getText().toString().trim();
            address = etBandingAddress.getText().toString().trim();
            if (isPhoneNumber(phone)) {
                httpPresenter.BanDingPhone(phone);
                httpPresenter.BanDingAddress(address);
            } else {
                Toast.makeText(this, "手机号输入错误", Toast.LENGTH_SHORT).show();
                return;
            }

            if (isPhone && isAddress) {
                Toast.makeText(this, "绑定成功", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/main/MainActivity").navigation();
                finish();
            }
        }
    }

    private boolean isPhoneNumber(String phone) {
        String regExp = "^1[3|4|5|7|8]\\d{9}$";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }
}

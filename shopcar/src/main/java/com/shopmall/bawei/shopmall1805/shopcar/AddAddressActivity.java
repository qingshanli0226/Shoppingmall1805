package com.shopmall.bawei.shopmall1805.shopcar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shopmall.bawei.shopcar.R;
import com.shopmall.bawei.shopmall1805.framework.BaseActivity;

public class AddAddressActivity extends BaseActivity {
    private Button btYes;
    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        btYes = findViewById(R.id.bt_yes);
        btYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }
}
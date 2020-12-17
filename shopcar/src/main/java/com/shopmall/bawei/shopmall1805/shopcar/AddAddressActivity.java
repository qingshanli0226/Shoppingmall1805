package com.shopmall.bawei.shopmall1805.shopcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                SharedPreferences address = getSharedPreferences("address", MODE_PRIVATE);
                SharedPreferences.Editor edit = address.edit();
                edit.putBoolean("loca",true);
                edit.commit();
                Toast.makeText(AddAddressActivity.this, "添加地址完成", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_address;
    }
}
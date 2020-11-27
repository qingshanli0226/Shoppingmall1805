package com.shopmall.bawei.shopmall1805.framework;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    private String TAG;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initData();
    }

    protected abstract void initData();
    protected abstract void initView();
    protected abstract int getLayoutId();

    protected void printLog(String message){
        Log.i(TAG, message);
    }
    protected void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

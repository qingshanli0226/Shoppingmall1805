package com.shopmall.bawei.shopmall1805.framework;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.shopmall.bawei.framework.R;

public abstract class BaseActivity extends AppCompatActivity implements Toolbar.IToolBarClickListner{
    private String TAG;
    protected Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        toolbar = findViewById(R.id.toolbar);
        toolbar.setToolBarClickListner(this);
        initData();
    }

    protected abstract void initData();
    protected abstract void initView();

    protected abstract int getLayoutId();

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    protected void printLog(String message){
        Log.i(TAG, message);
    }
    protected void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

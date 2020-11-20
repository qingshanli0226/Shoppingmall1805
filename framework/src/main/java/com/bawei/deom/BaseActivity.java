package com.bawei.deom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayouview());
        intView();
        inData();


    }



    protected abstract void inData();

    protected abstract void intView();

    protected abstract int getlayouview();


}

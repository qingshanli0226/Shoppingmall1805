package com.shopmall.bawei.order;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import framework.BaseActivity;
import view.ToolBar;
import view.loadinPage.ErrorBean;

public class BindingActivity extends BaseActivity implements View.OnClickListener, ToolBar.IToolBarClickListner {
    private EditText editPhone;
    private Button buttonPhone;
    private EditText editSite;
    private Button buttonSite;
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void initData() {
        editPhone = (EditText) findViewById(R.id.editPhone);
        buttonPhone = (Button) findViewById(R.id.buttonPhone);
        editSite = (EditText) findViewById(R.id.editSite);
        buttonSite = (Button) findViewById(R.id.buttonSite);
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_binding;
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
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}
package com.shopmall.bawei.shopcar;

import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.base.BaseActivity;
import com.example.framework.view.ToolBar;

import java.io.Serializable;

@Route(path = "/shopCar/ShopCarActivity")
public class ShopCarActivity extends BaseActivity {
    private ToolBar toolbar;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private  Intent intent;
    private boolean flag=false;

    @Override
    protected void initListener() {
        super.initListener();

    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        String type = intent.getStringExtra("type");
        Serializable extra = intent.getSerializableExtra("good");
        ARouter.getInstance().build("/detailpage/DetailActivity").withSerializable("good",extra).withString("type",type).navigation();
        finish();
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
       intent = getIntent();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.empty_car;
    }

    @Override
    protected void initView() {

        toolbar = (ToolBar) findViewById(R.id.toolbar);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        llCheckAll = (LinearLayout) findViewById(R.id.ll_check_all);
        checkboxAll = (CheckBox) findViewById(R.id.checkbox_all);
        tvShopcartTotal = (TextView) findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);
        llDelete = (LinearLayout) findViewById(R.id.ll_delete);
        cbAll = (CheckBox) findViewById(R.id.cb_all);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        btnCollection = (Button) findViewById(R.id.btn_collection);

    }

}
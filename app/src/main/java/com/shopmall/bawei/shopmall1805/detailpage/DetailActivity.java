package com.shopmall.bawei.shopmall1805.detailpage;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.base.BaseActivity;
import com.example.net.bean.MainBean;
import com.shopmall.bawei.shopmall1805.R;

@Route(path = "/detailpage/DetailActivity")
public class DetailActivity extends BaseActivity {


    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        MainBean.ResultBean.BannerInfoBean good = (MainBean.ResultBean.BannerInfoBean) extras.get("good");
        Toast.makeText(this, good.getValue().getUrl(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_detail;
    }

    @Override
    protected void initView() {

    }
}
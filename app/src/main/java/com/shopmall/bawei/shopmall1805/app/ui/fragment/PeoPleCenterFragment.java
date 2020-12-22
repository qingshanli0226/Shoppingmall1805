package com.shopmall.bawei.shopmall1805.app.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.common.ARouterUtils;
import com.shopmall.bawei.shopmall1805.framework.BaseFragment;
import com.shopmall.bawei.shopmall1805.user.LoginActivity;


public class PeoPleCenterFragment extends BaseFragment {
    private ImageButton ibUserIconAvator;
    private TextView tvUserLocation;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_people_center;
    }
    @Override
    protected void initData() {

    }
    @Override
    protected void initView() {
        ibUserIconAvator = findViewById(R.id.ib_user_icon_avator);
        tvUserLocation = findViewById(R.id.tv_user_location);

        ibUserIconAvator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        tvUserLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build(ARouterUtils.ADD_ADDRESS)
                        .navigation();
            }
        });
    }
}
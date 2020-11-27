package com.shopmall.bawei.shopmall1805.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.deom.BaseFragment;
import com.bawei.deom.IPrine;
import com.bawei.deom.IView;
import com.shopmall.bawei.shopmall1805.login.LoginActivity;
import com.shopmall.bawei.shopmall1805.R;

public class Myfragment  extends BaseFragment<IPrine,IView> {

    TextView tvUsername;

    @Override
    protected void inPrine() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        tvUsername=view.findViewById(R.id.tv_username);
        tvUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getlayoutview() {
        return R.layout.myfragment;
    }

}

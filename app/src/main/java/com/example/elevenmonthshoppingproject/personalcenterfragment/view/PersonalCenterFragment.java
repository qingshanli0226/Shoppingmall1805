package com.example.elevenmonthshoppingproject.personalcenterfragment.view;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.address.view.AddressMessageActivity;
import com.example.framwork.BaseFragment;

public class PersonalCenterFragment extends BaseFragment implements View.OnClickListener {
    private TextView tvUserLocation;

    private Intent intent;

    @Override
    protected int getLayoutId() {
        return R.layout.personalcenterfragment;
    }

    @Override
    protected void initView() {
        tvUserLocation = findViewById(R.id.tv_user_location);
        tvUserLocation.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_user_location:
                intent=new Intent(getContext(), AddressMessageActivity.class);
                startActivity(intent);
                break;
        }
    }
}

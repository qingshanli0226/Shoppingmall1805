package com.shopmall.bawei.shopmall1805.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.bawei.deom.BaseFragment;
import com.bawei.deom.IPrine;
import com.bawei.deom.IView;
import com.shopmall.bawei.shopmall1805.activity.location.LocationActivity;
import com.shopmall.bawei.shopmall1805.activity.login.LoginActivity;
import com.shopmall.bawei.shopmall1805.R;

public class Myfragment  extends BaseFragment<IPrine,IView> {

    TextView tvUsername;
    private TextView tvUserLocation;



    @Override
    protected void inPrine() {
        loadingPage.showSuccessView();
    }

    @Override
    protected void initData() {
          tvUserLocation.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                     Intent intent=new Intent(getContext(), LocationActivity.class);
                     startActivity(intent);
              }
          });
    }

    @Override
    protected void initView(View view) {
        tvUsername=view.findViewById(R.id.tv_username);
        tvUserLocation = (TextView) view.findViewById(R.id.tv_user_location);
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

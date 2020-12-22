package com.shopmall.bawei.shopmall1805.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bawei.deom.BaseFragment;
import com.bawei.deom.BaseMyFragment;
import com.bawei.deom.CacheManager;
import com.bawei.deom.IPrine;
import com.bawei.deom.IView;
import com.bawei.deom.bean.ReceivegoodsBeen;
import com.shopmall.bawei.shopmall1805.ReceliveGoodActivity;
import com.shopmall.bawei.shopmall1805.activity.location.LocationActivity;
import com.shopmall.bawei.shopmall1805.activity.login.LoginActivity;
import com.shopmall.bawei.shopmall1805.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Myfragment  extends BaseMyFragment {

    TextView tvUsername;
    private TextView tvUserLocation;
    private TextView tvUserPay;
    private TextView tvDelivery;









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
    protected void inPrine() {

    }


    @Override
    protected void initView(View view) {
        tvDelivery = (TextView) view.findViewById(R.id.tv_delivery);
        tvUsername=view.findViewById(R.id.tv_username);
        tvUserLocation = (TextView) view.findViewById(R.id.tv_user_location);
        tvUserPay = (TextView) view.findViewById(R.id.tv_user_pay);
        tvUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        tvUserPay.setText("待付款"+CacheManager.getInstance().getReceivegoodsBeen());
        tvDelivery.setText("待发货"+CacheManager.getInstance().getDelivery());
        tvUserPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), ReceliveGoodActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutView() {

        return R.layout.myfragment;
    }







}

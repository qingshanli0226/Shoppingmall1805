package com.shopmall.bawei.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import mvp.CacheManager;
import mvp.ShopUserManager;

public class OrderActivity extends AppCompatActivity {
    private LinearLayout orderAddress;
    private TextView username;
    private TextView userphone;
    private TextView useraddress;
    private ImageView bank;
    private RecyclerView orderRv;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderAddress = (LinearLayout) findViewById(R.id.order_address);
        username = (TextView) findViewById(R.id.username);
        userphone = (TextView) findViewById(R.id.userphone);
        useraddress = (TextView) findViewById(R.id.useraddress);
        bank = (ImageView) findViewById(R.id.bank);
        orderRv = (RecyclerView) findViewById(R.id.order_rv);
        orderRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        /*
        跳转到收货地址设置
      orderAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        userphone.setText(ShopUserManager.getInstance().getPhone());
        useraddress.setText(ShopUserManager.getInstance().getAddrees());
        username.setText(ShopUserManager.getInstance().getuserName());

        OrderAdapter orderAdapter=new OrderAdapter();
        orderAdapter.updataData(CacheManager.getInstance().getSelectedProductBeanList());
        orderRv.setAdapter(orderAdapter);

    }
}
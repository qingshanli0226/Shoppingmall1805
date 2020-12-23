package com.bw.pay;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.bw.framework.BaseActivity;
import com.bw.framework.CacheManager;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.bw.framework.OrderManager;
import com.bw.framework.ShopUserManager;
import com.bw.framework.dao.ShopcarMessage;
import com.bw.framework.MessageManager;
import com.bw.net.ForPayBean;
import com.bw.net.ForSendBean;
import com.bw.net.InventoryBean;
import com.bw.net.OrderInfoBean;
import com.bw.net.bean.Bean;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.ShopCarBean;
import com.bw.pay.contract.AddressContract;
import com.bw.pay.presenter.AddressPresenter;
import com.shopmall.bawei.pay.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Route(path = "/orderActivity/orderInfoActivity")
public class OrderActivity extends BaseActivity<IPresenter, IView>{

    private TextView startBtn;
    private TextView addressTv;
    private TextView nameTv;
    private TextView numberTv;
    private View myview;
    private LinearLayout linShoplist;
    private RecyclerView goodsList;
    private TextView orderPrice;
    private TextView price;
    private Button submitBtn;
    private OrderGoodsAdapter orderGoodsAdapter;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;
    private Button btnUpdate;


    private ShopUserManager.IUserLoginChangedListener loginBeanListener = new ShopUserManager.IUserLoginChangedListener() {

        @Override
        public void onUserLogin(LoginBean loginBean) {
            addressTv.setText(loginBean.getAddress()+"");
            numberTv.setText(loginBean.getPhone()+"");
            nameTv.setText(loginBean.getName());
        }

        @Override
        public void onUserLoginOut() {

        }

    };


    @Override
    protected void initView() {
        super.initView();
        ARouter.getInstance().inject(this);
        price = findViewById(R.id.orderAllPrice);
        startBtn = (TextView) findViewById(R.id.startBtn);
        addressTv = (TextView) findViewById(R.id.addressTv);
        nameTv = (TextView) findViewById(R.id.nameTv);
        numberTv = (TextView) findViewById(R.id.numberTv);
        myview = (View) findViewById(R.id.myview);
        linShoplist = (LinearLayout) findViewById(R.id.lin_shoplist);
        linearLayout1 = findViewById(R.id.addressLinear);
        linearLayout2 =findViewById(R.id.linear);
        goodsList = (RecyclerView) findViewById(R.id.goodsList);
        orderPrice = (TextView) findViewById(R.id.orderPrice);
        submitBtn = (Button) findViewById(R.id.submitBtn);
        btnUpdate = findViewById(R.id.btnUpdate);


        ShopUserManager.getInstance().registerUserLoginChangedListener(loginBeanListener);

        orderGoodsAdapter = new OrderGoodsAdapter();
        goodsList.setAdapter(orderGoodsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        goodsList.setLayoutManager(linearLayoutManager);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderActivity.this,AddressActivity.class));
            }
        });


        if (ShopUserManager.getInstance().isUserLogin()){
            LoginBean loginBean = ShopUserManager.getInstance().getLoginBean();
            if (loginBean.getAddress()!= null && loginBean.getPhone() != null){
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);

                addressTv.setText(loginBean.getAddress()+"");
                numberTv.setText(loginBean.getPhone()+"");
                nameTv.setText(loginBean.getName());

            }else {
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout1.setVisibility(View.GONE);
            }
        }




        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderActivity.this,AddressActivity.class));
            }
        });


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("---", "onClick: 点击提交订单");


                startActivity(new Intent(OrderActivity.this,PayActivity.class));

            }
        });

    }






    @Override
    protected void initPresenter() {
        super.initPresenter();
    }

    @Override
    protected void initData() {
        super.initData();

        orderGoodsAdapter.updataData(CacheManager.getInstance().getSelectedProductBeanList());

        String moneyValues = CacheManager.getInstance().getMoneyValues();

        orderPrice.setText("￥"+moneyValues);
        price.setText("￥"+moneyValues);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShopUserManager.getInstance().unRegisterUserLoginChangedListener(loginBeanListener);
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}

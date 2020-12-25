package com.shopmall.bawei.order.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.common2.GetShopCarBean;
import com.shopmall.bawei.order.R;
import com.shopmall.bawei.order.adapter.OrderAdapter;
import com.shopmall.bawei.order.contract.OrdderContract;
import com.shopmall.bawei.order.prsenter.OrderPresenter;

import java.util.List;
import java.util.Map;

import http.InventoryBean;
import http.OrderInfoBean;
import mvp.CacheManager;
import mvp.ShopUserManager;
import mvp.view.BaseMVPActivity;
import mvp.view.BottomBar;

public class OrderActivity extends BaseMVPActivity<OrderPresenter, OrdderContract.ISetShopCar> implements OrdderContract.ISetShopCar {
    private LinearLayout orderAddress;
    private TextView username;
    private TextView userphone;
    private TextView useraddress;
    private ImageView bank;
    private RecyclerView orderRv;
    private TextView orderPay;
    private Button orderButtonPay;



    @Override
    protected void initView() {
        orderAddress = (LinearLayout) findViewById(R.id.order_address);
        username = (TextView) findViewById(R.id.username);
        userphone = (TextView) findViewById(R.id.userphone);
        useraddress = (TextView) findViewById(R.id.useraddress);
        bank = (ImageView) findViewById(R.id.bank);
        orderRv = (RecyclerView) findViewById(R.id.order_rv);
        orderRv.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        orderPay = (TextView) findViewById(R.id.order_pay);
        orderButtonPay = (Button) findViewById(R.id.order_button_pay);
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
        orderPay.setText(String.valueOf(CacheManager.getInstance().getMoneyValue()));

        orderButtonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ihttpPresenter.checkInventory(CacheManager.getInstance().getSelectedProductBeanList());
            }
        });
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void initPresenter() {
        ihttpPresenter = new OrderPresenter();
        ihttpPresenter.attachView(this);
    }

    @Override
    protected void initData() {

    }

    public void onInventory(List<InventoryBean> inventoryBean) {
        if (checkInventoryIsEnough(inventoryBean)) {//库存充足的条件
            //充足情况下，向服务端下订单
            ihttpPresenter.getOrderInfo(CacheManager.getInstance().getSelectedProductBeanList());
        } else {
            Toast.makeText(OrderActivity.this, "库存不充足", Toast.LENGTH_SHORT).show();
        }
    }
    private boolean checkInventoryIsEnough(List<InventoryBean> inventoryBeans) {
        List<GetShopCarBean> shopcarBeanList = CacheManager.getInstance().getSelectedProductBeanList();
        for(InventoryBean inventoryBean:inventoryBeans) {
            for(GetShopCarBean shopcarBean:shopcarBeanList) {
                if (inventoryBean.getProductId().equals(shopcarBean.getProductId())) {
                    int inventoryNum = Integer.parseInt(inventoryBean.getProductNum());
                    int needNum = Integer.parseInt(inventoryBean.getProductNum());
                    if (needNum > inventoryNum) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public void onOrderInfo(final OrderInfoBean orderInfoBean) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(OrderActivity.this);
                Map<String,String> resultMap = payTask.payV2(orderInfoBean.getOrderInfo(), true);
                if (resultMap.get("resultStatus").equals("9000")) {//返回值是9000时代表支付成功
                    handler.sendEmptyMessage(1);
                } else {
                    handler.sendEmptyMessage(2);
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1: {
                    Toast.makeText(OrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    //第一步将这些支付成功的商品，从购物车中删除
                    CacheManager.getInstance().removeSelectedProducts();
                    //第二步跳转的主页面，并且显示HomeFragment
                  //  ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.HOME_INDEX).navigation();
                    break;
                }
                case 2: {
                    Toast.makeText(OrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    //第一步将这些支付成功的商品，从购物车中删除

                    //生成订单
                    /*  CacheManager.getInstance().removeSelectedProducts();*/
                    //第二步跳转的主页面，并且显示HomeFragment
                  //  ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.HOME_INDEX).navigation();
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    public void showError(String code, String message) {

    }

    @Override
    public void showLoaing() {

    }

    @Override
    public void hideLoading() {

    }
}
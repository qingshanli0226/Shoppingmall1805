package com.shopmall.bawei.order;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.framework.base.BaseMVPActivity;
import com.shopmall.framework.manager.CacheManager;
import com.shopmall.framework.manager.ShopUserManager;
import com.shopmall.net.bean.ShopcarBean;

import java.util.List;

@Route(path = "/order/OrderActivity")
public class OrderActivity extends BaseMVPActivity {
    private LinearLayout orderTitle;
    private ImageView orderBack;
    private TextView orderName;
    private TextView orderPhone;
    private TextView orderAddress;
    private RecyclerView orderRecycle;
    private TextView peisong;
    private TextView yun;
    private TextView ding;
    private TextView orderNum;
    private TextView xiao;
    private TextView orderMoney;
    private TextView orderDanNum;
    private TextView orderDanMoney;
    private Button orderSubmit;

    private OrderAdapter orderAdapter;

    @Override
    protected void initEvent() {
        orderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        orderSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ARouter.getInstance().build("/pay/PayActivity").navigation();
            }
        });
    }

    @Override
    protected void initData() {
        orderName.setText(ShopUserManager.getInstance().getUserName());
        orderPhone.setText(ShopUserManager.getInstance().getPhone());
        orderAddress.setText(ShopUserManager.getInstance().getAddress());

        List<ShopcarBean.ResultBean> selectedProductBeanList = CacheManager.getInstance().getShopCarBeanList();
        orderNum.setText(selectedProductBeanList.size()+"件");
        orderDanNum.setText(selectedProductBeanList.size()+"件");
        orderMoney.setText("￥"+CacheManager.getInstance().getMoneyValue());
        orderDanMoney.setText("￥"+CacheManager.getInstance().getMoneyValue());

        orderAdapter = new OrderAdapter();
        orderRecycle.setAdapter(orderAdapter);
        orderAdapter.updataData(selectedProductBeanList);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);

        orderTitle = (LinearLayout) findViewById(R.id.order_title);
        orderBack = (ImageView) findViewById(R.id.order_back);
        orderName = (TextView) findViewById(R.id.order_name);
        orderPhone = (TextView) findViewById(R.id.order_phone);
        orderAddress = (TextView) findViewById(R.id.order_address);
        orderRecycle = (RecyclerView) findViewById(R.id.order_recycle);
        peisong = (TextView) findViewById(R.id.peisong);
        yun = (TextView) findViewById(R.id.yun);
        ding = (TextView) findViewById(R.id.ding);
        orderNum = (TextView) findViewById(R.id.order_num);
        xiao = (TextView) findViewById(R.id.xiao);
        orderMoney = (TextView) findViewById(R.id.order_money);
        orderDanNum = (TextView) findViewById(R.id.order_dan_num);
        orderDanMoney = (TextView) findViewById(R.id.order_dan_money);
        orderSubmit = (Button) findViewById(R.id.order__submit);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order;
    }
}
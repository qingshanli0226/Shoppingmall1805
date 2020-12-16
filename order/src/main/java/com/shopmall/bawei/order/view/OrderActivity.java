package com.shopmall.bawei.order.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.ShopUsermange;
import com.example.framework.dao.ShopcarMessage;
import com.example.framework.view.manager.MessageManager;
import com.example.net.bean.IntonVoryBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.OrderInfoBean;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.order.R;
import com.shopmall.bawei.order.adpter.OrderAdpter;
import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

@Route(path="/order/Activity")
public class OrderActivity extends BaseActivity<IPresenter, IView> implements View.OnClickListener{
    private ImageButton ib_shopcart_back;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_adress;
    private RecyclerView rv_order;
    private TextView tv_price;
    private TextView tv_price_jie;
    private Button bt_buy;
    private RelativeLayout ll_goods_root;
    private OrderAdpter orderAdpter;

    @Override
    protected void initpreseter() {
    }

    @Override
    protected void initdate() {
        //获取选择的列表
        List<ShopcarBean> selectedShopBeans = CacheManager.getInstance().getSelectedShopBeans();
        orderAdpter.updataData(selectedShopBeans);
        tv_price.setText("￥"+CacheManager.getInstance().getMoney());
        tv_price_jie.setText("￥"+CacheManager.getInstance().getMoney());
        //将收货信息填一下
        tv_name.setText(""+ShopUsermange.getInstance().getName());
        tv_phone.setText(""+ShopUsermange.getInstance().getPhone());
        Log.e("###",""+ShopUsermange.getInstance().getPhone());
        tv_adress.setText(""+ShopUsermange.getInstance().getAddress());
    }

    @Override
    protected void initview() {
        ib_shopcart_back = (ImageButton) findViewById(R.id.ib_shopcart_back);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_adress = (TextView) findViewById(R.id.tv_adress);
        rv_order = (RecyclerView) findViewById(R.id.rv_order);
        orderAdpter = new OrderAdpter();
        rv_order.setAdapter(orderAdpter);
        rv_order.setLayoutManager(new LinearLayoutManager(this));
        tv_price = (TextView) findViewById(R.id.tv_price);
        tv_price_jie = (TextView) findViewById(R.id.tv_price_jie);
        bt_buy = (Button) findViewById(R.id.bt_buy);
        ll_goods_root = (RelativeLayout) findViewById(R.id.ll_goods_root);

        ib_shopcart_back.setOnClickListener(this);
        bt_buy.setOnClickListener(this);
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_order;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ib_shopcart_back) {
                finish();
        } else if (id == R.id.bt_buy) {
            //跳转到支付模块
            ARouter.getInstance().build("/pay/Activity").navigation();
        }
    }



}

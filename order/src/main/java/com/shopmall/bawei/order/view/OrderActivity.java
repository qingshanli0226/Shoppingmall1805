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
import com.alipay.sdk.app.PayTask;
import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.ShopUsermange;
import com.example.net.bean.IntonVoryBean;
import com.example.net.bean.LoginBean;
import com.example.net.bean.OrderInfoBean;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.order.R;
import com.shopmall.bawei.order.adpter.OrderAdpter;
import com.shopmall.bawei.order.contract.OrderContract;
import com.shopmall.bawei.order.presenter.OrderPresenter;

import java.util.List;
import java.util.Map;

@Route(path="/order/Activity")
public class OrderActivity extends BaseActivity<OrderPresenter, OrderContract.IOrderView> implements OrderContract.IOrderView,View.OnClickListener , ShopUsermange.IUserLoginChangeLiestener {

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
        httpresenter = new OrderPresenter();
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
            //首先检查选中商品的数量是否满足
            httpresenter.checkIntonvory(CacheManager.getInstance().getSelectedShopBeans());
        }
    }
    //如果登录获取
    @Override
    public void onUserLogin(LoginBean loginBean) {

    }

    @Override
    public void onUserlogout() {

    }
    //检查多个商品的库存
    @Override
    public void onCheckIntonvory(List<IntonVoryBean> intonVoryBeans) {
        if (checkIntontnvory(intonVoryBeans)){
            //库存足够的时候，发起订单
            httpresenter.orderinfo(CacheManager.getInstance().getSelectedShopBeans());
        }else {
            Toast.makeText(this, "库存不足", Toast.LENGTH_SHORT).show();
        }
    }
    //检查商品库存
    private boolean checkIntontnvory(List<IntonVoryBean> intonVoryBeans) {
        //获取选中的商品集合
        List<ShopcarBean> selectedShopBeans = CacheManager.getInstance().getSelectedShopBeans();
        for (IntonVoryBean intonVoryBean : intonVoryBeans) {
            for (ShopcarBean selectedShopBean : selectedShopBeans) {
                if (intonVoryBean.getProductId().equals(selectedShopBean.getProductId())){
                    int num = Integer.parseInt(intonVoryBean.getProductNum());
                    int needNum = Integer.parseInt(intonVoryBean.getProductNum());
                    if (needNum>num){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Toast.makeText(OrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    //然后跳转的主页面，并且显示HomeFragment
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                    break;
                case 2:
                    Toast.makeText(OrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    //然后跳转的主页面，并且显示HomeFragment
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                    break;
            }
        }
    };
    //下单的返回
    @Override
    public void getOrderInfo(final OrderInfoBean orderInfoBean) {
        //首先将你选中的商品从购物车删除，因为提交订单之后，商品就没了
        CacheManager.getInstance().removeselectshopBean();
        //服务端成功下单
        //使用支付宝完成支付功能
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(OrderActivity.this);
                Map<String, String> stringStringMap = payTask.payV2(orderInfoBean.getOrderInfo(), true);
                if (stringStringMap.get("resultStatus").equals("9000")){//9000代表支付成功
                    //在子线程进行完成
                    handler.sendEmptyMessage(1);
                }else {
                    //在子线程进行完成
                    handler.sendEmptyMessage(2);
                }


            }
        };
        //开启线程
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {
        showloading();
    }

    @Override
    public void hideloading() {
        hideLoading();
    }

    @Override
    public void showErroy(String message) {
        showerror(message);
    }

    @Override
    public void showEmpty() {
        showEnpty();
    }
}

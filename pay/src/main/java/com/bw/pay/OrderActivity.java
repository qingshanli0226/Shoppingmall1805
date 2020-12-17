package com.bw.pay;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
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
import com.bw.framework.ShopUserManager;
import com.bw.framework.dao.ShopcarMessage;
import com.bw.framework.MessageManager;
import com.bw.net.InventoryBean;
import com.bw.net.OrderInfoBean;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.ShopCarBean;
import com.bw.pay.contract.AddressContract;
import com.bw.pay.presenter.AddressPresenter;
import com.shopmall.bawei.pay.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Route(path = "/orderActivity/orderInfoActivity")
public class OrderActivity extends BaseActivity<AddressPresenter, AddressContract.AddressView> implements AddressContract.AddressView{

    private ImageButton ibShopcartBack;
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
    private PopupWindow popupWindow;
    private View popupView;
    private Button btnUpdate;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){

                Toast.makeText(OrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                CacheManager.getInstance().removeSelectedProducts();
                //支付成功，发送消息
                savePayMessage("支付成功");
            }else {
                Toast.makeText(OrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                CacheManager.getInstance().removeSelectedProducts();
                savePayMessage("支付失败");
            }
        }
    };

    private void savePayMessage(String message) {
        ShopcarMessage shopcarMessage = new ShopcarMessage();
        shopcarMessage.setBody(message);
        shopcarMessage.setType(MessageManager.PAY_TYPE);
        shopcarMessage.setIsRead(false);
        shopcarMessage.setTime(System.currentTimeMillis());
        shopcarMessage.setTitle("支付消息");

        MessageManager.getInstance().addMessage(shopcarMessage, new MessageManager.IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<ShopcarMessage> shopcarMessageList) {
            }
        });
    }

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

        popupWindow = new PopupWindow();
        popupWindow.setOutsideTouchable(true);
        popupView= getLayoutInflater().inflate(R.layout.popupwindow_layout, null);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("---", "onClick: 点击提交订单");
                popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
                popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
//                popupWindow.showAtLocation(popupView,0,0);
                popupWindow.setContentView(popupView);
                popupWindow.showAsDropDown(popupView);
            }
        });

        TextView textView = popupView.findViewById(R.id.order_price);
        textView.setText(CacheManager.getInstance().getMoneyValues());

        popupView.findViewById(R.id.btnPay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(OrderActivity.this, "点击支付", Toast.LENGTH_SHORT).show();
                checkInventory();
                popupWindow.dismiss();
                finish();
            }
        });

        //沙箱环境
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//设置沙箱环境.

    }



    private void checkInventory() {
        httpPresenter.checkInventory(CacheManager.getInstance().getSelectedProductBeanList());
    }


    @Override
    protected void initPresenter() {
        super.initPresenter();
        httpPresenter = new AddressPresenter();
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
    public void onUpdateNumberOk(String result) {

    }

    @Override
    public void onUpdateAddressOk(String result) {

    }
    private boolean checkInventoryIsEnough(List<InventoryBean> inventoryBeans) {

        List<ShopCarBean> shopcarBeanList = CacheManager.getInstance().getSelectedProductBeanList();
        for(InventoryBean inventoryBean:inventoryBeans) {
            for(ShopCarBean shopcarBean:shopcarBeanList) {
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

    @Override
    public void onInventory(List<InventoryBean> inventoryBeans) {
        if (checkInventoryIsEnough(inventoryBeans)){
            httpPresenter.getOrderInfo(CacheManager.getInstance().getSelectedProductBeanList());


        }else {
            Toast.makeText(this, inventoryBeans.get(0).getProductName()+"库存不充足", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onOrderInfo(final OrderInfoBean orderInfoBean) {
        //服务端已经成功下订单
        //使用支付宝完成支付功能
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(OrderActivity.this);
                Map<String, String> resultMap = payTask.payV2(orderInfoBean.getOrderInfo(), true);
                Log.i("---", "run: "+resultMap.get("resultStatus"));
                if (resultMap.get("resultStatus").equals("9000")){
                    handler.sendEmptyMessage(1);
                }else {
                    handler.sendEmptyMessage(2);
                }

            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showsLoaing() {

    }

    @Override
    public void hidesLoading(boolean isSuccess) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShopUserManager.getInstance().unRegisterUserLoginChangedListener(loginBeanListener);
    }

    @Override
    public void onLeftClick() {
        popupWindow.dismiss();
    }

    @Override
    public void onRightClick() {

    }
}

package com.shopmall.bawei.pay;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.net.bean.InventoryBean;
import com.example.net.bean.OrderInfoBean;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.framework.example.framework.BaseActivity;
import com.shopmall.bawei.framework.example.framework.dao.PayMessage;
import com.shopmall.bawei.framework.example.framework.manager.CacheManager;
import com.shopmall.bawei.framework.example.framework.manager.PayMessageManager;
import com.shopmall.bawei.framework.example.framework.manager.UserManage;
import com.shopmall.bawei.pay.adapter.PayAdapter;
import com.shopmall.bawei.pay.paymvp.PayContract;
import com.shopmall.bawei.pay.paymvp.PayPresenterImpl;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Route(path = "/Pay/PayActivity")
public class PayActivity extends BaseActivity<PayPresenterImpl, PayContract.orderView> implements View.OnClickListener, PayContract.orderView {

    private TextView tvUserName;
    private TextView tvUserTelphone;
    private TextView tvUserAddress;
    private Button btAddAddress;
    private RecyclerView rvShopcarList;
    private Button btRefer;
    private TextView tvTotal;
    private TextView tvCount;
    private List<ShopcarBean> shopcarBeanList = new ArrayList<>();
    private PayAdapter orderAdapter;



    private int sumCount = 0;
    private float sumPrice = 0;
    private float sumTotal = 0;
    @Override
    protected void initpreseter() {
        httpresenter = new PayPresenterImpl();

    }

    @Override
    protected void initdate() {

        for (ShopcarBean shopcarBean : shopcarBeanList) {
            Log.e("---",shopcarBean.getProductNum());
            Log.e("---",shopcarBean.getProductPrice());
            int productNum = Integer.parseInt(shopcarBean.getProductNum());
            float productPrice = Float.parseFloat(shopcarBean.getProductPrice());
            sumCount = sumCount + productNum;
            sumPrice = sumPrice + productPrice;
            sumTotal = sumTotal+(productNum*productPrice);
        }
        //商品件数
        tvCount.setText("共"+ sumCount +"件");
        //商品总价
        tvTotal.setText(sumTotal+"");

    }

    @Override
    protected void initview() {
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvUserTelphone = (TextView) findViewById(R.id.tvUserTelphone);
        tvUserAddress = (TextView) findViewById(R.id.tvUserAddress);
        btAddAddress = (Button) findViewById(R.id.btAddAddress);
        rvShopcarList = (RecyclerView) findViewById(R.id.rvShopcarList);
        btRefer = (Button) findViewById(R.id.btRefer);
        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvCount = (TextView) findViewById(R.id.tvCount);

        btAddAddress.setOnClickListener(this);
        btRefer.setOnClickListener(this);
        tvUserName.setText(UserManage.getInstance().quUsername()+"");

        //获取从购物车已选择的商品集合
        shopcarBeanList = CacheManager.getInstance().getselectproductList();
        orderAdapter = new PayAdapter();
        orderAdapter.updataData(shopcarBeanList);
        rvShopcarList.setAdapter(orderAdapter);
        rvShopcarList.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter.notifyDataSetChanged();
        //沙箱环境
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//设置沙箱环境.

    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_order;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btAddAddress) {

            final PopupWindow popupWindow = new PopupWindow();
            popupWindow.setHeight(350);
            popupWindow.setWidth(GridLayout.LayoutParams.MATCH_PARENT);

            View inflate = LayoutInflater.from(PayActivity.this).inflate(R.layout.item_popuwindow, null);
            final EditText etAddress = inflate.findViewById(R.id.etAddress);
            final EditText etTelphone = inflate.findViewById(R.id.etTelphone);
            Button btCommit = inflate.findViewById(R.id.btCommit);
            Button btSure = inflate.findViewById(R.id.btSure);
            Button btCancel = inflate.findViewById(R.id.btCancel);
            popupWindow.setContentView(inflate);
            popupWindow.setFocusable(true);
            View view = LayoutInflater.from(PayActivity.this).inflate(R.layout.activity_order, null);
            popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
            btCommit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    httpresenter.updateAddressData(etAddress.getText().toString());
                     Toast.makeText(PayActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                }
            });
            btSure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    httpresenter.updatePhoneData(etTelphone.getText().toString());
                    Toast.makeText(PayActivity.this, "添加地址成功", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                }
            });

            btCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(PayActivity.this, "取消添加", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                }
            });

        } else if (id == R.id.btRefer) {
            //第一步检查购物车上商品在仓库中是否都有库存
            httpresenter.checkInventory(CacheManager.getInstance().getSelectedProductBeanList());

        }
    }

    @Override
    public void updatePhone(String numPhone) {
        Log.e("---","手机号："+numPhone);
        tvUserTelphone.setText(numPhone);
    }

    @Override
    public void updateAddress(String address) {
        Log.e("---","地址："+address);
        tvUserAddress.setText(address);
    }

    @Override
    public void onInventory(List<InventoryBean> inventoryBean) {
        if (checkInventoryIsEnough(inventoryBean)){
            //充足情况下，向服务端下订单
            httpresenter.getOrderInfo(CacheManager.getInstance().getSelectedProductBeanList());
        }else {
            Toast.makeText(this, ""+inventoryBean.get(0).getProductName()+"库存不充足", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean checkInventoryIsEnough(List<InventoryBean> inventoryBeans) {
        List<ShopcarBean> shopcarBeanList = CacheManager.getInstance().getSelectedProductBeanList();
        for (InventoryBean inventoryBean:inventoryBeans) {
            for (ShopcarBean shopcarBean:shopcarBeanList){
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
    public void onOrderInfo(final OrderInfoBean orderInfoBean) {
        //服务端已经成功下订单
        //使用支付宝完成支付功能
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(PayActivity.this);
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
                    Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    //第一步将这些支付成功的商品，从购物车中删除
                    CacheManager.getInstance().removeSelectedProducts();

                    //发送消息
                    savePayMessage("支付成功");

                    //跳转的主页面，并且显示HomeFragment
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                    break;
                }
                case 2: {
                    Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    //将这些支付成功的商品，从购物车中删除
                    CacheManager.getInstance().removeSelectedProducts();
                    savePayMessage("支付失败");
                    //跳转的主页面，并且显示HomeFragment
                    ARouter.getInstance().build("/main/MainActivity").navigation();
                    break;
                }
                default:
                    break;
            }
        }
    };

    private void savePayMessage(String message) {
        final PayMessage payMessage = new PayMessage();
        payMessage.setTime(System.currentTimeMillis());
        payMessage.setBody(message);
        payMessage.setIsRead(false);
        payMessage.setTitle("支付消息");
        payMessage.setType(PayMessageManager.PAY_TYPE);
        Log.i("---",""+payMessage.getTitle()+"   "+payMessage.getBody());
        PayMessageManager.getInstance().addMessage(payMessage, new PayMessageManager.IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<PayMessage> payMessageList) {
                Toast.makeText(PayActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(payMessage);
            }
        });
    }

    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void showErroy(String message) {

    }

    @Override
    public void showEmpty() {

    }
}

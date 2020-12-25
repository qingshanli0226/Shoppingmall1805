package com.shopmall.bawei.shopmall1805.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.bawei.deom.BaseActivity;
import com.bawei.deom.CacheManager;
import com.bawei.deom.ShopUserManager;
import com.bawei.deom.bean.ReceivegoodsBeen;
import com.bawei.deom.selectedordelete.ShopcarContract;
import com.bawei.deom.selectedordelete.ShopcarPresenterImpl;
import com.shopmall.bawei.shopmall1805.MessageManager.MessageManager;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ShangTitle;
import com.shopmall.bawei.shopmall1805.apter.ThrorderApter;
import com.shopmall.bawei.shopmall1805.activity.home.MainActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import bean.ConfirmServerPayResultBean;
import bean.FindForPayBean;
import bean.GetOrderInfo;
import bean.InventoryBean;
import bean.Shoppingcartproducts;

public class TheorderActivity extends BaseActivity<ShopcarPresenterImpl, ShopcarContract.SelectedandDeletedCountrollerView> implements ShopcarContract.SelectedandDeletedCountrollerView {
    private TextView textName;
    private TextView textPhone;
    private TextView textFamily;
    private RecyclerView shangRecyle;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;

    ThrorderApter shopcarAdapter=new ThrorderApter();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_theorder;
    }

    @Override
    protected void inPresone() {
        prine=new ShopcarPresenterImpl();
    }

    @Override
    protected void inData() {
        List<Shoppingcartproducts.ResultBean> selectedProductBeanList = CacheManager.getInstance().getSelectedProductBeanList();
         shopcarAdapter.updataData(selectedProductBeanList);
         shangRecyle.setAdapter(shopcarAdapter);
         shangRecyle.setLayoutManager(new LinearLayoutManager(TheorderActivity.this));
          tvShopcartTotal.setText(CacheManager.getInstance().getMoneyValue());//设置金钱
        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               prine.checkInventory(CacheManager.getInstance().getSelectedProductBeanList());
            }
        });
    }

    @Override
    protected void intView() {
        textName = (TextView) findViewById(R.id.text_name);
        textPhone = (TextView) findViewById(R.id.text_phone);
        textFamily = (TextView) findViewById(R.id.text_family);
        shangRecyle = (RecyclerView) findViewById(R.id.shang_recyle);
        tvShopcartTotal = (TextView) findViewById(R.id.tv_shopcart_total);
        btnCheckOut = (Button) findViewById(R.id.btn_check_out);

        textName.setText(ShopUserManager.getInstance().getName());
        textPhone.setText(ShopUserManager.getInstance().getPhone());
        textFamily.setText(ShopUserManager.getInstance().getfamily());
        //沙箱环境
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//设置沙箱环境.
    }

    @Override
    public void onProductNumChange(String result, int position, String newNum) {

    }

    @Override
    public void onAllSelected(String request) {

    }

    @Override
    public void ononProductSelected(String result, int postion) {

    }

    @Override
    public void onDeleteProducts(String result) {

    }

    @Override
    public void onInventory(List<InventoryBean> inventoryBean) {
                if (checkInventoryIsEnough(inventoryBean)){
                       prine.getOrderInfo(CacheManager.getInstance().getSelectedProductBeanList());
                }else { ;
                    Toast.makeText(this, ""+inventoryBean.get(0).getProductName()+"库存不足", Toast.LENGTH_SHORT).show();
                }
    }

        private boolean checkInventoryIsEnough(List<InventoryBean> inventoryBeans) {
        List<Shoppingcartproducts.ResultBean> shopcarBeanList = CacheManager.getInstance().getSelectedProductBeanList();
        for (InventoryBean inventoryBean:inventoryBeans){
            for (Shoppingcartproducts.ResultBean shopcarBean:shopcarBeanList){
                if (inventoryBean.getProductId().equals(shopcarBean.getProductId())){
                    int inventoryNum=Integer.parseInt(inventoryBean.getProductNum());
                    int needNum=Integer.parseInt(inventoryBean.getProductNum());
                    if (needNum>inventoryNum){
                        return false;
                    }
                }
            }
        }


        return  true;
    }
    @Override
    public void onOrderInfo(final GetOrderInfo orderInfoBean) {
      Runnable runnable=new Runnable() {
          @Override
          public void run() {
              PayTask payTask=new PayTask(TheorderActivity.this);
              Map<String,String> resultMap=payTask.payV2(orderInfoBean.getOrderInfo(),true);
              if (resultMap.get("resultStatus").equals("9000")){
                  List<Shoppingcartproducts.ResultBean> selectedProductBeanList = CacheManager.getInstance().getSelectedProductBeanList();
                  CacheManager.getInstance().setDelivery(selectedProductBeanList.size()+"");
                   handler.sendEmptyMessage(1);
                   prine.confirmServerPayResult(orderInfoBean,true);


              }else {
                  List<Shoppingcartproducts.ResultBean> selectedProductBeanList = CacheManager.getInstance().getSelectedProductBeanList();
                  CacheManager.getInstance().setReceivegoods(selectedProductBeanList.size()+"");
                  handler.sendEmptyMessage(2);
                  prine.confirmServerPayResult(orderInfoBean,false);
              }
          }
      };
      Thread thread=new Thread(runnable);
      thread.start();
    }

    @Override
    public void onConfirmServerPayResult(ConfirmServerPayResultBean confirmServerPayResultBean) {
        Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFindForPay(List<FindForPayBean.ResultBean> list) {

    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1: {
                    //支付成功
                    Toast.makeText(TheorderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    savePayMessage("支付成功");
                    CacheManager.getInstance().removerSelectedProduct();//删除掉支付成功的商品
                    Intent intent = new Intent(TheorderActivity.this, MainActivity.class);
                    startActivity(intent);

                    break;
                }
                case 2: {
                    Toast.makeText(TheorderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    savePayMessage("支付失败");
                    CacheManager.getInstance().removerSelectedProduct();
                    Intent intent = new Intent(TheorderActivity.this, MainActivity.class);
                    startActivity(intent);
                         }
                    break;
            }
        }
    };

    private void savePayMessage(String message) {
        final ShangTitle shangTitle=new ShangTitle();
        shangTitle.setTime(System.currentTimeMillis());
        shangTitle.setBody(message);
        shangTitle.setIsRead(false);
        shangTitle.setTitle("支付消息");
        shangTitle.setType(MessageManager.PAY_TYPE);
        MessageManager.getInstance().addMessage(shangTitle, new MessageManager.IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<ShangTitle> shangTitles) {
                Toast.makeText(TheorderActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(shangTitle);
            }
        });
    }

    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }
}

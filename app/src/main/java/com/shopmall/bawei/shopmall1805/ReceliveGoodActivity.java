package com.shopmall.bawei.shopmall1805;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.bawei.deom.BaseActivity;
import com.bawei.deom.CacheManager;
import com.bawei.deom.selectedordelete.ShopcarContract;
import com.bawei.deom.selectedordelete.ShopcarPresenterImpl;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shopmall.bawei.shopmall1805.MessageManager.MessageManager;
import com.shopmall.bawei.shopmall1805.activity.home.MainActivity;
import com.shopmall.bawei.shopmall1805.apter.apter.ReceliveGoodApter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bean.ConfirmServerPayResultBean;
import bean.FindForPayBean;
import bean.GetOrderInfo;
import bean.InventoryBean;

public class ReceliveGoodActivity extends BaseActivity<ShopcarPresenterImpl, ShopcarContract.SelectedandDeletedCountrollerView> implements ShopcarContract.SelectedandDeletedCountrollerView {

    private RecyclerView recyle;
    ReceliveGoodApter receliveGoodApter;
    List<FindForPayBean.ResultBean> resultBeans=new ArrayList<>();
    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1: {
                    //支付成功
                    Toast.makeText(ReceliveGoodActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    savePayMessage("支付成功");
                    CacheManager.getInstance().removerSelectedProduct();//删除掉支付成功的商品
                    Intent intent = new Intent(ReceliveGoodActivity.this, MainActivity.class);
                    startActivity(intent);

                    break;
                }
                case 2: {
                    Toast.makeText(ReceliveGoodActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    savePayMessage("支付成功");
                    CacheManager.getInstance().removerSelectedProduct();
                    Intent intent = new Intent(ReceliveGoodActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                break;
            }
        }
    };
    @Override
    protected int getLayoutId() {
        return R.layout.activity_recelive_good;
    }

    @Override
    protected void inPresone() {
             prine=new ShopcarPresenterImpl();
    }

    @Override
    protected void inData() {
         prine.findForPay();

        receliveGoodApter=new ReceliveGoodApter(R.layout.iteam_recelivegood,resultBeans);
        recyle.setAdapter(receliveGoodApter);
        recyle.setLayoutManager(new LinearLayoutManager(this));
        receliveGoodApter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, final int position) {
                switch (view.getId()){
                    case R.id.recelivegoods_but:
                        Runnable runnable=new Runnable() {
                            @Override
                            public void run() {
                                PayTask payTask=new PayTask(ReceliveGoodActivity.this);
                                Map<String,String> resultMap=payTask.payV2(resultBeans.get(position).getOrderInfo()+"",true);
                                if (resultMap.get("resultStatus").equals("9000")){
                                    handler.sendEmptyMessage(1);
                                        GetOrderInfo getOrderInfo=new GetOrderInfo();
                                        getOrderInfo.setOrderInfo(resultBeans.get(position).getOrderInfo()+"");
                                    getOrderInfo.setOutTradeNo(resultBeans.get(position).getTradeNo()+"");
                                    prine.confirmServerPayResult(getOrderInfo,true);


                                }else {

                                    handler.sendEmptyMessage(2);
                                    GetOrderInfo getOrderInfo=new GetOrderInfo();
                                    getOrderInfo.setOrderInfo(resultBeans.get(position).getOrderInfo()+"");
                                    getOrderInfo.setOutTradeNo(resultBeans.get(position).getTradeNo()+"");
                                    prine.confirmServerPayResult(getOrderInfo,false);
                                }
                            }
                        };
                        Thread thread=new Thread(runnable);
                        thread.start();
                        break;
                }
            }
        });
    }

    @Override
    protected void intView() {

        recyle = (RecyclerView) findViewById(R.id.recyle);
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

    }

    @Override
    public void onOrderInfo(GetOrderInfo orderInfoBean) {

    }

    @Override
    public void onConfirmServerPayResult(ConfirmServerPayResultBean confirmServerPayResultBean) {

    }

    @Override
    public void onFindForPay(List<FindForPayBean.ResultBean> list) {
        Toast.makeText(this, ""+list.get(0).getBody(), Toast.LENGTH_SHORT).show();
           resultBeans.addAll(list);
           receliveGoodApter.notifyDataSetChanged();
    }

    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }
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
                Toast.makeText(ReceliveGoodActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(shangTitle);
            }
        });
    }
}

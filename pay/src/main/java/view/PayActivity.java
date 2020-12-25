package view;


import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.shopmall.bawei.pay.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import framework.BaseActivity;
import framework.CacheManagerc;
import framework.MessageMangerUlis;
import framework.ShopUserManager;
import framework.messagegreendao.ShopMessageGreenBean;
import view.loadinPage.ErrorBean;


@Route(path = SkipFinalUlis.PAY_ACTIVITY)
public class PayActivity extends BaseActivity implements ToolBar.IToolBarClickListner, View.OnClickListener{
    private TextView payText;
    private RecyclerView payRv;
    private TextView payMoney;
    private Button payButton;
    private PayAdper payAdper;
    private TextView textUserPhone;
    private TextView textUserAddress;
    private String orderInfo;

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void initData() {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        payText = (TextView) findViewById(R.id.payText);
        payRv = (RecyclerView) findViewById(R.id.payRv);
        payMoney = (TextView) findViewById(R.id.payMoney);
        payButton = (Button) findViewById(R.id.payButton);
        textUserPhone = (TextView) findViewById(R.id.textUserPhone);
        textUserAddress = (TextView) findViewById(R.id.textUserAddress);
        findViewById(R.id.payButton).setOnClickListener(this);
        tooBar = findViewById(R.id.tooBar);
        payRv.setLayoutManager(new LinearLayoutManager(this));

        payAdper = new PayAdper(R.layout.item_pay, CacheManagerc.getInstance().getShopcarBeansList());
        payRv.setAdapter(payAdper);
        payAdper.notifyDataSetChanged();
        payMoney.setText(CacheManagerc.getInstance().getOrderMoney()+"");
        if (ShopUserManager.getInstance().getLoginBean().getPhone()==null && ShopUserManager.getInstance().getLoginBean().getPoint()==null) {
            Toast.makeText(this, "手机号/地址未绑定", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(SkipFinalUlis.ORDER_ACTIVITY).navigation();
        }

        orderInfo = getIntent().getStringExtra("orderInfo");

    }
    @Override
    protected int getlayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.payButton) {//条件判断相反，更正
            new Thread(new Runnable() {
                @Override
                public void run() {
                        PayTask payTask = new PayTask(PayActivity.this);
                        Map<String,String> stringStringMap = payTask.payV2(orderInfo,true);
                        if (stringStringMap.get("resultStatus").equals("9000")){
                            handler.sendEmptyMessage(1);
                        }else {
                            handler.sendEmptyMessage(2);
                        }

                }
            }).start();
        }
    }
    int type;
    String tlite;
    String body;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String format = new SimpleDateFormat("yyyy-HH-dd : HH-mm-ss").format(new Date());
            String s = new Random().nextInt(5000) + "";
            ShopMessageGreenBean greenBean = null;

            switch (msg.what){
                case 1:
                    Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    CacheManagerc.getInstance().removeSelectedPrpducte();
                    type = 1;
                    tlite = "支付成功";
                    greenBean = new ShopMessageGreenBean(Long.parseLong(s), type, tlite, body, format, false, null, null);
                    break;
                case 2:
                    Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    type = 2;
                    CacheManagerc.getInstance().removeSelectedPrpducte();
                    tlite = "支付失败";
                    greenBean = new ShopMessageGreenBean(Long.parseLong(s), type, tlite, body, format, false, null, null);
                    break;
            }
            MessageMangerUlis.getInstance().addMessage(greenBean,null);

           // ARouter.getInstance().build(SkipFinalUlis.MAIN_ACTIVITY).navigation();
        }
    };
    @Override
    public void showLoaDing() {
        showLoaDing();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoading(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
        showEmpty();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
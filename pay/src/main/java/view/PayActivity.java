package view;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.pay.R;

import framework.BaseActivity;
import framework.CacheManagerc;
import framework.Contact;
import framework.ShopUserManager;
import view.loadinPage.ErrorBean;
import view.presenter.PayPresenter;

@Route(path = "/pay/PayActivity")
public class PayActivity extends BaseActivity<PayPresenter> implements ToolBar.IToolBarClickListner, View.OnClickListener, Contact.ICenterPayIview {
    private TextView payText;
    private RecyclerView payRv;
    private LinearLayout layoutPay;
    private TextView payMoney;
    private Button payButton;
    private PayAdper payAdper;
    @Override
    protected void createPresenter() {
        jsonPresenter = new PayPresenter(this);
    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void initData() {
        payText = (TextView) findViewById(R.id.payText);
        payRv = (RecyclerView) findViewById(R.id.payRv);
        layoutPay = (LinearLayout) findViewById(R.id.layoutPay);
        payMoney = (TextView) findViewById(R.id.payMoney);
        payButton = (Button) findViewById(R.id.payButton);
        findViewById(R.id.payButton).setOnClickListener(this);
        tooBar = findViewById(R.id.tooBar);
        payRv.setLayoutManager(new LinearLayoutManager(this));

        payAdper = new PayAdper(R.layout.item_paymessage, CacheManagerc.getInstance().getShopcarBeansList());
        payRv.setAdapter(payAdper);
        payAdper.notifyDataSetChanged();

        payMoney.setText(CacheManagerc.getInstance().getOrderMoney()+"");

        //jsonPresenter.getCheckInventory(CacheManagerc.getInstance().getShopcarBeansList());
        jsonPresenter.getGoPayOrder(CacheManagerc.getInstance().getShopcarBeansList());
        Log.i("====","获取购物车全选集合数据"+CacheManagerc.getInstance().getShopcarBeansList().toString());
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.payButton) {//条件判断相反，更正
            Toast.makeText(this, "点击支付按钮事件", Toast.LENGTH_SHORT).show();
            if (ShopUserManager.getInstance().getLoginBean().getResult().getPhone()==null && ShopUserManager.getInstance().getLoginBean().getResult().getPoint()==null) {
                Toast.makeText(this, "手机号/地址未绑定", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/path/BindingActivity").navigation();
            }else {
                Toast.makeText(this, "支付", Toast.LENGTH_SHORT).show();
            }
        }
    }

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
    //成功方法
    @Override
    public void onSuccess(String success) {

    }
    //失败方法
    @Override
    public void onEerror(String Error) {
        Log.i("====",""+Error);
    }
}
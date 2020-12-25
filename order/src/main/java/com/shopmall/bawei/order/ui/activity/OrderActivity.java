package com.shopmall.bawei.order.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseActivity;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.greendaobean.MessageBean;
import com.shopmall.bawei.framework.manager.GreendaoManager;
import com.shopmall.bawei.framework.manager.ShopCarmanager;
import com.shopmall.bawei.framework.manager.ShopUserManager;
import com.shopmall.bawei.framework.mvptest.presenter.ShopcarPresenter;
import com.shopmall.bawei.framework.notice.Notify;
import com.shopmall.bawei.order.R;
import com.shopmall.bawei.order.adapter.OrderAdapter;
import com.shopmall.bean.OrderBean;
import com.shopmall.bean.ShopcarBean;
import com.shopmall.restname.RestName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@Route(path = "/order/OrderActivity")
public class OrderActivity extends BaseActivity<ShopcarPresenter> implements Constant.ShopcarConstartView {
    private LinearLayout orderTitle;
    private TextView orderName;
    private TextView orderPhone;
    private TextView orderAddress;
    private TextView orderNum;
    private TextView orderMoney;
    private TextView orderDanNum;
    private TextView orderDanMoney;
    private Button orderSubmit;
    private RecyclerView orderRecycle;
    private OrderAdapter orderAdapter;
    private List<ShopcarBean.ResultBean> selectshopcarBeanList;
    private ImageView orderBack;
    @Override
    protected void oncreatePresenter() {
         mPresenter=new ShopcarPresenter(this);
    }

    @Override
    protected void initEnvent() {
        orderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  finish();
            }
        });


         orderSubmit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 List<ShopcarBean.ResultBean> selectshopcarBeanList = ShopCarmanager.getShopCarmanager().getSelectshopcarBeanList();
                 mPresenter.getOrderInfo(Constants.GETORDERINFO,selectshopcarBeanList);
             }
         });
    }

    @Override
    protected void initview() {

        ARouter.getInstance().inject(this);
        orderTitle = findViewById(R.id.order_title);
        orderName = findViewById(R.id.order_name);
        orderPhone = findViewById(R.id.order_phone);
        orderAddress = findViewById(R.id.order_address);
        orderNum = findViewById(R.id.order_num);
        orderMoney = findViewById(R.id.order_money);
        orderDanNum = findViewById(R.id.order_dan_num);
        orderDanMoney = findViewById(R.id.order_dan_money);
        orderSubmit = findViewById(R.id.order__submit);

        orderRecycle = findViewById(R.id.order_recycle);

        orderRecycle.setLayoutManager(new LinearLayoutManager(this));

        orderBack = findViewById(R.id.order_back);



    }

    @Override
    protected void initData() {
        orderName.setText(ShopUserManager.getInstance().getUserName());
        orderPhone.setText(ShopUserManager.getInstance().getphone());
        orderAddress.setText(ShopUserManager.getInstance().getaddress());

        selectshopcarBeanList = ShopCarmanager.getShopCarmanager().getSelectshopcarBeanList();
        orderNum.setText("共"+selectshopcarBeanList.size()+"件");
        orderDanNum.setText("共"+selectshopcarBeanList.size()+"件");
        orderMoney.setText("￥"+ ShopCarmanager.getShopCarmanager().getMoney());
        orderDanMoney.setText("￥"+ ShopCarmanager.getShopCarmanager().getMoney());
        RestName.money=ShopCarmanager.getShopCarmanager().getMoney();
        orderAdapter=new OrderAdapter();
        orderRecycle.setAdapter(orderAdapter);
        orderAdapter.updataData(selectshopcarBeanList);
    }

    private String getdate(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;


    }

    @Override
    protected int layoutid() {
        return R.layout.activity_order_main;
    }
   private OrderBean orderBean = null;
    @Override
    public void Success(Object... objects) {
        String msg=(String) objects[1];

        if (msg.equals("order")){
             orderBean=(OrderBean)objects[0];
             mPresenter.orderremoveManyProduct(Constants.REMOVEMANY_PRODUCT);
            Toast.makeText(this, "生成订单", Toast.LENGTH_SHORT).show();
        }else if (msg.equals("remove")){
            AlertDialog.Builder alert =new AlertDialog.Builder(OrderActivity.this);
            alert.setTitle("支付提示！");
            alert.setMessage("是否继续支付，前往购买结算此商品！！！");
            alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    RestName.OrderInfo=orderBean.getResult().getOrderInfo();//订单
                    RestName.OutTradeNo=orderBean.getResult().getOutTradeNo();//单号
                    ARouter.getInstance().build("/pay/PayMainActivity").navigation();
                    finish();
                }
            });
            alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(OrderActivity.this, "取消", Toast.LENGTH_SHORT).show();
                    MessageBean messageBean = new MessageBean();
                    messageBean.setTitle("支付提示！");
                    messageBean.setRead(false);
                    messageBean.setMsg("您有一比未支付信息的账单，请查看！");
                    messageBean.setDate(getdate());
                    messageBean.setUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1608091421071&di=e1d6c83c2c67972a86a4207bef44f88a&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Felement_pic%2F19%2F04%2F04%2F1c71da9c6caac2d9db76db9fff2600ed.jpg");
                    GreendaoManager.getInstance().insert(messageBean);
                    Notify.setnotify(OrderActivity.this,1,"您有一比未支付信息的账单，请查看！",R.mipmap.ic_launcher_round,"支付提示！");
                    finish();
                }
            });
            AlertDialog alertDialog=alert.create();
            alertDialog.setCanceledOnTouchOutside(false);
            alertDialog.show();
        }



    }

    @Override
    public void Error(String s) {

    }
}

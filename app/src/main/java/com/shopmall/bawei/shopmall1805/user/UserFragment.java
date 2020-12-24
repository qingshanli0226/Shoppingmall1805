package com.shopmall.bawei.shopmall1805.user;

import android.os.Handler;
import android.os.UserManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.framework.BaseFragment;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.bw.framework.OrderManager;
import com.bw.framework.ShopUserManager;
import com.bw.net.ForPayBean;
import com.bw.net.ForSendBean;
import com.bw.net.bean.LoginBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.user.contract.UserContract;
import com.shopmall.bawei.shopmall1805.user.presenter.UserPresenter;

import java.util.List;

@Route(path = "/fragment/userFragment")
public class UserFragment extends BaseFragment<IPresenter, IView>implements OrderManager.IOrderChangeListener, ShopUserManager.IUserLoginChangedListener {

    private ScrollView scrollview;
    private ImageButton ibUserIconAvator;
    private TextView tvUsername;
    private TextView tvAllOrder;
    private TextView tvUserPay;
    private TextView tvUserReceive;
    private TextView tvUserFinish;
    private TextView tvUserDrawback;
    private TextView tvUserLocation;
    private TextView tvUserCollect;
    private TextView tvUserCoupon;
    private TextView tvUserScore;
    private TextView tvUserPrize;
    private TextView tvUserTicket;
    private TextView tvUserInvitation;
    private TextView tvUserCallcenter;
    private TextView tvUserFeedback;
    private TextView tvUsercenter;
    private ImageButton ibUserSetting;
    private ImageButton ibUserMessage;

    private  Handler handler;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView(View view) {
        scrollview = (ScrollView) findViewById(R.id.scrollview);
        ibUserIconAvator = (ImageButton) findViewById(R.id.ib_user_icon_avator);
        tvUsername = (TextView) findViewById(R.id.tv_username);
        tvAllOrder = (TextView) findViewById(R.id.tv_all_order);
        tvUserPay = (TextView) findViewById(R.id.tv_user_pay);
        tvUserReceive = (TextView) findViewById(R.id.tv_user_receive);
        tvUserFinish = (TextView) findViewById(R.id.tv_user_finish);
        tvUserDrawback = (TextView) findViewById(R.id.tv_user_drawback);
        tvUserLocation = (TextView) findViewById(R.id.tv_user_location);
        tvUserCollect = (TextView) findViewById(R.id.tv_user_collect);
        tvUserCoupon = (TextView) findViewById(R.id.tv_user_coupon);
        tvUserScore = (TextView) findViewById(R.id.tv_user_score);
        tvUserPrize = (TextView) findViewById(R.id.tv_user_prize);
        tvUserTicket = (TextView) findViewById(R.id.tv_user_ticket);
        tvUserInvitation = (TextView) findViewById(R.id.tv_user_invitation);
        tvUserCallcenter = (TextView) findViewById(R.id.tv_user_callcenter);
        tvUserFeedback = (TextView) findViewById(R.id.tv_user_feedback);
        tvUsercenter = (TextView) findViewById(R.id.tv_usercenter);
        ibUserSetting = (ImageButton) findViewById(R.id.ib_user_setting);
        ibUserMessage = (ImageButton) findViewById(R.id.ib_user_message);
        handler = new Handler();

        ShopUserManager.getInstance().registerUserLoginChangedListener(this);
        OrderManager.getInstance().setOrderChangeListeners(this);


        Log.i("---", "initView: forSentBeanSize："+OrderManager.getInstance().getForSendBeanList().size());

        tvUserPay.setText("待付款"+OrderManager.getInstance().getForPayBeanList().size());
        tvUserReceive.setText("待收货"+OrderManager.getInstance().getForSendBeanList().size());

        if (ShopUserManager.getInstance().isUserLogin()){
            String name = ShopUserManager.getInstance().getName();
            tvUsername.setText(name);

        }else {
            ibUserIconAvator.setOnClickListener(v -> {
                ARouter.getInstance().build("/usr/LoginRegisterActivity").navigation();
            });
        }


        tvUserPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/order/AllOrderActivity").navigation();
            }
        });



    }

    @Override
    protected void initPresenter() {
        super.initPresenter();
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public void onRightClick() {

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        OrderManager.getInstance().unSetOrderChangeListeners(this);
        ShopUserManager.getInstance().unRegisterUserLoginChangedListener(this);
    }

    @Override
    public void onForPayChange(List<ForPayBean> forPayBeanList) {
        Log.e("---", "onForPayChange: "+forPayBeanList.size() );


       handler.post(new Runnable() {
            @Override
            public void run() {
                tvUserPay.setText("待付款"+forPayBeanList.size());
            }
        });

    }

    @Override
    public void onForSendChange(List<ForSendBean> forSendBeanList) {
        Log.e("---", "onForSendChange: "+forSendBeanList.size() );

        handler.post(new Runnable() {
            @Override
            public void run() {
                tvUserReceive.setText("待收货"+forSendBeanList.size());
            }
        });

    }

    @Override
    public void onUserLogin(LoginBean loginBean) {
        tvUsername.setText(loginBean.getName());
    }

    @Override
    public void onUserLoginOut() {

    }
}

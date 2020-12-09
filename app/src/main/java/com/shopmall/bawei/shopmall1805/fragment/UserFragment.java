package com.shopmall.bawei.shopmall1805.fragment;

import android.content.Context;
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
import com.bw.framework.ShopUserManager;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.ShopmallConstant;
import com.shopmall.bawei.shopmall1805.R;

@Route(path = "/fragment/userFragment")
public class UserFragment extends BaseFragment<IPresenter, IView> {

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


        ibUserIconAvator.setOnClickListener(v -> {
            boolean userLogin = ShopUserManager.getInstance().isUserLogin();
            Log.i("---", "initView: userLogin："+userLogin);
            if (userLogin == false){
                ARouter.getInstance().build("/usr/LoginRegisterActivity").navigation();
            }
        });

    }
}

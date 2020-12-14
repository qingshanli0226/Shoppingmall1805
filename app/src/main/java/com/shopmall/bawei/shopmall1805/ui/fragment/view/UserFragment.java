package com.shopmall.bawei.shopmall1805.ui.fragment.view;



import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseFragment;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.ShopUsermange;
import com.example.net.bean.LoginBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ui.activity.view.BangActivity;
import com.shopmall.bawei.shopmall1805.ui.fragment.contract.LogotContract;
import com.shopmall.bawei.shopmall1805.ui.fragment.presenter.LogotPresenter;


public class UserFragment extends BaseFragment<LogotPresenter, LogotContract.ILogotView> implements ShopUsermange.IUserLoginChangeLiestener,LogotContract.ILogotView, View.OnClickListener {

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
    protected void initPreseter() {
        httpresetnter = new LogotPresenter();
    }

    @Override
    protected void initView(View inflate) {
        ARouter.getInstance().inject(this);
        //初始化控件
        scrollview = inflate.findViewById(R.id.scrollview);
        ibUserIconAvator = inflate.findViewById(R.id.ib_user_icon_avator);
        tvUsername = inflate.findViewById(R.id.tv_username);
        tvAllOrder = inflate.findViewById(R.id.tv_all_order);
        tvUserPay = inflate.findViewById(R.id.tv_user_pay);
        tvUserReceive = inflate.findViewById(R.id.tv_user_receive);
        tvUserFinish = inflate.findViewById(R.id.tv_user_finish);
        tvUserDrawback = inflate.findViewById(R.id.tv_user_drawback);
        tvUserLocation = inflate.findViewById(R.id.tv_user_location);
        tvUserCollect = inflate.findViewById(R.id.tv_user_collect);
        tvUserCoupon = inflate.findViewById(R.id.tv_user_coupon);
        tvUserScore = inflate.findViewById(R.id.tv_user_score);
        tvUserPrize = inflate.findViewById(R.id.tv_user_prize);
        tvUserTicket = inflate.findViewById(R.id.tv_user_ticket);
        tvUserInvitation = inflate.findViewById(R.id.tv_user_invitation);
        tvUserCallcenter = inflate.findViewById(R.id.tv_user_callcenter);
        tvUserFeedback = inflate.findViewById(R.id.tv_user_feedback);
        tvUsercenter = inflate.findViewById(R.id.tv_usercenter);
        ibUserSetting = inflate.findViewById(R.id.ib_user_setting);
        ibUserMessage = inflate.findViewById(R.id.ib_user_message);

    }

    @Override
    protected void initdate() {
        //点击跳转到注册界面
        ibUserIconAvator.setOnClickListener(this);
        ibUserSetting.setOnClickListener(this);
        //点击跳转到绑定信息界面
        tvUserLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BangActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_user;
    }

    @Override
    public void onClick(View v) {
        if (v == ibUserIconAvator){
            ARouter.getInstance().build("/user/LoginRegisterActivity").navigation();
        }else if (v==ibUserSetting){//点击退出登录
            httpresetnter.logotUser();
        }
    }

    @Override
    public void onLogotSucess(String result) {
        Toast.makeText(getContext(), "退出登录成功", Toast.LENGTH_SHORT).show();
        ShopUsermange.getInstance().clearshopbean();
    }

    @Override
    public void onErroy(String message) {
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
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

    @Override
    public void onUserLogin(LoginBean loginBean) {
        tvUsername.setText(""+loginBean.getName() );
    }

    @Override
    public void onUserlogout() {

    }
}

package com.shopmall.bawei.shopmall1805.ui.fragment;



import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseFragment;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.shopmall.bawei.shopmall1805.R;


public class UserFragment extends BaseFragment<IPresenter, IView> implements View.OnClickListener {

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
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_user;
    }

    @Override
    public void onClick(View v) {
        if (v == ibUserIconAvator){
            ARouter.getInstance().build("/user/LoginRegisterActivity").navigation();
        }
    }
}

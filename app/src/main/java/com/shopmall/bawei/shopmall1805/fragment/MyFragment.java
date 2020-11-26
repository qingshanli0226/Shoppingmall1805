package com.shopmall.bawei.shopmall1805.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.LoginActivity;

public class MyFragment extends Fragment {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_my, container, false);
        scrollview = (ScrollView) inflate.findViewById(R.id.scrollview);
        ibUserIconAvator = (ImageButton) inflate.findViewById(R.id.ib_user_icon_avator);
        tvUsername = (TextView) inflate.findViewById(R.id.tv_username);
        tvAllOrder = (TextView) inflate.findViewById(R.id.tv_all_order);
        tvUserPay = (TextView) inflate.findViewById(R.id.tv_user_pay);
        tvUserReceive = (TextView) inflate.findViewById(R.id.tv_user_receive);
        tvUserFinish = (TextView) inflate.findViewById(R.id.tv_user_finish);
        tvUserDrawback = (TextView) inflate.findViewById(R.id.tv_user_drawback);
        tvUserLocation = (TextView) inflate.findViewById(R.id.tv_user_location);
        tvUserCollect = (TextView) inflate.findViewById(R.id.tv_user_collect);
        tvUserCoupon = (TextView) inflate.findViewById(R.id.tv_user_coupon);
        tvUserScore = (TextView) inflate.findViewById(R.id.tv_user_score);
        tvUserPrize = (TextView) inflate.findViewById(R.id.tv_user_prize);
        tvUserTicket = (TextView) inflate.findViewById(R.id.tv_user_ticket);
        tvUserInvitation = (TextView) inflate.findViewById(R.id.tv_user_invitation);
        tvUserCallcenter = (TextView) inflate.findViewById(R.id.tv_user_callcenter);
        tvUserFeedback = (TextView) inflate.findViewById(R.id.tv_user_feedback);
        tvUsercenter = (TextView) inflate.findViewById(R.id.tv_usercenter);
        ibUserSetting = (ImageButton) inflate.findViewById(R.id.ib_user_setting);
        ibUserMessage = (ImageButton) inflate.findViewById(R.id.ib_user_message);

        ibUserIconAvator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return inflate;
    }
}
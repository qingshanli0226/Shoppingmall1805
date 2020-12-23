package com.bawei.shopmall.user;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.NetConfig;
import com.bawei.framework.BaseFragment;
import com.bawei.framework.MessageManager;
import com.bawei.framework.ShopUserManager;
import com.bawei.framework.ShopUserManager.IUserLoginChangedListener;
import com.bawei.framework.greendao.MessageBean;
import com.bawei.net.mode.LoginBean;
import com.bawei.net.mode.RegisterBean;
import com.bawei.user.contact.UserContract;
import com.bawei.user.contact.UserContractImpl;
import com.shopmall.bawei.shopmall1805.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment<UserContractImpl, UserContract.IUserView> implements IUserLoginChangedListener, View.OnClickListener, UserContract.IUserView {

    private TextView tvUsername;
    private ImageView ibUserSetting;
    private ImageView ibUserIconAvator;
    private ImageView ibUserMessage;
    private TextView tvUserPay;
    private TextView tvUserSend;

    @Override
    protected int layoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);

        tvUsername = findViewById(R.id.tv_username);
        ibUserSetting = findViewById(R.id.ib_user_setting);

        if (ShopUserManager.getInstance().isUserLogin()) {
            tvUsername.setText(ShopUserManager.getInstance().getName());
        } else {
            ShopUserManager.getInstance().registerUserLoginChangeListener(this);
        }

        ibUserIconAvator = findViewById(R.id.ib_user_icon_avator);
        ibUserSetting = findViewById(R.id.ib_user_setting);
        ibUserMessage = findViewById(R.id.ib_user_message);
        tvUserPay = findViewById(R.id.tv_user_pay);
        tvUserSend = findViewById(R.id.tv_user_send);

        ibUserIconAvator.setOnClickListener(this);
        ibUserSetting.setOnClickListener(this);
        ibUserMessage.setOnClickListener(this);
        tvUserPay.setOnClickListener(this);
        tvUserSend.setOnClickListener(this);

        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageChanged(MessageBean messageBean) {
        int messageCount = MessageManager.getInstance().getMessageCount();
        if (messageCount != 0) {
            toolBar.setToolbarRightTv(messageCount + "");
        }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new UserContractImpl();
    }

    @Override
    public void onUserLogin(LoginBean loginBean) {
        if (ShopUserManager.getInstance().isUserLogin()) {
            tvUsername.setText(loginBean.getResult().getName());
        }
    }


    @Override
    public void onUserLogout() {
        if (!ShopUserManager.getInstance().isUserLogin()) {
            tvUsername.setText("登录/注册");
            Toast.makeText(getContext(), "退出成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ShopUserManager.getInstance().unRegisterUserLoginChangeListener(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_user_icon_avator:
                if (!ShopUserManager.getInstance().isUserLogin()) {
                    ARouter.getInstance().build(NetConfig.LOGIN_ACTIVITY_PATH).withInt(NetConfig.TO_LOGIN_KEY, 4).navigation();
                }
                break;
            case R.id.ib_user_setting:
                httpPresenter.logoutUser();
                ShopUserManager.getInstance().logoutUser();
                break;
            case R.id.ib_user_message:
                ARouter.getInstance().build("/message/MessageActivity").navigation();
                break;
            case R.id.tv_user_pay:
                ARouter.getInstance().build("/order/ForPayActivity").navigation();
                break;
            case R.id.tv_user_send:
                ARouter.getInstance().build("/order/ForSendActivity").navigation();
                break;
        }
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void login(LoginBean loginBean) {

    }

    @Override
    public void register(RegisterBean registerBean) {

    }

    @Override
    public void logout(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(NetConfig.tokenName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(NetConfig.tokenName);
        edit.commit();
    }

    @Override
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }
}

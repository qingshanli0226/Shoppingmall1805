package com.bawei.shopmall.user;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.NetConfig;
import com.bawei.framework.BaseFragment;
import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;
import com.bawei.framework.ShopUserManager;
import com.bawei.net.mode.LoginBean;
import com.shopmall.bawei.shopmall1805.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment<BasePresenter, IView> implements ShopUserManager.IUserLoginChangedListener, View.OnClickListener {

    private TextView tvUsername;
    private ImageView ibUserSetting;
    private ImageView ibUserIconAvator;

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

        ibUserIconAvator.setOnClickListener(this);
        ibUserSetting.setOnClickListener(this);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onUserLogin(LoginBean loginBean) {
        tvUsername.setText(loginBean.getResult().getName());
    }

    @Override
    public void onUserLogout() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ShopUserManager.getInstance().unRegisterUserLoginChangeListener(this);
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

                break;
        }
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}

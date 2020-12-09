package com.bawei.shopmall.user;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.NetConfig;
import com.bawei.framework.ShopUserManager;
import com.bawei.net.mode.LoginBean;
import com.shopmall.bawei.shopmall1805.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements ShopUserManager.IUserLoginChangedListener {

    private TextView tvUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ARouter.getInstance().inject(this);
        View inflate = inflater.inflate(R.layout.fragment_user, container, false);
        tvUsername = inflate.findViewById(R.id.tv_username);
        if (ShopUserManager.getInstance().isUserLogin()) {
            tvUsername.setText(ShopUserManager.getInstance().getName());
        } else {
            ShopUserManager.getInstance().registerUserLoginChangeListener(this);
        }
        inflate.findViewById(R.id.ib_user_icon_avator).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ShopUserManager.getInstance().isUserLogin()) {
                    ARouter.getInstance().build(NetConfig.LOGIN_ACTIVITY_PATH).withInt(NetConfig.TO_LOGIN_KEY, 4).navigation();
                }
            }
        });
        return inflate;
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
}

package com.shopmall.bawei.shopmall1805.home.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.framework.ShopUserManager;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.shopmall1805.R;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MineFragment extends Fragment implements ShopUserManager.IUserLoginChangedListener {
    TextView loginTv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("LQS", "onCreateView........");
        super.onCreateView(inflater, container, savedInstanceState);
        ARouter.getInstance().inject(this);
        View rootView = inflater.inflate(R.layout.fragment_mine, container, false);
        loginTv = rootView.findViewById(R.id.btnLogin);
        if (ShopUserManager.getInstance().isUserLogin()) {
            loginTv.setText(ShopUserManager.getInstance().getName());
        } else {
            ShopUserManager.getInstance().registerUserLoginChangeListener(this);
        }
        rootView.findViewById(R.id.btnLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ShopUserManager.getInstance().isUserLogin()) {
                    ARouter.getInstance().build(ShopmallConstant.LOGIN_ACTIVITY_PATH).withInt(ShopmallConstant.TO_LOGIN_KEY, ShopmallConstant.TO_LOGIN_FROM_MINE_FRAGMENT).navigation();
                }
            }
        });

        EventBus.getDefault().register(this);

        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onLogin(LoginBean loginBean) {
            Log.d("LQS", "onEventBus........");
    }

    @Override
    public void onUserLogin(LoginBean loginBean) {
        Log.d("LQS", "onUserLogin........");
        loginTv.setText(loginBean.getName());
    }

    @Override
    public void onUserLogout() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ShopUserManager.getInstance().unRegisterUserLoginChangeListener(this);
    }
}

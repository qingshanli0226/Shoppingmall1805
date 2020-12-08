package com.shopmall.bawei.shopmall1805.fragment;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.framework.base.BaseMVPFragment;
import com.shopmall.net.bean.LoginBean;
import com.shopmall.net.manager.ShopUserManager;

public class MyFragment extends BaseMVPFragment implements ShopUserManager.IUserLoginChangeListener {
    private ImageButton ibUserIconAvator;
    private TextView tvUsername;

    @Override
    protected void createViewid(View inflate) {
        ARouter.getInstance().inject(this);

        ibUserIconAvator = (ImageButton) inflate.findViewById(R.id.ib_user_icon_avator);
        tvUsername = (TextView) inflate.findViewById(R.id.tv_username);

        ShopUserManager.getInstance().registerUserLoginChangeListener(this);
    }

    @Override
    protected void createEnvent() {
        ibUserIconAvator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/user/UserActivity").navigation();
            }
        });
    }

    @Override
    protected void createData() {

    }

    @Override
    protected int fragmentid() {
        return R.layout.fragment_my;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    public void OnUserLogin(LoginBean loginBean) {
        tvUsername.setText(loginBean.getResult().getName());
        if (ShopUserManager.getInstance().getUserName()!=null){

        }
    }

    @Override
    public void OnUserLogout() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ShopUserManager.getInstance().unRegisterUserLoginChangeListener(this);
    }
}
package com.bawei.shopmall.user;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.framework.BaseFragment;
import com.bawei.framework.ShopUserManager;
import com.bawei.net.mode.LoginBean;
import com.bawei.shopmall.ARouterPath;
import com.shopmall.bawei.shopmall1805.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment implements View.OnClickListener, ShopUserManager.IUserLoginChangedListener {

    private View inflate;
    private ImageView ibUserIconAvator;
    private TextView tvUsername;

    @Override
    protected int layoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {
        ibUserIconAvator = (ImageView) findViewById(R.id.ib_user_icon_avator);
        tvUsername = (TextView) findViewById(R.id.tv_username);

        ibUserIconAvator.setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_user_icon_avator:
                ARouter.getInstance().inject(this);
                ARouter.getInstance().build(ARouterPath.LoginRegisterActivity).navigation();
                break;
        }
    }

    @Override
    public void onUserLogin(LoginBean loginBean) {
        tvUsername.setText(loginBean.getResult().getName());
    }

    @Override
    public void onUserLogout() {

    }
}

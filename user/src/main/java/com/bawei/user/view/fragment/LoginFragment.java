package com.bawei.user.view.fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.ErrorBean;
import com.bawei.common.view.MyToolBar;
import com.bawei.common.view.NetConfig;
import com.bawei.framework.BaseFragment;
import com.bawei.framework.ShopUserManager;
import com.bawei.net.mode.LoginBean;
import com.bawei.net.mode.RegisterBean;
import com.bawei.user.R;
import com.bawei.user.contact.UserContract;
import com.bawei.user.contact.UserContractImpl;
import com.bawei.user.view.LoginRegisterActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<UserContractImpl, UserContract.IUserView> implements View.OnClickListener, UserContract.IUserView {

    private MyToolBar toolbar;
    private EditText name;
    private EditText password;

    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.registerTv).setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new UserContractImpl();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginButton) {
            login();
        } else if (view.getId() == R.id.registerTv) {
            switchRegisterFragment();
        }
    }

    private void switchRegisterFragment() {
        LoginRegisterActivity loginRegisterActivity = (LoginRegisterActivity) getActivity();
        loginRegisterActivity.switchFragment(1, name.getText().toString().trim());
    }

    private void login() {
        httpPresenter.loginUser(name.getText().toString().trim(), password.getText().toString().trim());
    }

    @Override
    public void login(LoginBean loginBean) {
        Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
        ShopUserManager.getInstance().saveLoginBean(loginBean);
        EventBus.getDefault().post(loginBean);

        LoginRegisterActivity loginRegisterActivity = (LoginRegisterActivity) getActivity();
        int toLoginFromIndex = loginRegisterActivity.getToLoginFromIndex();
        if (toLoginFromIndex == NetConfig.TO_LOGIN_FROM_SHOPCAR_FRAGMTNT) {
            ARouter.getInstance().build("/main/MainActivity").withInt("index", 2).navigation();
        } else if (toLoginFromIndex == NetConfig.TO_LOGIN_FROM_MINE_FRAGMENT) {
            ARouter.getInstance().build("/main/MainActivity").withInt("index", 0).navigation();
        } else if (toLoginFromIndex == NetConfig.TO_LOGIN_FROM_GOODS_DETAIL_ADD_SHOPCAR) {
            getActivity().finish();
            return;
        } else {
            ARouter.getInstance().build("/shopcar/ShopcarActivity").navigation();
        }

        getActivity().finish();//是不是一定能回到MainActivity，这个不一定，因为，MainActivity有可能被系统回收.

    }

    @Override
    public void register(RegisterBean registerBean) {

    }

    @Override
    public void showLoaDing() {
        showloading();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess, errorBean);
    }

    @Override
    public void showEmpty() {
        showEmpty();
    }
}

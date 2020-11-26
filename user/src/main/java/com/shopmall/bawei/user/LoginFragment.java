package com.shopmall.bawei.user;

import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.framework.ShopUserManager;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.user.contract.LoginContract;
import com.shopmall.bawei.user.presenter.LoginPresneterImpl;

public class LoginFragment extends BaseFragment<LoginPresneterImpl, LoginContract.ILoginView>
implements LoginContract.ILoginView, View.OnClickListener,LoginRegisterActivity.INameInterface{
    private EditText nameEditText;
    private EditText passwordEditText;

    @Override
    protected void initView() {
        super.initView();
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.registerTv).setOnClickListener(this);
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {
        httpPresenter = new LoginPresneterImpl();
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
        LoginRegisterActivity loginRegisterActivity = (LoginRegisterActivity)getActivity();
        loginRegisterActivity.switchFragment(1, nameEditText.getText().toString().trim());//
    }

    private void login() {
        httpPresenter.login(nameEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        //登录成功
        //实现跳转到MainActivity，显示HomeFragment,Activity的启动模式问题.
        ShopUserManager.getInstance().saveLoginBean(loginBean);//把登录后的用户信息存储起来
        /*Intent intent = new Intent();
        intent.setAction("com.bawei.1801.HOME");//通过隐式方式启动主页面
        intent.putExtra("index", BottomBar.HOME_INDEX);
        startActivity(intent);*/

        LoginRegisterActivity loginRegisterActivity = (LoginRegisterActivity)getActivity();
        int toLoginFromIndex = loginRegisterActivity.getToLoginFromIndex();
        if (toLoginFromIndex == Constants.TO_LOGIN_FROM_SHOPCAR_FRAGMTNT) {
            ARouter.getInstance().build("/main/MainActivity");
        } else if (toLoginFromIndex == Constants.TO_LOGIN_FROM_MINE_FRAGMENT) {
            ARouter.getInstance().build("/main/MainActivity");
        } else if (toLoginFromIndex == Constants.TO_LOGIN_FROM_GOODS_DETAIL_ADD_SHOPCAR) {
            getActivity().finish();
            return;
        } else {
            ARouter.getInstance().build("/shopcar/ShopcarActivity").navigation();
        }

        getActivity().finish();//是不是一定能回到MainActivity，这个不一定，因为，MainActivity有可能被系统回收.
    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setName(String name) {
        nameEditText.setText(name);
        passwordEditText.setText("");
    }
}

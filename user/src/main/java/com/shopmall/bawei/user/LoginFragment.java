package com.shopmall.bawei.user;

import android.view.SurfaceHolder;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.framework.BaseMVPFragment;
import com.shopmall.bawei.framework.ShopUserManager;
import com.shopmall.bawei.framework.view.BottomBar;
import com.shopmall.bawei.net.mode.LoginBean;
import com.shopmall.bawei.user.contract.LoginContract;
import com.shopmall.bawei.user.presenter.LoginPresenterImpl;


public class LoginFragment extends BaseMVPFragment<LoginPresenterImpl, LoginContract.ILoginView>
        implements LoginContract.ILoginView, View.OnClickListener,LoginRegisterActivity.INameInterface {

    private EditText nameEditText;
    private EditText passwordEditText;

    @Override
    protected void initHttpData() {

    }

    @Override
    protected void initPresenter() {
        ihttpPresenter = new LoginPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        nameEditText = findViewById(R.id.name);
        passwordEditText = findViewById(R.id.password);
        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.registerTv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginButton) {
            login();
        } else if (v.getId() == R.id.registerTv) {
            switchRegisterFragment();
        }

    }

    private void switchRegisterFragment() {
        LoginRegisterActivity loginRegisterActivity = (LoginRegisterActivity)getActivity();
        loginRegisterActivity.switchFragment(1, nameEditText.getText().toString().trim());//
    }

    private void login() {
        ihttpPresenter.login(nameEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
    }

    @Override
    public void showLoaing() {
        showLoading();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {

    }


    @Override
    public void setName(String name) {
        nameEditText.setText(name);
        passwordEditText.setText("");
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        showMessage("登录成功");
        //实现跳转到MainActivity，显示HomeFragment,Activity的启动模式问题.
        ShopUserManager.getInstance().saveLoginBean(loginBean);//把登录后的用户信息存储起来
        /*Intent intent = new Intent();
        intent.setAction("com.bawei.1801.HOME");//通过隐式方式启动主页面
        intent.putExtra("index", BottomBar.HOME_INDEX);
        startActivity(intent);*/

        LoginRegisterActivity loginRegisterActivity = (LoginRegisterActivity)getActivity();
        int toLoginFromIndex = loginRegisterActivity.getToLoginFromIndex();
        if (toLoginFromIndex == ShopmallConstant.TO_LOGIN_FROM_SHOPCAR_FRAGMTNT) {
            ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.SHOPCAR_INDEX).navigation();
        } else if (toLoginFromIndex == ShopmallConstant.TO_LOGIN_FROM_MINE_FRAGMENT) {
            ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.HOME_INDEX).navigation();
        } else if (toLoginFromIndex == ShopmallConstant.TO_LOGIN_FROM_GOODS_DETAIL_ADD_SHOPCAR) {
            getActivity().finish();
            return;
        } else {
            ARouter.getInstance().build("/shopcar/ShopcarActivity").navigation();
        }

        getActivity().finish();//是不是一定能回到MainActivity，这个不一定，因为，MainActivity有可能被系统回收.
    }
}

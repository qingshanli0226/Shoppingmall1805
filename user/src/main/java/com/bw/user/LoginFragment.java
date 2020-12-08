package com.bw.user;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.framework.BaseFragment;
import com.bw.framework.ShopUserManager;
import com.bw.net.bean.LoginBean;
import com.bw.net.bean.ShopmallConstant;
import com.bw.user.contract.LoginContract;
import com.bw.user.presenter.LoginPresenterImpl;
import com.shopmall.bawei.user.R;

public class LoginFragment extends BaseFragment<LoginPresenterImpl, LoginContract.ILoginView> implements LoginContract.ILoginView, View.OnClickListener ,LoginRegisterActivity.INameInterface{

    private EditText nameEditText;
    private EditText passwordEditText;

    @Override
    protected void initPresenter() {
        httpPresenter = new LoginPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        nameEditText = view.findViewById(R.id.name);
        passwordEditText = view.findViewById(R.id.password);
        view.findViewById(R.id.loginButton).setOnClickListener(this);
        view.findViewById(R.id.registerTv).setOnClickListener(this);

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
        loginRegisterActivity.switchFragment(1);//
    }

    private void login() {
        httpPresenter.login(nameEditText.getText().toString().trim(), passwordEditText.getText().toString().trim());
    }

    @Override
    public void onLogin(LoginBean loginBean) {
        //实现跳转到MainActivity，显示HomeFragment,Activity的启动模式问题.
        ShopUserManager.getInstance().saveLoginBean(loginBean);//把登录后的用户信息存储起来
        Log.e("---","token"+loginBean.getResult().getToken());
        ARouter.getInstance().build("/fragment/userFragment").navigation();



        myToast("登录成功");
        getActivity().finish();//不一定回到 MainActivity，因为，MainActivity有可能被系统回收.
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void showsLoaing() {

    }

    @Override
    public void hidesLoading(boolean isSuccess) {

    }


    @Override
    public void showEmpty() {

    }

    @Override
    public void setName(String name) {
        nameEditText.setText(name+"");
        passwordEditText.setText("");
    }
}

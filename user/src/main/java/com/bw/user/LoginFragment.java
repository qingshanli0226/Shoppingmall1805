package com.bw.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.framework.BaseFragment;
import com.bw.framework.ShopUserManager;
import com.bw.net.bean.LoginBean;
import com.bw.user.contract.LoginContract;
import com.bw.user.presenter.LoginPresenterImpl;
import com.shopmall.bawei.user.R;

public class LoginFragment extends BaseFragment<LoginPresenterImpl, LoginContract.ILoginView> implements LoginContract.ILoginView, View.OnClickListener {

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





//    @Override
//    public void setName(String name) {
//        nameEditText.setText(name);
//        passwordEditText.setText("");
//    }

    @Override
    public void onLogin(LoginBean loginBean) {
        //实现跳转到MainActivity，显示HomeFragment,Activity的启动模式问题.
//        ShopUserManager.getInstance().saveLoginBean(loginBean);//把登录后的用户信息存储起来
        /*Intent intent = new Intent();
        intent.setAction("com.bawei.1801.HOME");//通过隐式方式启动主页面
        intent.putExtra("index", BottomBar.HOME_INDEX);
        startActivity(intent);*/
//
//        ShopUserManager.getInstance().init(getContext());
//        ShopUserManager.getInstance().saveLoginBean(loginBean);

       getContext().getSharedPreferences("1805A.xml", Context.MODE_PRIVATE)
               .edit()
               .putString("token",loginBean.getToken())
               .commit();


        LoginRegisterActivity loginRegisterActivity = (LoginRegisterActivity)getActivity();
        int toLoginFromIndex = loginRegisterActivity.getToLoginFromIndex();
        if (toLoginFromIndex == 0) {
//            ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.SHOPCAR_INDEX).navigation();
        } else if (toLoginFromIndex == 1) {
//            ARouter.getInstance().build("/main/MainActivity").withInt("index", BottomBar.HOME_INDEX).navigation();
        } else if (toLoginFromIndex ==2) {
            getActivity().finish();
            return;
        } else {

            ARouter.getInstance().build("/fragment/userFragment").navigation();
//            ARouter.getInstance().build("/activity/activity_shopCart").navigation();
        }

        myToast("登录成功");
        getActivity().finish();//是不是一定能回到MainActivity，这个不一定，因为，MainActivity有可能被系统回收.
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
}

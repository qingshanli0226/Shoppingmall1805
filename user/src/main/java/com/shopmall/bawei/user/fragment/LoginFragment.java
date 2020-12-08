package com.shopmall.bawei.user.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseFragment;
import com.example.net.ShopUserManger;
import com.example.net.LoginBean;
import com.shopmall.bawei.user.LoginRegisterActivity;
import com.shopmall.bawei.user.R;
import com.shopmall.bawei.user.contract.LoginContract;
import com.shopmall.bawei.user.presenter.LogPresenter;

public  class LoginFragment extends BaseFragment<LogPresenter, LoginContract.LoginView>implements LoginContract.LoginView {


    private ImageButton ibLoginBack;
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private ImageButton ibLoginVisible;
    private Button btnLogin;
    private TextView tvLoginRegister;
    private TextView tvLoginForgetPwd;
    private ImageButton ibWeiBo;
    private ImageButton ibQq;
    private ImageButton ibWeChat;


    protected void initView(View view) {
        ibLoginBack = (ImageButton) view.findViewById(R.id.ib_login_back);
        etLoginPhone = (EditText) view.findViewById(R.id.et_login_phone);
        etLoginPwd = (EditText) view.findViewById(R.id.et_login_pwd);
        ibLoginVisible = (ImageButton) view.findViewById(R.id.ib_login_visible);
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        tvLoginRegister = (TextView) view.findViewById(R.id.tv_login_register);
        tvLoginForgetPwd = (TextView) view.findViewById(R.id.tv_login_forget_pwd);
        ibWeiBo = (ImageButton) view.findViewById(R.id.ib_wei_bo);
        ibQq = (ImageButton) view.findViewById(R.id.ib_qq);
        ibWeChat = (ImageButton) view.findViewById(R.id.ib_we_chat);
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new LogPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initData() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = etLoginPhone.getText().toString();
                String pwd = etLoginPwd.getText().toString();
                httpPresenter.getUser(user,pwd);
            }
        });
        tvLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRegisterActivity.vrLoginRegister.setCurrentItem(1);
            }
        });
    }

    @Override
    public void getLoginCode(LoginBean loginBean) {

            ShopUserManger.getInstance()
                    .ShopLoginmange(loginBean);

            ARouter.getInstance()
                    .build("/aa/aaa")
                    .navigation();
        Log.i("wft", "getLoginCode: "+loginBean.getToken());
    }

    @Override
    public void onErroy(String message) {

    }

    @Override
    public void showsloading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void showErroy(String message) {

    }

    @Override
    public void showEmpty() {

    }
}
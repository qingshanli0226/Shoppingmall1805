package com.shopmall.user.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.user.R;
import com.shopmall.common.Constants;
import com.shopmall.framework.base.BaseMVPFragment;
import com.shopmall.framework.constart.Constart;
import com.shopmall.framework.mvptest.presenter.LoginPresenter;
import com.shopmall.net.bean.LoginBean;
import com.shopmall.framework.manager.ShopUserManager;

import java.util.HashMap;

public class UserLoginFragment extends BaseMVPFragment<LoginPresenter> implements Constart.LoginConstartView {
    private ImageButton ibLoginBack;
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private ImageButton ibLoginVisible;
    private Button btnLogin;
    private TextView tvLoginRegister;
    private TextView tvLoginForgetPwd;
    private ImageButton ibWeibo;
    private ImageButton ibQq;
    private ImageButton ibWechat;

    @Override
    protected void createViewid(View inflate) {
        ARouter.getInstance().inject(this);

        ibLoginBack = (ImageButton) inflate.findViewById(R.id.ib_login_back);
        etLoginPhone = (EditText) inflate.findViewById(R.id.et_login_phone);
        etLoginPwd = (EditText) inflate.findViewById(R.id.et_login_pwd);
        ibLoginVisible = (ImageButton) inflate.findViewById(R.id.ib_login_visible);
        btnLogin = (Button) inflate.findViewById(R.id.btn_login);
        tvLoginRegister = (TextView) inflate.findViewById(R.id.tv_login_register);
        tvLoginForgetPwd = (TextView) inflate.findViewById(R.id.tv_login_forget_pwd);
        ibWeibo = (ImageButton) inflate.findViewById(R.id.ib_weibo);
        ibQq = (ImageButton) inflate.findViewById(R.id.ib_qq);
        ibWechat = (ImageButton) inflate.findViewById(R.id.ib_wechat);

    }

    @Override
    protected void createEnvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                String phone = etLoginPhone.getText().toString();
                String pwd = etLoginPwd.getText().toString();
                map.put("name",phone);
                map.put("password",pwd);
                if (phone.isEmpty()||pwd.isEmpty()){
                    Toast.makeText(getContext(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.login(Constants.LOGIN_URL,map,loadingPage);
            }
        });
    }

    @Override
    protected void createData() {

    }

    @Override
    protected int fragmentid() {
        return R.layout.fragment_user_login;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    public void Success(Object... objects) {
        LoginBean loginBeans = (LoginBean) objects[0];

        if (loginBeans.getCode().equals("200")){
            ShopUserManager.getInstance().saveLoginBean(loginBeans);
            ARouter.getInstance().build("/app/MainActivity").navigation();
        }
    }

    @Override
    public void Error(String s) {

    }
}
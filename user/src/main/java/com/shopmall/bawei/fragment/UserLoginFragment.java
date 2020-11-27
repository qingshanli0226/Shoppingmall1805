package com.shopmall.bawei.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseFragment;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.mvptest.presenter.LoginPresenter;
import com.shopmall.bawei.user.R;

import java.util.HashMap;

public class UserLoginFragment extends BaseFragment<LoginPresenter> implements Constart.LoginConstartView {
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private Button btnLogin;
    private TextView tvLoginRegister;

    @Override
    protected void createViewid(View inflate) {


        etLoginPhone = inflate.findViewById(R.id.et_login_phone);
        etLoginPwd = inflate.findViewById(R.id.et_login_pwd);
        btnLogin =inflate.findViewById(R.id.btn_login);
        tvLoginRegister = inflate.findViewById(R.id.tv_login_register);

    }

    @Override
    protected void createEnvent() {
        /**
         * 点击登陆
         */
        btnLogin.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  HashMap<String,String> map=new HashMap<>();
                  String user = etLoginPhone.getText().toString();
                  String pwd = etLoginPwd.getText().toString();
                  map.put("name",user);
                  map.put("password",pwd);
                  if (user.isEmpty()||pwd.isEmpty()){
                      Toast.makeText(getContext(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                      return;
                  }

                  mPresenter.login(Constants.LOGIN_URL,map);

              }
          });

    }

    @Override
    protected void createData() {

    }

    @Override
    protected int fragmentid() {
        return R.layout.fragment_login;
    }

    @Override
    protected void createPresenter() {
         mPresenter=new LoginPresenter(this);
    }

    @Override
    public void Success(Object... objects) {

    }

    @Override
    public void Error(String s) {

    }
}

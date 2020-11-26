package com.shopmall.bawei.user.login;

import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framework.base.BaseActivity;
import com.example.framework.user.UserManager;
import com.example.net.bean.LoginBean;
import com.shopmall.bawei.user.R;
import com.shopmall.bawei.user.register.RegisterActivity;

public class LoginActivity extends BaseActivity<LoginPresenterImpl, LoginContract.LoginView> implements LoginContract.LoginView {
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
    private boolean flag=false;
    @Override
    protected void initPresenter() {
        presenter=new LoginPresenterImpl();
    }

    @Override
    protected void initListener() {
        super.initListener();
        ibLoginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ibLoginVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_invisible);
                    etLoginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_visible);
                    etLoginPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                flag=!flag;
            }
        });
        ibWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "本功能暂未开放,合作没谈拢,敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
        ibQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "本功能暂未开放,合作没谈拢,敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
        ibWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "本功能暂未开放,合作没谈拢,敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
        tvLoginForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "本功能暂未开放,重新注册一个吧,敬请期待", Toast.LENGTH_SHORT).show();
            }
        });
        tvLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etLoginPhone.getText().toString();
                String pwd = etLoginPwd.getText().toString();
                if(!name.equals("")&&name!=null&&pwd!=null&&!pwd.equals("")){
                    presenter.login(name,pwd);
                }else {
                    Toast.makeText(LoginActivity.this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ibLoginBack = (ImageButton) findViewById(R.id.ib_login_back);
        etLoginPhone = (EditText) findViewById(R.id.et_login_phone);
        etLoginPwd = (EditText) findViewById(R.id.et_login_pwd);
        ibLoginVisible = (ImageButton) findViewById(R.id.ib_login_visible);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvLoginRegister = (TextView) findViewById(R.id.tv_login_register);
        tvLoginForgetPwd = (TextView) findViewById(R.id.tv_login_forget_pwd);
        ibWeibo = (ImageButton) findViewById(R.id.ib_weibo);
        ibQq = (ImageButton) findViewById(R.id.ib_qq);
        ibWechat = (ImageButton) findViewById(R.id.ib_wechat);
    }

    @Override
    public void onOk(LoginBean bean) {
        String message = bean.getMessage();
        if(bean.getCode().equals("200")){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            LoginBean.ResultBean result = bean.getResult();
            UserManager.User user = new UserManager.User();
            user.setAddress(result.getAddress());
            user.setEmail(result.getEmail());
            user.setId(result.getId());
            user.setMoney(result.getMoney());
            user.setName(result.getName());
            user.setToken(result.getToken());
            user.setPhone(result.getPhone());
            user.setPoint(result.getPoint());
            user.setAvatar(result.getAvatar());
            UserManager.getInstance().bindUser(user);
            finish();
        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(String msg) {

    }
}
package com.shopmall.bawei.user.login;

import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.base.BaseActivity;
import com.example.framework.manager.UserManager;
import com.example.net.bean.LoginBean;
import com.shopmall.bawei.user.R;
import com.shoppmall.common.adapter.error.ErrorBean;

import java.io.Serializable;

@Route(path = "/user/LoginActivity")
public class LoginActivity extends BaseActivity<LoginPresenterImpl, LoginContract.LoginView> implements LoginContract.LoginView {
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
    private String key;
    private ProgressBar pbLogin;
    private  Intent intent;
    private String type;
    private Serializable extra;
    @Override
    protected void initPresenter() {
        presenter=new LoginPresenterImpl();
    }

    @Override
    protected void initListener() {
        super.initListener();

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
                aroutertoregister();
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
    public void onLeftClick() {
        super.onLeftClick();
        arouter();
    }

    private void aroutertoregister() {
        ARouter.getInstance().build("/user/RegisterActivity").withString("key",key).withSerializable("good",extra).withString("type",type).navigation();
        finish();
    }

    @Override
    protected void initData() {
       intent = getIntent();
        key = intent.getStringExtra("key");
        type=intent.getStringExtra("type");
        extra=intent.getSerializableExtra("good");
    }


    @Override
    protected int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {


        pbLogin = (ProgressBar) findViewById(R.id.pb_login);

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
            Log.i("Yoyo", "onOk: "+result.getToken());
            UserManager.getInstance().bindUser(result);
            arouter();
        }else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            etLoginPhone.setText("");
            etLoginPwd.setText("");
        }
    }

    private void arouter() {
        if(!UserManager.isLogin()&&!key.equals("detail")){
            key="0";
        }
        switch (key){
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
                ARouter.getInstance().build("/main/MainActivity").withString("position",key).navigation();
                break;
            case "detail":

                ARouter.getInstance().build("/detailpage/DetailActivity").withSerializable("good",extra).withString("type",type).navigation();
                break;
        }
    }


    @Override
    public void showloading() {
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        pbLogin.setVisibility(View.GONE);
        if(!isSuccess){
            Toast.makeText(this, errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showEmpty() {

    }
}
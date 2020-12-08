package com.shopmall.bawei.user.register;

import android.content.Intent;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.base.BaseActivity;
import com.example.net.bean.RegisterBean;
import com.shopmall.bawei.user.R;
import com.shoppmall.common.adapter.error.ErrorBean;

import java.io.Serializable;

@Route(path = "/user/RegisterActivity")
public class RegisterActivity extends BaseActivity<RegisterPresenterImpl, RegisterContract.RegisterView> implements RegisterContract.RegisterView {

    private EditText etRegisterPhone;
    private EditText etRegisterPwd;
    private ImageButton ibRegisterVisible;
    private EditText etRegisterRepwd;
    private ImageButton ibRegisterRevisible;
    private Button btnRegister;
    private String key;
    private boolean flag=false;
    private boolean reflag=false;
    private ProgressBar pbRegister;
    private String type;
    private Serializable extra;
    @Override
    protected void initListener() {
        super.initListener();

        ibRegisterRevisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(reflag){
                    ibRegisterRevisible.setBackgroundResource(R.drawable.new_password_drawable_invisible);
                    etRegisterRepwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    ibRegisterRevisible.setBackgroundResource(R.drawable.new_password_drawable_visible);
                    etRegisterRepwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                reflag=!reflag;
            }
        });
        ibRegisterVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag){
                    ibRegisterVisible.setBackgroundResource(R.drawable.new_password_drawable_invisible);
                    etRegisterPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    ibRegisterVisible.setBackgroundResource(R.drawable.new_password_drawable_visible);
                    etRegisterPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                flag=!flag;
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etRegisterPhone.getText().toString();
                String pwd = etRegisterPwd.getText().toString();
                String repwd = etRegisterRepwd.getText().toString();
                if(name!=null&&!name.equals("")&&pwd!=null&&!pwd.equals("")&&repwd!=null&&!repwd.equals("")){
                    if(pwd.equals(repwd)){
                        presenter.register(name,pwd);
                    }else {
                        Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        aroutertologin();
    }

    private void aroutertologin() {
        ARouter.getInstance().build("/user/LoginActivity").withString("key",key).withSerializable("good",extra).withString("type",type).navigation();
        finish();
    }

    @Override
    protected void initPresenter() {
        presenter=new RegisterPresenterImpl();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        type = intent.getStringExtra("type");
        extra = intent.getSerializableExtra("good");
    }
    @Override
    protected int getLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {


        pbRegister = (ProgressBar) findViewById(R.id.pb_register);

        etRegisterPhone = (EditText) findViewById(R.id.et_register_phone);
        etRegisterPwd = (EditText) findViewById(R.id.et_register_pwd);
        ibRegisterVisible = (ImageButton) findViewById(R.id.ib_register_visible);
        etRegisterRepwd = (EditText) findViewById(R.id.et_register_repwd);
        ibRegisterRevisible = (ImageButton) findViewById(R.id.ib_register_revisible);
        btnRegister = (Button) findViewById(R.id.btn_register);
    }

    @Override
    public void onOk(RegisterBean bean) {
        String result = bean.getResult();
        if(bean.getCode().equals("200")){
            Toast.makeText(this,result, Toast.LENGTH_SHORT).show();
            aroutertologin();
        }else {
            Toast.makeText(this,result, Toast.LENGTH_SHORT).show();
            etRegisterRepwd.setText("");
            etRegisterPwd.setText("");
            etRegisterPhone.setText("");
        }
    }

    @Override
    public void showloading() {
        pbRegister.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        pbRegister.setVisibility(View.GONE);
        if(!isSuccess){
            Toast.makeText(this, errorBean.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showEmpty() {

    }
}
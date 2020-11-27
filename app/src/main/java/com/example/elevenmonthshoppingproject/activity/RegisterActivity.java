package com.example.elevenmonthshoppingproject.activity;



import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.shop.ShopIView;
import com.example.elevenmonthshoppingproject.shop.ShopPresenterImp;
import com.example.net.BaseActivity;
import com.example.net.bean.LoginBean;
import com.example.net.bean.Recommonde;
import com.example.net.bean.RegisterBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends BaseActivity implements ShopIView.IShopView {

    private ShopPresenterImp shopPresenterImp;
    private EditText editName;
    private EditText editPass;
    private Button btnLogin;
    private RegisterBean registerBean;
    private String USERNAME_ZENG="^[A-Za-z0-9]{3,20}$";
    private String PASSWORD_ZENG="^[0-9]{3,20}$";
    Pattern pattern=Pattern.compile(USERNAME_ZENG);
    Pattern pattern1=Pattern.compile(PASSWORD_ZENG);


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void iniView() {
        editName = findViewById(R.id.edit_name);
        editPass = findViewById(R.id.edit_pass);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onregister(registerBean);
            }
        });



    }

    @Override
    protected void iniData() {
        shopPresenterImp=new ShopPresenterImp();
        shopPresenterImp.attatch(this);
    }

    @Override
    public void onShopview(Recommonde recommonde) {

    }

    @Override
    public void onregister(RegisterBean registerBean) {
        String username = editName.getText().toString().trim();
        String password = editPass.getText().toString().trim();
        if (username.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "用户名密码不可为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Matcher mtcusername = pattern.matcher(username);
        Matcher mtcpassword = pattern1.matcher(password);
        if (mtcusername.matches()){
            Toast.makeText(this, "用户名正确", Toast.LENGTH_SHORT).show();
            if (mtcpassword.matches()){
                shopPresenterImp.onregister(username,password);
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "用户名格式不对", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onlogin(LoginBean loginBean) {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("---","222");
        shopPresenterImp.detachview();
    }
}

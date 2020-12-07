package com.example.elevenmonthshoppingproject.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.service.LoginService;
import com.example.elevenmonthshoppingproject.shop.ShopIView;
import com.example.elevenmonthshoppingproject.shop.ShopPresenterImp;
import com.example.net.BaseActivity;
import com.example.net.bean.LoginBean;
import com.example.net.bean.Recommonde;
import com.example.net.bean.RegisterBean;
import com.example.user.ShopCarManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity implements ShopIView.IShopView,View.OnClickListener {
    private ShopPresenterImp shopPresenterImp;
    private EditText editName;
    private EditText editPass;
    private Button btnLogin;
    private LoginBean loginBean;
    private String USERNAME_ZENG="^[A-Za-z0-9]{3,20}$";
    private String PASSWORD_ZENG="^[0-9]{3,20}$";
    Pattern pattern = Pattern.compile(USERNAME_ZENG);
    Pattern pattern1 = Pattern.compile(PASSWORD_ZENG);
    private TextView txtJump;


    private Intent intent;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void iniView() {
        intent = new Intent(this, LoginService.class);
        startService(intent);


        txtJump = findViewById(R.id.txt_jump);
        editName = findViewById(R.id.edit_name);
        editPass = findViewById(R.id.edit_pass);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
        txtJump.setOnClickListener(this);
        //获取token值
        iniToken();
    }

    private void iniToken() {
        SharedPreferences gtlname = getSharedPreferences("gtlname", Context.MODE_PRIVATE);
        String token = gtlname.getString("token", "");
        Log.i("---","45"+token);

//        if (ShopCarManager.getInstance().isUserLogin()){
//            Toast.makeText(this, "自动登录成功"+token, Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }



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

    }

    @Override
    public void onlogin( LoginBean loginBean) {
        Toast.makeText(this, "登陆成功111", Toast.LENGTH_SHORT).show();
        Log.i("---",""+loginBean.getResult().getToken());
        getSharedPreferences("gtlname",MODE_PRIVATE).edit()
                .putString("token",loginBean.getResult().getToken()).commit();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);



    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
               String username = editName.getText().toString().trim();
        String password = editPass.getText().toString().trim();
        if (username.isEmpty()||password.isEmpty()){
            Toast.makeText(this, "用户名密码不可为空", Toast.LENGTH_SHORT).show();
           return;
        }
        Matcher matcheruser = pattern.matcher(username);
        Matcher matcherpass = pattern1.matcher(password);
        if (matcheruser.matches()){
           if (matcherpass.matches()){
               shopPresenterImp.onlogin(username,password);



               finish();
           }else {
               Toast.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
           }
        }else {
            Toast.makeText(this, "用户名格式不对", Toast.LENGTH_SHORT).show();
        }

                break;
            case R.id.txt_jump:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("---","111");
        shopPresenterImp.detachview();

            stopService(intent);

    }

}

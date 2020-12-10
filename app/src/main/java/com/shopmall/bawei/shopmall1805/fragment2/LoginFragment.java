package com.shopmall.bawei.shopmall1805.fragment2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.deom.BaseFragment;
import com.bawei.deom.Login;
import com.bawei.deom.ShopUserManager;
import com.bawei.deom.login.LoginCountroller;
import com.bawei.deom.login.LoginImpl;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.MainActivity;

import org.greenrobot.eventbus.EventBus;

import bean.LoginBean;
import bean.RegisterBean;

public class LoginFragment extends BaseFragment<LoginImpl,LoginCountroller.LoginView>implements LoginCountroller.LoginView {
    private EditText username;
    private EditText password;
    private Button loginbutton;
    private TextView goregister;



    @Override
    protected void inPrine() {
        prine=new LoginImpl();
        loadingPage.showSuccessView();
    }

    @Override
    protected void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.atguigu_logo);

        loginbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 prine.loginShow(username.getText().toString(),password.getText().toString());
             }
         });
         goregister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 EventBus.getDefault().post(new Login(1));
             }
         });
    }

    @Override
    protected void initView(View view) {
        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        loginbutton = (Button) view.findViewById(R.id.loginbutton);
        goregister = (TextView) view.findViewById(R.id.goregister);
    }

    @Override
    protected int getlayoutview() {
        return R.layout.loginfragment;
    }

    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }




    @Override
    public void onlogin(LoginBean loginBean) {

            Toast.makeText(getContext(), ""+loginBean.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("登录的token",loginBean.getResult().getToken()+"");
            getActivity().getSharedPreferences("login",Context.MODE_PRIVATE).edit().putString("login",loginBean.getResult().getToken()).commit();
            ShopUserManager.getInstance().saveLoginBean(loginBean);

        Intent intent=new Intent(getContext(), MainActivity.class);
            startActivity(intent);



    }

    @Override
    public void onregister(RegisterBean registerBean) {

    }
}

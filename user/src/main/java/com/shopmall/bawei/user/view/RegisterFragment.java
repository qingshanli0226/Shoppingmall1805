package com.shopmall.bawei.user.view;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framework.BaseFragment;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.InputType;
import com.example.net.bean.LoginBean;
import com.example.net.bean.RegisterBean;
import com.shopmall.bawei.user.LoginRegisterActivity;
import com.shopmall.bawei.user.R;
import com.shopmall.bawei.user.bean.LoginsBean;
import com.shopmall.bawei.user.contract.LoginContact;
import com.shopmall.bawei.user.contract.RegisterContact;
import com.shopmall.bawei.user.presenter.LoginPresenter;
import com.shopmall.bawei.user.presenter.RegisterPresenter;

import org.greenrobot.eventbus.EventBus;

public class RegisterFragment extends BaseFragment<RegisterPresenter, RegisterContact.LoginView> implements RegisterContact.LoginView, View.OnClickListener {
    private ImageButton ibLoginBack;
    private EditText etRegisterPhone;
    private EditText etRegisterPwd;
    private ImageButton ibLoginVisible;
    private Button btnRegister;
    private TextView tvRegisterLogin;
    private int count;


    @Override
    protected void initPreseter() {
        httpresetnter = new RegisterPresenter();
    }

    @Override
    protected void initView(View inflate) {
        ibLoginBack = inflate.findViewById(R.id.ib_login_back);
        etRegisterPhone = inflate.findViewById(R.id.et_register_phone);
        etRegisterPwd = inflate.findViewById(R.id.et_register_pwd);
        ibLoginVisible = inflate.findViewById(R.id.ib_login_visible);
        btnRegister =inflate. findViewById(R.id.btn_register);
        tvRegisterLogin = inflate.findViewById(R.id.tv_register_login);

    }

    @Override
    protected void initdate() {
        //点击事件
        btnRegister.setOnClickListener(this);
        //点击登录跳转登录界面
        tvRegisterLogin.setOnClickListener(this);
        ibLoginVisible.setOnClickListener(this);
    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_register;
    }


    @Override
    public void onregister(RegisterBean registerBean) {
        Toast.makeText(getContext(), ""+registerBean.getResult(), Toast.LENGTH_SHORT).show();
        //跳转登录界面
        int i = 0;
        EventBus.getDefault().postSticky(new LoginsBean(i));
    }

    @Override
    public void onErroy(String message) {
        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showsloading() {
        showloading();
    }

    @Override
    public void hideloading() {
        hideLoading();
    }

    @Override
    public void showErroy(String message) {
        showerror(message);
    }

    @Override
    public void showEmpty() {
        showEnpty();
    }

    @Override
    public void onClick(View v) {
        int i = 0;
        if (v ==tvRegisterLogin){
            EventBus.getDefault().postSticky(new LoginsBean(i));
        }else if (v == btnRegister){
            String name = etRegisterPhone.getText().toString();
            String password = etRegisterPwd.getText().toString();
            httpresetnter.getregister(name,password);
        }else if(v == ibLoginVisible){
            count++;
            if (count%2==0){
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_invisible);
                etRegisterPwd.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_visible);
                etRegisterPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        }
    }


}

package com.shopmall.bawei.shopmall1805.fragment2;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.deom.AutoRegitser;
import com.bawei.deom.BaseFragment;
import com.bawei.deom.login.LoginCountroller;
import com.bawei.deom.login.LoginImpl;
import com.shopmall.bawei.shopmall1805.R;

import org.greenrobot.eventbus.EventBus;

import bean.LoginBean;
import bean.RegisterBean;

public class RegisterFragment extends BaseFragment<LoginImpl, LoginCountroller.LoginView>implements LoginCountroller.LoginView {
    private EditText username;
    private EditText password;
    private Button registerbutton;



    @Override
    protected void inPrine() {
         prine=new LoginImpl();
    }

    @Override
    protected void initData() {
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prine.registerShow(username.getText().toString(),password.getText().toString());
            }
        });

    }

    @Override
    protected void initView(View view) {
        username = (EditText) view.findViewById(R.id.username);
        password = (EditText) view.findViewById(R.id.password);
        registerbutton = (Button) view.findViewById(R.id.registerbutton);
    }

    @Override
    protected int getlayoutview() {
        return R.layout.registerfragment;
    }

    @Override
    public void login(LoginBean loginBean) {

    }

    @Override
    public void register(RegisterBean registerBean) {
        Toast.makeText(getContext(), ""+registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        AutoRegitser autoRegitser=new AutoRegitser(0);
        EventBus.getDefault().post(autoRegitser);

    }

    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }

    @Override
    public void showLoadingPage2() {
        showLoadingPage();
    }

    @Override
    public void showErrorPage2(String errorMsg) {
        showErrorPage( errorMsg);
    }

    @Override
    public void showEmptyPage2() {
        showEmptyPage();
    }

    @Override
    public void showSuccessView2() {
        showSuccessView();
    }
}

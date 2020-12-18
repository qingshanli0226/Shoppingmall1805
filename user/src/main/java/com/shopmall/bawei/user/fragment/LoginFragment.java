package com.shopmall.bawei.user.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.net.bean.LoginBean;
import com.shopmall.bawei.framework.example.framework.BaseFragment;
import com.shopmall.bawei.framework.example.framework.InputType;
import com.shopmall.bawei.framework.example.framework.manager.UserManage;
import com.shopmall.bawei.user.LoginRegisterActivity;
import com.shopmall.bawei.user.R;
import com.shopmall.bawei.user.contract.LoginContact;
import com.shopmall.bawei.user.presenter.LoginPresenter;



public class LoginFragment extends BaseFragment<LoginPresenter, LoginContact.LoginView> implements LoginContact.LoginView,View.OnClickListener {
    private ImageButton ibLoginBack;
    private EditText etLoginPhone;
    private EditText etLoginPwd;
    private ImageButton ibLoginVisible;
    private Button btnLogin;
    private TextView tvLoginRegister;
    private TextView tvLoginForgetPwd;
    private ImageButton ibWeiBo;
    private ImageButton ibQq;
    private ImageButton ibWeChat;

    private int count;

    @Override
    protected void initPreseter() {
            httpresetnter = new LoginPresenter();
    }

    @Override
    protected void initView(View inflate) {
        //初始化控件
        ibLoginBack = inflate.findViewById(R.id.ib_login_back);
        etLoginPhone = inflate.findViewById(R.id.et_login_phone);
        etLoginPwd = inflate.findViewById(R.id.et_login_pwd);
        ibLoginVisible = inflate.findViewById(R.id.ib_login_visible);
        btnLogin = inflate.findViewById(R.id.btn_login);
        tvLoginRegister = inflate.findViewById(R.id.tv_login_register);
        tvLoginForgetPwd = inflate.findViewById(R.id.tv_login_forget_pwd);
        ibWeiBo = inflate.findViewById(R.id.ib_wei_bo);
        ibQq = inflate.findViewById(R.id.ib_qq);
        ibWeChat = inflate.findViewById(R.id.ib_we_chat);
    }

    @Override
    protected void initdate() {
        //点击注册跳转到注册界面
        tvLoginRegister.setOnClickListener(this);
        ibLoginVisible.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }
    @Override
    protected int getlayoutid() {
        return R.layout.fragment_login;
    }

    @Override
    public void onClick(View v) {
        if (v == tvLoginRegister){
            LoginRegisterActivity.loginRegister.setCurrentItem(1);
        }else if (v==ibLoginVisible){
            count++;
            if (count % 2 == 0) {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_invisible);
                etLoginPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else {
                ibLoginVisible.setBackgroundResource(R.drawable.new_password_drawable_visible);
                etLoginPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        }else if(v==btnLogin){
            String name = etLoginPhone.getText().toString();
            String password = etLoginPwd.getText().toString();
            httpresetnter.getlogin(name,password);
        }
    }

    @Override
    public void onlogin(LoginBean loginBean) {
        Toast.makeText(getContext(), "登陆成功", Toast.LENGTH_SHORT).show();
        //存储token值
        UserManage.getInstance().saveLoginBean(loginBean);

        ARouter.getInstance().build("/activity/MainActivity").navigation();
        getActivity().finish();

    }

    @Override
    public void onErroy(String message) {
        Toast.makeText(getContext(), "登陆失败", Toast.LENGTH_SHORT).show();
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
}

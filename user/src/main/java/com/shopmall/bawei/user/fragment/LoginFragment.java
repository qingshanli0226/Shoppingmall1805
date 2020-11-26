package com.shopmall.bawei.user.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shopmall.bawei.user.LoginRegisterActivity;
import com.shopmall.bawei.user.R;

public class LoginFragment extends Fragment {


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);

        tvLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRegisterActivity.vrLoginRegister.setCurrentItem(1);
            }
        });

        return view;
    }

    private void initView(View view) {
        ibLoginBack = (ImageButton) view.findViewById(R.id.ib_login_back);
        etLoginPhone = (EditText) view.findViewById(R.id.et_login_phone);
        etLoginPwd = (EditText) view.findViewById(R.id.et_login_pwd);
        ibLoginVisible = (ImageButton) view.findViewById(R.id.ib_login_visible);
        btnLogin = (Button) view.findViewById(R.id.btn_login);
        tvLoginRegister = (TextView) view.findViewById(R.id.tv_login_register);
        tvLoginForgetPwd = (TextView) view.findViewById(R.id.tv_login_forget_pwd);
        ibWeiBo = (ImageButton) view.findViewById(R.id.ib_wei_bo);
        ibQq = (ImageButton) view.findViewById(R.id.ib_qq);
        ibWeChat = (ImageButton) view.findViewById(R.id.ib_we_chat);
    }
}
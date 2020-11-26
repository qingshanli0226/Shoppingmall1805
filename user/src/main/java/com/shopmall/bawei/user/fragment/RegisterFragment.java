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

public class RegisterFragment extends Fragment {


    private ImageButton ibLoginBack;
    private EditText etRegisterPhone;
    private EditText etRegisterPwd;
    private ImageButton ibLoginVisible;
    private Button btnRegister;
    private TextView tvRegisterLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initView(view);

        tvRegisterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRegisterActivity.vrLoginRegister.setCurrentItem(0);
            }
        });

        return view;
    }

    private void initView(View view) {
        ibLoginBack = (ImageButton) view.findViewById(R.id.ib_login_back);
        etRegisterPhone = (EditText) view.findViewById(R.id.et_register_phone);
        etRegisterPwd = (EditText) view.findViewById(R.id.et_register_pwd);
        ibLoginVisible = (ImageButton) view.findViewById(R.id.ib_login_visible);
        btnRegister = (Button) view.findViewById(R.id.btn_register);
        tvRegisterLogin = (TextView) view.findViewById(R.id.tv_register_login);
    }
}
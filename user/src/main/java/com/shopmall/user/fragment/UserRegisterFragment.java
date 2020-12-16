package com.shopmall.user.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.shopmall.bawei.user.R;
import com.shopmall.common.Constants;
import com.shopmall.framework.base.BaseMVPFragment;
import com.shopmall.framework.contract.Contract;
import com.shopmall.framework.mvptest.presenter.RegisterPresenter;

import java.util.HashMap;

public class UserRegisterFragment extends BaseMVPFragment<RegisterPresenter> implements Contract.RegisterConstartView {
    private ImageButton ibRegistBack;
    private EditText etRegistPhone;
    private EditText etRegistPwd;
    private ImageButton ibLoginVisible;
    private Button btnRegist;

    @Override
    protected void createViewid(View inflate) {
        ibRegistBack = (ImageButton) inflate.findViewById(R.id.ib_regist_back);
        etRegistPhone = (EditText) inflate.findViewById(R.id.et_regist_phone);
        etRegistPwd = (EditText) inflate.findViewById(R.id.et_regist_pwd);
        ibLoginVisible = (ImageButton) inflate.findViewById(R.id.ib_login_visible);
        btnRegist = (Button) inflate.findViewById(R.id.btn_regist);
    }

    @Override
    protected void createEnvent() {
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> map = new HashMap<>();
                String phone = etRegistPhone.getText().toString();
                String pwd = etRegistPwd.getText().toString();
                map.put("name",phone);
                map.put("password",pwd);
                if (phone.isEmpty()||pwd.isEmpty()){
                    Toast.makeText(getContext(), "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.register(Constants.REGISTER_URL,map,loadingPage);
            }
        });
    }

    @Override
    protected void createData() {

    }

    @Override
    protected int fragmentid() {
        return R.layout.fragment_user_register;
    }

    @Override
    protected void createPresenter() {
        mPresenter = new RegisterPresenter(this);
    }

    @Override
    public void Success(Object... objects) {
        Toast.makeText(getContext(), ""+objects[0], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Error(String s) {
        Toast.makeText(getContext(), "Error:"+s, Toast.LENGTH_SHORT).show();
    }
}
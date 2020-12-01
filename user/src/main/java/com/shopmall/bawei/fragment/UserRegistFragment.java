package com.shopmall.bawei.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseFragment;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.mvptest.presenter.RegistPresenter;
import com.shopmall.bawei.user.R;

import java.util.HashMap;

public class UserRegistFragment extends BaseFragment<RegistPresenter> implements Constart.RegistConstartView {

    private EditText etRegistPhone;
    private EditText etRegistPwd;
    private Button btnRegist;
    @Override
    protected void createViewid(View inflate) {


        etRegistPhone = inflate.findViewById(R.id.et_regist_phone);
        etRegistPwd = inflate.findViewById(R.id.et_regist_pwd);
        btnRegist = inflate.findViewById(R.id.btn_regist);

    }

    @Override
    protected void createEnvent() {
         btnRegist.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 HashMap<String,String> map=new HashMap<>();


                 String user = etRegistPhone.getText().toString();
                 String pwd = etRegistPwd.getText().toString();
                 map.put("name",user);
                 map.put("password",pwd);
                 if (user.isEmpty()||pwd.isEmpty()){
                     Toast.makeText(getContext(), "账号或密码不能为空！！", Toast.LENGTH_SHORT).show();
                     return;
                 }
                mPresenter.regist(Constants.REGIST_URL,map,logingPage);
             }
         });
    }

    @Override
    protected void createData() {

    }

    @Override
    protected int fragmentid() {
        return R.layout.fragment_regist;
    }

    @Override
    protected void createPresenter() {
        mPresenter=new RegistPresenter(this);

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

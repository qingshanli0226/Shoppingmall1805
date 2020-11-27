package fragment;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shopmall.bawei.user.R;

import framework.BaseFragment;

public
class FragmentRegister extends BaseFragment {
    private EditText registerUser;
    private EditText registerPassword;
    private Button registerLoginButton;
    private TextView registerSumpLogin;
    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {
        registerSumpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void InitData() {
        registerUser = (EditText) findViewById(R.id.registerUser);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerLoginButton = (Button) findViewById(R.id.registerLoginButton);
        registerSumpLogin = (TextView) findViewById(R.id.registerSumpLogin);

    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_register;
    }
}

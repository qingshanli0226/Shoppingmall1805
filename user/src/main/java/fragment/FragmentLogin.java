package fragment;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shopmall.bawei.user.R;

import framework.BaseFragment;

public
class FragmentLogin extends BaseFragment {
    private EditText loginUser;
    private EditText loginPassword;
    private Button loginLoginButton;
    private TextView loginSumpResgin;
    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {
        loginSumpResgin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void InitData() {

        loginUser = (EditText) findViewById(R.id.loginUser);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginLoginButton = (Button) findViewById(R.id.loginLoginButton);
        loginSumpResgin = (TextView) findViewById(R.id.loginSumpResgin);

    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_login;
    }
}

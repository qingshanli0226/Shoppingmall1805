import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shopmall.bawei.user.R;

import framework.BaseActivity;

public
class logActivity extends BaseActivity {
    private EditText loginUser;
    private EditText loginPassword;
    private Button loginLoginButton;
    private TextView loginSumpResgin;

    @Override
    protected void OnClickListener() {

    }
    @Override
    protected void initData() {
        loginUser = (EditText) findViewById(R.id.loginUser);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginLoginButton = (Button) findViewById(R.id.loginLoginButton);
        loginSumpResgin = (TextView) findViewById(R.id.loginSumpResgin);
    }

    @Override
    protected int getlayoutId() {
        return R.layout.login_acitvity;
    }
}

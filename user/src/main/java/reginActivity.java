import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shopmall.bawei.user.R;

import framework.BaseActivity;

public
class reginActivity extends BaseActivity {
    private EditText registerUser;
    private EditText registerPassword;
    private Button registerLoginButton;
    private TextView registerSumpLogin;
    @Override
    protected void OnClickListener() {

    }
    @Override
    protected void initData() {

        registerUser = (EditText) findViewById(R.id.registerUser);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerLoginButton = (Button) findViewById(R.id.registerLoginButton);
        registerSumpLogin = (TextView) findViewById(R.id.registerSumpLogin);

    }

    @Override
    protected int getlayoutId() {
        return R.layout.reginactivity;
    }
}

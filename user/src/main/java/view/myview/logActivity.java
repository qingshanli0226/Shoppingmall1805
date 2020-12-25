package view.myview;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shopmall.bawei.user.R;

import framework.BaseActivity;
import framework.mvpc.JsonPresenter;
import view.ToolBar;
import view.loadinPage.ErrorBean;

public
class logActivity extends BaseActivity<JsonPresenter> implements ToolBar.IToolBarClickListner {
    private EditText loginUser;
    private EditText loginPassword;
    private Button loginLoginButton;
    private TextView loginSumpResgin;

    @Override
    protected void createPresenter() {

    }

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

    @Override
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}

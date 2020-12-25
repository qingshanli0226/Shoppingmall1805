package view.myview;


import android.view.View;
import android.widget.EditText;

import com.shopmall.bawei.user.R;

import framework.BaseFragment;
import framework.mvpc.JsonPresenter;
import mode.RegisterBean;
import view.ToolBar;
import view.myview.callbacklr.JsonDataCallBackLR;
import view.UserActivity;
import view.loadinPage.ErrorBean;

public
class FragmentRegister extends BaseFragment<JsonPresenter> implements UserActivity.INameInterface,View.OnClickListener,ToolBar.IToolBarClickListner {
    private EditText registerUser;
    private EditText registerPassword;

    @Override
    protected void createPresenter() {
        presenter = new JsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {
    }

    @Override
    protected void InitData() {
        registerUser = (EditText) findViewById(R.id.registerUser);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        findViewById(R.id.registerSumpLogin).setOnClickListener(this);
        findViewById(R.id.registerLoginButton).setOnClickListener(this);
        tooBar = (ToolBar) findViewById(R.id.tooBar);
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_register;
    }

    //注册后直接展示登录账号
    @Override
    public void setName(String name) {

    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.registerLoginButton){
            login();

        }else {
            switchLoginFragment();
        }
    }
    //登录
    private void login() {
        presenter.loginAndRegister(1,registerUser.getText().toString().trim(),registerPassword.getText().toString().trim(),new JsonDataCallBackLR(){
            @Override
            public void registerBean(RegisterBean registerBean) {
                if (200 ==Integer.parseInt(registerBean.getCode())){
                    UserActivity.userViewPager.setCurrentItem(0);
                }
            }

            @Override
            public void Error(String error) {

            }
        });
    }

    private void switchLoginFragment() {
        UserActivity.userViewPager.setCurrentItem(0);
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

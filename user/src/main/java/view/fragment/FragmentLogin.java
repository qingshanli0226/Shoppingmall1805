package view.fragment;


import android.view.View;
import android.widget.EditText;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.user.R;

import framework.BaseFragment;
import framework.ShopUserManager;
import framework.mvpc.JsonPresenter;
import mode.LoginBean;
import view.ShopmallConstant;
import view.ToolBar;
import view.UserActivity;
import view.fragment.callbacklr.JsonDataCallBackLR;
import view.loadinPage.ErrorBean;

public
class FragmentLogin extends BaseFragment  implements UserActivity.INameInterface,View.OnClickListener,ToolBar.IToolBarClickListner {
    private EditText loginUser;
    private EditText loginPassword;

    @Override
    protected void createPresenter() {
        presenter = new JsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void InitData() {

        loginUser = (EditText) findViewById(R.id.loginUser);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        findViewById(R.id.loginLoginButton).setOnClickListener(this);
        findViewById(R.id.loginSumpResgin).setOnClickListener(this);
        tooBar = (ToolBar) findViewById(R.id.tooBar);
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.loginLoginButton){
            presenter.loginAndRegister(2,loginUser.getText().toString().trim(),loginPassword.getText().toString().trim(),new JsonDataCallBackLR(){
                @Override
                public void loginBean(LoginBean loginBean) {
                    setToast("====","登录成功");
                    ShopUserManager.getInstance().saveLoginBean(loginBean);//把登录后的用户信息存储起来

                    UserActivity loginRegisterActivity = (UserActivity)getActivity();
                    int toLoginFromIndex = loginRegisterActivity.getToLoginFromIndex();
                    if (toLoginFromIndex == ShopmallConstant.TO_LOGIN_FROM_SHOPCAR_FRAGMTNT) {
                        ARouter.getInstance().build("/main/MainActivity").withInt("index", ShopmallConstant.BUTTON_LOGIN_INDEX1).navigation();
                    } else if (toLoginFromIndex == ShopmallConstant.TO_LOGIN_FROM_MINE_FRAGMENT) {
                        ARouter.getInstance().build("/main/MainActivity").withInt("index", ShopmallConstant.BUTTON_LOGIN_INDEX2).navigation();
                    }else {
                        ARouter.getInstance().build("/shopcar/ShopcarActivity").navigation();
                    }
                }
                @Override
                public void Error(String error) {
                    super.Error(error);
                }
            });
        }else {
            switchRegisterFragment();
        }
    }

    private void switchRegisterFragment() {
        UserActivity.userViewPager.setCurrentItem(1);
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

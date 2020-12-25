package view.myview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shopmall.bawei.user.R;

import framework.BaseActivity;
import framework.Contact;
import view.ToolBar;
import view.loadinPage.ErrorBean;
import view.presenter.UserPresenter;

public class MainActivity extends BaseActivity<UserPresenter> implements ToolBar.IToolBarClickListner, Contact.ICenterUserIview {

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getlayoutId() {
        return R.layout.login_acitvity;
    }

    @Override
    public void onUnpaidSuccess() {

    }

    @Override
    public void onError(String Error) {

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
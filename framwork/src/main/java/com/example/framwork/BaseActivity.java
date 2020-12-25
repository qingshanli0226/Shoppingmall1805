package com.example.framwork;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framwork.view.LoginPage;
import com.example.framwork.view.ToolBar;
import com.example.net.bean.ErrorBean;

public abstract class BaseActivity extends AppCompatActivity implements ToolBar.IToolBarClickListner {

    protected ToolBar toolbar;
    private LoginPage loadingPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏是白底黑色
        /*getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);*/
        loadingPage = new LoginPage(this) {
            @Override
            protected int getsuccessId() {
                return getLayoutId();
            }
        };
        setContentView(getLayoutId());

        initView();
        toolbar = findViewById(R.id.toolbar);//在这里实例化toolbar
//        toolbar.setToolBarClickListner(this);
    }

    //子类需要实现的抽象方法
    protected abstract void initView();
    protected abstract int getLayoutId();
    protected void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void launchActivity(Class launcActivityClass, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle == null) {
            bundle = new Bundle();
        }
        intent.putExtras(bundle);
        intent.setClass(this, launcActivityClass);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    protected void destroy() {

    }


    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    public void showLoading() {
        loadingPage.loadingPage();
    }
    public void hideLoadingPage(boolean isSuccess, ErrorBean errorBean) {
        if (isSuccess) {
            showSuccess();
        } else {
            showError(errorBean.getErrorMessage());
        }
    }
    public void showError(String errorMsg) {
        loadingPage.showError(errorMsg);
    }
    public void showSuccess() {
        loadingPage.showsuccessPage();
    }

    public void showEmptyPage() {
        loadingPage.showEnptyPage();
    }




  }

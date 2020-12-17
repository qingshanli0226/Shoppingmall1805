package com.bw.framework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bw.framework.view.LoadingPage;
import com.bw.framework.view.ToolBar;
import com.shopmall.bawei.framework.R;

public abstract class BaseActivity<P extends IPresenter,V extends IView> extends AppCompatActivity implements ToolBar.IToolBarClickListner{

    protected P httpPresenter;
    protected ToolBar toolbar;
    protected LoadingPage loadingPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        loadingPage = new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        setContentView(getLayoutId());
        initView();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setToolBarClickListner(this);

        initPresenter();
        initData();
        if (httpPresenter != null){
            httpPresenter.accatchView((V) this);
        }



    }


    protected void initData() {

    }

    protected void initPresenter() {
    }

    protected void initView() {
    }

    protected abstract int getLayoutId();

    private void myToast(String message){
        Toast.makeText(this,message+"",Toast.LENGTH_SHORT).show();
    }

    public void showLoading() {
        loadingPage.showLoadingPage();
    }
    public void hideLoadingPage(boolean isSuccess) {
        if (isSuccess) {
            showSuccess();
        } else {
            showError("正在加载。。。。。。。");
        }
    }
    public void showError(String errorMsg) {
        loadingPage.showErrorPage(errorMsg);
    }
    public void showSuccess() {
        loadingPage.showSuccessView();
    }

    public void showEmptyPage() {
        loadingPage.showEmptyPage();
    }

}

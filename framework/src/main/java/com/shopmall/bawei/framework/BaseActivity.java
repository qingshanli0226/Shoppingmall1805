package com.shopmall.bawei.framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.view.LoadingPage;
import com.shopmall.bawei.framework.view.ToolBar;
/*
import com.lqs.kuaishou.kuaishou1801.AdrActivity;
import com.lqs.kuaishou.kuaishou1801.cache.CacheManager;*/

//定义Activity的基类，在里面定义抽象方法，抽象方法按照一定时序调用。并且在基类中定义方法，让子类复用
public abstract class BaseActivity extends AppCompatActivity implements ToolBar.IToolBarClickListner {

    private String TAG;
    protected ToolBar toolbar;
    private LoadingPage loadingPage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏是白底黑色
        /*getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);*/
        loadingPage = new LoadingPage(this) {
            @Override
            protected int getSuccessLayoutId() {
                return getLayoutId();
            }
        };
        setContentView(getLayoutId());

        initView();
        toolbar = findViewById(R.id.toolbar);//在这里实例化toolbar
        toolbar.setToolBarClickListner(this);
        TAG = "LQS:" + getClass().getSimpleName();
        create();
    }

    protected void create() {
        printLog("oncreate..................");
    }

    @Override
    protected void onStart() {
        super.onStart();
        printLog("onStart..................");
    }

    @Override
    protected void onStop() {
        super.onStop();
        printLog("onStop..................");
    }

    //子类需要实现的抽象方法
    protected abstract void initView();
    protected abstract int getLayoutId();

    protected void printLog(String message) {
        Log.d(TAG, message);
    }

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
        printLog("onDestroy..............");
    }

    protected void destroy() {

    }


    @Override
    protected void onPause() {
        super.onPause();
        pause();
    }

    public void pause() {
        printLog("onPause..................");
/*
        CacheManager.getInstance().saveAdrTime(System.currentTimeMillis());
*/
    }


    @Override
    protected void onResume() {
        super.onResume();
        resume();
    }

    public void resume() {
       /* long pauseTime = CacheManager.getInstance().getAdrTime();
        if (System.currentTimeMillis() - pauseTime > 5000) {
            //弹出广告页
            launchActivity(AdrActivity.class, null);
        }*/
        printLog("onResume..................");
    }


    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public void onRightClick() {

    }

    public void showLoading() {
        loadingPage.showLoadingPage();
    }
    public void hideLoadingPage(boolean isSuccess, ErrorBean errorBean) {
        if (isSuccess) {
            showSuccess();
        } else {
            showError(errorBean.getErrorMessage());
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

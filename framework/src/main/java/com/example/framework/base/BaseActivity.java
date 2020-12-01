package com.example.framework.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.framework.mvp.IPresenter;
import com.example.framework.mvp.IView;
import com.example.framework.view.ToolBar;
import com.shopmall.bawei.framework.R;


public abstract class BaseActivity<T extends IPresenter, V extends IView> extends AppCompatActivity implements ToolBar.IToolBarClickListner{
    protected T presenter;
    protected ToolBar toolBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        initPresenter();
        toolBar = findViewById(R.id.toolbar);//在这里实例化toolbar
        toolBar.setToolBarClickListner(this);
        initView();

        initData();
        initListener();
        if(presenter!=null){
            presenter.attchView((V)this);
        }
    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onLeftClick() {

    }

    protected abstract void initPresenter();

    protected  void initListener(){

    }

    protected abstract void initData();

    protected abstract int getLayoutID();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ondestroy();
    }

    protected void ondestroy(){
        if(presenter!=null){
            presenter.detachView();
        }
    }
}

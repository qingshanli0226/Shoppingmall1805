package com.shopmall.bawei.framework.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopmall.bawei.framework.logingpage.LogingPage;
import com.shopmall.bawei.framework.mvp.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public abstract class BaseFragment<P extends Presenter> extends Fragment {

         protected P mPresenter;
         protected LogingPage logingPage;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
        EventBus.getDefault().register(this);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // View inflate = inflater.inflate(fragmentid(), null);

         logingPage=new LogingPage(getContext()) {
             @Override
             protected int getsuccessId() {
                 return fragmentid();
             }
         };
        return logingPage;

    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createViewid(logingPage);
        createData();
        createEnvent();
    }

    /**
     * 获取控件id
     * @param inflate
     */
    protected abstract void createViewid(View inflate);
    /**
     * 初始化事件
     */
    protected abstract void createEnvent();

    /**
     * 初始化数据
     */
    protected abstract void createData();

    /**
     * 获取fragment布局id
     * @return
     */
    protected abstract int fragmentid();


    /**
     * 创建P 层
     */
    protected abstract void createPresenter();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getconnect(Boolean connect){
         if (connect){
             connected();
         }else {
             disconnect();
         }
    }
    //未连接
    public void connected() {
        logingPage.showsucessPage();

    }
     //已连接
    public void disconnect() {
        logingPage.showError("网络未连接");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

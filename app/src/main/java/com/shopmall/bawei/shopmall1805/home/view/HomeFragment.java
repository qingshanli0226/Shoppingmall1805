package com.shopmall.bawei.shopmall1805.home.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseFragment;
import com.example.framework.NetConnectManager;
import com.example.framework.dao.ShopcarMessage;
import com.example.framework.view.ToolBar;
import com.example.framework.view.manager.MessageManager;
import com.example.net.bean.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adpter.PrimereAdpter;
import com.shopmall.bawei.shopmall1805.bean.Messagebean;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.HomePresenter;
import com.shopmall.bawei.shopmall1805.message.MessageActivity;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class HomeFragment extends BaseFragment<HomePresenter, HomeContract.SkerakView> implements MessageActivity.IMessageLister, HomeContract.SkerakView,ToolBar.IToolBarClickListenter {
    private RecyclerView rv;
    private PrimereAdpter primereAdpter;
    private ToolBar toolbar;

    @Override
    protected void initPreseter() {
        httpresetnter= new HomePresenter();
        //注册点击事件
        toolbar.setiToolBarClickListenter(this);
    }

    @Override
    protected void initstart() {
        super.initstart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView(View inflate) {
        toolbar = inflate.findViewById(R.id.toolbar);
        rv = inflate.findViewById(R.id.rv);
        primereAdpter = new PrimereAdpter();
        rv.setAdapter(primereAdpter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    @Override
    protected void initdate() {
        if (NetConnectManager.getInstance().getisconnect()){
            httpresetnter.getskerak();
            logingPage.showsucessPage();
        }else {
            logingPage.showError("请检查网络状态");
        }
        //刷新消息个数
        int messageCount = MessageManager.getInstance().getMessageCount();
        if (messageCount!=0){
            toolbar.setToolBarRightTv("消息:"+messageCount);
        }


    }

    @Override
    public void onConnected() {
        super.onConnected();
        httpresetnter.getskerak();
        logingPage.showsucessPage();
    }

    @Override
    public void onDeConnected() {
        super.onDeConnected();
        logingPage.showError("请检查网络状态");
    }

    //接受EvenBus发货来的数据
    @Subscribe
    public void onMessageChanged(ShopcarMessage shopcarMessage){
        int messageCount = MessageManager.getInstance().getMessageCount();
        if (messageCount!=0){
            toolbar.setToolBarRightTv("消息"+messageCount);
        }
    }
    @Override
    protected int getlayoutid() {
        return R.layout.fragment_home;
    }


    @Override
    public void onErroy(String message) {
        Toast.makeText(getContext(), "消息:"+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showsloading() {
        showloading();
    }


    @Override
    public void hideloading() {
        hideLoading();
    }

    @Override
    public void showErroy(String message) {
        showerror(message);
    }

    @Override
    public void showEmpty() {
        showEnpty();
    }

    @Override
    public void onskerk(HomeBean homeBeanList) {
        primereAdpter.addOneData(homeBeanList.getBanner_info());
        primereAdpter.addOneData(homeBeanList.getChannel_info());
        primereAdpter.addOneData(homeBeanList.getAct_info());
        primereAdpter.addOneData(homeBeanList.getSeckill_info().getList());
        primereAdpter.addOneData(homeBeanList.getRecommend_info());
        primereAdpter.addOneData(homeBeanList.getHot_info());
    }

    @Override
    public void onLeftClick() {

    }
    //点击跳转到消息页面
    @Override
    public void onRightClick() {
        Intent intent = new Intent(getContext(), MessageActivity.class);
        startActivity(intent);
    }


    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onMessageCount(int size) {
        Toast.makeText(getContext(), ""+size, Toast.LENGTH_SHORT).show();
    }
}

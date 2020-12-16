package com.shopmall.bawei.shopmall1805.home.view;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.framework.BaseFragment;
import com.example.framework.dao.ShopcarMessage;
import com.example.framework.view.manager.MessageManager;
import com.example.net.bean.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adpter.PrimereAdpter;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.HomePresenter;
import com.shopmall.bawei.shopmall1805.message.MessageActivity;

import org.greenrobot.eventbus.Subscribe;
public class HomeFragment extends BaseFragment<HomePresenter, HomeContract.SkerakView> implements HomeContract.SkerakView {
    private RecyclerView rv;
    private PrimereAdpter primereAdpter;
    private EditText ed;
    private TextView ivMessage;

    @Override
    protected void initPreseter() {
        httpresetnter= new HomePresenter();
    }

    @Override
    protected void initView(View inflate) {
        ivMessage = inflate.findViewById(R.id.iv_message);
        ed = inflate.findViewById(R.id.ed);
        rv = inflate.findViewById(R.id.rv);
        primereAdpter = new PrimereAdpter();
        rv.setAdapter(primereAdpter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    protected void initdate() {
        httpresetnter.getskerak();
        int messageCount = MessageManager.getInstance().getMessageCount();
        if (messageCount!=0){
            ivMessage.setText("消息:"+messageCount);
        }
        //点击跳转到消息页面
        ivMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MessageActivity.class);
                startActivity(intent);
            }
        });
    }
    //接受EvenBus发货来的数据
    @Subscribe
    public void onMessageChanged(ShopcarMessage shopcarMessage){
        int messageCount = MessageManager.getInstance().getMessageCount();
        if (messageCount!=0){
            ivMessage.setText(""+messageCount);
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
}

package com.shopmall.bawei.shopmall1805.message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.dao.ShopcarMessage;
import com.example.framework.view.manager.MessageManager;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class MessageActivity extends BaseActivity<IPresenter, IView> {

    private RecyclerView rvMessage;
    private MessageAdpter messageAdpter;

    @Override
    protected void initpreseter() {

    }

    @Override
    protected void initdate() {
        MessageManager.getInstance().quereMessage(new MessageManager.IMessageListenter() {
            @Override
            public void onresult(boolean issucess, List<ShopcarMessage> shopcarMessages) {
                messageAdpter.updataData(shopcarMessages);
            }
        });
    }

    @Override
    protected void initview() {
        //初始化控件
        rvMessage = findViewById(R.id.rv_message);
        messageAdpter = new MessageAdpter();
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
        rvMessage.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvMessage.setAdapter(messageAdpter);
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_message;
    }
}

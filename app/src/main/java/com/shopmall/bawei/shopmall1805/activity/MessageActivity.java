package com.shopmall.bawei.shopmall1805.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.bw.framework.BaseActivity;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.bw.framework.MessageManager;
import com.bw.framework.dao.ShopcarMessage;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

public class MessageActivity extends BaseActivity<IPresenter, IView> {

    private RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView = findViewById(R.id.messageRv);
        messageAdapter = new MessageAdapter();
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        super.initData();
        MessageManager.getInstance().queryMessage(new MessageManager.IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<ShopcarMessage> shopcarMessageList) {
                messageAdapter.updataData(shopcarMessageList);
            }
        });
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}

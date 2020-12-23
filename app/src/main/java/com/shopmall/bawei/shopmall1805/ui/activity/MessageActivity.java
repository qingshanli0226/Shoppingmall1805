package com.shopmall.bawei.shopmall1805.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.framework.MessageManager;
import com.example.framework.ShopcarMessage;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.MessageAdapter;

import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView messageRec;
    private MessageAdapter messageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();

        MessageManager.getInstance().queryMessage(new MessageManager.IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<ShopcarMessage> shopcarMessageList) {
                messageAdapter = new MessageAdapter(R.layout.message_item,shopcarMessageList);
                messageRec.setAdapter(messageAdapter);
                messageRec.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
            }
        });
    }

    private void initView() {
        messageRec = (RecyclerView) findViewById(R.id.message_rec);
    }
}
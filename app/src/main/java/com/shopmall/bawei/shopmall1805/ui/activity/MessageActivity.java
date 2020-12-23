package com.shopmall.bawei.shopmall1805.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
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
            public void onResult(boolean isSuccess, final List<ShopcarMessage> shopcarMessageList) {
                messageAdapter = new MessageAdapter(R.layout.message_item,shopcarMessageList);
                messageRec.setAdapter(messageAdapter);
                messageRec.setLayoutManager(new LinearLayoutManager(MessageActivity.this));

                messageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        ShopcarMessage shopcarMessage = shopcarMessageList.get(position);
                        shopcarMessage.setIsRead(true);
                        MessageManager.getInstance().updateMessage(shopcarMessage, new MessageManager.IMessageListener() {
                            @Override
                            public void onResult(boolean isSuccess, List<ShopcarMessage> shopcarMessageList) {
                                Toast.makeText(MessageActivity.this, "已读", Toast.LENGTH_SHORT).show();
                            }
                        });
                        messageAdapter.notifyItemChanged(position);
                    }
                });

                messageAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, final int position) {
                        ShopcarMessage shopcarMessage = shopcarMessageList.get(position);
                        MessageManager.getInstance().deleteMessage(shopcarMessage, new MessageManager.IMessageListener() {
                            @Override
                            public void onResult(boolean isSuccess, List<ShopcarMessage> shopcarMessageList) {
                                Toast.makeText(MessageActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                        messageAdapter.notifyItemRemoved(position);
                        return true;
                    }
                });

            }
        });


    }

    private void initView() {
        messageRec = (RecyclerView) findViewById(R.id.message_rec);
    }
}
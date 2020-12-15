package com.shopmall.bawei.shopmall1805.mesage;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawei.deom.BaseActivity;
import com.shopmall.bawei.shopmall1805.MessageManager.MessageManager;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ShangTitle;
import com.shopmall.bawei.shopmall1805.apter.MessageApter;

import java.util.List;

public class MessageActivity extends BaseActivity {
    private RecyclerView messageRecyle;
    MessageApter messageApter=new MessageApter();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void inPresone() {

    }

    @Override
    protected void inData() {
        MessageManager.getInstance().querMessage(new MessageManager.IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<ShangTitle> shangTitles) {
                messageApter.updataData(shangTitles);
            }
        });
        messageRecyle.setAdapter(messageApter);
        messageRecyle.setLayoutManager(new LinearLayoutManager(MessageActivity.this));
    }

    @Override
    protected void intView() {
        messageRecyle = (RecyclerView) findViewById(R.id.message_recyle);

    }
}

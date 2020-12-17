package com.shopmall.bawei.shopmall1805.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.shopmall.bawei.framework.base.BaseActivity;
import com.shopmall.bawei.framework.greendaobean.MessageBean;
import com.shopmall.bawei.framework.manager.GreendaoManager;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.MessageAdapter;

import java.util.List;

public class HoneMessageActivity extends BaseActivity {
    private ImageView messageBack;
    private RecyclerView messageRecycle;
    private MessageAdapter messageAdapter;
    @Override
    protected void oncreatePresenter() {

    }

    @Override
    protected void initEnvent() {
         messageBack.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
    }

    @Override
    protected void initview() {


        messageBack = findViewById(R.id.message_back);
        messageRecycle = findViewById(R.id.message_recycle);
        messageRecycle.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void initData() {
        messageAdapter=new MessageAdapter();
        messageRecycle.setAdapter(messageAdapter);
        List<MessageBean> selectall = GreendaoManager.getInstance().selectall();
        Toast.makeText(this, ""+selectall.size(), Toast.LENGTH_SHORT).show();
        messageAdapter.updataData(selectall);
    }

    @Override
    protected int layoutid() {
        return R.layout.activity_message_home;
    }
}

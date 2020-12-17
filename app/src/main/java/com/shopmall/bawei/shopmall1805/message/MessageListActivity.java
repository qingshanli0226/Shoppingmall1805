package com.shopmall.bawei.shopmall1805.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.framework.BaseMVPActivity;
import com.shopmall.bawei.framework.IPresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.framework.dao.ShopcarMessage;
import com.shopmall.bawei.framework.view.manager.MessageManager;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

@Route(path = "/message/MessageListActivity")
public class MessageListActivity extends BaseMVPActivity<IPresenter,IView> {
    private MessageAdapter messageAdapter;
    private Thread thread;
    private boolean flag = true;
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {
        MessageManager.getInstance().queryMessage(new MessageManager.IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<ShopcarMessage> shopcarMessageList) {
                messageAdapter.updataData(shopcarMessageList);
            }
        });
    }

    @Override
    protected void initView() {
        RecyclerView messageRv = findViewById(R.id.messageRV);
        messageRv.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter();
        messageRv.setAdapter(messageAdapter);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!thread.isInterrupted()) {
                    Log.d("LQS", " messageActivity run.......");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        Log.d("LQS", " 睡眠时中断了异常 run.......");
                        e.printStackTrace();
                        break;
                    }
                }
            }
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.start();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_list;
    }

    @Override
    protected void destroy() {
        super.destroy();
        Log.d("LQS", "stop.....");

        thread.interrupt();
    }
}

package com.shopmall.bawei.shopmall1805.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.framework.example.framework.BaseActivity;
import com.shopmall.bawei.framework.example.framework.IPresenter;
import com.shopmall.bawei.framework.example.framework.IView;
import com.shopmall.bawei.framework.example.framework.dao.PayMessage;
import com.shopmall.bawei.framework.example.framework.manager.PayMessageManager;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

@Route(path = "/message/MessageActivity")
public class MessageActivity extends BaseActivity<IPresenter, IView> {

    private RecyclerView rvMessage;
    private MessageAdapter messageAdapter;

    @Override
    protected void initpreseter() {

    }

    @Override
    protected void initdate() {

    }

    @Override
    protected void initview() {



        ARouter.getInstance().inject(this);
        rvMessage = (RecyclerView) findViewById(R.id.rvMessage);
        messageAdapter = new MessageAdapter();

        PayMessageManager.getInstance().queryMessage(new PayMessageManager.IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<PayMessage> payMessageList) {
                messageAdapter.updataData(payMessageList);
            }
        });

        rvMessage.setAdapter(messageAdapter);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter.notifyDataSetChanged();

    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_message;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

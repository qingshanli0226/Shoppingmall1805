package com.bawei.shopmall.message;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bawei.framework.BaseActivity;
import com.bawei.framework.IPresenter;
import com.bawei.framework.IView;
import com.bawei.framework.MessageManager;
import com.bawei.framework.greendao.MessageBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

@Route(path = "/message/MessageActivity")
public class MessageActivity extends BaseActivity<IPresenter, IView> {

    private MessageAdapter messageAdapter;
    private RecyclerView messageRecycler;

    @Override
    protected int layoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initData() {
        MessageManager.getInstance().queryMessage(new MessageManager.IMessageListener() {
            @Override
            public void onResult(boolean isSuccess, List<MessageBean> messageBeanList) {
                messageAdapter.updateData(messageBeanList);
            }
        });
    }

    @Override
    protected void initView() {
        messageRecycler = findViewById(R.id.message_recycler);
        messageRecycler.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter();
        messageRecycler.setAdapter(messageAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }
}

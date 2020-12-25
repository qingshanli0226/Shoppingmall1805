package com.shopmall.bawei.shopmall1805.message;

import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.base.BaseActivity;
import com.example.framework.greendao.MessageBean;
import com.example.framework.manager.MSGManager;
import com.shopmall.bawei.shopmall1805.R;
import com.shoppmall.common.adapter.adapter.BaseRvAdapter;

import java.util.List;

@Route(path = "/message/MessageActivity")
public class MessageActivity extends BaseActivity {
    private RecyclerView rvMessage;
    private MessageAdapter adapter;
    private List<MessageBean> messageBeans;
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initListener() {
        super.initListener();
        adapter.setRecyclerViewListener(new BaseRvAdapter.IBaseRecyclerViewListener() {
            @Override
            public void onItemClick(int position) {
                MessageBean messageBean = messageBeans.get(position);
                if(messageBean.getIsNew()){
                    messageBean.setIsNew(false);
                    MSGManager.getInstance().updateMessage(messageBean, new MSGManager.IMessageListener() {
                        @Override
                        public void onResult(boolean isSuccess, List<MessageBean> messageBeanList) {
                            if(isSuccess){
                                Toast.makeText(MessageActivity.this, "确认消息", Toast.LENGTH_SHORT).show();
                                adapter.updataData(messageBeans);
                            }
                        }
                    });
                }else {
                    Toast.makeText(MessageActivity.this, "已确认消息", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public Boolean onItemLongClick(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MessageActivity.this);
                builder.setMessage("删除该消息");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MSGManager.getInstance().deleteMessage(messageBeans.get(position), new MSGManager.IMessageListener() {
                            @Override
                            public void onResult(boolean isSuccess, List<MessageBean> messageBeanList) {
                                adapter.updataData(messageBeans);
                            }
                        });
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        messageBeans = MSGManager.getInstance().getMessageBeanList();
        adapter.updataData(messageBeans);
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
        ARouter.getInstance().build("/main/MainActivity").navigation();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        rvMessage = (RecyclerView) findViewById(R.id.rv_message);
        rvMessage.setLayoutManager(new LinearLayoutManager(this));
        adapter=new MessageAdapter();
        rvMessage.setAdapter(adapter);
    }

}
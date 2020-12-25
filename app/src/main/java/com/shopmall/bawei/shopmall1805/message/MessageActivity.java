package com.shopmall.bawei.shopmall1805.message;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.example.framework.BaseRVAdapter;
import com.example.framework.CacheManager;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.dao.ShopcarMessage;
import com.example.framework.view.manager.MessageManager;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.bean.Messagebean;
import com.shopmall.bawei.shopmall1805.ui.activity.MainActivity;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageActivity extends BaseActivity<IPresenter, IView> {

    private RecyclerView rvMessage;
    private MessageAdpter messageAdpter;
    private Handler handler=new Handler();
    private IMessageLister iMessageLister;
    @Override
    protected void initpreseter() {

    }

    @Override
    protected void initdate() {

        MessageManager.getInstance().quereMessage(new MessageManager.IMessageListenter() {
            @Override
            public void onresult(boolean issucess, final List<ShopcarMessage> shopcarMessages) {
                messageAdpter.updataData(shopcarMessages);
                //点击变消息已读未读
                messageAdpter.setiRecyclerViewItemClickListener(new BaseRVAdapter.IRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        ShopcarMessage shopcarMessage = shopcarMessages.get(position);

                        MessageManager.getInstance().updateMessage(shopcarMessage, new MessageManager.IMessageListenter() {
                            @Override
                            public void onresult(boolean issucess, List<ShopcarMessage> shopcarMessages) {
                            }
                        });
                        messageAdpter.notifyItemChanged(position);
                    }
                });
                //长点击删除这一条消息
                messageAdpter.setiRecyclerViewItemLongClickListener(new BaseRVAdapter.IRecyclerViewItemLongClickListener() {
                    @Override
                    public void onItemClick(final int position) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(MessageActivity.this);
                        builder.setIcon(R.drawable.ic_launcher_background);//设置图标
                        builder.setTitle("删除一条消息");//设置对话框的标题
                        builder.setMessage("你确定要删除消息吗？");//设置对话框的内容
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {  //这个是设置确定按钮

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                final ShopcarMessage shopcarMessage = shopcarMessages.get(position);
                                MessageManager.getInstance().deleteMessage(shopcarMessage, new MessageManager.IMessageListenter() {
                                    @Override
                                    public void onresult(boolean issucess, final List<ShopcarMessage> shopcarMessages) {
                                        Toast.makeText(MessageActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                                        List<ShopcarMessage> queresmessage = MessageManager.getInstance().queresmessage();
//                                        //使用EventBus更新消息数量
//                                        iMessageLister.onMessageCount(queresmessage.size());
                                        //重新赋值
                                        messageAdpter.updataData(queresmessage);
                                    }
                                });

                            }
                        });

                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {  //取消按钮

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(MessageActivity.this, "取消删除",Toast.LENGTH_SHORT).show();

                            }
                        });
                        AlertDialog b=builder.create();
                        b.show();  //必须show一下才能看到对话框，跟Toast一样的道理
                    }
                });
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
    public interface IMessageLister{
        void onMessageCount(int size);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        MessageManager.IMessageListenter iMessageListenter;
        iMessageListenter = null;
    }
}

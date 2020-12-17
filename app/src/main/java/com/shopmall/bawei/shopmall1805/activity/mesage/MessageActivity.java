package com.shopmall.bawei.shopmall1805.activity.mesage;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bawei.deom.BaseActivity;
import com.shopmall.bawei.shopmall1805.MessageManager.MessageManager;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ShangTitle;
import com.shopmall.bawei.shopmall1805.apter.MessageApter;
import com.shopmall.bawei.shopmall1805.apter.apter2.BaseRVAdapter;

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
            public void onResult(boolean isSuccess, final List<ShangTitle> shangTitles) {
                messageApter.updataData(shangTitles);

                messageApter.setiRecyclerViewItemClickListener(new BaseRVAdapter.IRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(final int position) {
                           // shangTitles.get(position).setIsRead(true);
                            Toast.makeText(MessageActivity.this, ""+shangTitles.size(), Toast.LENGTH_SHORT).show();

                            // ShangTitle shangTitle=new ShangTitle();
                            ShangTitle shangTitle = shangTitles.get(position);
//                            Log.e("布尔值", MessageActivity.this.shangTitle.getIsRead()+"");
                           MessageManager.getInstance().updateMessage(shangTitle, new MessageManager.IMessageListener() {
                               @Override
                               public void onResult(boolean isSuccess, List<ShangTitle> shangTitles) {
                                   Toast.makeText(MessageActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                   messageApter.notifyItemChanged(position);
                               }
                           });
                        }
                });
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

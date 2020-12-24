package com.shopmall.bawei.shopmall1805.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.deom.BaseFragment;
import com.bawei.deom.NetConnectManager;
import com.bawei.deom.countroller.UserCountroller;
import com.bawei.deom.countroller.UserIMPL;
import com.shopmall.bawei.shopmall1805.MessageManager.MessageManager;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ShangTitle;
import com.shopmall.bawei.shopmall1805.apter.PrimereAdpter;
import com.shopmall.bawei.shopmall1805.activity.mesage.MessageActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import bean.HomeBean;
import bean.TAGBean;


public class HomepageFragment extends BaseFragment<UserIMPL, UserCountroller.UserView> implements UserCountroller.UserView {
    private RecyclerView rv;
    private PrimereAdpter primereAdpter;
    private TextView toolerMessage;
    private ImageView toolImage;


    @Override
    protected void initHttpData() {
        if (NetConnectManager.getInstance().isConnected()){
            prine.getskerak(loadingPage);
        }else {
            Toast.makeText(getContext(), "当前无网络连接", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDisConnected() {
        super.onDisConnected();
        Toast.makeText(getContext(), "当前无网络连接", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onConnected() {
        super.onConnected();
        prine.getskerak(loadingPage);
    }

    @Override
    protected void inPrine() {
        prine=new UserIMPL();


    }

    @Override
    protected void initData() {

        int messageCount = MessageManager.getInstance().getMessageCount();
        if (messageCount!=0) {
            toolerMessage.setText("消息"+messageCount+"");
        }
        toolImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), MessageActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView(View view) {
        rv = (RecyclerView) view.findViewById(R.id.rv);
        primereAdpter = new PrimereAdpter();
        rv.setAdapter(primereAdpter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        toolImage = (ImageView) view.findViewById(R.id.tool_image);

        toolerMessage = (TextView) view.findViewById(R.id.tooler_message);

    }

    @Override
    protected int getlayoutview() {
        return R.layout.firstfragment;
    }


    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageChanged(ShangTitle shopcarMessage) {
        int messageCount = MessageManager.getInstance().getMessageCount();
        if (messageCount!=0) {
            toolerMessage.setText("消息"+messageCount+"");
        }
    }

    @Override
    public void onskerk(HomeBean homeBeanList) {
        primereAdpter.addOneData(homeBeanList.getBanner_info());
        primereAdpter.addOneData(homeBeanList.getChannel_info());
        primereAdpter.addOneData(homeBeanList.getAct_info());
        primereAdpter.addOneData(homeBeanList.getSeckill_info().getList());
        primereAdpter.addOneData(homeBeanList.getRecommend_info());
        primereAdpter.addOneData(homeBeanList.getHot_info());
    }

    @Override
    public void onTagBiew(List<TAGBean.ResultBean> resultBeanList) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

}

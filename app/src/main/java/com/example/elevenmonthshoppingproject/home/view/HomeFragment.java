package com.example.elevenmonthshoppingproject.home.view;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.home.contract.HomeContract;
import com.example.elevenmonthshoppingproject.home.presenter.HomePresenterImpl;
import com.example.framwork.BaseMVPFragment;
import com.example.framwork.BaseRVAdapter;
import com.example.framwork.ConnectManager;
import com.example.framwork.ShopCarGreen;
import com.example.framwork.sql.MySqlOpenHelper;
import com.example.framwork.view.manager.MessageManager;
import com.example.net.NetBusinessException;
import com.example.net.bean.ErrorBean;
import com.example.net.bean.HomeBean;
import com.example.net.bean.MoneyBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseMVPFragment<HomePresenterImpl, HomeContract.HomeIView> implements HomeContract.HomeIView, BaseRVAdapter.IBaseRecyclerLinsterner {

    private RecyclerView rvHome;
    private RecyAdapter recyAdapter;
    private HomePresenterImpl homePresenter;
    private TextView tvMessageHome;

    private MyBroadcaseReceiver myBroadcaseReceiver;
    private String pay;

    @Override
    public void onHomeData(HomeBean homeBean) {
        List<Object> datelist = new ArrayList<>();
        datelist.add(homeBean.getBanner_info());
        datelist.add(homeBean.getChannel_info());
        datelist.add(homeBean.getAct_info());
        datelist.add(homeBean.getHot_info());
        datelist.add(homeBean.getRecommend_info());
        datelist.add(homeBean.getSeckill_info().getList());
        recyAdapter.updatelist(datelist);
        recyAdapter.setBaseRVAdapterlinterner(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.homefragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageChanged(ShopCarGreen shopcarMessage) {
        int messageCount = MessageManager.getInstance().getMessageCount();
        if (messageCount!=0) {
            toolbar.setToolbarRightTv(messageCount+"");
        }
    }
    @Override
    protected void initView() {
         myBroadcaseReceiver = new MyBroadcaseReceiver();
         getActivity().registerReceiver(myBroadcaseReceiver,new IntentFilter("unorderbroadcast"));

        rvHome=findViewById(R.id.rv_home);
        tvMessageHome = findViewById(R.id.tv_message_home);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        recyAdapter=new RecyAdapter();
        rvHome.setAdapter(recyAdapter);

        tvMessageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("*---","**");
                ARouter.getInstance().build("/message/MessageListActivity").navigation();
            }
        });

    }

    @Override
    protected void initData() {
        int messageCount = MessageManager.getInstance().getMessageCount();
        if (messageCount!=0) {
            toolbar.setToolbarRightTv(messageCount+"");
        }
    }

    @Override
    protected void initPresenter() {
        homePresenter=new HomePresenterImpl();
        homePresenter.attatch(this);

    }

    @Override
    protected void onDisConnected() {
        super.onDisConnected();
//        showError("当前无网络");
    }

    @Override
    protected void onConnected() {
        super.onConnected();
        homePresenter.getHomeData();
    }

    @Override
    protected void initHttpData() {

        if (ConnectManager.getInstance().isConnected()){
            homePresenter.getHomeData();
        }else {
            showError("当前无网络");
        }

    }
    @Override
    public void onError(String code, String message) {
        Toast.makeText(getContext(), "加载数据失败"+code+""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadings() {
          showLoading();
//        loginPage.showError("错误");
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean message) {
        hideLoadingPage(isSuccess,message);
    }

    @Override
    public void onItemclick(int position) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        homePresenter.detachview();
        getActivity().unregisterReceiver(myBroadcaseReceiver);
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    public class MyBroadcaseReceiver extends  BroadcastReceiver{

        @Override
        public void onReceive(final Context context, Intent intent) {
             pay = intent.getStringExtra("pay");

            Toast.makeText(getContext(), ""+pay, Toast.LENGTH_SHORT).show();

        }
    }
}

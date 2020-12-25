package com.shopmall.bawei.shopmall1805.home.fragment;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.shopmall1805.Adper.homeadper.PrimereAdper;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.PayMessageActivity;
import com.shopmall.bawei.shopmall1805.home.fragment.jsonCallBack.JsonDataBack;

import framework.BaseFragment;
import framework.MessageMangerUlis;
import framework.NetContentManager;
import framework.mvpc.JsonPresenter;
import mode.HomeBean;
import view.SkipFinalUlis;
import view.loadinPage.ErrorBean;
import view.ToolBar;

public
class FragmentHomePage extends BaseFragment<JsonPresenter> implements View.OnClickListener {
    private RecyclerView rv;
    private PrimereAdper primereAdpter;
    private TextView textMessage;
    @Override
    protected void createPresenter() {
        presenter = new JsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {
    }
    @Override
    protected void InitData() {
        NetContentManager.getInstance().registerNetConnectLisenter(netConnectListener);
        JsonData();
        rv = (RecyclerView)findViewById(R.id.rv);
        tooBar = (ToolBar) findViewById(R.id.tooBar);
        textMessage = (TextView) findViewById(R.id.textMessage);
        textMessage.setOnClickListener(this);
        textMessage.setText(""+ MessageMangerUlis.getInstance().getReadMessage());

        primereAdpter = new PrimereAdper();
        rv.setAdapter(primereAdpter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

    }
    private void JsonData() {
        presenter.getshopcal(11,new JsonDataBack() {
            @Override
            public void homeBean(HomeBean homeBean) {
                primereAdpter.addOneData(homeBean.getResult().getBanner_info());
                primereAdpter.addOneData(homeBean.getResult().getChannel_info());
                primereAdpter.addOneData(homeBean.getResult().getAct_info());
                primereAdpter.addOneData(homeBean.getResult().getSeckill_info().getList());
                primereAdpter.addOneData(homeBean.getResult().getRecommend_info());
                primereAdpter.addOneData(homeBean.getResult().getHot_info());
            }
            @Override
            public void Error(String error) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.textMessage){
            ARouter.getInstance().build(SkipFinalUlis.REFRESH_ACTIVITY)
                    .navigation();
        }
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_homepage;
    }


    @Override
    public void showLoaDing() {
        showLoaDing();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
        showEmpty();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
    private NetContentManager.INetConnectListener netConnectListener = new NetContentManager.INetConnectListener() {
        @Override
        public void onConnected() {
            Log.i("====","发送通知");
            showEmpty();
        }

        @Override
        public void onDisConnected() {
            Log.i("====","无");
            LoadIngPage.showEmptyPage();
        }
    };

}

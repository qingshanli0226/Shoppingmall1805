package com.bawei.shopmall.home.view;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.ErrorBean;
import com.bawei.framework.BaseFragment;
import com.bawei.framework.MessageManager;
import com.bawei.framework.NetConnectManager;
import com.bawei.framework.greendao.MessageBean;
import com.bawei.net.mode.HomeBean;
import com.bawei.shopmall.home.contract.HomeContract;
import com.bawei.shopmall.home.contract.HomeImpl;
import com.shopmall.bawei.shopmall1805.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeFragment extends BaseFragment<HomeImpl, HomeContract.IHomeView> implements HomeContract.IHomeView, View.OnClickListener {

    private RelativeLayout normalContent;
    private RecyclerView homeRv;
    private TextView errorTv;

    private HomeAdapter homeAdapter;

    @Override
    protected void initHttpData() {
        if (NetConnectManager.getInstance().isConnected()) {
            httpPresenter.getHomeData();
        } else {
            showError("当前无网络连接");
        }
    }

    @Override
    protected void onDisConnected() {
        super.onDisConnected();
        showError("当前无网络连连接");
    }

    @Override
    protected void onConnected() {
        super.onConnected();
        httpPresenter.getHomeData();
    }

    @Override
    protected void initView() {
        normalContent = (RelativeLayout) findViewById(R.id.normalContent);
        homeRv = (RecyclerView) findViewById(R.id.homeRv);
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter();
        homeRv.setAdapter(homeAdapter);
        errorTv = (TextView) findViewById(R.id.errorTv);
        errorTv.setOnClickListener(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.errorTv:
                httpPresenter.getHomeData();
                break;
            default:
                break;
        }
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        httpPresenter.getHomeData();
    }

    @Override
    protected void initPresenter() {
        httpPresenter = new HomeImpl();

    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        homeAdapter.addOneData(homeBean.getResult().getBanner_info());
        homeAdapter.addOneData(homeBean.getResult().getChannel_info());
        homeAdapter.addOneData(homeBean.getResult().getAct_info());
        homeAdapter.addOneData(homeBean.getResult().getSeckill_info());
        homeAdapter.addOneData(homeBean.getResult().getRecommend_info());
        homeAdapter.addOneData(homeBean.getResult().getHot_info());
    }

    @Override
    public void showLoaDing() {
        showloading();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess, errorBean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageChanged(MessageBean messageBean) {
        int messageCount = MessageManager.getInstance().getMessageCount();
        if (messageCount != 0) {
            toolBar.setToolbarRightTv(messageCount + "");
        }
    }

    @Override
    public void showEmpty() {
        showEmptyPage();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {
        ARouter.getInstance().build("/message/MessageActivity").navigation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

package com.shopmall.bawei.shopmall1805.home.view.fragment;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shopmall.bawei.common.MyToolBar;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.framework.BaseRvAdapter;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.HomeImpl;
import com.shopmall.bawei.shopmall1805.home.view.HomeAdapter;

public class HomeFragment extends BaseFragment<HomeImpl, HomeContract.IHomeView> implements HomeContract.IHomeView, View.OnClickListener {

    private MyToolBar toolbar;
    private RelativeLayout normalContent;
    private RecyclerView homeRv;
    private ProgressBar loadingBar;
    private TextView errorTv;
    private HomeAdapter homeAdapter;
    @Override
    protected void initView() {
        normalContent = (RelativeLayout) findViewById(R.id.normalContent);
        homeRv = (RecyclerView) findViewById(R.id.homeRv);
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter();
        homeRv.setAdapter(homeAdapter);
        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        errorTv = (TextView) findViewById(R.id.errorTv);
        errorTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.errorTv:
                httpPresenter.getHomeData();
                break;
            default:break;
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
    public void onError(String msg) {
        errorTv.setVisibility(View.VISIBLE);
        normalContent.setVisibility(View.GONE);
        errorTv.setText(msg + "点击刷新数据");
    }

    @Override
    public void showLoading() {
        errorTv.setVisibility(View.GONE);
        normalContent.setVisibility(View.VISIBLE);
        loadingBar.setVisibility(View.VISIBLE);
        errorTv.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {
    }
}

package com.shopmall.bawei.shopmall1805.home.view;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.view.MyToolBar;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.contract.HomeImpl;

public class HomeFragment extends BaseFragment<HomeImpl, HomeContract.IHomeView> implements HomeContract.IHomeView, View.OnClickListener {

    private MyToolBar toolbar;
    private RecyclerView homeRv;

    private HomeAdapter homeAdapter;


    @Override
    protected void initView() {
        homeRv = (RecyclerView) findViewById(R.id.homeRv);
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter();
        homeRv.setAdapter(homeAdapter);
    }

    @Override
    public void onClick(View v) {
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
        homeAdapter.addOneData(homeBean.getBanner_info());
        homeAdapter.addOneData(homeBean.getChannel_info());
        homeAdapter.addOneData(homeBean.getAct_info());
        homeAdapter.addOneData(homeBean.getSeckill_info());
        homeAdapter.addOneData(homeBean.getRecommend_info());
        homeAdapter.addOneData(homeBean.getHot_info());
    }

//    @Override
//    public void onError(String msg) {
//        errorTv.setVisibility(View.VISIBLE);
//        normalContent.setVisibility(View.GONE);
//        errorTv.setText(msg + "点击刷新数据");
//    }

//    @Override
//    public void showLoaDing() {
//        errorTv.setVisibility(View.GONE);
//        normalContent.setVisibility(View.VISIBLE);
//        loadingBar.setVisibility(View.VISIBLE);
//        errorTv.setVisibility(View.GONE);
//    }


    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoaDing() {
        showLoading();
    }


    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {
//        Intent intent = new Intent(getActivity(), LoginRegisterActivity.class);
//        getActivity().startActivity(intent);
    }
}

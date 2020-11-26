package com.shopmall.bawei.shopmall1805.home.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.BaseMVPFragment;
import com.shopmall.bawei.net.mode.HomeBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.HomePresenterImpl;
import com.shopmall.bawei.user.LoginRegisterActivity;


public class HomeFragment extends BaseMVPFragment<HomePresenterImpl, HomeContract.IHomeView> implements HomeContract.IHomeView, View.OnClickListener {
    private RecyclerView homeRv;
    private HomeAdapter homeAdapter;
    private TextView errorTv;
    private RelativeLayout normalContent;

    @Override
    protected void initHttpData() {
        ihttpPresenter.getHomeData();
    }

    @Override
    protected void initPresenter() {
        ihttpPresenter = new HomePresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        homeRv = findViewById(R.id.homeRv);
        homeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeAdapter = new HomeAdapter();
        homeRv.setAdapter(homeAdapter);
        errorTv = findViewById(R.id.errorTv);
        errorTv.setOnClickListener(this);
        normalContent = findViewById(R.id.normalContent);
    }

    @Override
    public void onHomeData(HomeBean homeBean) {
        Toast.makeText(getActivity(), "获取到首页数据", Toast.LENGTH_SHORT).show();
        homeAdapter.addOneData(homeBean.getBanner_info());
        homeAdapter.addOneData(homeBean.getChannel_info());
        homeAdapter.addOneData(homeBean.getAct_info());
        homeAdapter.addOneData(homeBean.getHot_info());
        homeAdapter.addOneData(homeBean.getRecommend_info());
        homeAdapter.addOneData(homeBean.getSeckill_info());
    }


    @Override
    public void showLoaing() {
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.errorTv:
                ihttpPresenter.getHomeData();
                break;
            default:break;
        }
    }

    @Override
    public void onRightClick() {
        super.onRightClick();
        //实现跳转到消息页面

        Intent intent = new Intent(getActivity(), LoginRegisterActivity.class);
        getActivity().startActivity(intent);
    }
}

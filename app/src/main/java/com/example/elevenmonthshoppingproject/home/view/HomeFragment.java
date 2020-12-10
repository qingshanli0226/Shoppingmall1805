package com.example.elevenmonthshoppingproject.home.view;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.elevenmonthshoppingproject.R;
import com.example.elevenmonthshoppingproject.home.contract.HomeContract;
import com.example.elevenmonthshoppingproject.home.presenter.HomePresenterImpl;
import com.example.framwork.BaseMVPFragment;
import com.example.framwork.BaseRVAdapter;
import com.example.net.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseMVPFragment<HomePresenterImpl, HomeContract.HomeIView> implements HomeContract.HomeIView, BaseRVAdapter.IBaseRecyclerLinsterner {

    private RecyclerView rvHome;
    private RecyAdapter recyAdapter;
    private HomePresenterImpl homePresenter;

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

    @Override
    protected void iniView(View view) {
        rvHome=view.findViewById(R.id.rv_home);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        recyAdapter=new RecyAdapter();
        rvHome.setAdapter(recyAdapter);
    }

    @Override
    protected void iniData() {

    }

    @Override
    protected void iniHttpData() {
        homePresenter.getHomeData();
    }

    @Override
    protected void iniPresenter() {
        homePresenter=new HomePresenterImpl();
        homePresenter.attatch(this);

    }

    @Override
    public void onError(String code, String message) {
        Toast.makeText(getContext(), "加载数据失败"+code+""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
//        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
//        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void onItemclick(int position) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        homePresenter.detachview();
    }
}

package com.shopmall.bawei.shopmall1805.home;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.base.BaseFragment;
import com.example.framework.manager.MSGManager;
import com.example.net.bean.MainBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shoppmall.common.adapter.error.ErrorBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<HomePresenterImpl, HomeContract.HomeView> implements HomeContract.HomeView {
    private ImageView searchBtnHome;
    private EditText searchEtHome;

    private RecyclerView rvHomeMain;
    private HomeAdapter homeAdapter;
    private List<Object> list=new ArrayList<>();
    @Override
    protected void initDate() {
        presenter.loadMain();
        int i = MSGManager.getInstance().getMessageCount();
        if(i>0){
            toolBar.setToolBarRightTv(i+"");
        }
    }


    @Override
    protected void initLisenter() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        presenter=new HomePresenterImpl();
        searchBtnHome = findViewById(R.id.search_btn_home);
        searchEtHome = findViewById(R.id.search_et_home);
        rvHomeMain = findViewById(R.id.rv_home_main);
        rvHomeMain.requestFocus();
        rvHomeMain.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new HomeAdapter(getContext());
        rvHomeMain.setAdapter(homeAdapter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    public void onOk(MainBean bean) {
        list.clear();
        MainBean.ResultBean result = bean.getResult();
        list.add(result.getBanner_info());
        list.add(result.getChannel_info());
        list.add(result.getAct_info());
        list.add(result.getSeckill_info());
        list.add(result.getRecommend_info());
        list.add(result.getHot_info());
        homeAdapter.addData(list);
    }

    @Override
    public void showloading() {
        showLoading();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
        showEmptyPage();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void payBack(String back){
        if(back.equals("payBack")){
            toolBar.setToolBarRightTv(MSGManager.getInstance().getMessageCount()+"");
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
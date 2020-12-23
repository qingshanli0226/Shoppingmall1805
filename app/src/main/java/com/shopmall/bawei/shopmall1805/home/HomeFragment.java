package com.shopmall.bawei.shopmall1805.home;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bw.framework.BaseFragment;
import com.bw.framework.MessageManager;
import com.bw.framework.dao.ShopcarMessage;
import com.bw.net.bean.HomeFragmentBean;
import com.shopmall.bawei.shopmall1805.R;

import com.shopmall.bawei.shopmall1805.activity.MessageActivity;
import com.shopmall.bawei.shopmall1805.home.adapter.HomeFragmentAdapter;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

@Route(path = "fragment/homeFragment")
public class HomeFragment extends BaseFragment<HomePresenter, HomeContract.IHomeView> implements HomeContract.IHomeView {


    private RecyclerView homeRv;
    private List<Object> list =new ArrayList<>();
    private  HomeFragmentAdapter homeFragmentAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        homeRv = view.findViewById(R.id.rv);
//        homeListAdapter = new HomeListAdapter();
//        homeRv.setAdapter(homeListAdapter);
        homeRv.setLayoutManager(new LinearLayoutManager(getContext()));
        homeFragmentAdapter = new HomeFragmentAdapter();
        homeRv.setAdapter(homeFragmentAdapter);



    }
     private  int count = 0;
     Handler handler = new Handler(){
         @Override
         public void handleMessage(@NonNull Message msg) {
             super.handleMessage(msg);
             if (msg.what == 101){
                 toolbar.setToolbarRightTv(""+count);
             }
         }
     };

    @Override
    protected void initPresenter() {
        super.initPresenter();
        httpPresenter = new HomePresenter();
    }

    @Override
    protected void initData() {
        super.initData();
        httpPresenter.onGetHomeData();

        MessageManager.getInstance().queryMessage((isSuccess, shopcarMessageList) -> {

            Log.i("---", "initView: queryMessage："+shopcarMessageList.size());

            for (ShopcarMessage shopcarMessage : shopcarMessageList) {
                if (!shopcarMessage.getIsRead()){
                    count++;
                }
            }
            handler.sendEmptyMessage(101);
        });
    }

    @Override
    public void onOk(HomeFragmentBean homeFragmentBean) {
        list.add(homeFragmentBean.getResult().getBanner_info());
        list.add(homeFragmentBean.getResult().getChannel_info());
        list.add(homeFragmentBean.getResult().getAct_info());
        list.add(homeFragmentBean.getResult().getRecommend_info());
        list.add(homeFragmentBean.getResult().getSeckill_info().getList());
        list.add(homeFragmentBean.getResult().getHot_info());
        homeFragmentAdapter.updataData(list);

    }

    @Override
    public void onError(String message) {
        myToast(R.string.getDataError+message);
    }

    @Override
    public void showsLoaing() {
          showLoading();
    }

    @Override
    public void hidesLoading(boolean isSuccess) {
        hideLoadingPage(isSuccess);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onRightClick() {
        Intent intent = new Intent(getContext(), MessageActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLeftClick() {
        super.onLeftClick();
    }
}

package com.shopmall.bawei.shopmall1805.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shopmall.bawei.shopmall1805.Adper.homeadper.PrimereAdper;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.fragment.jsonCallBack.JsonDataBack;

import android.widget.Toast;

import framework.BaseFragment;
import framework.mvpc.jsonPresenter;
import mode.HomeBean;

public
class Fragmenthomepage extends BaseFragment {
    private RecyclerView rv;
    private PrimereAdper primereAdpter;
    @Override
    protected void createPresenter() {
        Presenter = new jsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void InitData() {
        JsonData();
        rv = (RecyclerView)findViewById(R.id.rv);
        primereAdpter = new PrimereAdper();
        rv.setAdapter(primereAdpter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void JsonData() {
        Presenter.getshopcal(11,new JsonDataBack() {
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

/*    private void goShopBay(int i, int position) {//购买
        userBean usernv = null;


        if (i==1){
            HomeBean.ResultBean.HotInfoBean hotInfoBean = hotInfoBeanList.get(position);
            usernv = new userBean(hotInfoBean.getName(),hotInfoBean.getCover_price(),hotInfoBean.getFigure());
        }else if (i == 4){
            HomeBean.ResultBean.RecommendInfoBean recommendInfoBean = recommendInfoBeans.get(position);
            usernv = new userBean(recommendInfoBean.getName(),recommendInfoBean.getCover_price(),recommendInfoBean.getFigure());
        }

        if (usernv !=null){
            Intent intent = new Intent(getContext(), goShopActivity.class);
            intent.putExtra("user",usernv);
            startActivity(intent);
        }
    }*/

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_homepage;
    }


}

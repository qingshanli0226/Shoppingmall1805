package com.shopmall.bawei.shopmall1805.home.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shopmall.bawei.shopmall1805.Adper.homeadper.PrimereAdper;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.fragment.jsonCallBack.JsonDataBack;

import framework.BaseFragment;
import framework.mvpc.JsonPresenter;
import mode.HomeBean;
import view.loadinPage.ErrorBean;
import view.ToolBar;

public
class FragmentHomePage extends BaseFragment implements ToolBar.IToolBarClickListner {
    private RecyclerView rv;
    private PrimereAdper primereAdpter;
    @Override
    protected void createPresenter() {
        presenter = new JsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void InitData() {
        JsonData();
        rv = (RecyclerView)findViewById(R.id.rv);
        tooBar = (ToolBar) findViewById(R.id.tooBar);

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
}

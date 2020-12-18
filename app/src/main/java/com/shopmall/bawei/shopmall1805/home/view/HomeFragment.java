package com.shopmall.bawei.shopmall1805.home.view;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.net.bean.HomeBean;
import com.shopmall.bawei.framework.example.framework.BaseFragment;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.PrimereAdpter;
import com.shopmall.bawei.shopmall1805.home.contract.HomeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.HomePresenter;

public class HomeFragment extends BaseFragment<HomePresenter, HomeContract.SkerakView> implements HomeContract.SkerakView {
    private RecyclerView rv;
    private PrimereAdpter primereAdpter;
    private ImageView imgMessage;

    @Override
    protected void initPreseter() {
        httpresetnter = new HomePresenter();
    }

    @Override
    protected void initView(View inflate) {

        rv = inflate.findViewById(R.id.rv);
        imgMessage = inflate.findViewById(R.id.imgMessage);
        primereAdpter = new PrimereAdpter();
        rv.setAdapter(primereAdpter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    protected void initdate() {
        httpresetnter.getskerak();

        imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到消息列表
                ARouter.getInstance().build("/message/MessageActivity").navigation();
            }
        });

    }

    @Override
    protected int getlayoutid() {
        return R.layout.fragment_home;
    }


    @Override
    public void onErroy(String message) {
        Toast.makeText(getContext(), "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showsloading() {
        showloading();
    }


    @Override
    public void hideloading() {
        hideLoading();
    }

    @Override
    public void showErroy(String message) {
        showerror(message);
    }

    @Override
    public void showEmpty() {
        showEnpty();
    }

    @Override
    public void onskerk(HomeBean homeBeanList) {
        primereAdpter.addOneData(homeBeanList.getBanner_info());
        primereAdpter.addOneData(homeBeanList.getChannel_info());
        primereAdpter.addOneData(homeBeanList.getAct_info());
        primereAdpter.addOneData(homeBeanList.getSeckill_info().getList());
        primereAdpter.addOneData(homeBeanList.getRecommend_info());
        primereAdpter.addOneData(homeBeanList.getHot_info());
    }

}

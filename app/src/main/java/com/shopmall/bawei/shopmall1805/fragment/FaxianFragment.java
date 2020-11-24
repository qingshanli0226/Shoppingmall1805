package com.shopmall.bawei.shopmall1805.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.deom.BaseFragment;
import com.bawei.deom.countroller.UserCountroller;
import com.bawei.deom.countroller.UserIMPL;
import com.shopmall.bawei.shopmall1805.R;

import java.util.List;

import bean.BaseBean;
import bean.HomeBean;
import bean.TAGBean;
import bean.typebean.SkirtBean;

public class FaxianFragment extends BaseFragment<UserIMPL,UserCountroller.UserView>implements UserCountroller.UserView {

    @Override
    protected void inPrine() {
        prine=new UserIMPL();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int getlayoutview() {
        return R.layout.faxianfragment;
    }


    @Override
    public void loading() {

    }

    @Override
    public void hideloading() {

    }


    @Override
    public void ChannelInfoBean(BaseBean<HomeBean> listBaseBean) {

    }

    @Override
    public void TagBiew(List<TAGBean.ResultBean> resultBeanList) {

    }



}

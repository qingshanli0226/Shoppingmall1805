package com.bawei.shopmall.type.view;


import android.view.View;
import android.widget.ProgressBar;

import com.bawei.common.view.MyToolBar;
import com.bawei.framework.base.BaseFragment;
import com.bawei.net.bean.HomeBean;
import com.bawei.shopmall.R;
import com.bawei.shopmall.home.contract.HomeContract;
import com.bawei.shopmall.home.contract.HomeImpl;

public class TypeFragment extends BaseFragment<HomeImpl, HomeContract.IHomeView> implements HomeContract.IHomeView, View.OnClickListener {

    private MyToolBar toolbar;
    private ProgressBar loadingBar;



    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        loadingBar = (ProgressBar) findViewById(R.id.loadingBar);
        toolbar.setToolBarClickListner(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myToolbar:
                break;
        }
    }


    @Override
    protected int layoutId() {
        return R.layout.fragment_type;
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
    }

    @Override
    public void onError(String msg) {
    }

    @Override
    public void showLoading() {
        loadingBar.setVisibility(View.VISIBLE);
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

package com.shopmall.bawei.shopmall1805.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;
import framework.greendao.RxGreen;
import framework.greendao.usernv;
import view.loadinPage.ErrorBean;
import view.ToolBar;

public
class FragmentShop extends BaseFragment implements ToolBar.IToolBarClickListner {
    private RecyclerView goRv;
    private List<usernv> list = new ArrayList<>();
    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void InitData() {

        tooBar = (ToolBar) findViewById(R.id.tooBar);
        goRv = (RecyclerView)findViewById(R.id.Go_rv);

        List<usernv> usernvs = RxGreen.getInstance(getContext(), "user.db")
                .SeekAllUser();
        list.addAll(usernvs);

    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_shop;
    }


    @Override
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}

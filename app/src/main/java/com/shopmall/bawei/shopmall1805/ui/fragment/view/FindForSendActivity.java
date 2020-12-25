package com.shopmall.bawei.shopmall1805.ui.fragment.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.example.framework.IPresenter;
import com.example.framework.IView;

import com.example.net.FindSendBean;
import com.example.net.bean.FindPayBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adpter.FindSendAdpter;

import java.util.List;


public class FindForSendActivity extends BaseActivity<IPresenter, IView> implements CacheManager.IShopcarPayCharListerter{
    private RecyclerView rv;
    private FindSendAdpter findSendAdpter;



    @Override
    protected void initpreseter() {

    }

    @Override
    protected void initdate() {
        CacheManager.getInstance().setshopcarpayListenter(this);
        List<FindSendBean> findSendBeans = CacheManager.getInstance().getfindsendList();
        findSendAdpter.updataData(findSendBeans);
    }

    @Override
    protected void initview() {
        //初始化控件
        rv = findViewById(R.id.rv);
        findSendAdpter = new FindSendAdpter();
        rv.setAdapter(findSendAdpter);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_find_for_send;
    }

    @Override
    public void onPayList(List<FindPayBean> shopcarBeanList) {

    }

    @Override
    public void onSendList(List<FindSendBean> findSendBeans) {
        findSendAdpter.updataData(findSendBeans);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopcarpayChangerListener(this);
    }
}

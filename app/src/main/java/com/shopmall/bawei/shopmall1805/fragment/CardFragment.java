package com.shopmall.bawei.shopmall1805.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bw.framework.BaseFragment;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.bw.shopcar.ShopApplication;
import com.shopmall.bawei.shopmall1805.DaoSession;
import com.shopmall.bawei.shopmall1805.GreenDaoBean;
import com.shopmall.bawei.shopmall1805.GreenDaoBeanDao;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.ShopmallApplication;
import com.shopmall.bawei.shopmall1805.activity.MyDetailsAdapter;

import java.util.List;

public class CardFragment extends BaseFragment<IPresenter, IView> {

    private RecyclerView recyclerView;
    private MyDetailsAdapter myDetailsAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_card;
    }

    @Override
    protected void initView(View view) {



        recyclerView = view.findViewById(R.id.recyclerview);
        myDetailsAdapter = new MyDetailsAdapter();
        recyclerView.setAdapter(myDetailsAdapter);
        myDetailsAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));





    }

    @Override
    protected void initData() {
        super.initData();
        DaoSession daoSession = ((ShopmallApplication) getContext().getApplicationContext()).getDaoSession();
        GreenDaoBeanDao greenDaoBeanDao = daoSession.getGreenDaoBeanDao();
        List<GreenDaoBean> greenDaoBeans = greenDaoBeanDao.loadAll();

        myDetailsAdapter.updataData(greenDaoBeans);
        myDetailsAdapter.notifyDataSetChanged();
    }
}

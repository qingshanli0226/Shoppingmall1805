package com.shopmall.bawei.shopmall1805.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.bw.framework.BaseFragment;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.shopmall.bawei.shopmall1805.GreenDaoBean;
import com.shopmall.bawei.shopmall1805.MyGreenManager;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.detail.MyDetailsAdapter;

import java.util.List;

public class CardFragment extends BaseFragment<IPresenter, IView> {

    private RecyclerView recyclerView;
    private MyDetailsAdapter myDetailsAdapter;
    private RelativeLayout relativeLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_card;
    }

    @Override
    protected void initView(View view) {

        List<GreenDaoBean> listAll = MyGreenManager.getMyGreenManager().getListAll();

        Log.i("---",""+listAll.size());
        recyclerView = view.findViewById(R.id.recyclerview);
        myDetailsAdapter = new MyDetailsAdapter();
        recyclerView.setAdapter(myDetailsAdapter);
        myDetailsAdapter.updataData(listAll);
        myDetailsAdapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    protected void initData() {
        super.initData();
//        DaoSession daoSession = ((ShopmallApplication) getContext().getApplicationContext()).getDaoSession();
//        GreenDaoBeanDao greenDaoBeanDao = daoSession.getGreenDaoBeanDao();
//        List<GreenDaoBean> greenDaoBeans = greenDaoBeanDao.loadAll();


    }
}

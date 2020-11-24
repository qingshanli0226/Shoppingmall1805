package com.shopmall.bawei.shopmall1805.Fragment.fragment_fen;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.framework.BaseActivity;
import com.example.framework.BaseFragment;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.net.bean.Biaobean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adpter.BiaoAdpter;
import com.shopmall.bawei.shopmall1805.contract.BiaoContract;
import com.shopmall.bawei.shopmall1805.presenter.BiaoPresenter;

import java.util.List;

public class Fragment_biao extends BaseFragment<BiaoPresenter, BiaoContract.biaoView> implements BiaoContract.biaoView {


    private RecyclerView rvBiao;
    private BiaoAdpter biaoAdpter = new BiaoAdpter();
    @Override
    protected void initPreseter() {
        httpresetnter = new BiaoPresenter();
    }

    @Override
    protected void initView(View inflate) {
        //初始化控件
        rvBiao = inflate.findViewById(R.id.rv_biao);
        rvBiao.setLayoutManager(new GridLayoutManager(getContext(),3));
        rvBiao.setAdapter(biaoAdpter);
    }

    @Override
    protected void initdate() {
        httpresetnter.getbiao();
    }


    @Override
    protected int getlayoutid() {
        return R.layout.fragment_fragment_biao;
    }

    @Override
    public void onbiao(List<Biaobean.ResultBean> beans) {
        Toast.makeText(getContext(), ""+beans.get(0).getName(), Toast.LENGTH_SHORT).show();
        biaoAdpter.updataData(beans);
        biaoAdpter.notifyDataSetChanged();
    }

    @Override
    public void onErroy(String message) {

    }
}

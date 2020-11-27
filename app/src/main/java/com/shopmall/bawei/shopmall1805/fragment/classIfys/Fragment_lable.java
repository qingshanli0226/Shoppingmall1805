package com.shopmall.bawei.shopmall1805.fragment.classIfys;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.shopmall.bawei.shopmall1805.Adper.classify.LableAdper;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.fragment.jsonCallBack.JsonDataBack;

import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;
import framework.mvpc.jsonPresenter;
import mode.LableBean;

public
class Fragment_lable extends BaseFragment {
    private RecyclerView Rvc;
    private List<LableBean.ResultBean> resultBeans = new ArrayList<>();
    private LableAdper biaoAdper;
    @Override
    protected void createPresenter() {
        Presenter = new jsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void InitData() {
        Rvc = (RecyclerView) findViewById(R.id.Rvc);

        Rvc.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        Presenter.getshopcal(12,new JsonDataBack() {
            @Override
            public void javabean(LableBean e) {
                resultBeans.addAll(e.getResult());
                biaoAdper.notifyDataSetChanged();
            }

            @Override
            public void Error(String error) {

            }
        });

        biaoAdper = new LableAdper(R.layout.item_biao,resultBeans);
        Rvc.setAdapter(biaoAdper);
        biaoAdper.notifyDataSetChanged();

    }

    @Override
    protected int getlayoutId() {
        return R.layout.classifs2_fragment2;
    }
}

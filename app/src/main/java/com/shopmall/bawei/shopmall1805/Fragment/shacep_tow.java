package com.shopmall.bawei.shopmall1805.Fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.shopmall.bawei.shopmall1805.Adper.biaoAdper;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;
import framework.User2;
import framework.User3;
import framework.mvpc.jsonPresenter;
import mode.ClothesBean;
import mode.javabean;

public
class shacep_tow extends BaseFragment {
    private RecyclerView Rvc;
    private List<javabean.ResultBean> resultBeans = new ArrayList<>();
    private biaoAdper biaoAdper;
    @Override
    protected void createPresenter() {
        Presenter = new jsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void InitData(View inflate) {
        Log.i("====","标签");
        Rvc = (RecyclerView) inflate.findViewById(R.id.Rvc);

        Rvc.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

        Presenter.getBaiocal(new User3() {
            @Override
            public void Susses(javabean e) {
                Log.i("====","标签获取到的数据是"+e.toString());
                resultBeans.addAll(e.getResult());
                Log.i("====","打印标签集合尺寸"+resultBeans.size());
                biaoAdper.notifyDataSetChanged();
            }

            @Override
            public void Error(String error) {

            }
        });

        biaoAdper = new biaoAdper(R.layout.item_biao,resultBeans);
        Rvc.setAdapter(biaoAdper);
        biaoAdper.notifyDataSetChanged();

    }

    @Override
    protected int getlayoutId() {
        return R.layout.shacep_tow;
    }
}

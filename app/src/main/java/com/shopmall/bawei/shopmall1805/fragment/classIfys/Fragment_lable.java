package com.shopmall.bawei.shopmall1805.fragment.classIfys;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.shopmall.bawei.shopmall1805.Adper.JsonCallbackInterface;
import com.shopmall.bawei.shopmall1805.Adper.lableAdper;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;
import framework.User2;
import framework.mvpc.jsonPresenter;
import mode.javabean;

public
class Fragment_lable extends BaseFragment {
    private RecyclerView Rvc;
    private List<javabean.ResultBean> resultBeans = new ArrayList<>();
    private lableAdper biaoAdper;
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

        Presenter.getshopcal(5,new JsonCallbackInterface(){
            @Override
            public void successLable(javabean javabean) {
                resultBeans.addAll(javabean.getResult());
                biaoAdper.notifyDataSetChanged();
            }

        });

        biaoAdper = new lableAdper(R.layout.item_biao,resultBeans);
        Rvc.setAdapter(biaoAdper);
        biaoAdper.notifyDataSetChanged();

    }

    @Override
    protected int getlayoutId() {
        return R.layout.classifs2_fragment2;
    }
}

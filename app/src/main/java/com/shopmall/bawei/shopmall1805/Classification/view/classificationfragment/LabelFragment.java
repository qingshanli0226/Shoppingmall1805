package com.shopmall.bawei.shopmall1805.Classification.view.classificationfragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.common2.TagBean;
import com.shopmall.bawei.shopmall1805.Classification.contract.LabelContract;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.ClassificationSmallLabelAdapter;

import com.shopmall.bawei.shopmall1805.Classification.prsenter.LabelPresenter;

import java.util.List;


import mvp.view.BaseFragment;
import mvp.view.BaseMVPFragment;


public class LabelFragment extends BaseMVPFragment<LabelPresenter, LabelContract.ILabel> implements LabelContract.ILabel, View.OnClickListener {
    private RecyclerView biaoRv;
    private TextView errorTv;
    private RelativeLayout normalContent;

    @Override
    protected void initHttpData() {
        ihttpPresenter.getILabelData();
    }

    @Override
    protected void initPresenter() {
        ihttpPresenter=new LabelPresenter();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_label;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        biaoRv = (RecyclerView) findViewById(R.id.biao_rv);
        errorTv=findViewById(R.id.errorTv);
        errorTv.setOnClickListener(this);
        normalContent=findViewById(R.id.normalContent);

        biaoRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

    }


    @Override
    public void onLabel(TagBean tagBean) {
        List<TagBean.ResultBean> result = tagBean.getResult();
        ClassificationSmallLabelAdapter classificationSmallLabelAdapter = new ClassificationSmallLabelAdapter(R.layout.item_classification_label, result);
        biaoRv.setAdapter(classificationSmallLabelAdapter);
    }

    @Override
    public void showError(String code, String message) {
        errorTv.setVisibility(View.VISIBLE);
        normalContent.setVisibility(View.GONE);
        errorTv.setText(message + " 点击刷新数据");
    }

    @Override
    public void showLoaing() {
        errorTv.setVisibility(View.GONE);
        normalContent.setVisibility(View.VISIBLE);
        normalContent.setVisibility(View.VISIBLE);
        errorTv.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.errorTv:
                ihttpPresenter.getILabelData();
                break;
            default:break;
        }
    }
}
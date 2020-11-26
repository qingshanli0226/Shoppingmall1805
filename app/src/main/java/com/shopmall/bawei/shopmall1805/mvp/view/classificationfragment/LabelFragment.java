package com.shopmall.bawei.shopmall1805.mvp.view.classificationfragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.ClassificationSmallLabelAdapter;
import com.shopmall.bawei.shopmall1805.mvp.contract.LabelContract;
import com.shopmall.bawei.shopmall1805.mvp.model.LabelModel;
import com.shopmall.bawei.shopmall1805.mvp.prsenter.LabelPresenter;

import java.util.List;

import baseurl.TagBean;
import mvp.view.BaseFragment;


public class LabelFragment extends BaseFragment<LabelPresenter> implements LabelContract.geteview {
    private RecyclerView biaoRv;
    private View view;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(bandlayout(), container, false);
            initview();
            initdata();

        return view;
    }

    @Override
    public void getdata(List<TagBean.ResultBean> list) {
        ClassificationSmallLabelAdapter classificationSmallLabelAdapter = new ClassificationSmallLabelAdapter(R.layout.item_classification_label, list);
        biaoRv.setAdapter(classificationSmallLabelAdapter);
    }

    @Override
    public int bandlayout() {
        return R.layout.fragment_label;
    }

    @Override
    public void initview() {
        biaoRv = (RecyclerView) view.findViewById(R.id.biao_rv);
        biaoRv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

    }

    @Override
    public void initdata() {
        ipresenter=new LabelPresenter(new LabelModel(),this);
        ipresenter.inithomedata();

    }
}
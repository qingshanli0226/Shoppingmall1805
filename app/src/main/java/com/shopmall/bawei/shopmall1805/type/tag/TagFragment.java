package com.shopmall.bawei.shopmall1805.type.tag;

import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.framework.base.BaseFragment;
import com.example.net.bean.TagBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shoppmall.common.adapter.error.ErrorBean;

import java.util.ArrayList;
import java.util.List;

public class TagFragment extends BaseFragment<TagPresenterImpl, TagContract.TagView> implements TagContract.TagView {
    private RecyclerView rvTag;
    private List<TagBean.ResultBean> list=new ArrayList<>();
    private TagDapter tagDapter;
    @Override
    protected void initDate() {
        presenter.showTag();
    }

    @Override
    protected void initLisenter() {

    }

    @Override
    protected void initView(View inflate) {
        presenter=new TagPresenterImpl();
        rvTag = (RecyclerView) inflate.findViewById(R.id.rv_tag);
        rvTag.setLayoutManager(new GridLayoutManager(getContext(),3));
        tagDapter=new TagDapter();
        rvTag.setAdapter(tagDapter);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tag;
    }

    @Override
    public void onOk(TagBean bean) {
        list.clear();
        List<TagBean.ResultBean> result = bean.getResult();
        list.addAll(result);
        tagDapter.updataData(list);
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(getContext(), ""+msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showloading() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

    }

    @Override
    public void showEmpty() {

    }
}
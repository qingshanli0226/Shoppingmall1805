package com.shopmall.bawei.shopmall1805.ui.fragmentsort;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseFragment;
import com.shopmall.bawei.framework.constart.Constant;
import com.shopmall.bawei.framework.mvptest.presenter.TagPresenter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.TagResultAdapter;
import com.shopmall.bean.TagData;

public class TagFragment_Sort extends BaseFragment<TagPresenter> implements Constant.TagConstartView {

    private RecyclerView recycleTag2;
    private TagResultAdapter tagResultAdapter;
    @Override
    protected void createViewid(View inflate) {


        recycleTag2 = inflate.findViewById(R.id.recycle_tag2);

    }

    @Override
    protected void createEnvent() {

    }

    @Override
    protected void createData() {

        mPresenter.Tag(Constants.TAG_URL2,logingPage);

        recycleTag2.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

    }

    @Override
    protected int fragmentid() {
        return R.layout.tag_fragment_sort;
    }

    @Override
    protected void createPresenter() {
        mPresenter=new TagPresenter(this);
    }

    @Override
    public void Success(Object... objects) {
          TagData tagData=(TagData) objects[0];
        Log.e("tag",""+tagData);
        tagResultAdapter=new TagResultAdapter(R.layout.tag_item,tagData.getResult());
        recycleTag2.setAdapter(tagResultAdapter);

    }

    @Override
    public void Error(String s) {
        Log.e("tag",""+s);

    }
}

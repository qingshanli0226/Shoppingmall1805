package com.shopmall.bawei.shopmall1805.ui.fragmentf2;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.shopmall.bawei.common.Constants;
import com.shopmall.bawei.framework.base.BaseFragment;
import com.shopmall.bawei.framework.constart.Constart;
import com.shopmall.bawei.framework.mvptest.presenter.TagPresenter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.adapter.TagResultAdapter;
import com.shopmall.bean.TagData;

public class Fragment2_f2 extends BaseFragment<TagPresenter> implements Constart.TagConstartView {

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

        mPresenter.Tag(Constants.TAG_URL2);

        recycleTag2.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));

    }

    @Override
    protected int fragmentid() {
        return R.layout.fragment2_f2;
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

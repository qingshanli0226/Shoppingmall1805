package com.shopmall.bawei.shopmall1805.Fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;
import framework.greendao.RxGreen;
import framework.greendao.usernv;

public
class Fragment_shop extends BaseFragment {
    private Toolbar toobar;
    private RecyclerView GoRv;
    private List<usernv> list = new ArrayList<>();
    @Override
    protected void createPresenter() {

    }

    @Override
    protected void OnClickListener() {

    }

    @Override
    protected void InitData(View inflate) {
        toobar = (Toolbar) inflate.findViewById(R.id.toobar);
        GoRv = (RecyclerView) inflate.findViewById(R.id.Go_rv);
        List<usernv> usernvs = RxGreen.getInstance(getContext(), "user.db")
                .SeekAllUser();
        list.addAll(usernvs);

    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_shop;
    }


}

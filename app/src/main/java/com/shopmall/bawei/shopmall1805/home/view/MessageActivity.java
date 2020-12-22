package com.shopmall.bawei.shopmall1805.home.view;

import android.widget.ListView;

import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.view.RefreshLayout;
import com.shopmall.bawei.shopmall1805.R;

public class MessageActivity extends BaseActivity {
    private RefreshLayout rfl;
    private ListView lv;

    @Override
    protected int layoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        rfl = (RefreshLayout) findViewById(R.id.rfl);
        lv = (ListView) findViewById(R.id.lv);
        rfl.addRefreshListener(new RefreshLayout.IRefreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onRefreshComplete() {

            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }
}

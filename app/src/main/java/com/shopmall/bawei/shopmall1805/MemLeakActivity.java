package com.shopmall.bawei.shopmall1805;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.shopmall.bawei.framework.BaseMVPActivity;
import com.shopmall.bawei.framework.BaseRVAdapter;
import com.shopmall.bawei.framework.IPresenter;
import com.shopmall.bawei.framework.IView;
@Route(path = "/main/MemLeakActivity")
public class MemLeakActivity extends BaseMVPActivity<IPresenter,IView> {
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mem_leak;
    }
}

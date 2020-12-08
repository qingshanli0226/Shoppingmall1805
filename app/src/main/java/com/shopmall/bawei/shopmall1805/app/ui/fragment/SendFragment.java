package com.shopmall.bawei.shopmall1805.app.ui.fragment;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.framework.BaseFragment;

public class SendFragment extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_send;
    }

    @Override
    protected void initData() {
        toolbar.setToolBarLeftImg(R.drawable.menu_cyc);
        toolbar.setToolBarTitle("社区");
        toolbar.setToolBarRightImg(R.drawable.new_message_icon);
    }
    @Override
    protected void initView() {

    }

}
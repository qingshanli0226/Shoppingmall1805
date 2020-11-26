package com.shopmall.bawei.shopmall1805.home.view.fragment;


import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.common.MyToolBar;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.net.mode.RecommendedBeen;
import com.shopmall.bawei.net.typebean.BugBean;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.contract.TypeContract;
import com.shopmall.bawei.shopmall1805.home.presenter.TypeImpl;

import java.util.ArrayList;
import java.util.List;

public class TypeFragment extends BaseFragment<TypeImpl, TypeContract.ITypeView> implements TypeContract.ITypeView, View.OnClickListener {
    MyToolBar myToolBar;
    private SegmentTabLayout segmentTabLayout;
    private ImageView iv_type_search;
    private FrameLayout fl_type;
    private List<Fragment> fragmentList = new ArrayList<>();
    private Fragment tempFragment;
    @Override
    protected int layoutId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initListener() {
        segmentTabLayout = (SegmentTabLayout) findViewById(R.id.tl_1);
        iv_type_search = (ImageView) findViewById(R.id.iv_type_search);
        fl_type = (FrameLayout) findViewById(R.id.fl_type);
        initData();
    }

    @Override
    protected void initData() {
        super.initData();

        initFragment();

        String[] titles = {"分类", "标签"};

        segmentTabLayout.setTabData(titles);

        segmentTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(tempFragment,fragmentList.get(position));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        switchFragment(tempFragment, fragmentList.get(0));
    }

    private void initFragment() {
        fragmentList.add(new ListFragment());
        fragmentList.add(new TagFragment());

        switchFragment(tempFragment,  fragmentList.get(0));
    }
    public void switchFragment(Fragment fromFragment, Fragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }

                    transaction.add(R.id.fl_type, nextFragment, "tagFragment").commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }


    @Override
    protected void initPresenter() {
        httpPresenter = new TypeImpl();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void UserView(List<BugBean.ResultBean.ChildBean> list) {

    }

    @Override
    public void UserRe(List<BugBean.ResultBean.HotProductListBean> list) {

    }
}
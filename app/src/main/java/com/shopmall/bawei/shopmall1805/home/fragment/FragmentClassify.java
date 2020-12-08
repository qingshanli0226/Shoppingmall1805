package com.shopmall.bawei.shopmall1805.home.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.fragment.classIfys.FragmentClassify2;
import com.shopmall.bawei.shopmall1805.home.fragment.classIfys.FragmentLable;

import java.util.ArrayList;
import java.util.List;

import framework.BaseFragment;
import framework.mvpc.jsonPresenter;
import view.ToolBar;
import view.loadinPage.ErrorBean;

public
class FragmentClassify extends BaseFragment implements ToolBar.IToolBarClickListner {

    private RadioGroup radioGroupClassify;
    private RadioButton radioButtonClassify;
    private RadioButton radioButtonLable;
    private List<Fragment> list = new ArrayList<>();


    @Override
    protected void createPresenter() {
        Presenter = new jsonPresenter(this);
    }

    @Override
    protected void OnClickListener() {
        radioGroupClassify.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonClassify:
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentViewClassify,new FragmentClassify2())
                        .commit();
                        radioButtonClassify.setTextColor(Color.RED);
                        radioButtonLable.setTextColor(Color.WHITE);
                        break;
                    case R.id.radioButtonLable:
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentViewClassify,new FragmentLable())
                                .commit();
                        radioButtonClassify.setTextColor(Color.WHITE);
                        radioButtonLable.setTextColor(Color.RED);
                        break;
                }
            }
        });
    }

    @Override
    protected void InitData() {
        list.clear();
        radioGroupClassify = (RadioGroup) findViewById(R.id.radioGroupClassify);
        radioButtonClassify = (RadioButton) findViewById(R.id.radioButtonClassify);
        radioButtonLable = (RadioButton) findViewById(R.id.radioButtonLable);
        tooBar = (ToolBar) findViewById(R.id.tooBar);

        FragmentActivity activity = getActivity();
        activity.getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentViewClassify,new FragmentLable())
                .add(R.id.fragmentViewClassify,new FragmentClassify2())
                .show(new FragmentClassify2())
                .commit();

    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_classifs; }

    @Override
    public void showLoaDing() {
        showLoaDing();
    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
        showEmpty();
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}

package com.shopmall.bawei.shopmall1805.type.view;

import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

public class TypeTagFragment extends BaseFragment<BasePresenter, IView> implements IView {
    private ImageView ivTypeSearch;
    private FrameLayout fl_type;

    private RadioGroup rg;
    private RadioButton rbl;
    private RadioButton rbr;


    private VpAdapter adapter;



    private List<Fragment> list = new ArrayList<>();

    private FragmentManager fm;



    @Override
    protected void initView() {
        ivTypeSearch = (ImageView) findViewById(R.id.iv_type_search);
        fl_type = (FrameLayout) findViewById(R.id.fl_type);
        rg = (RadioGroup) findViewById(R.id.rg);
        rbl = (RadioButton) findViewById(R.id.rbl);
        rbr = (RadioButton) findViewById(R.id.rbr);



        list.add(new TypeFragment<>());
        list.add(new TagFragment<>());

        fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fmts = fm.beginTransaction();
        fmts.add(R.id.fl_type,list.get(0));
        fmts.add(R.id.fl_type,list.get(1));
        fmts.show(list.get(0));
        fmts.hide(list.get(1));
        fmts.commit();

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fmts = fm.beginTransaction();
                switch (checkedId){
                    case R.id.rbl:
                        fmts.show(list.get(0));
                        fmts.hide(list.get(1));
                        rbl.setTextColor(Color.WHITE);
                        rbr.setTextColor(Color.RED);
                        break;
                    case R.id.rbr:
                        fmts.show(list.get(1));
                        fmts.hide(list.get(0));
                        rbl.setTextColor(Color.RED);
                        rbr.setTextColor(Color.WHITE);
                        break;
                }
                fmts.commit();
            }
        });
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_type_tag;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showEmpty() {
        showEmpty();
    }

    @Override
    public void showLoaDing() {
        showLoading();
    }


    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    class VpAdapter extends FragmentPagerAdapter{
        public VpAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }


    }
}

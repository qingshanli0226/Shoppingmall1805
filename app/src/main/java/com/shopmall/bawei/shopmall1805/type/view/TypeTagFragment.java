package com.shopmall.bawei.shopmall1805.type.view;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.shopmall1805.R;

import java.util.ArrayList;
import java.util.List;

public class TypeTagFragment extends BaseFragment<BasePresenter, IView> implements IView {
    private ImageView ivTypeSearch;
    private ViewPager vp;

    private RadioGroup rg;
    private RadioButton rbl;
    private RadioButton rbr;


    private VpAdapter adapter;


    private String[] titles = {"分类","标签"};

    private List<Fragment> list = new ArrayList<>();



    @Override
    protected void initView() {
        ivTypeSearch = (ImageView) findViewById(R.id.iv_type_search);
        vp = (ViewPager) findViewById(R.id.fl_type);
        rg = (RadioGroup) findViewById(R.id.rg);
        rbl = (RadioButton) findViewById(R.id.rbl);
        rbr = (RadioButton) findViewById(R.id.rbr);



        list.add(new ListFragment<>());
        list.add(new TypeFragment<>());

        adapter = new VpAdapter(getActivity().getSupportFragmentManager());
        vp.setAdapter(adapter);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbl:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rbr:
                        vp.setCurrentItem(1);
                        break;
                }
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rg.check(R.id.rbl);
                        break;
                    case 1:
                        rg.check(R.id.rbr);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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

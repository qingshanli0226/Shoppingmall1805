package com.shopmall.bawei.shopmall1805.type;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.bw.framework.BaseFragment;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.type.adapter.MyPagerAdapter;
import com.shopmall.bawei.shopmall1805.type.fragment.ClassifyFragment;
import com.shopmall.bawei.shopmall1805.type.fragment.TagFragment;

import java.util.ArrayList;
import java.util.List;

public class TypeFragment extends BaseFragment<IPresenter, IView> {

    private ViewPager frameLayout;
    private MyPagerAdapter myPagerAdapter;
    private List<Fragment> fragments = new ArrayList<>();
    private ClassifyFragment classifyFragment;
    private TagFragment tagFragment;
    private Button btn1,btn2;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_type;
    }

    @Override
    protected void initView(View view) {
        frameLayout = view.findViewById(R.id.frame_type);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);

        tagFragment = new TagFragment();
        classifyFragment = new ClassifyFragment();

        fragments.add(classifyFragment);
        fragments.add(tagFragment);

        myPagerAdapter = new MyPagerAdapter(getFragmentManager(),fragments);
        frameLayout.setAdapter(myPagerAdapter);


       btn1.setOnClickListener(v -> frameLayout.setCurrentItem(0));

       btn2.setOnClickListener(v -> frameLayout.setCurrentItem(1));




    }

    @Override
    public void onRightClick() {

    }
}

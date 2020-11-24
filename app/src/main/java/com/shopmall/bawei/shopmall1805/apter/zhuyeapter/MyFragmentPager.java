package com.shopmall.bawei.shopmall1805.apter.zhuyeapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



import java.util.ArrayList;

public class MyFragmentPager extends FragmentPagerAdapter {
    ArrayList<Fragment> arrayList;
    public MyFragmentPager(FragmentManager fm, ArrayList<Fragment> arrayList) {
        super(fm);
        this.arrayList=arrayList;
    }

    @Override
    public Fragment getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
}

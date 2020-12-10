package com.example.elevenmonthshoppingproject.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class TypeFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private String[] mTitles;

    public TypeFragmentAdapter(@NonNull FragmentManager fm, List<Fragment> fragments, String[] mTitles) {
        super(fm);
        this.fragments = fragments;
        this.mTitles = mTitles;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}

package com.shopmall.bawei.user;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class LoginRegisterAdapter extends FragmentStatePagerAdapter {
    private Fragment[] fragments = new Fragment[] {new LoginFragment(),new RegisterFragment()};


    public LoginRegisterAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}

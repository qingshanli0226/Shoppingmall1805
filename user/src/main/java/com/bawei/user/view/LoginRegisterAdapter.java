package com.bawei.user.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bawei.user.view.fragment.LoginFragment;
import com.bawei.user.view.fragment.RegisterFragment;

public class LoginRegisterAdapter extends FragmentStatePagerAdapter {
    private Fragment[] fragments = new Fragment[]{
            new LoginFragment(), new RegisterFragment()
    };

    public LoginRegisterAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}

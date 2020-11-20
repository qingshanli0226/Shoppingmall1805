package com.example.framework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutID(), container, false);
        initView(inflate);
        initDate();
        initLisenter();
        return inflate;
    }

    protected  void initDate(){

    }

    protected abstract void initLisenter();

    protected abstract void initView(View inflate);

    protected abstract int getLayoutID();
}

package com.bawei.framework;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
abstract public class BaseFragment extends Fragment {


    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    protected abstract void initData();

    protected abstract void initView(View inflate);

    abstract int getLayoutId();

}

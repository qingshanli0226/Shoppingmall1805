package com.bawei.deom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




public abstract class BaseFragment<Prine extends IPrine,PView extends IView> extends Fragment {

   public Prine prine;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(getlayoutview(),null);
        initView(view);
        initData();
        inPrine();
        prine.attach((PView)this);
        return view;
    }

    protected abstract void inPrine();


    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract int getlayoutview();


}

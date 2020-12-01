package com.bawei.deom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.deom.view.LoadingPage;


public abstract class BaseFragment<Prine extends IPrine,PView extends IView> extends Fragment {

   public Prine prine;
    protected LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         loadingPage=new LoadingPage(getContext()) {
             @Override
             protected int getSuccessLayoutid() {
                 return getlayoutview();
             }
         };
        return loadingPage;
    }
@Override
public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initView(loadingPage);
    inPrine();
    if (prine!=null){
        prine.attach((PView)this);
    }

    initData();
}

    protected abstract void inPrine();


    protected abstract void initData();

    protected abstract void initView(View view);

    protected abstract int getlayoutview();


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (prine!=null){
            prine.onDestroy();
        }
    }
}

package com.bawei.deom;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.deom.view.LoadingPage;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


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
        iscreate();

        return loadingPage;
    }

    private void iscreate() {

    }


    @Override
public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    initView(loadingPage);
    inPrine();
    if (prine!=null){
        prine.attach((PView)this);
    }
        initHttpData();
    initData();
}

    protected abstract void initHttpData();

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
     @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConnectChange(Boolean isConnected){
        if (isConnected){
            onConnected();
        }else {
            onDisConnected();
        }
     }

    protected void onDisConnected() {
    }

    protected  void onConnected(){};
}

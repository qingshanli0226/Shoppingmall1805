package com.example.framework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment<P extends IPresenter,V extends IView> extends Fragment {
    protected P httpresetnter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getlayoutid(), container, false);
        initView(inflate);
        initPreseter();
        initdate();
        httpresetnter.attchView((V)this);
        return inflate;
    }
    protected abstract void initPreseter();
    protected abstract void initView(View inflate);
    protected abstract void initdate();
    protected abstract int getlayoutid();

    @Override
    public void onDestroy() {
        super.onDestroy();
        httpresetnter.ondechView();
    }
}

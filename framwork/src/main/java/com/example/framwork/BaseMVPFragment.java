package com.example.framwork;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.framwork.view.LoginPage;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMVPFragment<T extends  IPresenter,V extends  IView> extends Fragment {
    protected T ihttpPresenter;
    protected ProgressBar loadingBar;
    private LoginPage loginPage;
    private List<String> dataList = new ArrayList<>();




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        loadingBar=view.findViewById(R.id.loadingBar);

        loginPage=new LoginPage(getContext()) {
            @Override
            protected int getsuccessId() {
                return getLayoutId();
            }
        };

        iniView(view);
        iniData();
        iniPresenter();
//        ihttpPresenter.attatch((V) this);
        iniHttpData();

        return view;
    }
    protected abstract int getLayoutId();
    protected abstract void iniView(View view);
    protected abstract void iniData();
    protected abstract void iniPresenter();
    protected abstract void iniHttpData();


    public void showLoad() {
        loginPage.loadingPage();
    }

    public void hideLoadingPage(boolean isSuccess, String errorBean) {
        if (isSuccess) {
            loginPage.showsuccessPage();
        } else {
           loginPage.showError(errorBean);
        }
    }




}

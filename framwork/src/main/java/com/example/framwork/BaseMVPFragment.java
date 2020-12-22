package com.example.framwork;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.framwork.view.LoginPage;
import com.example.framwork.view.ToolBar;


public abstract class BaseMVPFragment<T extends  IPresenter,V extends  IView> extends Fragment implements ToolBar.IToolBarClickListner {
    protected T ihttpPresenter;

    protected LoginPage loginPage;

    protected ToolBar toolbar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        loginPage=new LoginPage(getContext()) {
            @Override
            protected int getsuccessId() {

                return getLayoutId();
            }
        };




        iniEven();

        return loginPage;




    }

    protected abstract void iniEven();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        iniView(loginPage);
//        toolbar=loginPage.findViewById(R.id.toolbar);
//        toolbar.setToolBarClickListner(this);
        iniData();
        iniPresenter();
//        ihttpPresenter.attatch((V) this);
        iniHttpData();
    }

    protected abstract void iniView(View loginPage);

    protected abstract int getLayoutId();

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

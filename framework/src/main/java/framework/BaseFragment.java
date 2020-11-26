package framework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import framework.mvpc.jsonPresenter;
import view.loadinPage.ErrorBean;
import view.loadinPage.LoadIngPagec;

public abstract
class BaseFragment<P extends jsonPresenter> extends Fragment implements Contact.CenterUserIview {
    protected  P Presenter;

    protected LoadIngPagec loadIngPagec;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        loadIngPagec = new LoadIngPagec(getContext()) {
            @Override
            protected int getSuccessLayoutId() {
                return getlayoutId();
            }
        };

        return loadIngPagec;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitData();
        OnClickListener();
    }

    public <T extends View> T findViewById(int id){
        return loadIngPagec.getSuccessView().findViewById(id);
    }




    protected abstract void createPresenter();


    protected abstract void OnClickListener();

    protected abstract void InitData();


    protected abstract int getlayoutId();


    public void  shwoLoading(){
        loadIngPagec.showLoadingpage();
    }

    public void hideLoadingPage(boolean isSuccess, ErrorBean errorBean) {
        if (isSuccess) {
            showSuccess();
        } else {
            showError(errorBean.getErrorMessage());
        }
    }
    public void showError(String errorMsg) {
        loadIngPagec.showErrorPage(errorMsg);
    }
    public void showSuccess() {
        loadIngPagec.showSuccessView();
    }

    public void showEmptyPage() {
        loadIngPagec.showEmptyPage();
    }
}

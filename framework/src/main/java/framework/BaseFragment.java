package framework;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import framework.mvpc.jsonPresenter;
import view.loadinPage.LoadIngPagec;

public abstract
class BaseFragment<P extends jsonPresenter> extends Fragment implements Contact.CenterUserIview {
    protected  P Presenter;
    protected LoadIngPagec LoadIngPage;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoadIngPage = new LoadIngPagec(getContext()) {
            @Override
            protected int getSuccessLayoutId() {
                return getlayoutId();
            }
        };
        return LoadIngPage;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitData();
        OnClickListener();
    }

    public <T extends View> T findViewById(@IdRes int id) {
        return LoadIngPage.getSuccessView().findViewById(id);
    }
    protected abstract void createPresenter();


    protected abstract void OnClickListener();

    protected abstract void InitData();


    protected abstract int getlayoutId();

    public void setToast(String type , String setMessage){
        Toast.makeText(getContext(), type+"- -  >"+setMessage,Toast.LENGTH_SHORT).show();
    }


}

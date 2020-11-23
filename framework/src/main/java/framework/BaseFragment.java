package framework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import framework.mvpc.jsonPresenter;

public abstract
class BaseFragment<P extends jsonPresenter> extends Fragment implements Contact.CenterUserIview {
    protected  P Presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getlayoutId(), null);
        InitData(inflate);
        OnClickListener();
        return inflate;
    }

    protected abstract void createPresenter();


    protected abstract void OnClickListener();

    protected abstract void InitData(View inflate);


    protected abstract int getlayoutId();


}

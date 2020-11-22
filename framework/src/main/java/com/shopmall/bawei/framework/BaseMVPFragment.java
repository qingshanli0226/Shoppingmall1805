package com.shopmall.bawei.framework;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

//定义抽象的MVPFragment类，使用这个类代表的是该Fragment是需要网络请求数据.
public abstract class BaseMVPFragment<T extends IPresenter, V extends IView> extends BaseFragment {

    protected T ihttpPresenter;
    protected ProgressBar loadingBar;//基类来定义加载的UI的形式
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingBar = findViewById(R.id.loadingBar);//在framwork里定义这个loadingbar控件，只是为了让编译器通过检查

        initPresenter();
        ihttpPresenter.attachView((V)this);
        initHttpData();
    }

    protected abstract void initHttpData();

    protected abstract void initPresenter();


    private List<String> dataList = new ArrayList<>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        dataList.add("1111");
        Log.d("LQS", getClass().getSimpleName() + " onAttach " + dataList.size());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("LQS", getClass().getSimpleName() + " onDetach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("LQS", getClass().getSimpleName() + " onCreate");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("LQS", getClass().getSimpleName() + " onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LQS", getClass().getSimpleName() + " onDestroy");
        ihttpPresenter.detachView();
    }
}

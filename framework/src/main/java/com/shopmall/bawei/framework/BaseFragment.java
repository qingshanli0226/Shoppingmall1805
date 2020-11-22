package com.shopmall.bawei.framework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shopmall.bawei.framework.view.ToolBar;


public abstract class BaseFragment extends Fragment implements ToolBar.IToolBarClickListner {
    private View rootView;
    private String TAG;

    protected ToolBar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
         TAG = "LQS:" + getClass().getSimpleName();

         rootView = inflater.inflate(getLayoutId(), container, false);
         toolbar = findViewById(R.id.toolbar);//在这里实例化toolbar
         toolbar.setToolBarClickListner(this);
         return rootView;
    }

    protected abstract int getLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    //注解。表示一个资源id，不能随便传递一个整型
    public <T extends View> T findViewById(@IdRes int id) {
        return rootView.findViewById(id);
    }

    protected void printLog(String message) {
        Log.d(TAG, message);
    }

    protected void showMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected void launchActivity(Class launcActivityClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(getActivity(), launcActivityClass);
        startActivity(intent);
    }

    @Override
    public void onLeftClick() {
        getActivity().finish();//左侧点击事件，大部分都是销毁当前的Activity，所以定义一个默认实现
    }

    @Override
    public void onRightClick() {//右侧不能确定点击之后，子类的行为，所以在基类中没必要定义一个默认实现，具体实现让子类完成.
    }
}

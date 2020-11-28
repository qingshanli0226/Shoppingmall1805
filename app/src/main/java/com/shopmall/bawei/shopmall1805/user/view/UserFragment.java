package com.shopmall.bawei.shopmall1805.user.view;

import android.view.View;
import android.widget.ImageButton;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.framework.BaseFragment;
import com.shopmall.bawei.framework.BasePresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.shopmall1805.R;

public class UserFragment extends BaseFragment<BasePresenter, IView> implements IView, View.OnClickListener {
    private ImageButton ibUserSetting;
    private ImageButton ibUserMessage;


    @Override
    protected void initView() {
        ibUserSetting = (ImageButton) findViewById(R.id.ib_user_setting);
        ibUserMessage = (ImageButton) findViewById(R.id.ib_user_message);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ib_user_setting:
                toLogin();
                break;
            case R.id.ib_user_message:

                break;
        }
    }

    private void toLogin() {
        ARouter.getInstance().build("/user/LoginRegisterActivity").navigation();
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initListener() {
        ibUserSetting.setOnClickListener(this);
        ibUserMessage.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onError(String msg) {

    }

    @Override
    public void showLoaDing() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}

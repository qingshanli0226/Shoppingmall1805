package com.bawei.shopmall.user;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.MyToolBar;
import com.bawei.framework.BaseFragment;
import com.bawei.shopmall.ARouterPath;
import com.shopmall.bawei.shopmall1805.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment implements View.OnClickListener {

    private MyToolBar myToolbar;
    private View inflate;
    private ImageView ib_user_icon_avator;
    private TextView tv_username;

    @Override
    protected int layoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {
        ib_user_icon_avator = (ImageView) findViewById(R.id.ib_user_icon_avator);
        tv_username = (TextView) findViewById(R.id.tv_username);

        ib_user_icon_avator.setOnClickListener(this);
    }

    @Override
    protected void initListener() {


    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_user_icon_avator:
                ARouter.getInstance().inject(this);
                ARouter.getInstance().build(ARouterPath.LoginRegisterActivity).navigation();
                break;
        }
    }
}

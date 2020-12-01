package com.shopmall.bawei.shopmall1805.ui.fragment_main;

import android.view.View;
import android.widget.TextView;

import com.shopmall.bawei.framework.base.BaseFragment;
import com.shopmall.bawei.framework.manager.ShopUserManager;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bean.Loginbean;

public class ShopCarFragment extends BaseFragment implements ShopUserManager.IUserListener {
    private TextView shopFragment;

    @Override
    protected void createViewid(View inflate) {
          ShopUserManager.getInstance().RegistUserLogin(this);


         shopFragment = inflate.findViewById(R.id.shop_fragment);

    }

    @Override
    protected void createEnvent() {

    }

    @Override
    protected void createData() {

    }

    @Override
    protected int fragmentid() {
        return R.layout.shopcar_fragment;
    }

    @Override
    protected void createPresenter() {

    }

    /**
     * 用户登陆成功后通知方法
     * @param loginbean
     */
    @Override
    public void OnUserLogin(Loginbean loginbean) {
            shopFragment.setText(loginbean.getResult().getName());
    }

    @Override
    public void OnUserLoginout() {

    }
}

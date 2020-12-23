package com.shopmall.bawei.shopmall1805.ui.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.framework.base.BaseFragment;
import com.shopmall.bawei.framework.manager.ShopUserManager;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bean.Loginbean;

public class IndividualFragment extends BaseFragment implements ShopUserManager.IUserListener {

    private ImageView logMy;
    private TextView usersMy;
    private TextView fahuoMy;
    private TextView individualPay;

    @Override
    protected void createViewid(View inflate) {
        ARouter.getInstance().inject(this);

        logMy = inflate.findViewById(R.id.log_my);

        fahuoMy = inflate.findViewById(R.id.fahuo_my);

        individualPay = inflate.findViewById(R.id.individual_pay);

        usersMy = inflate.findViewById(R.id.users_my);
        ShopUserManager.getInstance().RegistUserLogin(this);
    }

    @Override
    protected void createEnvent() {
        logMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/user/UserMainActivity").navigation();
            }
        });
        //发货地址
        fahuoMy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/user/InfoMainActivity").navigation();
            }
        });

        individualPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/order/OrderPayActivity").navigation();
            }
        });
    }

    @Override
    protected void createData() {

    }

    @Override
    protected int fragmentid() {
        return  R.layout.individual_fragment;
    }

    @Override
    protected void createPresenter() {

    }

    /**
     * 用户上线通知回调接口实现方法
     * @param loginbean
     */
    @Override
    public void OnUserLogin(Loginbean loginbean) {
        usersMy.setText(loginbean.getResult().getName());

    }

    @Override
    public void OnUserLoginout() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ShopUserManager.getInstance().UnRegistUserLogin(this);
    }
}

package com.bawei.shopmall.user;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.common.view.NetConfig;
import com.bawei.framework.BaseFragment;
import com.bawei.framework.BasePresenter;
import com.bawei.framework.IView;
import com.bawei.framework.ShopUserManager;
import com.bawei.framework.ShopUserManager.IUserLoginChangedListener;
import com.bawei.net.RetrofitCreate;
import com.bawei.net.mode.LoginBean;
import com.bawei.net.mode.LogoutBean;
import com.shopmall.bawei.shopmall1805.R;

import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends BaseFragment<BasePresenter, IView> implements IUserLoginChangedListener, View.OnClickListener {

    private TextView tvUsername;
    private ImageView ibUserSetting;
    private ImageView ibUserIconAvator;
    private String message;

    @Override
    protected int layoutId() {
        return R.layout.fragment_user;
    }

    @Override
    protected void initView() {
        ARouter.getInstance().inject(this);

        tvUsername = findViewById(R.id.tv_username);
        ibUserSetting = findViewById(R.id.ib_user_setting);

        if (ShopUserManager.getInstance().isUserLogin()) {
            tvUsername.setText(ShopUserManager.getInstance().getName());
        } else {
            ShopUserManager.getInstance().registerUserLoginChangeListener(this);
        }

        ibUserIconAvator = findViewById(R.id.ib_user_icon_avator);
        ibUserSetting = findViewById(R.id.ib_user_setting);

        ibUserIconAvator.setOnClickListener(this);
        ibUserSetting.setOnClickListener(this);

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onUserLogin(LoginBean loginBean) {
        tvUsername.setText(loginBean.getResult().getName());
    }

    @Override
    public void onUserLogout() {
        Toast.makeText(getContext(), "退出成功", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(NetConfig.tokenName, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(NetConfig.tokenName);
        edit.commit();
        tvUsername.setText("登录/注册");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ShopUserManager.getInstance().unRegisterUserLoginChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_user_icon_avator:
                if (!ShopUserManager.getInstance().isUserLogin()) {
                    ARouter.getInstance().build(NetConfig.LOGIN_ACTIVITY_PATH).withInt(NetConfig.TO_LOGIN_KEY, 4).navigation();
                }
                break;
            case R.id.ib_user_setting:

                HashMap<String, String> map = new HashMap<>();
                map.put(NetConfig.tokenName, ShopUserManager.getInstance().getToken());
                RetrofitCreate.getApi().logout(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<LogoutBean>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(LogoutBean logoutBean) {
                                if (logoutBean.getCode().equals("200")) {
                                    message = logoutBean.getMessage() + logoutBean.getResult();
                                    ShopUserManager.getInstance().logoutUser();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
                break;
        }
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick() {

    }
}

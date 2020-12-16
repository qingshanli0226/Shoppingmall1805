package com.shopmall.bawei.user;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.framework.BaseActivity;
import com.example.framework.CacheManager;
import com.example.framework.IPresenter;
import com.example.framework.IView;
import com.example.framework.MyViewPager;
import com.example.net.Confing;
import com.example.net.bean.ShopcarBean;
import com.shopmall.bawei.user.adpter.FragmentAdpter;
import com.shopmall.bawei.user.bean.LoginsBean;
import com.shopmall.bawei.user.view.LoginFragment;
import com.shopmall.bawei.user.view.RegisterFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

@Route(path="/user/LoginRegisterActivity")
public class LoginRegisterActivity extends BaseActivity<IPresenter,IView> {
    public MyViewPager vrLoginRegister;
    private LoginFragment loginFragment = new LoginFragment();
    private RegisterFragment registerFragment = new RegisterFragment();
    private List<Fragment> list = new ArrayList<>();
    private FragmentAdpter fragmentAdpter;
    private int toFiemIndex;
    @Override
    protected void initpreseter() {

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void oncurrenitem(LoginsBean loginBean) {
            vrLoginRegister.setCurrentItem(loginBean.getI());
            Log.e("EventBus",""+loginBean.getI());
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void autologin(String string){
        Toast.makeText(this, ""+string, Toast.LENGTH_SHORT).show();
        ARouter.getInstance().build("/main/MainActivity").navigation();
    }
    @Override
    protected void initdate() {
        List<ShopcarBean> selectedShopBeans = CacheManager.getInstance().getSelectedShopBeans();
        ARouter.getInstance().inject(this);
        fragmentAdpter = new FragmentAdpter(getSupportFragmentManager(),list);
        vrLoginRegister.setAdapter(fragmentAdpter);
        //接受传过来的position
        Intent intent = getIntent();
        toFiemIndex = intent.getIntExtra(Confing.TO_LOGIN_KEY,0);

    }

    @Override
    protected void initview() {
        EventBus.getDefault().register(this);
        //初始化控件
        vrLoginRegister = findViewById(R.id.vr_login_register);
        //添加fragment
        list.add(loginFragment);
        list.add(registerFragment);
    }

    @Override
    protected int getlayoutid() {
        return R.layout.activity_login_register;
    }
    public int getToLoginFilemIndex(){
        return toFiemIndex;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

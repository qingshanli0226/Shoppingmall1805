package com.shopmall.bawei.shopmall1805.home;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.fragment.FragmentClassify;
import com.shopmall.bawei.shopmall1805.home.fragment.FragmentHomePage;
import com.shopmall.bawei.shopmall1805.home.fragment.FragmentPersonAge;
import com.shopmall.bawei.shopmall1805.service.AutomactionLoginService;

import java.util.List;

import framework.BaseActivity;

import framework.CacheManager;
import framework.CacheManagerc;
import framework.ShopUserManager;
import framework.mvpc.JsonPresenter;
import mode.ShopcarBean;
import view.SkipFinalUlis;
import view.sview.FragmentShopcar;
import view.ShopmallConstant;
import view.loadinPage.ErrorBean;

@Route(path = SkipFinalUlis.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity<JsonPresenter> {
    private RadioGroup radioGroupMain;
    private RadioButton radioButtonShopcar;

    @Override
    protected void createPresenter() { }

    @Override
    protected void OnClickListener() {
        //ARouter注入
        ARouter.getInstance().inject(this);
        radioGroupMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonHome:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentViewLayout,new FragmentHomePage())
                        .commit();
                        break;
                    case R.id.radioButtonClassify:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentViewLayout,new FragmentClassify())
                        .commit();
                        break;
                    case R.id.radioButtonShopcar:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentViewLayout,new FragmentShopcar())
                        .commit();
                        if (!ShopUserManager.getInstance().isUserLogin()){
                            //如果用户没有登录
                            ARouter.getInstance().build(ShopmallConstant.LOGIN_ACTIVITY_PATH).withInt(ShopmallConstant.TO_LOGIN_KEY, ShopmallConstant.TO_LOGIN_FROM_SHOPCAR_FRAGMTNT).navigation();//跳转到loginActivity
                            finish();
                            return;
                        }
                        break;
                    case R.id.radioButtonPreson:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentViewLayout,new FragmentPersonAge())
                        .commit();
                        break;
                }
            }
        });
        if (!ShopUserManager.getInstance().isUserLogin()){
            Intent intent = new Intent(this, AutomactionLoginService.class);
            startService(intent);//开启服务service

        }
    }
    @Override
    protected void initData() {
        radioGroupMain = (RadioGroup) findViewById(R.id.radioGroupMain);
        radioButtonShopcar = (RadioButton) findViewById(R.id.radioButtonShopcar);

        FragmentTransaction add = getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentViewLayout, new FragmentHomePage())
                .add(R.id.fragmentViewLayout, new FragmentClassify())
                .add(R.id.fragmentViewLayout, new FragmentShopcar())
                .add(R.id.fragmentViewLayout, new FragmentPersonAge());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentViewLayout,new FragmentHomePage())
                .commit();
        switchFragmentByIndex(getIntent());
        CacheManagerc.getInstance().setiShopcarDataChangeListener(iShopcarDataChangeListener);

    }
    private CacheManagerc.IShopcarDataChangeListener  iShopcarDataChangeListener = new CacheManagerc.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            Log.i("pppp","加入购物车返回尺寸"+shopcarBeanList.size());
            int count = shopcarBeanList.size();
            radioButtonShopcar.setText("购物车"+count);
        }

        @Override
        public void onOneDataChanged(int position, ShopcarBean shopcarBean) {

        }

        @Override
        public void onMoneyChanged(String moneyVilue) {

        }

        @Override
        public void onAllSelected(boolean isAllSelect) {

        }
    };
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        switchFragmentByIndex(intent);
    }
    private void switchFragmentByIndex(Intent intent) {
        int index = intent.getIntExtra("index", 0);
        setLog(">>",""+index);
        if (index == ShopmallConstant.BUTTON_LOGIN_INDEX1){
        }else if (index == ShopmallConstant.BUTTON_LOGIN_INDEX2){
        }
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void showLoaDing() {

    }

    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

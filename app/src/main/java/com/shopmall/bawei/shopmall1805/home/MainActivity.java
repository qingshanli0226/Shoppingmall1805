package com.shopmall.bawei.shopmall1805.home;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.fragment.FragmentClassify;
import com.shopmall.bawei.shopmall1805.home.fragment.FragmentHomePage;
import com.shopmall.bawei.shopmall1805.home.fragment.FragmentPersonAge;

import java.util.List;

import framework.BaseActivity;
import framework.CacheManagerc;
import framework.ShopUserManager;
import mode.ShopcarBean;
import view.FragmentShopcar;
import view.ShopmallConstant;
import view.loadinPage.ErrorBean;

@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity {
    private RadioGroup radioGroupMain;
    private RadioButton radioButtonShopcar;

    @Override
    protected void createPresenter() {

    }

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
        //注册listener监听购物车公共数据是否发生改变,改变后，要去刷新购物车数量
        initShopcarDataChangeListener();

    }
    //此监听为当获取到数据时，刷新按钮购物车的数据
    private CacheManagerc.IShopcarDataChangeListener  iShopcarDataChangeListener = new CacheManagerc.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            int count = shopcarBeanList.size();
            radioButtonShopcar.setText("购物车:"+count);
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

    private void initShopcarDataChangeListener(){
        CacheManagerc.getInstance().setiShopcarDataChangeListener(iShopcarDataChangeListener);
    }
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
            setLog(">>","进入购物车"+index);
        }else if (index == ShopmallConstant.BUTTON_LOGIN_INDEX2){
            setLog(">>","进入首页"+index);
        }
    }

    @Override
    protected int getlayoutId() {
        return R.layout.activity_main ;
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
}

package com.shopmall.bawei.shopmall1805.home;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.shopmall.bawei.common.ARouterHelper;
import com.shopmall.bawei.common.ErrorBean;
import com.shopmall.bawei.common.FragmentHelper;
import com.shopmall.bawei.common.UrlHelper;
import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.framework.IPresenter;
import com.shopmall.bawei.framework.IView;
import com.shopmall.bawei.framework.UserManager;
import com.shopmall.bawei.net.mode.ShopCarBean;
import com.shopmall.bawei.shopcar.view.ShopCarFragment;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.home.view.HomeFragment;
import com.shopmall.bawei.shopmall1805.type.view.TypeTagFragment;
import com.shopmall.bawei.shopmall1805.user.view.UserFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = ARouterHelper.APP_MAIN)
public class MainActivity extends BaseActivity<IPresenter, IView> implements IView {

    private List<Fragment> fragments = new ArrayList();

    private TypeTagFragment typeTagFragment;

    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManager.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopCarBean> shopcarBeanList) {
            if(UserManager.getInstance().isUserLogin()) {
                if(CacheManager.getInstance().getShopCarBeanList().size() == 0){
                    rbCart.setText("购物车");
                } else {
                    rbCart.setText("购物车:" + String.valueOf(CacheManager.getInstance().getShopCarBeanList().size()));
                }
            }
        }

        @Override
        public void onOneDataChanged(int position, ShopCarBean shopcarBean) {

        }

        @Override
        public void onMoneyChanged(String moneyValue) {

        }

        @Override
        public void onAllSelected(boolean isAllSelect) {

        }
    };


    private int position;
    private RadioGroup rgMain;
    private RadioButton rbHome;
    private RadioButton rbType;
    private RadioButton rbCommunity;
    private RadioButton rbCart;
    private RadioButton rbUser;

    private FragmentManager manager;


    private Fragment mContext;


    private Fragment currentFragment;


    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = manager.beginTransaction();
                switch (checkedId){
                    case R.id.rb_home:
                        position = 0;
                        break;
                    case R.id.rb_type:
                        position = 1;
                        break;
                    case R.id.rb_community:
                        position = 2;
                        break;
                    case R.id.rb_cart:
                        position = 3;
                        break;
                    case R.id.rb_user:
                        position = 4;
                        break;

                }
                if(position == FragmentHelper.SHOP_INDEX && !UserManager.getInstance().isUserLogin()){
                    Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                    ARouter.getInstance().build(ARouterHelper.USER_LOGIN).withInt(UrlHelper.TO_LOGIN_KEY,UrlHelper.TO_LOGIN_FROM_SHOP_CAR).navigation();
                    return;
                }
                for (int i = 0; i < fragments.size() ; i++) {
                    if(position == i){
                        transaction.show(fragments.get(position));
                    }else{
                        transaction.hide(fragments.get(i));
                    }
                }
                transaction.commit();
            }
        });
        rgMain.check(R.id.rb_home);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        CacheManager.getInstance().setShopCarDataChangeListener(iShopcarDataChangeListener);
        setIntent(intent);
        switchFragment(intent);
    }

    private void switchFragment(Intent intent) {
        int index = intent.getIntExtra("index", 0);
        switch (index){
            case FragmentHelper.SHOP_INDEX:
                rgMain.check(R.id.rb_cart);
                break;
            case FragmentHelper.HOME_INDEX:
                rgMain.check(R.id.rb_home);
                break;
            case FragmentHelper.TYPE_INDEX:
                rgMain.check(R.id.rb_type);
                break;
            case FragmentHelper.MINE_INDEX:
                rgMain.check(R.id.rb_user);
                break;
            default:rgMain.check(R.id.rb_user);
        }
    }

    @Override
    protected void initView() {
        CacheManager.getInstance().setShopCarDataChangeListener(iShopcarDataChangeListener);
        ARouter.getInstance().inject(this);
        /**
         *
         *
         *
         *
         *
         *
         *
         *
         */
        fragments.add(new HomeFragment());
        rgMain = (RadioGroup) findViewById(R.id.rg_main);
        rbHome = (RadioButton) findViewById(R.id.rb_home);
        rbType = (RadioButton) findViewById(R.id.rb_type);
        rbCommunity = (RadioButton) findViewById(R.id.rb_community);
        rbCart = (RadioButton) findViewById(R.id.rb_cart);
        rbUser = (RadioButton) findViewById(R.id.rb_user);
        manager = getSupportFragmentManager();

        initPermission();

        typeTagFragment = new TypeTagFragment();
        fragments.add(typeTagFragment);
        fragments.add(new HomeFragment());
        fragments.add(new ShopCarFragment());
        fragments.add(new UserFragment());

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayoutId,fragments.get(0));
        fragmentTransaction.add(R.id.frameLayoutId,fragments.get(1));
        fragmentTransaction.add(R.id.frameLayoutId,fragments.get(2));
        fragmentTransaction.add(R.id.frameLayoutId,fragments.get(3));
        fragmentTransaction.add(R.id.frameLayoutId,fragments.get(4));
        fragmentTransaction.hide(fragments.get(1));
        fragmentTransaction.hide(fragments.get(2));
        fragmentTransaction.hide(fragments.get(3));
        fragmentTransaction.hide(fragments.get(4));
        fragmentTransaction.commit();
        if(UserManager.getInstance().isUserLogin()) {
            if(CacheManager.getInstance().getShopCarBeanList().size() == 0){
                rbCart.setText("购物车");
            } else {
                rbCart.setText("购物车:" + String.valueOf(CacheManager.getInstance().getShopCarBeanList().size()));
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CacheManager.getInstance().unSetShopCarDataChangeListener(iShopcarDataChangeListener);
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//判断当前系统版本是不是大于等于23
            Toast.makeText(this, getString(R.string.permissions), Toast.LENGTH_SHORT).show();
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        } else {
            Toast.makeText(this, getString(R.string.permissions2), Toast.LENGTH_SHORT).show();
        }//这个就是在代码里做了版本适配(兼容适配),确保了应用程序在15到29之间，动态权限申请不会出现找不到方法的错误

    }

    @Override
    protected void initData() {

    }


    @Override
    public void onError(String msg) {

    }
    @Override
    protected void initPresenter() {

    }

    @Override
    public void showLoaDing() {
        showLoading();
    }


    @Override
    public void hideLoading(boolean isSuccess, ErrorBean errorBean) {
        hideLoadingPage(isSuccess,errorBean);
    }

    @Override
    public void showEmpty() {
        showEmpty();
    }
}

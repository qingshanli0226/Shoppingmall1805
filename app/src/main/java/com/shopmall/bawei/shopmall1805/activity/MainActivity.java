package com.shopmall.bawei.shopmall1805.activity;

import android.content.Intent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bw.framework.BaseActivity;
import com.bw.framework.CacheManager;
import com.bw.framework.IPresenter;
import com.bw.framework.IView;
import com.bw.framework.ShopUserManager;
import com.bw.net.bean.ShopCarBean;
import com.bw.shopcar.ShopCarFragment;
import com.shopmall.bawei.shopmall1805.R;
import com.shopmall.bawei.shopmall1805.user.FindFragment;
import com.shopmall.bawei.shopmall1805.home.HomeFragment;
import com.shopmall.bawei.shopmall1805.type.TypeFragment;
import com.shopmall.bawei.shopmall1805.user.UserFragment;

import java.util.List;

@Route(path = "/activity/MainActivity")
public class MainActivity extends BaseActivity<IPresenter, IView> {

    private FrameLayout frameLayout;
    private RadioGroup group;
    private RadioButton rd1,rd2,rd3,rd4,rd5;
    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private TypeFragment typeFragment;
    private UserFragment userFragment;
    private ShopCarFragment cardFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();

        ARouter.getInstance().inject(this);

        frameLayout = findViewById(R.id.frame_layout);
        group = findViewById(R.id.group);
        rd1 = findViewById(R.id.rd1);
        rd2 = findViewById(R.id.rd2);
        rd3 = findViewById(R.id.rd3);
        rd4 = findViewById(R.id.rd4);
        rd5 = findViewById(R.id.rd5);

        homeFragment = new HomeFragment();
        typeFragment = new TypeFragment();
        findFragment = new FindFragment();
        cardFragment = new ShopCarFragment();
        userFragment = new UserFragment();

         getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, homeFragment)
                .add(R.id.frame_layout, typeFragment)
                .add(R.id.frame_layout, findFragment)
                 .add(R.id.frame_layout, cardFragment)
                .add(R.id.frame_layout, userFragment).commit();

         getSupportFragmentManager().beginTransaction().show(homeFragment)
                 .hide(typeFragment)
                 .hide(findFragment)
                 .hide(cardFragment)
                 .hide(userFragment).commit();

        group.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId){
                case R.id.rd1:
                    getSupportFragmentManager().beginTransaction().show(homeFragment)
                            .hide(typeFragment)
                            .hide(findFragment)
                            .hide(cardFragment)
                            .hide(userFragment).commit();
                    break;
                case R.id.rd2:
                    getSupportFragmentManager().beginTransaction().show(typeFragment)
                            .hide(homeFragment)
                            .hide(findFragment)
                            .hide(cardFragment)
                            .hide(userFragment).commit();
                    break;
                case R.id.rd3:
                    getSupportFragmentManager().beginTransaction().show(findFragment)
                            .hide(typeFragment)
                            .hide(homeFragment)
                            .hide(cardFragment)
                            .hide(userFragment).commit();
                    break;
                case R.id.rd4:
                    getSupportFragmentManager().beginTransaction().show(cardFragment)
                            .hide(typeFragment)
                            .hide(findFragment)
                            .hide(homeFragment)
                            .hide(userFragment).commit();
//                    ARouter.getInstance().build("/activity/activity_shopCart").navigation();
                    break;
                case R.id.rd5:
                    getSupportFragmentManager().beginTransaction().show(userFragment)
                            .hide(typeFragment)
                            .hide(homeFragment)
                            .hide(cardFragment)
                            .hide(findFragment).commit();
                    break;
            }
        });


        //页面显示时，刷新购物车数据
        updateShopcarCount();
        //注册listener监听购物车公共数据是否发生改变,改变后，要去刷新购物车数量
        initShopcarDataChangeListener();

    }

    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManager.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopCarBean> shopCarBeanList) {
            int count = shopCarBeanList.size();

        }

        @Override
        public void onOneDataChanged(int position, ShopCarBean shopCarBean) {

        }

        @Override
        public void onMoneyChanged(String moneyValue) {

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

        switch (index){
            case 0:
                getSupportFragmentManager().beginTransaction().show(homeFragment)
                        .hide(typeFragment)
                        .hide(findFragment)
                        .hide(cardFragment)
                        .hide(userFragment).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().show(typeFragment)
                        .hide(homeFragment)
                        .hide(findFragment)
                        .hide(cardFragment)
                        .hide(userFragment).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().show(findFragment)
                        .hide(typeFragment)
                        .hide(homeFragment)
                        .hide(cardFragment)
                        .hide(userFragment).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().show(cardFragment)
                        .hide(typeFragment)
                        .hide(findFragment)
                        .hide(homeFragment)
                        .hide(userFragment).commit();
                break;
            case 4:
                getSupportFragmentManager().beginTransaction().show(userFragment)
                        .hide(typeFragment)
                        .hide(homeFragment)
                        .hide(cardFragment)
                        .hide(findFragment).commit();
                break;
        }

    }

    private void initShopcarDataChangeListener() {
        CacheManager.getInstance().setShopCarDataChangerListener(iShopcarDataChangeListener);
    }

    private void updateShopcarCount() {
        //如果用户登录了，才可以刷新购物车数据，否则购物车数据并没有初始化，没必要刷新
        if (ShopUserManager.getInstance().isUserLogin()) {
            int count = CacheManager.getInstance().getShopCarBeans().size();
            if (count != 0) {
            }
        } else {
            Toast.makeText(this,"当前用户没有登录", Toast.LENGTH_SHORT).show();
        }
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
        CacheManager.getInstance().unSetShopCarDataChangerListener(iShopcarDataChangeListener);
    }
}

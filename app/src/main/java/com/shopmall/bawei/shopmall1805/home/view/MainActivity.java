package com.shopmall.bawei.shopmall1805.home.view;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei.shopmall.shopcar.view.ShopcarFragment;
import com.shopmall.bawei.common.ShopmallConstant;
import com.shopmall.bawei.framework.BaseActivity;
import com.shopmall.bawei.framework.CacheManager;
import com.shopmall.bawei.framework.ShopUserManager;
import com.shopmall.bawei.framework.view.BottomBar;
import com.shopmall.bawei.net.mode.ShopcarBean;
import com.shopmall.bawei.shopmall1805.R;
import java.util.List;


@Route(path = "/main/MainActivity")
public class MainActivity extends BaseActivity implements View.OnClickListener, BottomBar.IBottomBarSelectListener {

    private BottomBar bottomBar;
    private Fragment[] fragments = new Fragment[] {new HomeFragment(), new TypeFragment(), new ShopcarFragment(),new MineFragment()};
    private Fragment currentFragment; //当前正在显示的Fragment

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {
        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setBottomBarSelectListener(this);


        switchFragmentByIndex(getIntent());
        initPermission();
        showMessage("onCreate");
        //ARouter注入
        ARouter.getInstance().inject(this);

        //页面显示时，刷新购物车数据
        updateShopcarCount();
        //注册listener监听购物车公共数据是否发生改变,改变后，要去刷新购物车数量
        initShopcarDataChangeListener();

    }

    private CacheManager.IShopcarDataChangeListener iShopcarDataChangeListener = new CacheManager.IShopcarDataChangeListener() {
        @Override
        public void onDataChanged(List<ShopcarBean> shopcarBeanList) {
            int count = shopcarBeanList.size();
            bottomBar.setShopcarCount(String.valueOf(count));//数据发生改变后，去刷新UI
        }

        @Override
        public void onOneDataChanged(int position, ShopcarBean shopcarBean) {

        }

        @Override
        public void onMoneyChanged(String moneyValue) {

        }

        @Override
        public void onAllSelected(boolean isAllSelect) {

        }
    };

    private void initShopcarDataChangeListener() {
        CacheManager.getInstance().setShopcarDataChangeListener(iShopcarDataChangeListener);
    }

    private void updateShopcarCount() {
        //如果用户登录了，才可以刷新购物车数据，否则购物车数据并没有初始化，没必要刷新
        if (ShopUserManager.getInstance().isUserLogin()) {
            int count = CacheManager.getInstance().getShopcarBeanList().size();
            bottomBar.setShopcarCount(String.valueOf(count));
        }
    }

    //该方法被调用时机：1，该Activity的启动模式不是标准模式。2，在调用startActivity启动该Activity时，该Activity实例已经存在，并且复用存在的Activity实例时，该方法将被调用
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        showMessage("onNewIntent");
        setIntent(intent);//这个方法是将intent设置成默认创建的intent
        switchFragmentByIndex(intent);
    }

    private void switchFragmentByIndex(Intent intent) {
        int index = intent.getIntExtra("index", 0);
        if (index == BottomBar.SHOPCAR_INDEX) {
            bottomBar.selectShopcar();
        } else if (index == BottomBar.HOME_INDEX) {
            bottomBar.selectHome();//让bottomBar和Fragment对应显示
        } else if (index == BottomBar.TYPE_INDEX) {
            bottomBar.selectType();
        } else {
            bottomBar.selectMine();
        }
        switchFragment(index);//切换到参数指定的Fragment页面

    }

    private void initPermission() {
        //该Api（方法）在23版本之前系统是没有的。例如15版本的系统就没有该API
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//判断当前系统版本是不是大于等于23
            Toast.makeText(this, "系统版本大于23，需动态申请权限", Toast.LENGTH_SHORT).show();
            requestPermissions(new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        } else {
            Toast.makeText(this, "系统版本低于23，所以无需动态申请权限", Toast.LENGTH_SHORT).show();
        }//这个就是在代码里做了版本适配(兼容适配),确保了应用程序在15到29之间，动态权限申请不会出现找不到方法的错误
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
                default:break;
        }
    }

    @Override
    public void onBottomBarSelected(int selectIndex) {
        if (selectIndex == BottomBar.SHOPCAR_INDEX && !ShopUserManager.getInstance().isUserLogin()) {//如果当前用户没有登录，则跳转到登录界面
            showMessage("请先登录");
            //通过key来表明，从哪个地方进入到登录页面的，方便我们登录成功时，根据这个key做具体的跳转
            ARouter.getInstance().build(ShopmallConstant.LOGIN_ACTIVITY_PATH).withInt(ShopmallConstant.TO_LOGIN_KEY, ShopmallConstant.TO_LOGIN_FROM_SHOPCAR_FRAGMTNT).navigation();//跳转到loginActivity
            //finish();
            return;
        }
        switchFragment(selectIndex);//MainActivity监听BottomBar的点击事件，根据点击Button的位置去切换到对应的Fragment
    }

    //实现Fragment的切换
    private void switchFragment(int selectIndex) {
        Fragment fragment = fragments[selectIndex];//首先找到要切换的Fragment
        if (currentFragment == fragment) {//判断当前显示的Fragment是不是我们要切换的Fragment
             return;
        }

        //使用show+hide方式完成Fragment的切换
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (currentFragment!=null) {//currentFragment为空的情况是MainActivity刚实例化时
            fragmentTransaction.hide(currentFragment);
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment).commit();
        } else {
            fragmentTransaction.add( R.id.frameLayoutId, fragment,fragment.getClass().getSimpleName()).commit();
        }
        currentFragment = fragment;
    }


    public void switchFragment() {
    }

    @Override
    protected void destroy() {
        super.destroy();
        CacheManager.getInstance().unSetShopcarDataChangerListener(iShopcarDataChangeListener);
    }
}
